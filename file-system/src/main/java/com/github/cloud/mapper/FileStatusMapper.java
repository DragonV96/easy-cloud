package com.github.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.cloud.entity.FileUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author : glw
* @datetime : 2021-01-03 18:17:23
* @description : 文件状态数据访问层
*/
public interface FileStatusMapper extends BaseMapper<FileUser> {

    /**
     * 根据文件 id 列表批量删除文件状态
     * @param ids
     * @return
     */
    boolean deleteBatchByFileInfoId(@Param("ids") List<Long> ids);

    /**
     * 根据主键 id 查询文件 id
     * @param id
     * @return
     */
    Long queryFileInfoIdsById(@Param("id") Long id);

    /**
     * 根据文件 id 查询主键 id 列表
     * @param fileInfoId
     * @return
     */
    List<Long> queryIdsByFileInfoId(Long fileInfoId);
}