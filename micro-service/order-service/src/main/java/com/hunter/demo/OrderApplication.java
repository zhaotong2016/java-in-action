package com.hunter.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

/**
 * @author Hunter
 * @date 2021/12/12 00:14
 **/
//@SpringBootApplication
public class OrderApplication  {

    public static void main(String[] args) {
      //  SpringApplication.run(OrderApplication.class);


        Integer sum = null;

        String isin = "hkxx";

        if (null == sum || StringUtils.isEmpty(isin)){
            System.out.println(isin+"1");
        }else{
            System.out.println(isin+"2");
        }


        sum = 1;

        if (null == sum || StringUtils.isEmpty(isin)){
            System.out.println(isin+"3");
        }else{
            System.out.println(isin+"4");
        }
    }


}
