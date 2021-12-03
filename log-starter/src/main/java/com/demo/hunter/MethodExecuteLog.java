package com.demo.hunter;

import java.lang.annotation.*;

/**
 * @author Hunter
 * @date 2021/12/03 23:18
 **/
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodExecuteLog {
}
