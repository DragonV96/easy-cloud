package com.github.cloud.controller;

import com.github.cloud.dto.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "文件上传", tags = "文件上传")
    @PostMapping("/upload")
    public ApiResponse upload() {
        return ApiResponse.success();
    }
}
