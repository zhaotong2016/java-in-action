package com.hunter.demo.ext;

import com.hunter.demo.bean.User;
import com.hunter.demo.config.AppConfig;
import com.hunter.demo.ext.core.MyMapperFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class DemoTest {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);


//        DefaultListableBeanFactory defaultListableBeanFactory = context.getDefaultListableBeanFactory();

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


        extensionPoint(context);


    }

    /**
     *
     */
    public static void extensionPoint( ApplicationContext context){
        //扩展点
        // 1、@Import ImportBeanDefinitionRegistrar  @MapperScan spring实现
        // 2、BeanDefinitionRegistryPostProcessor    @Mapper springboot实现方式

        // 动态代理
      /* UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{UserMapper.class},new MyMapperProxy());
       User user = userMapper.getUser(11);


        System.out.println(user.toString());*/
        //代理对象交给spring容器管理



        MyMapperFactoryBean myFactoryBean3 = context.getBean(MyMapperFactoryBean.class);
        System.out.println("MyFactorybean ===========" + myFactoryBean3);




 /*       UserMapper userMapper = context.getBean(UserMapper.class);
        userMapper.getUser(11);

        AccountMapper accountMapper = context.getBean(AccountMapper.class);
        accountMapper.getUser(11);*/
    }

    /**
     *  注入bean方式
     *  1、@Configuration  通过import(User.class)注入
     *  2、@Component  @ComponentScan("com.hunter.demo.ext")
     *  3、@Bean
     * @param context
     */
    public static void diBean(ApplicationContext context){
        User user = context.getBean(User.class);
        System.out.printf("注入Bean的方式：" + user.toString());
    }
}
