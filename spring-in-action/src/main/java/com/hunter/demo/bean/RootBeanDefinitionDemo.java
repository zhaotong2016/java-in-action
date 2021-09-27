package com.hunter.demo.bean;

import lombok.Data;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

public class RootBeanDefinitionDemo {


    @Data
    static class Root{
        private String name;
        private String description;
        private Boolean isRoot;

        public Root() {
        }

        public Root(String name, String description, Boolean isRoot) {
            this.name = name;
            this.description = description;
            this.isRoot = isRoot;
        }

        @Override
        public String toString() {
            return "Root{" +
                    "name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", isRoot=" + isRoot +
                    '}';
        }
    }
    @Data
    static class Child{
        private String name;
        private String description;
        private Boolean isRoot;
        private String parentName;

        public Child(String name, String description, Boolean isRoot, String parentName) {
            this.name = name;
            this.description = description;
            this.isRoot = isRoot;
            this.parentName = parentName;
        }

        public Child() {
        }

        @Override
        public String toString() {
            return "Child{" +
                    "name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", isRoot=" + isRoot +
                    ", parentName='" + parentName + '\'' +
                    '}';
        }
    }

    /**
     * 1、测试 RootBeanDefinition 通过构造器注入
     * @return
     */
    public static RootBeanDefinition getRootBeanDefinitionByConstructor(){

        ConstructorArgumentValues cargs = new ConstructorArgumentValues();

        cargs.addIndexedArgumentValue(0,"rootA");
        cargs.addIndexedArgumentValue(1,"this is a rootBeanDefinition");
        cargs.addIndexedArgumentValue(2,true);

        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Root.class,cargs,null);


        return rootBeanDefinition;

    }

    /**
     * 2、 测试 RootBeanDefinition 通过Setter注入
     * @return
     */
    public static RootBeanDefinition getRootBeanDefinitionBySetter(){

        MutablePropertyValues pvs = new MutablePropertyValues();

        pvs.addPropertyValue("name","rootA");
        pvs.addPropertyValue("description","this is a rootBeanDefinition");
        pvs.addPropertyValue("isRoot",true);

        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Child.class,null,pvs);

        return rootBeanDefinition;
    }

    /**
     * test RootBeanDefinition
     */
    public static void testRootBeanDefinition(){
        AnnotationConfigApplicationContext configApplicationContext =
                new AnnotationConfigApplicationContext();

        RootBeanDefinition rootBeanDefinition = getRootBeanDefinitionByConstructor();
        RootBeanDefinition childBeanDefinition =  getRootBeanDefinitionBySetter();
        configApplicationContext.registerBeanDefinition("root",rootBeanDefinition);
        configApplicationContext.registerBeanDefinition("child",childBeanDefinition);
        configApplicationContext.refresh();

        Root root =(Root) configApplicationContext.getBean("root");

        System.out.println(root);

        Child child = configApplicationContext.getBean(Child.class);

        System.out.println(child);
    }
    /**
     * test ChildBeanDefinition
     * @return
     */
    public static void testChildBeanDefinition(){

        AnnotationConfigApplicationContext configApplicationContext =
                new AnnotationConfigApplicationContext();


        ChildBeanDefinition childBeanDefinition = new ChildBeanDefinition("root");

        childBeanDefinition.setBeanClass(Child.class);
        childBeanDefinition.getPropertyValues().addPropertyValue("name","child");
        childBeanDefinition.getPropertyValues().addPropertyValue("description","test child");
        childBeanDefinition.getPropertyValues().addPropertyValue("isRoot",false);
        childBeanDefinition.getPropertyValues().addPropertyValue("parentName","root");


        //注册
        RootBeanDefinition rootBeanDefinition = getRootBeanDefinitionBySetter();
        configApplicationContext.registerBeanDefinition("root",rootBeanDefinition);
        configApplicationContext.registerBeanDefinition("child",childBeanDefinition);

        //刷新上下文
        configApplicationContext.refresh();

        Root rootA = (Root)configApplicationContext.getBean("root");

        System.out.println(rootA);

        Child child = (Child)configApplicationContext.getBean("child");

        System.out.println(child);

    }


   //=========================================================================

    public static GenericBeanDefinition getRootBeanDefinition() {
        GenericBeanDefinition rootBeanDefinition = new GenericBeanDefinition();
        rootBeanDefinition.setBeanClass(Root.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("name", "root")
                .add("description", "I am a rootBeanDefinition")
                .add("isRoot", true);
        rootBeanDefinition.setPropertyValues(propertyValues);
        return rootBeanDefinition;
    }

    public static GenericBeanDefinition getChildBeanDefinition() {
        GenericBeanDefinition childBeanDefinition = new GenericBeanDefinition();
        childBeanDefinition.setBeanClass(Child.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("parentName", "root");
        childBeanDefinition.setParentName("root");
        childBeanDefinition.setPropertyValues(propertyValues);
        return childBeanDefinition;
    }

    /**
     * test GenericBeanDefinition
     */
    public static void testGenericBeanDefinition(){
        // 1. 构建一个空的容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        GenericBeanDefinition rootBeanDefinition = getRootBeanDefinition();
        GenericBeanDefinition childBeanDefinition = getChildBeanDefinition();
        applicationContext.registerBeanDefinition("root", rootBeanDefinition);
        applicationContext.registerBeanDefinition("child", childBeanDefinition);
        applicationContext.refresh();
        Root root = applicationContext.getBean(Root.class);
        Child child = applicationContext.getBean(Child.class);
        System.out.println(root.toString());
        System.out.println(child.toString());
    }

    public static void main(String[] args) {
        //测试
        //testRootBeanDefinition();

        //testChildBeanDefinition();

        testGenericBeanDefinition();
    }
}
