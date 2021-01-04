package com.github.cloud.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.cloud.dto.request.AddFileStatusRequest;
import com.github.cloud.dto.request.PageFileStatusRequest;
import com.github.cloud.dto.request.UpdateFileStatusRequest;
import com.github.cloud.dto.response.FileStatusResponse;
import com.github.cloud.entity.FileStatus;

import java.util.List;


/**
* @author : glw
* @datetime : 2021-01-03 18:17:23
* @description : 文件状态业务层接口
*/
public interface FileStatusService extends IService<FileStatus> {

    /**
    * 分页查询文件状态
    * @param request
    * @return
    */
    IPage<FileStatus> page(PageFileStatusRequest request);

    /**
    * 根据主键 id 查询文件状态详情
    * @param id
    * @return
    */
    FileStatusResponse detail(Integer id);

    /**
    * 新增文件状态
    * @param request
    * @return
    */
    boolean save(AddFileStatusRequest request);

    /**
    * 更新文件状态
    * @param request
    * @return
    */
    boolean update(UpdateFileStatusRequest request);

    /**
    * 根据文件 id 删除文件状态
    * @param id
    * @return
    */
    boolean deleteByFileInfoId(Long id);

    /**
    * 根据文件 id 列表批量删除文件状态
    * @param ids
    * @return
    */
    boolean deleteBatchByFileInfoId(List<Long> ids);
}
