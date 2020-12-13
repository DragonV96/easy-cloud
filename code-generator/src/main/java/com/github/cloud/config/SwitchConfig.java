package com.github.cloud.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : glw
 * @date : 2020/12/12
 * @time : 18:21
 * @Description : 项目开关配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "generator.switch")
public class SwitchConfig {

    /**
     * 是否开启类注释的邮箱
     */
    private boolean enableEmail;

    /**
     * 是否开启 swagger
     */
    private boolean enableSwagger;

    /**
     * 是否开启 shiro
     */
    private boolean enableShiro;

    /**
     * 是否开启 log TODO 未完
     */
    private boolean enableLog;

    /**
     * 是否开启生成枚举 TODO 未完
     */
    private boolean enableEnum;
}
