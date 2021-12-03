package com.demo.hunter;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StringUtils;

/**
 * @author Hunter
 * @date 2021/12/03 23:05
 **/
@Slf4j
public class MethodLogAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        String name = invocation.getMethod().getName();
        long start = System.currentTimeMillis();

        Object result = invocation.proceed();

        long end = System.currentTimeMillis();

        log.info(String.format("Method log form interceptor:{%s}->{%s}(),time cost:{%d}ms",
                invocation.getMethod().getDeclaringClass().getCanonicalName(),name,(end-start)));


        return result;
    }
}
