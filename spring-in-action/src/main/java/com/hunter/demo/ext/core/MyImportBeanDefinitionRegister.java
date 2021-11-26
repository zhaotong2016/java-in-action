package com.hunter.demo.ext.core;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 1、实现 ImportBeanDefinitionRegistrar接口
 * 2、在配置类中引入 @Import
 *
 */
public class MyImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

    public static final String userMapperScanValue = "com.hunter.demo.mapper.UserMapper";
    public static final String accountMapperScanValue = "com.hunter.demo.mapper.AccountMapper";

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {

        //todo
        //包扫描多个 优化

        //beandefintion底层注册原理
        BeanDefinition beanDefinition = new RootBeanDefinition(MyMapperFactoryBean.class);


        //构造填充
        beanDefinition.getConstructorArgumentValues()
                .addGenericArgumentValue(userMapperScanValue);
        registry.registerBeanDefinition("userMapper",beanDefinition);


        //属性填充...
        //beanDefinition.getPropertyValues().addPropertyValue("xx","value");

        BeanDefinition beanDefinition2 = new RootBeanDefinition(MyMapperFactoryBean.class);
        beanDefinition2.getConstructorArgumentValues()
                .addGenericArgumentValue(accountMapperScanValue);
        //bean注册
        registry.registerBeanDefinition("accountMapper",beanDefinition2);


    }
}
