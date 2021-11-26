package com.hunter.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Hunter
 * @date 2021/10/08 18:04
 **/
@Component
public class TestBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("aopCommon")){
            System.out.println("postProcessAfterInitialization前" + bean.toString());
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (beanName.equals("aopCommon")){
            System.out.println("postProcessAfterInitialization后" + bean.toString());
        }

        return bean;
    }
}
