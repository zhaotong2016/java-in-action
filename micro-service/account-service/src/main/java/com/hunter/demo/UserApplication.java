package com.hunter.demo;


import com.hunter.demo.autoconfigure.Hunter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableDiscoveryClient
@SpringBootApplication
public class UserApplication implements CommandLineRunner {


    @Autowired
    private Hunter hunter;


    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        hunter.getHunterService();
    }
}
