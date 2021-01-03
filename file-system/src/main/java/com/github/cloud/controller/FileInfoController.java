package com.github.cloud.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.cloud.dto.request.AddFileInfoRequest;
import com.github.cloud.dto.request.PageFileInfoRequest;
import com.github.cloud.dto.request.UpdateFileInfoRequest;
import com.github.cloud.dto.response.ApiResponse;
import com.github.cloud.dto.response.FileInfoResponse;
import com.github.cloud.entity.FileInfo;
import com.github.cloud.service.FileInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* @author : glw
* @datetime : 2021-01-03 18:17:22
* @description : 文件信息控制层
*/
@Api(value = "文件信息" , tags = {"文件信息"})
@RestController
@RequestMapping("/fileInfo")
public class FileInfoController {

    @Autowired
    private FileInfoService fileInfoService;

    @ApiOperation(value = "分页查询文件信息" , tags = "分页查询")
    @GetMapping("/page")
    public ApiResponse<IPage<FileInfo>> page(PageFileInfoRequest request) {
    IPage<FileInfo> response = fileInfoService.page(request);
        return ApiResponse.success(response);
    }


    @ApiOperation(value = "查询文件信息详情" , tags = "查询详情")
    @GetMapping("/detail/{id}")
    public ApiResponse<FileInfoResponse> detail(@PathVariable("id") Long id) {
        FileInfoResponse response = fileInfoService.detail(id);
        return ApiResponse.success(response);
    }


    @ApiOperation(value = "新增文件信息" , tags = "新增")
    @PostMapping("/save")
    public ApiResponse<Boolean> save(@RequestBody @Valid AddFileInfoRequest request) {
        boolean save = fileInfoService.save(request);
        return ApiResponse.success(save);
    }


    @ApiOperation(value = "更新文件信息" , tags = "更新")
    @PutMapping("/update")
    public ApiResponse<Boolean> update(@RequestBody @Valid UpdateFileInfoRequest request) {
        boolean update = fileInfoService.update(request);
        return ApiResponse.success(update);
    }


    @ApiOperation(value = "删除文件信息" , tags = "删除")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Boolean> delete(@PathVariable("id") Long id) {
        boolean delete = fileInfoService.delete(id);
        return ApiResponse.success(delete);
    }


    @ApiOperation(value = "批量删除文件信息" , tags = "批量删除")
    @DeleteMapping("/delete/batch/{ids}")
    public ApiResponse<Boolean> deleteBatch(@PathVariable("ids") List<Long> ids) {
        boolean delete = fileInfoService.deleteBatch(ids);
        return ApiResponse.success(delete);
    }
}