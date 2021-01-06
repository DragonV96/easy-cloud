package com.github.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.cloud.entity.FileInfo;
import org.apache.ibatis.annotations.Param;

/**
* @author : glw
* @datetime : 2021-01-03 18:17:22
* @description : 文件信息数据访问层
*/
public interface FileInfoMapper extends BaseMapper<FileInfo> {

    /**
     * 根据文件 hash 查询文件信息 id
     * @param fileHash
     * @return
     */
    Long queryIdByHash(@Param("fileHash") String fileHash);
}