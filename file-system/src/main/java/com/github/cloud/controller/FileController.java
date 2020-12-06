package com.github.cloud.controller;

import com.github.cloud.config.FastDFSClient;
import com.github.cloud.dto.request.UpdateFileRequest;
import com.github.cloud.dto.response.ApiResponse;
import com.github.tobato.fastdfs.domain.StorePath;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
@RestController
@RequestMapping("/file")
@Api(value = "文件处理接口", tags = "文件处理接口")
public class FileController {

    @Autowired
    private FastDFSClient fastDFSClient;

    @ApiOperation(value = "文件上传", tags = "文件上传")
    @ApiImplicitParams({@ApiImplicitParam(name = "file", value = "文件流对象,接收数组格式", dataType = "multipart/form-data")}
    )
    @PostMapping("/upload")
    public ApiResponse upload(@RequestParam(value = "file") MultipartFile file) {
        StorePath storePath = fastDFSClient.uploadFile(file);
        log.info("FileController upload, storePath = {}", storePath);
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
    public ApiResponse uploadBatch(MultipartFile[] files) {
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
