package com.github.cloud.enums;

import lombok.Getter;

/**
 * @author : glw
 * @datetime : 2020/12/17 19:05
 * @description : 模板枚举
 */
@Getter
public enum TemplateEnum {
    ADD("Add.vm", "新增请求对象"),
    UPDATE("Update.vm", "更新请求对象"),
    PAGE("Page.vm", "分页查询请求对象"),
    CONTROLLER("Controller.vm", "控制层"),
    ENTITY("Entity.vm", "表对象"),
    MAPPER("Mapper.vm", "数据访问层"),
    MAPPER_XML("Mapper.xml.vm", "数据访问层xml"),
    RESPONSE("Response.vm", "响应请求对象"),
    SERVICE("Service.vm", "业务层接口"),
    SERVICE_IMPL("ServiceImpl.vm", "业务层实现"),
    ;

    /**
     * 模板文件名
     */
    private String name;

    /**
     * 描述
     */
    private String desc;

    TemplateEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
