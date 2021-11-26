package com.hunter.demo.mybatis;


import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @MyMapperScan注解通过@Import方法导入MyMapperScannerRegistrar类
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@Import(MyMapperScannerRegistrar.class)
public @interface MyMapperScan {
}
