package com.github.cloud.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.cloud.dto.request.AddFileInfoRequest;
import com.github.cloud.dto.request.PageFileInfoRequest;
import com.github.cloud.dto.request.UpdateFileInfoRequest;
import com.github.cloud.dto.response.FileInfoResponse;
import com.github.cloud.entity.FileInfo;

import java.util.List;


/**
* @author : glw
* @datetime : 2021-01-03 18:17:22
* @description : 文件信息业务层接口
*/
public interface FileInfoService extends IService<FileInfo> {

    /**
    * 分页查询文件信息
    * @param request
    * @return
    */
    IPage<FileInfo> page(PageFileInfoRequest request);

    /**
    * 根据主键 id 查询文件信息详情
    * @param id
    * @return
    */
    FileInfoResponse detail(Long id);

    /**
    * 新增文件信息
    * @param request
    * @return
    */
    boolean save(AddFileInfoRequest request);

    /**
    * 更新文件信息
    * @param request
    * @return
    */
    boolean update(UpdateFileInfoRequest request);

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
