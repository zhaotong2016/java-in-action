package com.hunter.demo.ext;

import com.hunter.demo.ext.core.MyMapperProxy;
import com.hunter.demo.mapper.UserMapper;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

//@Component
public class MyFactoryBean2 implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return (UserMapper) Proxy.newProxyInstance( Thread.currentThread().getContextClassLoader(),
                new Class[]{UserMapper.class},new MyMapperProxy());
    }

    @Override
    public Class<?> getObjectType() {
        return UserMapper.class;
    }
}
