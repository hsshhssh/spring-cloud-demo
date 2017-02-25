package com.hssh.spring.cloud.component.zkconf;

import java.lang.annotation.*;

/**
 * Created by hssh on 2017/2/15.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {

    String path();

    String key();

}
