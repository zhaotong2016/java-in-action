package com.hunter.demo.mybatis;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * ImportBeanDefinitionRegistrar接口是Spring的扩展点之一，
 * Spring容器启动时会回调所有实现了ImportBeanDefinitionRegistrar接口的实现类
 * 中的registerBeanDefinitions方法，完成自定义BeanDefinition注册。
 *
 * @author Hunter
 * @date 2021/11/06 11:39
 **/
public class MyMapperScannerRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    public static final String basePackages ="com.hunter.demo.mybatis";
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        MapperBeanDefinitionScanner scanner = new MapperBeanDefinitionScanner(registry,false);
        scanner.setResourceLoader(resourceLoader);
        scanner.registerDefaultFilters();
        scanner.doScan(basePackages);
    }
}
