package com.hunter.demo.annotation;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@MyComponentScan2(basePackages  = "com.hunter.com.hunter.demo.user.annotation")
public class ComponentScanDemo {



    public static void main(String[] args) {
        testAnntotationApplication();
    }
    public static void testAnntotationApplication(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        context.register(ComponentScanDemo.class);

        // 启动 Spring 应用上下文
        context.refresh();

        // 依赖查找 TestClass Bean
        // TestClass 标注 @MyComponent2
        // @MyComponent2 <- @MyComponent <- @Component
        // 从 Spring 4.0 开始支持多层次 @Component "派生"
        TestClass testClass = context.getBean(TestClass.class);

        // Annotation -> AnnotationAttributes(Map)

        System.out.println(testClass);

        // 关闭 Spring 应用上下文
        context.close();
    }
}
