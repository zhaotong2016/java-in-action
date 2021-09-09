package com.hunter.demo.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


public class ThreadTest {

    public static void main(String[] args) {
       // test();
        test1();

    }


    public static void test() {
        long startTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + " start time " + startTime);
        new Thread(() -> testA()).start();
        new Thread(() -> testB()).start();
        long endTime = System.currentTimeMillis();

        System.out.println(Thread.currentThread().getName() + " end time " + (endTime - startTime));
    }

    public static void testA() {
        System.out.println(Thread.currentThread().getName() + " test A");
    }

    public static void testB() {
        System.out.println(Thread.currentThread().getName() + " test B");
    }


    public static void test1() {
        //任务1：洗水壶->烧开水
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> { //无返回值，使用默认线程池
            try {
                System.out.println("T1:洗水壶...");
                Thread.sleep(1);
                System.out.println("T1:烧开水...");
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //任务2：洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> { //有返回值，使用默认线程池
            try {
                System.out.println("T2:洗茶壶...");
                Thread.sleep(1);
                System.out.println("T2:洗茶杯...");
                Thread.sleep(2);
                System.out.println("T2:拿茶叶...");
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "龙井茶";
        });

        //任务3：任务1和任务2完成后执行：泡茶
        CompletableFuture<String> f3 = f1.thenCombine(f2, (__, tf) -> {
            System.out.println("T1:拿到茶叶:" + tf);
            System.out.println("T1:泡茶...");
            return "上茶:" + tf;
        });
        //等待任务3执行结果
        System.out.println(f3.join());


    }

    void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        } catch (InterruptedException e) {
        }
    }
}
