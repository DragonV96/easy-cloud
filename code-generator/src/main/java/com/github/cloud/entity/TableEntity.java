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
 * @Description : 表生成对象
 */
@EqualsAndHashCode
@ToString
@Data
public class TableEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表对应实体类名（首字母大写驼峰）
     */
    private String entityClassName;

    /**
     * 表对应实体类注释
     */
    private String comment;

    /**
     * 实体类对应包路径
     */
    private String entityPackageName;

    /** 表列信息 */
    private List<TableColumnEntity> columns;
}
