package com.hunter.demo.mapper;

import com.hunter.demo.bean.User;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void test() throws Exception {
        System.out.println("jdbc service。。。"+ jdbcTemplate.toString());
        jdbcTemplate.execute("INSERT INTO `user` (`name`, `age`) VALUES ('Hunter', '30')");

        UserService userService = (UserService) AopContext.currentProxy();
       /* userService.test1();
        int i = 1/0;*/


        userService.test2();
    }

    // @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED) // 使用当前事务
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW) // 挂起事务，新建事务
    @Override
    public void test1() throws Exception {
        jdbcTemplate.execute("INSERT INTO `user` (`name`, `age`) VALUES ('Hunter2', '31')");
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.NOT_SUPPORTED) // 不适用事务
    @Override
    public void test2() {
        jdbcTemplate.execute("INSERT INTO `user` (`name`, `age`) VALUES ('Hunter3', '32')");
        int i = 1/0;
    }

    @Override
    public User query(int id) {
        String query = "select * from user where id = ?";
        User user = jdbcTemplate.queryForObject(query,new  Object [] {id},User.class);
        return user;
    }
}
