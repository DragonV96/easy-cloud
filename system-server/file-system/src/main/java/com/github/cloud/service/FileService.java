package com.github.cloud.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.cloud.dto.request.AddFileRequest;
import com.github.cloud.dto.request.PageFileRequest;
import com.github.cloud.dto.request.QueryFileRequest;
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
    IPage<FileInfo> page(PageFileRequest request);

    /**
     * 根据主键 id 查询文件信息详情
     * @param id
     * @return
     */
    FileInfoResponse detail(Long id);

    /**
     * 根据文件用户主键 id 删除文件
     * @param id
     */
    void delete(Long id);

    /**
     * 根据文件用户主键 id 列表批量删除文件 TODO 文件较多时，放入 MQ 中异步删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 一次性上传
     * @param file
     * @param request
     */
    void uploadOnce(MultipartFile file, AddFileRequest request);

    /**
     * 分片上传
     * @param file
     * @param request
     */
    void uploadSlice(MultipartFile file, AddFileRequest request);

    /**
     * 秒传（之前已存在相同 MD5 的文件）
     * @param request
     */
    void uploadExist(AddFileRequest request);

    /**
     * 根据文件 MD5 查询文件是否存在
     * @param request
     * @return
     */
    boolean uploadCheck(QueryFileRequest request);
}
