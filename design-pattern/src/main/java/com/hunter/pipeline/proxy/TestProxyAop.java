package com.hunter.pipeline.proxy;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class TestProxyAop {

    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory(new House());

        factory.addInterface(Construction.class);

        factory.addAdvice(new BeforeConstructAdvice());

        factory.setExposeProxy(true);


        Construction construction = (Construction) factory;

        construction.construct();
    }
}

interface Construction{
    void construct();

    void givePermission();

    boolean isPermitted();
}

class House implements Construction{

    private boolean permitted = false;

    @Override
    public void construct() {
        System.out.println("I'm constructing a house");
    }

    @Override
    public void givePermission() {
        System.out.println("Permission is given to construct a simple house");
        this.permitted = true;
    }

    @Override
    public boolean isPermitted() {
        return this.permitted;
    }
}

class BeforeConstructAdvice implements MethodBeforeAdvice{

    @Override
    public void before(Method method, Object[] arguments, Object target) throws Throwable {
        if (method.getName().equals("construct")){
            ((Construction)target).givePermission();
        }
    }
}