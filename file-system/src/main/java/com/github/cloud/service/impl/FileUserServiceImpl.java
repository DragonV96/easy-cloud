package com.github.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.cloud.dto.request.AddFileRequest;
import com.github.cloud.dto.request.PageFileUserRequest;
import com.github.cloud.dto.request.UpdateFileRequest;
import com.github.cloud.dto.response.FileUserResponse;
import com.github.cloud.entity.FileUser;
import com.github.cloud.mapper.FileStatusMapper;
import com.github.cloud.service.FileUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
* @author : glw
* @datetime : 2021-01-03 18:17:23
* @description : 文件状态业务层实现
*/
@Service
public class FileUserServiceImpl extends ServiceImpl<FileStatusMapper, FileUser> implements FileUserService {

    @Override
    public IPage<FileUser> page(PageFileUserRequest request) {
        IPage<FileUser> page = new Page<>(request.getCurrent(), request.getSize());
        FileUser entity = new FileUser();
        BeanUtils.copyProperties(request, entity);
        return super.page(page, new QueryWrapper<>(entity));
    }

    @Override
    public FileUserResponse detail(Long id) {
        FileUser entity = super.getById(id);
        FileUserResponse response = new FileUserResponse();
        BeanUtils.copyProperties(response, entity);
        return response;
    }

    @Override
    public boolean save(AddFileRequest request) {
        FileUser entity = new FileUser();
        BeanUtils.copyProperties(request, entity);
        return super.save(entity);
    }

    @Override
    public boolean update(UpdateFileRequest request) {
        FileUser entity = new FileUser();
        BeanUtils.copyProperties(request, entity);
        return super.updateById(entity);
    }

    @Override
    public boolean delete(Long id) {
        return super.removeById(id);
    }

    @Override
    public boolean deleteBatch(List<Long> ids) {
        return super.removeByIds(ids);
    }

    @Override
    public Long queryFileInfoIdsById(Long id) {
        return super.getBaseMapper().queryFileInfoIdsById(id);
    }

    @Override
    public List<Long> queryIdsByFileInfoId(Long fileInfoId) {
        return super.getBaseMapper().queryIdsByFileInfoId(fileInfoId);
    }

    @Override
    public boolean deleteByFileInfoId(Long id) {
        QueryWrapper<FileUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_info_id", id);
        return super.remove(queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteBatchByFileInfoId(List<Long> ids) {
        return this.getBaseMapper().deleteBatchByFileInfoId(ids);
    }
}
