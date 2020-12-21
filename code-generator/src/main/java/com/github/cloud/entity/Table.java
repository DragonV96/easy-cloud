package com.github.cloud.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author : glw
 * @datetime : 2020/12/17 17:20
 * @description : 表生成对象
 */
@EqualsAndHashCode
@ToString
@Data
public class Table implements Serializable {

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
     * 表注释
     */
    private String tableComment;

    /**
     * 表对应的 首字母大写 类名
     */
    private String classUpperName;

    /**
     * 表对应的 首字母小写写 类名
     */
    private String classLowerName;

    /**
     * 表字段列表
     */
    private List<TableColumn> columns;
}
