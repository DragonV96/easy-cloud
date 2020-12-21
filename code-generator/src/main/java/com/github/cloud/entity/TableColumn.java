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
     * 库名
     */
    private String tableSchema;

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
     * 列注释
     */
    private String columnComment;




    /**
     * 列名
     */
    private String columnField;

    /**
     * 列类型
     */
    private String columnType;

    /**
     * id 列对应的 java 类型
     */
    private String idType;

    /**
     * 普通列对应的 java 类型
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
