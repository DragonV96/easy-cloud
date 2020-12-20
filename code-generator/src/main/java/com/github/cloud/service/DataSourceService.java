package com.github.cloud.service;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * @author : glw
 * @datetime : 2020/12/18 00:19
 * @description : 数据源操作业务层接口
 */
public interface DataSourceService {

    /**
     * 执行 SQL
     * @param sql
     * @return
     */
    ResultSet execute(Connection connect, String sql);

    /**
     * 开启数据库连接
     * @return
     */
    Connection connect();

    /**
     * 关闭数据库连接
     * @param conn
     */
    void close(Connection conn);
}
