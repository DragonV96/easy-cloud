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
* @datetime : 2021-01-03 18:17:23
* @description : 文件状态分页查询请求对象
*/
@EqualsAndHashCode
@Data
@ToString
@ApiModel(value = "文件状态新增请求对象")
public class PageFileStatusRequest extends PageRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件状态id", name = "fileStatusId")
    @NotNull(message = "文件状态id不能为空")
    private Integer fileStatusId;

    @ApiModelProperty(value = "文件id", name = "fileInfoId")
    @NotNull(message = "文件id不能为空")
    private Long fileInfoId;

    @ApiModelProperty(value = "文件状态（1初始化 2上传中 3上传完成 4上传异常）", name = "fileStatus")
    @NotNull(message = "文件状态不能为空")
    private Byte fileStatus;

    @ApiModelProperty(value = "分片数量", name = "chunks")
    private Integer chunks;

    @ApiModelProperty(value = "当前分片数", name = "currentChunk")
    private Integer currentChunk;

}