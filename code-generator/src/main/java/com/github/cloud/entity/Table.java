package com.github.cloud.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

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
     * 表名
     */
    private String tableName;

    /**
     * 表注释
     */
    private String tableComment;

    /**
     * 是否含有日期
     */
    private Boolean hasDate = false;

    /**
     * 是否含有非空基本数据类型
     */
    private Boolean hasNotNull = false;

    /**
     * 是否含有非空字符串
     */
    private Boolean hasNotBlank = false;
}
