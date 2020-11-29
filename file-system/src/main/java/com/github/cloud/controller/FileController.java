package com.github.cloud.controller;

import com.github.cloud.dto.request.UpdateFileRequest;
import com.github.cloud.dto.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : glw
 * @date : 2020/11/24
 * @time : 22:36
 * @Description : 文件处理接口
 */
@RestController
@RequestMapping("/file")
@Api(value = "文件处理接口", tags = "文件处理接口")
public class FileController {

    @ApiOperation(value = "文件上传", tags = "文件上传")
    @PostMapping("/upload")
    public ApiResponse upload(@RequestBody MultipartFile file) {
        return ApiResponse.success();
    }

    @ApiOperation(value = "删除文件", tags = "删除文件")
    @DeleteMapping("/delete")
    public ApiResponse delete(@RequestParam("fileId") Long fileId) {
        return ApiResponse.success();
    }

    @ApiOperation(value = "修改文件信息", tags = "修改文件信息")
    @PutMapping("/update")
    public ApiResponse update(@RequestBody @Valid UpdateFileRequest request) {
        return ApiResponse.success();
    }

    @ApiOperation(value = "文件下载", tags = "文件下载")
    @GetMapping("/download/{fileId}")
    public ApiResponse download(@PathVariable("fileId") Long fileId) {
        return ApiResponse.success();
    }

    @ApiOperation(value = "批量文件上传", tags = "批量文件上传")
    @PostMapping("/upload/batch")
    public ApiResponse uploadBatch(@RequestBody MultipartFile[] files) {
        return ApiResponse.success();
    }

    @ApiOperation(value = "批量删除文件", tags = "批量删除文件")
    @DeleteMapping("/delete/batch")
    public ApiResponse deleteBatch(@RequestParam("fileIds") List<Long> fileIds) {
        return ApiResponse.success();
    }

    @ApiOperation(value = "批量文件下载", tags = "批量文件下载")
    @GetMapping("/download/batch/{fileIds}")
    public ApiResponse downloadBatch(@PathVariable("fileIds") List<Long> fileIds) {
        return ApiResponse.success();
    }
}
