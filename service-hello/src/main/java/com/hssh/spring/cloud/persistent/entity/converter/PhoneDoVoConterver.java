package com.hssh.spring.cloud.persistent.entity.converter;

import org.apache.commons.lang.StringUtils;
import org.dozer.CustomConverter;

/**
 * Created by hssh on 2017/2/25.
 */
public class PhoneDoVoConterver implements CustomConverter {
    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {

        if(sourceFieldValue instanceof String && StringUtils.isNotBlank((String) sourceFieldValue)) {
            String num = (String) sourceFieldValue;
            return StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*");
        }
        return null;

    }
}
