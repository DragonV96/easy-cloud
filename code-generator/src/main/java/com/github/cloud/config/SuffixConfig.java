package com.github.cloud.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : glw
 * @date : 2020/12/13
 * @time : 14:13
 * @Description : 项目类后缀配置（为空则默认无后缀）
 */
@Data
@Component
@ConfigurationProperties(prefix = "generator.suffix")
public class SuffixConfig {

    /**
     * entity 实体类后缀 TODO 未完
     */
    private String entitySuffix;

    /**
     * request 后缀
     */
    private String requestSuffix;

    /**
     * response 后缀
     */
    private String responseSuffix;

    /**
     * controller 后缀 TODO 未完
     */
    private String controllerSuffix;

    /**
     * service 接口后缀 TODO 未完
     */
    private String serviceSuffix;

    /**
     * service 实现类后缀 TODO 未完
     */
    private String serviceImplSuffix;

    /**
     * mapper 接口后缀 TODO 未完
     */
    private String mapperSuffix;
}
