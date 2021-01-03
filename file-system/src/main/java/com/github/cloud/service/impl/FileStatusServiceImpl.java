package com.github.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.cloud.dto.request.AddFileStatusRequest;
import com.github.cloud.dto.request.PageFileStatusRequest;
import com.github.cloud.dto.request.UpdateFileStatusRequest;
import com.github.cloud.dto.response.FileStatusResponse;
import com.github.cloud.entity.FileStatus;
import com.github.cloud.mapper.FileStatusMapper;
import com.github.cloud.service.FileStatusService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* @author : glw
* @datetime : 2021-01-03 18:17:23
* @description : 文件状态业务层实现
*/
@Service
public class FileStatusServiceImpl extends ServiceImpl<FileStatusMapper, FileStatus> implements FileStatusService {

    @Override
    public IPage<FileStatus> page(PageFileStatusRequest request) {
        IPage<FileStatus> page = new Page<>(request.getCurrent(), request.getSize());
        FileStatus entity = new FileStatus();
        BeanUtils.copyProperties(request, entity);
        return super.page(page, new QueryWrapper<>(entity));
    }

    @Override
    public FileStatusResponse detail(Integer id) {
        FileStatus entity = super.getById(id);
        FileStatusResponse response = new FileStatusResponse();
        BeanUtils.copyProperties(response, entity);
        return response;
    }

    @Override
    public boolean save(AddFileStatusRequest request) {
        FileStatus entity = new FileStatus();
        BeanUtils.copyProperties(request, entity);
        return super.save(entity);
    }

    @Override
    public boolean update(UpdateFileStatusRequest request) {
        FileStatus entity = new FileStatus();
        BeanUtils.copyProperties(request, entity);
        return super.updateById(entity);
    }

    @Override
    public boolean delete(Integer id) {
        return super.removeById(id);
    }

    @Override
    public boolean deleteBatch(List<Integer> ids) {
        return super.removeByIds(ids);
    }
}
