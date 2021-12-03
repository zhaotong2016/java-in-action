package com.demo.hunter;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * @author Hunter
 * @date 2021/12/03 23:12
 **/
@Configuration
@ConditionalOnClass(SpringBootApplication.class)
public class MethodLogConfiguration extends AbstractPointcutAdvisor implements InitializingBean {

    private Pointcut pointcut;

    private Advice advice;
    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.pointcut = new AnnotationMatchingPointcut(
                null,MethodExecuteLog.class);
        this.advice = new MethodLogAdvice();
    }
}
