package com.github.cloud.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
* @author : glw
* @datetime : 2021-01-04 14:18:22
* @description : 文件状态新增请求对象
*/
@EqualsAndHashCode
@Data
@ToString
@ApiModel(value = "文件状态新增请求对象")
public class UpdateFileStatusRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件状态id", name = "fileStatusId", required = true)
    @NotNull(message = "文件状态id不能为空")
    private Integer fileStatusId;

    @ApiModelProperty(value = "文件id", name = "fileInfoId", required = true)
    @NotNull(message = "文件id不能为空")
    private Long fileInfoId;

    @ApiModelProperty(value = "文件状态（1初始化 2上传中 3上传完成 4上传异常）", name = "fileStatus", required = true)
    @NotNull(message = "文件状态不能为空")
    private Byte fileStatus;

    @ApiModelProperty(value = "分片数量", name = "chunks")
    private Integer chunks;

    @ApiModelProperty(value = "当前分片数", name = "currentChunk")
    private Integer currentChunk;

}