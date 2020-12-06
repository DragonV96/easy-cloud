package com.github.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.cloud.dto.request.AddDemo;
import com.github.cloud.dto.request.PageDemo;
import com.github.cloud.dto.request.UpdateDemo;
import com.github.cloud.dto.response.DemoResponse;
import com.github.cloud.entity.Demo;
import com.github.cloud.mapper.DemoMapper;
import com.github.cloud.service.DemoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : glw
 * @date : 2020/12/4
 * @time : 23:40
 * @Description : 业务层实现类
 */
@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements DemoService {

    @Override
    public IPage<Demo> page(PageDemo request) {
        IPage<Demo> page = new Page<>(request.getCurrent(), request.getSize());
        Demo entity = new Demo();
        BeanUtils.copyProperties(request, entity);
        return super.page(page, new QueryWrapper<>(entity));
    }

    @Override
    public DemoResponse detail(Long id) {
        Demo entity = super.getById(id);
        DemoResponse response = new DemoResponse();
        BeanUtils.copyProperties(response, entity);
        return response;
    }

    @Override
    public boolean save(AddDemo request) {
        Demo entity = new Demo();
        BeanUtils.copyProperties(request, entity);
        return super.save(entity);
    }

    @Override
    public boolean update(UpdateDemo request) {
        Demo entity = new Demo();
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
}
