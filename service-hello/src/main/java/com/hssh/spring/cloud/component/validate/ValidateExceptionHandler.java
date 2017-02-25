package com.hssh.spring.cloud.component.validate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hssh on 2017/2/23.
 */
@ControllerAdvice
public class ValidateExceptionHandler {

    @ExceptionHandler(value=ValidateException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public String defaultHandler(HttpServletRequest request, HttpServletResponse response, ValidateException e) {
        return e.getMessage();
    }

}
