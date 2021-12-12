package com.hunter.demo.test;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication
@ComponentScan(basePackages = "com.hunter.demo.test")
public class Index {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new
                AnnotationConfigApplicationContext(Index.class);

        A a = context.getBean(A.class);

        B b = context.getBean(B.class);

        System.out.println(a);
    }


}
