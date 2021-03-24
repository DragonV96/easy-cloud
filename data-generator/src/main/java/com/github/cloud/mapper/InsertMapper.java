package com.github.cloud.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author : glw
 * @datetime : 2021/3/24 22:49
 * @Description :
 */
public interface InsertMapper {

    /**
     * 插入数据
     * @param sql
     */
    void insertData(@Param("sql") String sql);
}
