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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : glw
 * @date : 2020/12/6
 * @time : 15:13
 * @Description : demo控制层
 */
@Api(value = "demo" , tags = {"demo"})
@RestController
@RequestMapping("/demo")
public class DemoContoller {

    @Autowired
    private DemoService demoService;

    @ApiOperation(value = "分页查询xxx" , tags = "分页查询")
    @GetMapping("/page")
    public ApiResponse<IPage<Demo>> page(PageDemo request) {
        IPage<Demo> response = demoService.page(request);
        return ApiResponse.success(response);
    }

    @ApiOperation(value = "查询xxx详情" , tags = "查询详情")
    @GetMapping("/{id}")
    public ApiResponse<DemoResponse> detail(@PathVariable("id") Long id) {
        DemoResponse response = demoService.detail(id);
        return ApiResponse.success(response);
    }

    @ApiOperation(value = "新增xxx" , tags = "新增")
    @PostMapping
    public ApiResponse<Boolean> save(@RequestBody @Valid AddDemo request) {
        boolean save = demoService.save(request);
        return ApiResponse.success(save);
    }

    @ApiOperation(value = "更新xxx" , tags = "更新")
    @PutMapping
    public ApiResponse<Boolean> update(@RequestBody @Valid UpdateDemo request) {
        boolean update = demoService.update(request);
        return ApiResponse.success(update);
    }

    @ApiOperation(value = "删除xxx" , tags = "删除")
    @DeleteMapping("/{id}")
    public ApiResponse<Boolean> delete(@PathVariable("id") Long id) {
        boolean delete = demoService.delete(id);
        return ApiResponse.success(delete);
    }

    @ApiOperation(value = "批量删除xxx" , tags = "批量删除")
    @DeleteMapping("/batch/{ids}")
    public ApiResponse<Boolean> deleteBatch(@PathVariable("ids") List<Long> ids) {
        boolean delete = demoService.deleteBatch(ids);
        return ApiResponse.success(delete);
    }
}
