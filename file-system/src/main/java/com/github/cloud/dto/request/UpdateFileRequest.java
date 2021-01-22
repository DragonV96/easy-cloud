package com.github.cloud.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
* @author : glw
* @datetime : 2021-01-05 21:09:18
* @description : 用户文件新增请求对象
*/
@Data
@ApiModel(value = "用户文件新增请求对象")
public class UpdateFileRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户文件主键id", name = "fileUserId", required = true)
    @NotNull(message = "用户文件主键id不能为空")
    private Long fileUserId;

    @ApiModelProperty(value = "文件名", name = "fileName", required = true)
    @NotBlank(message = "文件名不能为空")
    @Size(max = 100, message = "文件名超过长度限制，最大长度为100个字节")
    private String fileName;

}