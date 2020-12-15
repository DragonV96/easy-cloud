package com.github.cloud.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author : glw
 * @date : 2020/12/9
 * @time : 23:37
 * @Description : 表字段生成对象
 */
@EqualsAndHashCode
@ToString
@Data
public class TableColumn implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 列名
     */
    private String columnField;

    /**
     * 列注释
     */
    private String columnComment;

    /**
     * 列类型
     */
    private String columnType;

    /**
     * 列对应的 java 类型
     */
    private String javaType;

    /**
     * java 列名（首字母小写驼峰）
     */
    private String javaField;

    /**
     * 是否主键（1-是；2-否）
     */
    private String isKey;

    /**
     * 是否自增（1-是；2-否）
     */
    private String isIncrement;

    /**
     * 是否非空（1-是；2-否）
     */
    private String isRequired;
}
