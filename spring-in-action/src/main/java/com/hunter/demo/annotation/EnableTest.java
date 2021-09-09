package com.hunter.demo.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EnableTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        context.register(EnableTest.class);


    }
}
