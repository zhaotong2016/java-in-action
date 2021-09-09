package com.hunter.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

//@SpringBootApplication
public class Index {




    public static void main(String[] args) {
        //SpringApplication.run(Index.class);

    }


    final ReentrantLock lock = new ReentrantLock();

    public Map test() {

        Map map = new HashMap();

        int count = 0;
        int maxRetry = 5;
        boolean result = false;
        do{
            count++;

            if (count == 3){
                result = true;
            }
            System.out.println("当前i值："+count);

        }while (result && count<maxRetry);
        return map;

    }


}
