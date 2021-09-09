package com.hunter.demo.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class UserService implements InitializingBean {

    //UserService类-> 构造方法（实例化)推断构造方法 ->对象 -> 依赖注入 ->初始化-前@PostConstruct -> 初始化(InitializingBean)->
    //初始化后(AOP)-> 代理对象-> bean

    // 先根据类型查找- 再根据name查找
    //@Autowired
    private User user;


    //@PostConstruct



    //初始化
    @Override
    public void afterPropertiesSet() throws Exception {
        test();
    }


    public void test(){
        System.out.println("user service。。。。");
    }
}
