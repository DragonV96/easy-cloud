package com.github.cloud.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : glw
 * @date : 2020/12/12
 * @time : 18:21
 * @Description : 项目开关配置
 */
@Getter
@Component
@ConfigurationProperties(prefix = "generator.switch")
public class SwitchConfig {

    /**
     * 是否开启 swagger
     */
    private boolean enableSwagger;

    /**
     * 是否开启 shiro
     */
    private boolean enableShiro;
}
