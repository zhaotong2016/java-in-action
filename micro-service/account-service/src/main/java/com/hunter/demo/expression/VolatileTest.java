package com.hunter.demo.expression;

import java.util.HashMap;

/**
 * @author Hunter
 * @date 2022/01/05 17:00
 **/
public class VolatileTest {

    private static  boolean initFlag = false;

    public static void main(String[] args) {

        hashmapTest();
    }


    public static void hashmapTest(){
        HashMap<Integer,String> map = new HashMap<>();
        map.put(1,"hello");

        System.out.println(map);
    }

    public static void threadTest(){
        new Thread(() ->{
            System.out.println("stating");
            while (!initFlag){
                //System.out.println("执行了~~~~~~~");
            }
            System.out.println("success");
        }).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            getInitData();
        }).start();
    }
    public static void getInitData(){
        System.out.println("VolatileTest.getInitData");
        initFlag = true;

        System.out.println("VolatileTest.getInitData end");
    }
}
