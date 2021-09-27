package com.hunter.demo;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

//@SpringBootApplication
public class Index {


    /**
     * accountId: 241
     * nonce: "e9bb0d1c000194013af6ac8bdff15551"
     * rootOrgId: 1
     * signature: "4de8d3f52ae5df8cc5808435ab5e2b835524edb1"
     * timestamp: "1631929005991"
     * userId: 241
     *
     *
     * @param args
     */


    public static void main(String[] args) {
        //SpringApplication.run(Index.class);

        System.out.println();
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
