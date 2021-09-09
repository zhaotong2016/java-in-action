package com.hunter.demo.ext.core;

import org.apache.ibatis.annotations.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 实现jdk动态代理
 */
public class MyMapperProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("========================" +method.getAnnotation(Select.class));
        System.out.println("========================" +method.getAnnotation(Select.class).value()[0]);
        return null;
    }
}
