package com.github.cloud.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;


/**
* @author : glw
* @datetime : 2021-01-03 18:17:23
* @description : 文件状态响应对象
*/
@EqualsAndHashCode
@Data
@ToString
@ApiModel(value = "文件状态响应对象")
public class FileStatusResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件状态id", name = "fileStatusId")
    private Integer fileStatusId;

    @ApiModelProperty(value = "文件id", name = "fileInfoId")
    private Long fileInfoId;

    @ApiModelProperty(value = "文件状态（1初始化 2上传中 3上传完成 4上传异常）", name = "fileStatus")
    private Byte fileStatus;

    @ApiModelProperty(value = "分片数量", name = "chunks")
    private Integer chunks;

    @ApiModelProperty(value = "当前分片数", name = "currentChunk")
    private Integer currentChunk;

    @ApiModelProperty(value = "下载次数", name = "downloadCount")
    private Integer downloadCount;

}