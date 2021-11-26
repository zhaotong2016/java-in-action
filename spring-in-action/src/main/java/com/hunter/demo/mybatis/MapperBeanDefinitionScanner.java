package com.hunter.demo.mybatis;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

/**
 * @author Hunter
 * @date 2021/11/06 12:00
 **/
public class MapperBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

    public MapperBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, useDefaultFilters);
    }

    @Override
    protected void registerDefaultFilters() {
       addIncludeFilter(new AnnotationTypeFilter(MyMapperScan.class));
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        return super.doScan(basePackages);
    }
}
