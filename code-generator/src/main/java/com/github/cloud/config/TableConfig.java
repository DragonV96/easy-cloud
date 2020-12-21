package com.github.cloud.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : glw
 * @date : 2020/12/9
 * @time : 23:34
 * @Description : 表属性配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "generator.table")
public class TableConfig {

    /**
     * 数据库名
     */
    private String databaseName;

    /**
     * 表名命名风格（1-全小写下划线转首字母驼峰；2-全大写下划线转首字母驼峰；3-原本就是首字母驼峰不转换）
     */
    private String tableNameStyle;

    /**
     * 字段名命名风格（1-全小写下划线转首字母驼峰；2-全大写下划线转首字母驼峰；3-原本就是首字母驼峰不转换）
     */
    private String columnNameStyle;

    /**
     * 表信息生成配置列表
     */
    private List<TableConfig.AllowTable> allowTables;

    /**
     * 表信息生成配置
     */
    @Data
    public static class AllowTable {

        /**
         * 表名
         */
        private String tableName;

        /**
         * 表前缀移除
         */
        private String removePrefix;

        /**
         * 主键名
         */
        private String primaryKeyName;

        /**
         * 主键类型，对应 mybatis 5种类型
         */
        private String primaryKeyType;
    }
}
