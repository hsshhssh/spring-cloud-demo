package com.hssh.spring.cloud.component.zkdbcp;

import com.github.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by hssh on 2017/2/17.
 */
@Configuration
@Component
@EnableConfigurationProperties(ZkdbcpConfigProperties.class)
@EnableTransactionManagement
public class ZkdbcpConfig {

    @Autowired
    private ZkdbcpConfigProperties properties;

    /**
     * 开启事务
     * @param dataSource
     * @return
     */
    @Bean
    public PlatformTransactionManager txManager(@Qualifier("zKDynamicDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Autowired(required = false)
    private ZkClient zkClient;

    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("zKDynamicDataSource")DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public AbstractRoutingDataSource zKDynamicDataSource() {
        ZkDynamicDataSource zkDynamicDataSource = new ZkDynamicDataSource();

        if(null == zkClient) {
            zkDynamicDataSource.init(properties);
        }
        else {
            zkDynamicDataSource.init(zkClient, properties);
        }

        return zkDynamicDataSource;
    }

}
