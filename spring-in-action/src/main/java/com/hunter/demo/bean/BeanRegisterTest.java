package com.hunter.demo.bean;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanRegisterTest {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext  = new AnnotationConfigApplicationContext();

        //方式一，注册bean
//         AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
//        beanDefinition.setBeanClass(User.class);
//        applicationContext.registerBeanDefinition("user",beanDefinition);
//        applicationContext.refresh();

        //方式二，注册bean
//        applicationContext.register(User.class);
//        applicationContext.refresh();

//        User user = applicationContext.getBean("user",User.class);
//
//        user.test();
//        System.out.println(user);
//

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerSingleton("user",new User());


        User user = beanFactory.getBean("user",User.class);
        user.test();






    }
}
