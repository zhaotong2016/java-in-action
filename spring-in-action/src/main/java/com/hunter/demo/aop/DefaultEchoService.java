package com.hunter.demo.aop;

import org.springframework.context.annotation.Configuration;

@Configuration // @Configuration 需要 @ComponentScan -> ConfigurationClassPostProcessor
// CGLIB 代理对象
public class DefaultEchoService implements EchoService {
    @Override
    public String echo(String message) throws NullPointerException {
        return "[ECHO] " + message;
    }
}
