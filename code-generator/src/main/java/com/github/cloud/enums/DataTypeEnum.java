package com.github.cloud.enums;

import lombok.Getter;

/**
 * @author : glw
 * @datetime : 2020/12/30 15:50
 * @description : 数据类型枚举
 */
@Getter
public enum DataTypeEnum {
    VARCHAR("varchar", "String"),
    INT("int", "Integer"),
    BIGINT("bigint", "Long"),
    TIMESTAMP("timestamp", "Date"),
    DATETIME("datetime", "Date"),
    CHAR("char", "String"),
    BIT("bit", "Boolean"),
    TINYINT("tinyint", "Byte"),
    ;

    /**
     * 数据库数据类型
     */
    private String dataType;

    /**
     * java 数据类型
     */
    private String javaType;

    DataTypeEnum(String dataType, String javaType) {
        this.dataType = dataType;
        this.javaType = javaType;
    }
}
