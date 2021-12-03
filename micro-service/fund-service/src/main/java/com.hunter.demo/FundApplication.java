package com.hunter.demo;


import com.demo.hunter.MethodLogAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDiscoveryClient
@SpringBootApplication
public class FundApplication {

    public static void main(String[] args) {
        SpringApplication.run(FundApplication.class, args);


    }

    @Bean
    public MethodLogAspect logAspect(){
        return new MethodLogAspect();
    }

}
