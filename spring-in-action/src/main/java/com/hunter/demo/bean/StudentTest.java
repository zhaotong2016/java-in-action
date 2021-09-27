package com.hunter.demo.bean;


import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
//import org.springframework.boot.context.properties.ConfigurationBeanFactoryMetadata;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.util.Iterator;

public class StudentTest implements BeanFactoryPostProcessor, InitializingBean {

    private String name;

    //private ConfigurationBeanFactoryMetadata beanFactoryMetadata;

    private ApplicationContext applicationContext;


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("执行BeanFactoryPostProcessor.............");
        Iterator it = beanFactory.getBeanNamesIterator();

        String[] names = beanFactory.getBeanDefinitionNames();
        // 获取了所有的bean名称列表
        for(int i=0; i<names.length; i++){
            String name = names[i];
            BeanDefinition bd = beanFactory.getBeanDefinition(name);
            System.out.println(name + " bean properties: " + bd.getPropertyValues().toString());
            // 本内容只是个demo，打印持有的bean的属性情况
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行InitializingBean afterPropertiesSet.............");

    }



    static class  AService{
        private String id;
    }
    public static void main(String[] args) {


        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();


        RootBeanDefinition beanDefinition = new RootBeanDefinition(AService.class);
        beanDefinition.setPropertyValues(new MutablePropertyValues().add("id","8888"));


        AService aService = (AService) beanFactory.createBean(AService.class);



        System.out.println("aService===>"+ aService.id);
      /*  BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(StudentTest.class);

        AbstractBeanDefinition abstractBeanDefinition = beanDefinitionBuilder.getBeanDefinition();
        context.registerBean(abstractBeanDefinition.getBeanClass());

        context.refresh();*/




    }


    private <A extends Annotation> A getAnnotation(Object bean, String beanName,
                                                   Class<A> type) {
       // A annotation = this.beanFactoryMetadata.findFactoryAnnotation(beanName, type);
       // if (annotation == null) {
        A annotation = AnnotationUtils.findAnnotation(bean.getClass(), type);
        //}
        return annotation;
    }

}
