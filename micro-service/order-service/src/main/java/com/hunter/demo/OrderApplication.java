package com.hunter.demo;

import com.hunter.demo.service.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Hunter
 * @date 2021/12/12 00:14
 **/
@SpringBootApplication
public class OrderApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class);
    }

    @Autowired
    private Test test;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        test.testInterface();
    }
}
