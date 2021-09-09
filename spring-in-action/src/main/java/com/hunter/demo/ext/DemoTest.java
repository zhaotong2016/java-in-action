package com.hunter.demo.ext;

import com.hunter.demo.config.AppConfig;
import com.hunter.demo.transaction.mapper.AccountMapper;
import com.hunter.demo.transaction.mapper.UserMapper;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DemoTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);


        DefaultListableBeanFactory defaultListableBeanFactory = context.getDefaultListableBeanFactory();

        //注册
//         AbstractBeanDefinition abstractBeanDefinition =
//                BeanDefinitionBuilder.rootBeanDefinition(FoxService.class).getBeanDefinition();
//
//        //属性填充
//        abstractBeanDefinition.getPropertyValues().addPropertyValue("name","hunter");
//        defaultListableBeanFactory.registerBeanDefinition("foxService",abstractBeanDefinition);
//
//
//
//        FoxService foxService = context.getBean(FoxService.class);
//        System.out.println("foxService ===========" + foxService);


        // BeanDefinitionRegistry bean注册器

        //扩展点
        // 1、@Import ImportBeanDefinitionRegistrar  @MapperScan spring实现
        // 2、BeanDefinitionRegistryPostProcessor    @Mapper springboot实现方式

        // 动态代理
//       UserMapper userMapper = (UserMapper) Proxy.newProxyInstance( Thread.currentThread().getContextClassLoader(),
//                new Class[]{UserMapper.class},new MyMapperProxy());
//        userMapper.getUser(11);


        //代理对象交给spring容器管理



//        MyMapperFactoryBean myFactoryBean3 = context.getBean(MyMapperFactoryBean.class);
//        System.out.println("MyFactorybean ===========" + myFactoryBean3);


        UserMapper userMapper = context.getBean(UserMapper.class);
        userMapper.getUser(11);

        AccountMapper accountMapper = context.getBean(AccountMapper.class);
        accountMapper.getUser(11);


    }


}
