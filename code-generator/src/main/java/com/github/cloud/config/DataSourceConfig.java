package com.github.cloud.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : glw
 * @datetime : 2020/12/18 00:10
 * @description : 数据源配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "generator.datasource")
public class DataSourceConfig {

    /**
     * jdbc 驱动
     */
    private String driverClassName;

    /**
     * 数据库 url
     */
    private String url;

    /**
     * 数据库账号
     */
    private String username;

    /**
     * 数据库密码
     */
    private String password;

}
