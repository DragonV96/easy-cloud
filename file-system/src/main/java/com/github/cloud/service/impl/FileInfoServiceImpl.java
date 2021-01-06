package com.github.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.cloud.dto.request.AddFileRequest;
import com.github.cloud.dto.request.PageFileInfoRequest;
import com.github.cloud.dto.response.FileInfoResponse;
import com.github.cloud.entity.FileInfo;
import com.github.cloud.mapper.FileInfoMapper;
import com.github.cloud.service.FileInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* @author : glw
* @datetime : 2021-01-03 18:17:22
* @description : 文件信息业务层实现
*/
@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {

    @Override
    public IPage<FileInfo> page(PageFileInfoRequest request) {
        IPage<FileInfo> page = new Page<>(request.getCurrent(), request.getSize());
        FileInfo entity = new FileInfo();
        BeanUtils.copyProperties(request, entity);
        return super.page(page, new QueryWrapper<>(entity));
    }

    @Override
    public FileInfoResponse detail(Long id) {
        FileInfo entity = super.getById(id);
        FileInfoResponse response = new FileInfoResponse();
        BeanUtils.copyProperties(response, entity);
        return response;
    }

    @Override
    public Long queryIdByHash(String fileHash) {
        return this.getBaseMapper().queryIdByHash(fileHash);
    }

    @Override
    public boolean save(AddFileRequest request) {
        FileInfo entity = new FileInfo();
        BeanUtils.copyProperties(request, entity);
        return super.save(entity);
    }

    @Override
    public boolean delete(Long id) {
        return super.removeById(id);
    }

    @Override
    public boolean deleteBatch(List<Long> ids) {
        return super.removeByIds(ids);
    }
}
