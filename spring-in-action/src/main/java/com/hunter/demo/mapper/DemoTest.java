package com.hunter.demo.mapper;

import com.hunter.demo.bean.User;
import com.hunter.demo.config.AppConfig;
import com.hunter.demo.mapper.UserMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DemoTest {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext configApplicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);



       // UserService jdbcService = configApplicationContext.getBean(UserService.class);
       // jdbcService.test();

       // jdbcService.query(33);

        UserMapper userMapper = configApplicationContext.getBean(UserMapper.class);

        User user = userMapper.getUser(56);
        System.out.println(user);
    }
}
