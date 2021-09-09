package com.hunter.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;


@Slf4j
public class CountTimesPlugin implements MethodBeforeAdvice {

    private int count;

    protected void count(Method m){
        count++;
    }
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        count( method);
        log.info("The method %s invoked times %s",method.getName() + ":" +count);
    }
}
