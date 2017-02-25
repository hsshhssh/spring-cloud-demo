package com.hssh.spring.cloud.component.zkdbcp;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by hssh on 2017/2/18.
 */
@ConfigurationProperties(prefix = ZkdbcpConfigProperties.ZKDBCP_PREFIX)
public class ZkdbcpConfigProperties {

    public static final String ZKDBCP_PREFIX = "zkdbcp";

    /**
     * zk配置配置文件的路径
     */
    public String basePath;

    /**
     * 业务组名
     */
    public String bizGroup;

    /**
     * 业务名称
     */
    public String bizName;

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getBizGroup() {
        return bizGroup;
    }

    public void setBizGroup(String bizGroup) {
        this.bizGroup = bizGroup;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }
}
