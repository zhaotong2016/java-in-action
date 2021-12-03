package com.demo.hunter;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @author Hunter
 * @date 2021/12/03 23:39
 **/
@Slf4j
@Aspect
public class MethodLogAspect {

    @Pointcut("@annotation(MethodExecuteLog)")
    public void pointcut(){

    }

    @Around("pointcut()")
    public Object log(ProceedingJoinPoint point){

        Object result = null;
        if (point instanceof MethodInvocationProceedingJoinPoint){

            long start = System.currentTimeMillis();
            MethodInvocationProceedingJoinPoint methodPoint = (MethodInvocationProceedingJoinPoint) point;

            MethodSignature signature = (MethodSignature) methodPoint.getSignature();

            Method method = signature.getMethod();

            try {
                result = methodPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }finally {
                long end = System.currentTimeMillis();

                log.info(String.format("Method log form Aspect:{%s}->{%s}(),time cost:{%d}ms",
                        method.getDeclaringClass().getCanonicalName(),method.getName(),(end-start)));

            }

        }

        return result;
    }


}
