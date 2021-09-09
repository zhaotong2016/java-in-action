package com.hunter.demo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class MyLock {

    // 测试转账的main方法
    public static void main(String[] args) throws InterruptedException {
        Account src = new Account(10000);
        Account target = new Account(10000);
        CountDownLatch countDownLatch = new CountDownLatch(9999);
        for (int i = 0; i < 9999; i++) {
            new Thread(() -> {
                src.transfer(1, target);
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("src=" + src.getBalance());
        System.out.println("target=" + target.getBalance());
    }


    static class Account { //账户类

        private Integer balance;


        public Integer getBalance() {
            return balance;
        }

        public void setBalance(Integer balance) {
            this.balance = balance;
        }

        public Account(Integer balance) {
            this.balance = balance;
        }


        /**
         * 当账户 Account 在执行转账操作的时候，首先向 Allocator 同时申请转出账户和转入账户这两个资源，成功后再锁定这两个资源；
         * 当转账操作执行完，释放锁之后，我们需通知 Allocator 同时释放转出账户和转入账户这两个资源
         */
        public void transfer(Integer money, Account target) {//转账方法
            //锁定账户
            Allocator.getInstance().apply(this, target);
            this.balance -= money;
            target.setBalance(target.getBalance() + money);
            //释放账户
            Allocator.getInstance().free(this, target);
        }


    }

    /**
     * 单例锁类
     */
    static class Allocator {
        private Allocator() {
        }

        private List<Account> locks = new ArrayList<>();

        /**
         * 申请资源
         * @param src
         * @param tag
         */
        public synchronized void apply(Account src, Account tag) {
            while (locks.contains(src) || locks.contains(tag)) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                }
            }
            locks.add(src);
            locks.add(tag);
        }

        /**
         * 释放资源
         * @param src
         * @param tag
         */
        public synchronized void free(Account src, Account tag) {
            locks.remove(src);
            locks.remove(tag);
            this.notifyAll();
        }

        public static Allocator getInstance() {
            return AllocatorSingle.install;
        }

        static class AllocatorSingle {
            public static Allocator install = new Allocator();
        }
    }
}
