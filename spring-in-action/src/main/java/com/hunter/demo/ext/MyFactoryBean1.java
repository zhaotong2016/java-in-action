package com.hunter.demo.ext;

import org.springframework.beans.factory.FactoryBean;

//@Component
public class MyFactoryBean1 implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new FoxService();
    }

    @Override
    public Class<?> getObjectType() {
        return FoxService.class;
    }
}
