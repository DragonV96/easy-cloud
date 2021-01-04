package com.github.cloud.dto.request;

import com.github.cloud.constatnt.FileConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
public class AddFileStatusRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分片数量", name = "chunks")
    private Integer chunks = FileConstant.INIT_CHUNK;

    @ApiModelProperty(value = "当前分片数", name = "currentChunk")
    private Integer currentChunk = FileConstant.INIT_CHUNK;

    @ApiModelProperty(value = "文件id", name = "fileInfoId", hidden = true)
    private Long fileInfoId;

    @ApiModelProperty(value = "文件状态（1初始化 2上传中 3上传完成 4上传异常）", name = "fileStatus", hidden = true)
    private Byte fileStatus;

}