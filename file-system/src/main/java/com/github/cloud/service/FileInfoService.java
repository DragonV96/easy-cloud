package com.github.cloud.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.cloud.dto.request.AddFileRequest;
import com.github.cloud.dto.request.PageFileRequest;
import com.github.cloud.dto.response.FileInfoResponse;
import com.github.cloud.dto.response.PageFileResponse;
import com.github.cloud.entity.FileInfo;

import java.util.List;


/**
* @author : glw
* @datetime : 2021-01-03 18:17:22
* @description : 文件信息业务层接口
*/
public interface FileInfoService extends IService<FileInfo> {

    /**
    * 分页查询文件记录
    * @param request
    * @return
    */
    IPage<PageFileResponse> page(PageFileRequest request);

    /**
    * 根据主键 id 查询文件信息详情
    * @param id
    * @return
    */
    FileInfoResponse detail(Long id);

    /**
    * 根据文件 hash 查询文件信息 id
    * @param fileHash
    * @return
    */
    Long queryIdByHash(String fileHash);

    /**
    * 新增文件信息
    * @param request
    * @return
    */
    boolean save(AddFileRequest request);

    /**
    * 根据主键 id 删除文件信息
    * @param id
    * @return
    */
    boolean delete(Long id);

    /**
    * 根据主键 id 列表批量删除文件信息
    * @param ids
    * @return
    */
    boolean deleteBatch(List<Long> ids);
}
