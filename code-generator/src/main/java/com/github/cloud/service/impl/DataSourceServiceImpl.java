package com.github.cloud.service.impl;

import com.github.cloud.config.DataSourceConfig;
import com.github.cloud.service.DataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

/**
 * @author : glw
 * @datetime : 2020/12/18 15:20
 * @description : 数据源操作业务层实现
 */
@Slf4j
@Service
public class DataSourceServiceImpl implements DataSourceService {

    private static final String QUERY_TABLE = "select t.table_name, t.table_comment from tables t where t.table_schema = s% and t.table_name = s%";
    private static final String QUERY_COLUMNS = "select t.table_schema, t.table_name, t.column_name, t.column_default, t.is_nullable, t.data_type, t.character_maximum, t.column_comment from columns t where t.table_schema = s% and t.table_name = s%";

    @Autowired
    private DataSourceService dataSourceService;

    @Autowired
    private DataSourceConfig dataSourceConfig;

    @Override
    public ResultSet execute(Connection connect, String sql) {
        ResultSet resultSet = null;
        try {
            PreparedStatement statement = connect.prepareStatement(sql);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            log.error("SQL 执行错误，错误信息：{}，错误原因：{}", e.getMessage(), e.getCause());
        } finally {
            dataSourceService.close(connect);
        }
        return resultSet;
    }

    @Override
    public Connection connect() {
        Connection conn = null;
        try {
            Class.forName(dataSourceConfig.getDriverClassName());
            conn = DriverManager.getConnection(dataSourceConfig.getUrl(), dataSourceConfig.getUsername(), dataSourceConfig.getPassword());
        } catch (ClassNotFoundException e) {
            log.error("找不到 jdbc 驱动类，错误信息：{}，错误原因：{}", e.getMessage(), e.getCause());
        } catch (SQLException e) {
            log.error("SQL 异常，错误信息：{}，错误原因：{}", e.getMessage(), e.getCause());
        }
        return conn;
    }

    @Override
    public void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            log.error("数据库连接关闭异常，错误信息：{}，错误原因：{}", e.getMessage(), e.getCause());
        }
    }
}
