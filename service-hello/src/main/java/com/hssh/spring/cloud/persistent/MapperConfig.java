package com.hssh.spring.cloud.persistent;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hssh on 2017/2/19.
 */
@Configuration
@MapperScan(basePackages = "com.hssh.spring.cloud.persistent.dao")
public class MapperConfig {
}
