package com.github.cloud.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

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

    @ApiModelProperty(value = "文件名", name = "fileName", required = true)
    @NotBlank(message = "文件名不能为空")
    @Max(value = 100, message = "文件名超过长度限制，最大长度为100个字节")
    private String fileName;

    @ApiModelProperty(value = "上传文件的用户名id", name = "uploaderId", required = true)
    @NotNull(message = "上传文件的用户名id不能为空")
    private Long uploaderId;

    @ApiModelProperty(value = "文件MD5", name = "fileHash", required = true)
    @NotBlank(message = "文件MD5不能为空")
    @Max(value = 32, message = "文件MD5超过长度限制，最大长度为32个字节")
    private String fileHash;

    @ApiModelProperty(value = "分片文件大小（字节）", name = "chunkFileSize", required = true)
    @NotNull(message = "分片文件大小不能为空")
    private Long chunkFileSize;

    @ApiModelProperty(value = "文件大小（字节）", name = "fileSize", required = true)
    @NotNull(message = "文件大小不能为空")
    private Long fileSize;

    @ApiModelProperty(value = "分片数量", name = "chunks", required = true)
    @NotNull(message = "分片数量不能为空")
    private Integer chunks;

    @ApiModelProperty(value = "当前分片数", name = "currentChunk", required = true)
    @NotNull(message = "当前分片数不能为空")
    private Integer currentChunk;

    @ApiModelProperty(value = "上传类型（1一次性上传；2分片上传；3秒传）", name = "uploadType", required = true)
    @NotNull(message = "上传类型不能为空")
    private Integer uploadType;

    @ApiModelProperty(value = "文件id", name = "fileInfoId", hidden = true)
    private Long fileInfoId;

    @ApiModelProperty(value = "文件类型（1图片；2视频；3音频；4文档；5压缩文件；6可执行文件；7其他）", name = "fileType", hidden = true)
    private Byte fileType;

    @ApiModelProperty(value = "fastDFS文件组", name = "dfsGroup", hidden = true)
    private String dfsGroup;

    @ApiModelProperty(value = "fastDFS文件地址", name = "dfsPath", hidden = true)
    private String dfsPath;

    @ApiModelProperty(value = "文件状态（1上传中；2上传完成；3上传异常）", name = "fileStatus", hidden = true)
    private Byte fileStatus;

    @ApiModelProperty(value = "已上传文件大小（字节）", name = "uploadedFileSize", hidden = true)
    private Long uploadedFileSize;

    @ApiModelProperty(value = "上传耗时净值（毫秒）", name = "duration", hidden = true)
    private Long duration;

    @ApiModelProperty(value = "上传开始时间", name = "uploadStartTime", hidden = true)
    private Date uploadStartTime;

    @ApiModelProperty(value = "上传结束时间", name = "uploadEndTime", hidden = true)
    private Date uploadEndTime;
}
