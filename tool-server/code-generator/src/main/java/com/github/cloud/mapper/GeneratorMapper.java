package com.github.cloud.mapper;

import com.github.cloud.entity.Table;
import com.github.cloud.entity.TableColumn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : glw
 * @datetime : 2020/12/20 16:19
 * @description :
 */
public interface GeneratorMapper {

    /**
     * 查询表信息
     * @param databaseName
     * @param tableName
     * @return
     */
    Table queryTable(@Param("databaseName") String databaseName, @Param("tableName") String tableName);

    /**
     * 查询表字段信息
     * @param databaseName
     * @param tableName
     * @return
     */
    List<TableColumn> queryTableColumn(@Param("databaseName") String databaseName, @Param("tableName") String tableName);
}
