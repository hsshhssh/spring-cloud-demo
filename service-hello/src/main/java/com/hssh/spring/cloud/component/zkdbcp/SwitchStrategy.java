package com.hssh.spring.cloud.component.zkdbcp;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by hssh on 2017/2/17.
 */
@Component
@Aspect
public class SwitchStrategy {

    @Pointcut("@annotation(com.hssh.spring.cloud.component.zkdbcp.DataSourceName)")
    public void point(){}


    @Before("point()")
    public void before(JoinPoint jp) {
        Signature signature = jp.getSignature();
        Method method = ((MethodSignature)signature).getMethod();
        DataSourceName annotation = method.getAnnotation(DataSourceName.class);
        ContextHolder.setHolder(annotation.value());
    }

}
