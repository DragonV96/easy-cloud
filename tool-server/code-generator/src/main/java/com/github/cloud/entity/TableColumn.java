package com.github.cloud.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : glw
 * @date : 2020/12/9
 * @time : 23:37
 * @Description : 表字段生成对象
 */
@Data
public class TableColumn implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 列名
     */
    private String columnName;

    /**
     * 列的默认值
     */
    private String columnDefault;

    /**
     * 列能否为空
     */
    private String isNullable;

    /**
     * 列的类型
     */
    private String dataType;

    /**
     * 列的最大长度
     */
    private String characterMaximum;

    /**
     * 键类型
     */
    private String columnKey;

    /**
     * 列注释
     */
    private String columnComment;

    /**
     * 普通列对应的 java 类型
     */
    private String javaType;

    /**
     * java 列名（首字母小写驼峰）
     */
    private String javaField;

    /**
     * 是否为主键 id
     */
    private Boolean isKey;

    /**
     * 主键 id mybatis 类型
     */
    private String idType;
}
