package com.hunter.demo.config;


import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class AopCommon {


    @Pointcut(value = "execution(* com.hunter.demo.service.*(..))")
    public void aspectPointcut(){
    }

    @Before(value = "aspectPointcut()")
    public void beforeAdvice(){
        System.out.println("before");

    }

    @After(value = "aspectPointcut()")
    public void afterAdvice(){
        System.out.println("after");
    }

    @AfterReturning(value = "aspectPointcut()")
    public void returnAdvice(){
        System.out.println("return");
    }

    @AfterThrowing(value = "aspectPointcut()")
    public void throwingAdvice(){

    }

}
