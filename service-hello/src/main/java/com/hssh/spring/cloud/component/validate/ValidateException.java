package com.hssh.spring.cloud.component.validate;

/**
 * Created by hssh on 2017/2/23.
 */
public class ValidateException extends RuntimeException {

    public ValidateException() {
        super();
    }

    public ValidateException(String message) {
        super(message);
    }
}
