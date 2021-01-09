package com.github.cloud.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.cloud.dto.request.AddFileRequest;
import com.github.cloud.dto.request.PageFileInfoRequest;
import com.github.cloud.dto.request.QueryFileRequest;
import com.github.cloud.dto.request.UpdateFileRequest;
import com.github.cloud.dto.response.ApiResponse;
import com.github.cloud.dto.response.FileInfoResponse;
import com.github.cloud.entity.FileInfo;
import com.github.cloud.service.FileInfoService;
import com.github.cloud.service.FileService;
import com.github.cloud.service.FileUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
* @author : glw
* @datetime : 2021-01-03 18:17:22
* @description : 文件操作控制层
*/
@Api(value = "文件操作" , tags = {"文件操作"})
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private FileInfoService fileInfoService;

    @Autowired
    private FileUserService fileUserService;

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

    @ApiOperation(value = "上传文件查重" , tags = "查询")
    @GetMapping("/upload/check")
    public ApiResponse<Boolean> uploadCheck(QueryFileRequest request) {
        boolean upload = fileService.uploadCheck(request);
        return ApiResponse.success(upload);
    }

    @ApiOperation(value = "上传文件" , tags = "新增")
    @PostMapping("/upload")
    public ApiResponse<Boolean> upload(@RequestBody MultipartFile file, @RequestBody @Valid AddFileRequest request) {
        boolean upload = fileService.upload(file, request);
        return ApiResponse.success(upload);
    }

    @ApiOperation(value = "下载文件" , tags = "更新")
    @PostMapping("/download/{id}")
    public ApiResponse<Boolean> download(@PathVariable("id") Long id) {
        boolean download = fileService.download(id);
        return ApiResponse.success(download);
    }

    @ApiOperation(value = "更新文件信息" , tags = "更新")
    @PutMapping("/update")
    public ApiResponse<Boolean> update(@RequestBody @Valid UpdateFileRequest request) {
        boolean update = fileUserService.update(request);
        return ApiResponse.success(update);
    }

    @ApiOperation(value = "删除文件信息" , tags = "删除")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Boolean> delete(@PathVariable("id") Long id) {
        boolean delete = fileService.delete(id);
        return ApiResponse.success(delete);
    }

    @ApiOperation(value = "批量删除文件信息" , tags = "批量删除")
    @DeleteMapping("/delete/batch/{ids}")
    public ApiResponse<Boolean> deleteBatch(@PathVariable("ids") List<Long> ids) {
        boolean delete = fileService.deleteBatch(ids);
        return ApiResponse.success(delete);
    }
}