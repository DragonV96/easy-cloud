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
     * 项目子模块路径
     */
    private String modulePackageName;

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
