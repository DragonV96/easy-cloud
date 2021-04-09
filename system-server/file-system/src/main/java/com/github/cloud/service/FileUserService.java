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
* @description : 文件用户业务层接口
*/
public interface FileUserService extends IService<FileUser> {

    /**
    * 分页查询文件用户
    * @param request
    * @return
    */
    IPage<FileUser> page(PageFileUserRequest request);

    /**
    * 根据主键 id 查询文件用户详情
    * @param id
    * @return
    */
    FileUserResponse detail(Long id);

    /**
    * 新增文件用户
    * @param request
    * @return
    */
    boolean save(AddFileRequest request);

    /**
    * 更新文件用户
    * @param request
    * @return
    */
    boolean update(UpdateFileRequest request);

    /**
     * 根据主键 id 删除文件用户
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 根据主键 id 列表批量删除文件用户
     * @param ids
     * @return
     */
    boolean deleteBatch(List<Long> ids);

    /**
    * 根据主键 id 查询文件 id
    * @param id
    * @return
    */
    Long queryFileInfoIdsById(Long id);

    /**
    * 根据文件 id 查询主键 id 列表
    * @param fileInfoId
    * @return
    */
    List<Long> queryIdsByFileInfoId(Long fileInfoId);

    /**
    * 根据文件 id 删除文件用户
    * @param id
    * @return
    */
    boolean deleteByFileInfoId(Long id);

    /**
    * 根据文件 id 列表批量删除文件用户
    * @param ids
    * @return
    */
    boolean deleteBatchByFileInfoId(List<Long> ids);
}
