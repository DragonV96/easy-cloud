package com.github.cloud.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.cloud.dto.request.AddFileRequest;
import com.github.cloud.dto.request.PageFileUserRequest;
import com.github.cloud.dto.request.UpdateFileRequest;
import com.github.cloud.dto.response.FileUserResponse;
import com.github.cloud.entity.FileUser;

import java.util.List;


/**
* @author : glw
* @datetime : 2021-01-03 18:17:23
* @description : 文件状态业务层接口
*/
public interface FileUserService extends IService<FileUser> {

    /**
    * 分页查询文件状态
    * @param request
    * @return
    */
    IPage<FileUser> page(PageFileUserRequest request);

    /**
    * 根据主键 id 查询文件状态详情
    * @param id
    * @return
    */
    FileUserResponse detail(Long id);

    /**
    * 新增文件状态
    * @param request
    * @return
    */
    boolean save(AddFileRequest request);

    /**
    * 更新文件状态
    * @param request
    * @return
    */
    boolean update(UpdateFileRequest request);

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
