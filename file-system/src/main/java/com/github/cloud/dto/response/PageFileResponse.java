package com.github.cloud.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
* @author : glw
* @datetime : 2021-01-12 00:09:18
* @description : 文件分页响应对象
*/
@Data
@ApiModel(value = "文件分页响应对象")
public class PageFileResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户文件主键id", name = "fileUserId")
    private Long fileUserId;

    @ApiModelProperty(value = "文件名", name = "fileName")
    private String fileName;

    @ApiModelProperty(value = "文件id", name = "fileInfoId")
    private Long fileInfoId;

    @ApiModelProperty(value = "文件类型（1图片；2视频；3音频；4文档；5压缩文件；6可执行文件；7其他）", name = "fileType")
    private Byte fileType;

    @ApiModelProperty(value = "fastDFS文件组", name = "dfsGroup")
    private String dfsGroup;

    @ApiModelProperty(value = "fastDFS文件地址", name = "dfsPath")
    private String dfsPath;

    @ApiModelProperty(value = "文件MD5", name = "fileHash")
    private String fileHash;

    @ApiModelProperty(value = "已上传文件大小（字节）", name = "uploadedFileSize")
    private Long uploadedFileSize;

    @ApiModelProperty(value = "文件大小（字节）", name = "fileSize")
    private Long fileSize;

    @ApiModelProperty(value = "上传耗时净值（毫秒）", name = "duration")
    private Long duration;

    @ApiModelProperty(value = "文件状态（1上传中；2上传完成；3上传异常）", name = "fileStatus")
    private Byte fileStatus;

    @ApiModelProperty(value = "分片数量", name = "chunks")
    private Integer chunks;

    @ApiModelProperty(value = "当前分片数", name = "currentChunk")
    private Integer currentChunk;

    @ApiModelProperty(value = "上传开始时间", name = "uploadStartTime")
    private Date uploadStartTime;

    @ApiModelProperty(value = "上传结束时间", name = "uploadEndTime")
    private Date uploadEndTime;

}