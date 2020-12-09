package com.github.cloud.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author : glw
 * @date : 2020/12/9
 * @time : 23:37
 * @Description : 业务字段生成对象
 */
@EqualsAndHashCode
@ToString
@Data
public class BusinessEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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
