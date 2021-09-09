package com.hunter.demo.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectConfig {

    @Pointcut("execution(* com.hunter.demo.product.controller.*.*(..))")
    public void webLog() {}


    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("执行了===================================");
    }
}
