package com.hunter.demo.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Service
public class ProductService {



    //spring 自带线程池
    @Autowired
    ThreadPoolTaskExecutor applicationTaskExecutor;

    @Data
    class A{
        private int a;
        private int b;
    }



    public String query(){
       log.info("query");


        A aClass = new A();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(()->{

            int a = 0;
            for (int i = 0; i < 100000 ; i++) {
                a++;
            }
            System.out.println("=================A:" + a);
            aClass.setA(a);
        },applicationTaskExecutor);

        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(()->{
            int b = 0;
            for (int i = 0; i < 100000 ; i++) {
                b++;
            }
            System.out.println("=================B:" + b);
            aClass.setB(b);
        },applicationTaskExecutor);

        try {
            CompletableFuture.allOf(completableFuture1,completableFuture2).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.out.println("==================" + stopWatch);
        return String.valueOf(aClass.getA()+aClass.getB());
    }

    public String query1(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int a = 0;
        for (int i = 0; i < 100000 ; i++) {
            a++;
        }
        System.out.println("=================A:" + a);
        int b = 0;
        for (int i = 0; i < 100000 ; i++) {
            b++;
        }
        System.out.println("=================B:" + b);

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());

        return String.valueOf(a+b);
    }
    public String query2(){
        log.info("query");

        A aClass = new A();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(()->{

            int a = 0;
            for (int i = 0; i < 100000 ; i++) {
                a++;
            }
            System.out.println("=================A:" + a);
            return  a;
        },applicationTaskExecutor);

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(()->{
            int b = 0;
            for (int i = 0; i < 100000 ; i++) {
                b++;
            }
            System.out.println("=================B:" + b);
            return b;
        },applicationTaskExecutor);

        //任务3：任务1和任务2完成后执行：1+2
        CompletableFuture<Integer> f3 = f1.thenCombine(f2, (__, tf)->{
            System.out.println("=================f2:" + tf);
            return __+tf;
        });
        f3.join();

        Integer c = 0;
        try {
            c = f3.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.out.println( stopWatch.prettyPrint());
        return String.valueOf(c);
    }


    ExecutorService executorService = Executors.newFixedThreadPool(10);

    final ReentrantLock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final BlockingQueue<Integer> queue = new LinkedBlockingDeque<>(5);

    public void product(Integer count){
        for (Integer i = 0; i < count; i++) {
            Integer finalI = i;
            executorService.submit(()->{
                lock.tryLock();
                while (queue.size() == 5){
                    System.out.println("队列满了..");
                    try {
                        notFull.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        lock.unlock();
                    }
                }
               queue.add(finalI);notEmpty.signalAll();
               try {
                   Thread.sleep(10);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
                System.out.println("product:" +executorService.toString() +"===队列剩余的空间:" + (5-queue.size()));
           });
        }


    }


    public void cosumer(){
        executorService.submit(()->{
            try {
                lock.tryLock();
                while (queue.isEmpty()){
                    System.out.println("队列空了..");
                    try {
                        notEmpty.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.poll();
                notFull.signalAll();
                System.out.println("cosumer:" +executorService.toString() +"===队列剩余的空间:" + (5-queue.size()));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });


    }
}
