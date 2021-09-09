package com.hunter.demo.ext.core;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * 实现FactoryBean
 * 例如 {@link org.mybatis.spring.mapper.MapperFactoryBean}
 */
public class MyMapperFactoryBean implements FactoryBean {

    Class mapperInterface;

    public MyMapperFactoryBean(Class mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object getObject() throws Exception {
        return  Proxy.newProxyInstance( Thread.currentThread().getContextClassLoader(),
                new Class[]{mapperInterface},new MyMapperProxy());
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }
}
