package com.github.cloud.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : glw
 * @date : 2020/12/11
 * @time : 1:07
 * @Description : 项目包结构及名称配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "generator.package")
public class PackageConfig {

    /**
     * 项目根路径
     */
    private String rootPackageName;

    /**
     * controller 包路径
     */
    private String controllerPackageName;

    /**
     * service 接口包路径
     */
    private String servicePackageName;

    /**
     * service 接口实现类包路径
     */
    private String serviceImplPackageName;

    /**
     * mapper 包路径
     */
    private String mapperPackageName;

    /**
     * 数据库实体类包路径
     */
    private String entityPackageName;

    /**
     * dto 包路径
     */
    private String dtoPackageName;

    /**
     * request 包路径
     */
    private String requestPackageName;

    /**
     * response 包路径
     */
    private String responsePackageName;
}
