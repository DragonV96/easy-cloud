package com.github.cloud.config;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

/**
 * @author : glw
 * @date : 2020/12/11
 * @time : 1:07
 * @Description : 项目包结构及名称配置
 */
@Getter
@Configuration
public class ProjectConfig {

    /**
     * 项目包路径
     */
    private String packageName;

    /**
     * 项目子模块路径
     */
    private String moduleName;

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

    /**
     * request 后缀
     */
    private String requestSuffix;

    /**
     * response 后缀
     */
    private String responseSuffix;
}
