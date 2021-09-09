package com.hunter.demo.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
       BeanDefinition beanDefinition =
               configurableListableBeanFactory.getBeanDefinition("account");
       beanDefinition.setLazyInit(true);

        MutablePropertyValues mp = beanDefinition.getPropertyValues();

        if (mp.contains("name")){
            mp.addPropertyValue("name","Hello BeanFactoryPostProcessor");
        }

    }


}
