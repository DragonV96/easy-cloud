package com.github.cloud.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.cloud.dto.request.AddFileRequest;
import com.github.cloud.dto.request.PageFileInfoRequest;
import com.github.cloud.dto.request.UpdateFileInfoRequest;
import com.github.cloud.dto.response.FileInfoResponse;
import com.github.cloud.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author : glw
 * @datetime : 2021/1/4 15:10
 * @description : 文件处理业务层接口
 */
public interface FileService {

    /**
     * 上传文件
     * @param file
     * @param request
     * @return
     */
    boolean upload(MultipartFile file, AddFileRequest request);

    /**
     * 下载文件
     * @param id
     * @return
     */
    boolean download(Long id);

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
     * 更新文件
     * @param request
     * @return
     */
    boolean update(UpdateFileInfoRequest request);

    /**
     * 根据主键 id 删除文件
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 根据主键 id 列表批量删除文件
     * @param ids
     * @return
     */
    boolean deleteBatch(List<Long> ids);
}
