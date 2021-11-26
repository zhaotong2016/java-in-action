package com.hunter.demo.mybatis;

import lombok.Data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Hunter
 * @date 2021/11/06 12:11
 **/
@Data
@MyMapperScan
public class User {

    private String userName;


    public static void main(String[] args) {


        //ExecutorService

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        final Lock lock = new ReentrantLock();

        Condition condition = lock.newCondition();
        ExecutorService finalExecutorService = executorService;

        for (int i = 0; i < 100; i++) {
            executorService.submit(()->{
                try {

                    lock.lock();
                    System.out.println("获取锁1");
                    System.out.println("executorService1:" + finalExecutorService.toString());
                    Thread.sleep(10);
                    condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            });
        }


        executorService.submit(()->{
            try {

                lock.lock();
                System.out.println("获取锁2");

                System.out.println("executorService2:" + finalExecutorService.toString());
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });





        executorService =  Executors.newCachedThreadPool();
        executorService = Executors.newSingleThreadExecutor();

        executorService = Executors.newScheduledThreadPool(10);
        executorService = Executors.newWorkStealingPool();
    }

}
