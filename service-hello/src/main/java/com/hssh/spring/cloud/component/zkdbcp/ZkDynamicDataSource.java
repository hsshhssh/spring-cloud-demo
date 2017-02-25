package com.hssh.spring.cloud.component.zkdbcp;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.zkclient.ZkClient;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.hssh.spring.cloud.utils.ZkUtils;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

/**
 * 动态数据源
 * Created by hssh on 2017/2/16.
 */
public class ZkDynamicDataSource extends AbstractRoutingDataSource{

    public ZkDynamicDataSource() {
        super();
    }

    @Autowired
    private ZkdbcpConfigProperties properties;

    public void init(ZkdbcpConfigProperties properties)
    {
        ZkClient zk = new ZkClient(System.getenv("spring.cloud.zookeeper.connect-string"));
        this.init(zk, properties);
    }


    public void init(ZkClient zk, ZkdbcpConfigProperties properties)
    {
        Preconditions.checkArgument(zk!=null && properties!=null, "zk is not init");
        Map<Object, Object> dataSourceMap = Maps.newHashMap();
        this.properties = properties;
        for(String s : DataSourceName.allName) {
            String path = String.format("%s%s/%s/%s",this.properties.getBasePath(),this.properties.getBizGroup(), this.properties.getBizName(), s);
            PropertiesConfiguration config = ZkUtils.byteToProper(zk.readData(path));
            Preconditions.checkNotNull(config, "load zk config error");
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setUrl(config.getString("url"));
            dataSource.setUsername(config.getString("username"));
            dataSource.setPassword(config.getString("password"));
            dataSourceMap.put(s, dataSource);
        }
        this.setTargetDataSources(dataSourceMap);
        this.setDefaultTargetDataSource(dataSourceMap.get(DataSourceName.master));
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return ContextHolder.getHolder();
    }
}
