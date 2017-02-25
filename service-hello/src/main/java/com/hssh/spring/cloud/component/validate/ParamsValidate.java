package com.hssh.spring.cloud.component.validate;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * Created by hssh on 2017/2/23.
 */
@Aspect
@Component
public class ParamsValidate {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Pointcut("execution(* com.hssh.spring.cloud.web.*.*(..))")
    public void point() {}


    @Before("point()")
    public void before(JoinPoint jp) {
        // 返回目标对象 区别getThis(返回代理对象)
        Object object = jp.getTarget();
        Signature signature = jp.getSignature();
        Method method = ((MethodSignature)signature).getMethod();
        Object[] params = jp.getArgs();

        ExecutableValidator executableValidator = validator.forExecutables();
        Set<ConstraintViolation<Object>> methodValidators = executableValidator.validateParameters(object, method, params);

        Set<ConstraintViolation<Object>> paramsValidators = Sets.newHashSet();
        for(Object p : params) {
            paramsValidators.addAll(validator.validate(p));
        }

        if(methodValidators.size() > 0 || paramsValidators.size() >0) {
            List<String> msgList = Lists.newArrayList();
            for(ConstraintViolation<Object> c : methodValidators) {
                msgList.add(c.getMessage());
            }
            for(ConstraintViolation<Object> c : paramsValidators) {
                msgList.add(c.getMessage());
            }

            throw new ValidateException(Joiner.on("\n").join(msgList));
        }
    }



}
