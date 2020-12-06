package com.github.cloud.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.cloud.dto.request.AddDemo;
import com.github.cloud.dto.request.PageDemo;
import com.github.cloud.dto.request.UpdateDemo;
import com.github.cloud.dto.response.DemoResponse;
import com.github.cloud.entity.Demo;

import java.util.List;

/**
 * @author : glw
 * @date : 2020/12/4
 * @time : 23:40
 * @Description : 业务层接口
 */
public interface DemoService extends IService<Demo> {

    /**
     * 分页查询xxx
     * @param request
     * @return
     */
    IPage<Demo> page(PageDemo request);

    /**
     * 根据主键 id 查询xxx详情
     * @param id
     * @return
     */
    DemoResponse detail(Long id);

    /**
     * 新增xxx
     * @param request
     * @return
     */
    boolean save(AddDemo request);

    /**
     * 更新xxx
     * @param request
     * @return
     */
    boolean update(UpdateDemo request);

    /**
     * 根据主键 id 删除xxx
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 根据主键 id 列表批量删除xxx
     * @param ids
     * @return
     */
    boolean deleteBatch(List<Long> ids);
}
