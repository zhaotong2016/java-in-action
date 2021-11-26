package com.hunter.demo.config;


import lombok.Data;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Data
@Component
public class AopCommon implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        this.userName = "Hunter168";
    }

    private String userName = "defaultHunter";

    public AopCommon() {
        this.userName = "初始化为Hunter";
    }

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
