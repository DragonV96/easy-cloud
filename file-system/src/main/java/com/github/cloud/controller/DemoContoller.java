package com.github.cloud.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.cloud.dto.request.AddDemo;
import com.github.cloud.dto.request.PageDemo;
import com.github.cloud.dto.request.UpdateDemo;
import com.github.cloud.dto.response.ApiResponse;
import com.github.cloud.dto.response.DemoResponse;
import com.github.cloud.entity.Demo;
import com.github.cloud.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : glw
 * @date : 2020/12/6
 * @time : 15:13
 * @Description : demo控制层
 */
@Api(value = "demo", tags = {"demo"})
@RestController
@RequestMapping("/demo")
public class DemoContoller {

    @Autowired
    private DemoService demoService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询xxx", tags = "分页查询")
    public ApiResponse<IPage<Demo>> page(PageDemo request){
        IPage<Demo> response = demoService.page(request);
        return ApiResponse.success(response);
    }

    /**
     * 根据主键 id 查询xxx详情
     * @param id
     * @return
     */
    public ApiResponse<DemoResponse> detail(Long id){
        DemoResponse response = demoService.detail(id);
        return ApiResponse.success(response);
    }

    /**
     * 新增xxx
     * @param request
     * @return
     */
    public ApiResponse<Boolean> save(AddDemo request){
        boolean save = demoService.save(request);
        return ApiResponse.success(save);
    }

    /**
     * 更新xxx
     * @param request
     * @return
     */
    public ApiResponse<Boolean> update(UpdateDemo request) {
        boolean update = demoService.update(request);
        return ApiResponse.success(update);
    }

    /**
     * 根据主键 id 删除xxx
     * @param id
     * @return
     */
    public ApiResponse<Boolean> delete(Long id){
        boolean delete = demoService.delete(id);
        return ApiResponse.success(delete);
    }

    /**
     * 根据主键 id 列表批量删除xxx
     * @param ids
     * @return
     */
    public ApiResponse<Boolean> deleteBatch(List<Long> ids){
        boolean delete = demoService.deleteBatch(ids);
        return ApiResponse.success(delete);
    }
}
