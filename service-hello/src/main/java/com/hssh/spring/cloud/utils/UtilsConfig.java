package com.hssh.spring.cloud.utils;

import com.hssh.spring.cloud.persistent.entity.converter.NameDoVoConterver;
import com.hssh.spring.cloud.persistent.entity.converter.PhoneDoVoConterver;
import com.hssh.spring.cloud.persistent.entity.model.User;
import com.hssh.spring.cloud.persistent.entity.vo.UserVo;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.dozer.loader.api.FieldsMappingOptions.customConverter;

/**
 * Created by hssh on 2017/2/25.
 */
@Configuration
public class UtilsConfig {

    @Bean
    public DozerBeanMapper dozerBeanMapper() {
        final DozerBeanMapper mapper = new DozerBeanMapper();
        BeanMappingBuilder builder = new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(User.class, UserVo.class)
                        .fields("phone", "phone", customConverter(PhoneDoVoConterver.class))
                        .fields("userName", "userName", customConverter(NameDoVoConterver.class));
            }
        };

        mapper.addMapping(builder);
        return mapper;
    }

}
