package com.hunter.demo.bean;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

//@Aspect
@Component
public class AppAspect {

    //@Before("execution(public void com.hunter.com.hunter.demo.bean.UserService.test())")
    public void before(JoinPoint joinPoint){

        System.out.println("test aspect...."+ joinPoint.getTarget());
    }
}
