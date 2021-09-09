package com.hunter.demo.annotation;


import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 自定义 Component "派生性"注解
 *
 * @since
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component // 元注解，实现 @Component "派生性"
public @interface MyComponent {
}
