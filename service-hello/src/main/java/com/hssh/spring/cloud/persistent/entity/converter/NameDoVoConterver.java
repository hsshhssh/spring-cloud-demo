package com.hssh.spring.cloud.persistent.entity.converter;

import org.apache.commons.lang3.StringUtils;
import org.dozer.CustomConverter;

/**
 * Created by hssh on 2017/2/25.
 */
public class NameDoVoConterver implements CustomConverter {
    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
        if(sourceFieldValue instanceof String && StringUtils.isNotBlank((String) sourceFieldValue)) {
            String fullName = (String) sourceFieldValue;
            String name = StringUtils.left(fullName, 1);
            return StringUtils.rightPad(name, StringUtils.length(fullName), "*");
        }
        return null;
    }
}
