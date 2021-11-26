package com.hunter.demo.mybatis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Hunter
 * @date 2021/11/06 12:09
 **/
public class MyMapperScannerRegistrarTest {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(MyMapperConfig.class);

        User user = context.getBean(User.class);

        System.out.printf("ImportBeanDefinitionRegistrar:" + user);
    }
}
