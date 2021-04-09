package com.github.cloud.constant;

/**
 * @author : glw
 * @datetime : 2021/3/24 22:31
 * @Description : 插入数据常量
 */
public interface InsertConstant {

    // 例子
    // INSERT INTO users (username, password, enabled) VALUES
    // ('111', '$2a$10$EuWPZHzz32YirDdFAZm2kWU', TRUE),
    // ('111', '$2a$jexM34kuWj7VEOJhhZkDrxfvUu', TRUE);

    /**
     * 插入 SQL
     */
    String INSERT_INTO = "INSERT INTO ";

    /**
     * 值
     */
    String VALUES = "VALUES ";

    /**
     * 左括号
     */
    String LEFT_BRACKETS = "(";

    /**
     * 右括号
     */
    String RIGHT_BRACKETS = ")";

    /**
     * 逗号
     */
    String COMMA = ", ";

    /**
     * 引号
     */
    String QUOTATION_MARKS = "'";

    /**
     * 分号
     */
    String SEMICOLON = ";";
}
