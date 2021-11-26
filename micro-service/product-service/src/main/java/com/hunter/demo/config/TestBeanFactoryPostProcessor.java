package com.hunter.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * @author Hunter
 * @date 2021/10/08 18:03
 **/
@Component
public class TestBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        Iterator<String> iterator =  beanFactory.getBeanNamesIterator();

        int i = 0;
        while (iterator.hasNext()){
            i++;
            System.out.println("BeanFactoryPostProcessor======================== 第：" +i+ "==>" +iterator.next());
        }

        BeanDefinition bd  = beanFactory.getBeanDefinition("aopCommon");
        System.out.println("属性值：" + bd.getPropertyValues().toString());

        MutablePropertyValues pv = bd.getPropertyValues();

        if (pv.contains("userName")){
            pv.add("userName","Hunter666");
        }

        //bd.setScope(BeanDefinition.SCOPE_PROTOTYPE);

    }
}
