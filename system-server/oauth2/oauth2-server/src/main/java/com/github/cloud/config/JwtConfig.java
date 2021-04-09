package com.github.cloud.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : glw
 * @datetime : 2021/1/16 12:13
 * @Description : JWT 证书配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    /**
     * 资源文件夹下的证书文件
     */
    private String file;

    /**
     * 密码
     */
    private String key;

    /**
     * 证书别名
     */
    private String alias;

}
