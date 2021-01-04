package com.github.cloud.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author : glw
 * @datetime : 2021/1/4 19:33
 * @description : 文件新增请求对象
 */
@EqualsAndHashCode
@Data
@ToString
@ApiModel(value = "文件新增请求对象")
public class AddFileRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件状态新增请求对象", name = "fileStatus")
    private AddFileInfoRequest fileInfo;

    @ApiModelProperty(value = "文件状态新增请求对象", name = "fileStatus")
    private AddFileStatusRequest fileStatus;
}
