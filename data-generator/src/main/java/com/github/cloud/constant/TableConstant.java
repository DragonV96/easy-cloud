package com.github.cloud.constant;

/**
 * @author : glw
 * @datetime : 2021/3/24 22:34
 * @Description : 表常量
 */
public interface TableConstant {

    /**
     * 用户表
     */
    String USER = "users ";

    /**
     * 字段
     */
    String COLUMN = "(username, real_name, gender, age, email, create_at, update_at) ";

    /**
     * 平均数据长度
     */
    int DATA_LENGTH = 60;
}
