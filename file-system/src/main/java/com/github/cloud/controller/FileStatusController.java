package com.github.cloud.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.cloud.dto.request.AddFileStatusRequest;
import com.github.cloud.dto.request.PageFileStatusRequest;
import com.github.cloud.dto.request.UpdateFileStatusRequest;
import com.github.cloud.dto.response.ApiResponse;
import com.github.cloud.dto.response.FileStatusResponse;
import com.github.cloud.entity.FileStatus;
import com.github.cloud.service.FileStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* @author : glw
* @datetime : 2021-01-03 18:17:23
* @description : 文件状态控制层
*/
@Api(value = "文件状态" , tags = {"文件状态"})
@RestController
@RequestMapping("/fileStatus")
public class FileStatusController {

    @Autowired
    private FileStatusService fileStatusService;

    @ApiOperation(value = "分页查询文件状态" , tags = "分页查询")
    @GetMapping("/page")
    public ApiResponse<IPage<FileStatus>> page(PageFileStatusRequest request) {
    IPage<FileStatus> response = fileStatusService.page(request);
        return ApiResponse.success(response);
    }


    @ApiOperation(value = "查询文件状态详情" , tags = "查询详情")
    @GetMapping("/detail/{id}")
    public ApiResponse<FileStatusResponse> detail(@PathVariable("id") Integer id) {
        FileStatusResponse response = fileStatusService.detail(id);
        return ApiResponse.success(response);
    }


    @ApiOperation(value = "新增文件状态" , tags = "新增")
    @PostMapping("/save")
    public ApiResponse<Boolean> save(@RequestBody @Valid AddFileStatusRequest request) {
        boolean save = fileStatusService.save(request);
        return ApiResponse.success(save);
    }


    @ApiOperation(value = "更新文件状态" , tags = "更新")
    @PutMapping("/update")
    public ApiResponse<Boolean> update(@RequestBody @Valid UpdateFileStatusRequest request) {
        boolean update = fileStatusService.update(request);
        return ApiResponse.success(update);
    }


    @ApiOperation(value = "删除文件状态" , tags = "删除")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Boolean> delete(@PathVariable("id") Integer id) {
        boolean delete = fileStatusService.delete(id);
        return ApiResponse.success(delete);
    }


    @ApiOperation(value = "批量删除文件状态" , tags = "批量删除")
    @DeleteMapping("/delete/batch/{ids}")
    public ApiResponse<Boolean> deleteBatch(@PathVariable("ids") List<Integer> ids) {
        boolean delete = fileStatusService.deleteBatch(ids);
        return ApiResponse.success(delete);
    }
}