package com.github.cloud.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


/**
* @author : glw
* @datetime : 2021-01-03 18:17:22
* @description : 文件信息新增请求对象
*/
@EqualsAndHashCode
@Data
@ToString
@ApiModel(value = "文件信息新增请求对象")
public class UpdateFileInfoRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件id", name = "fileInfoId")
    @NotNull(message = "文件id不能为空")
    private Long fileInfoId;

    @ApiModelProperty(value = "文件名", name = "fileName")
    @NotBlank(message = "文件名不能为空")
    private String fileName;

    @ApiModelProperty(value = "文件类型（1图片 2视频 3音频 4文档 5压缩文件 6可执行文件 7其他）", name = "fileType")
    @NotNull(message = "文件类型不能为空")
    private Byte fileType;

    @ApiModelProperty(value = "fastDFS文件组", name = "dfsGroup")
    private String dfsGroup;

    @ApiModelProperty(value = "fastDFS文件地址", name = "dfsPath")
    private String dfsPath;

    @ApiModelProperty(value = "文件MD5", name = "fileHash")
    @NotBlank(message = "文件MD5不能为空")
    private String fileHash;

    @ApiModelProperty(value = "已上传文件大小（字节）", name = "uploadedFileSize")
    private Long uploadedFileSize;

    @ApiModelProperty(value = "文件大小（字节）", name = "fileSize")
    private Long fileSize;

    @ApiModelProperty(value = "上传文件的用户名id", name = "uploaderId")
    @NotNull(message = "上传文件的用户名id不能为空")
    private Long uploaderId;

    @ApiModelProperty(value = "上传耗时净值（毫秒）", name = "duration")
    private Long duration;

    @ApiModelProperty(value = "上传开始时间", name = "uploadStartTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "上传开始时间不能为空")
    private Date uploadStartTime;

    @ApiModelProperty(value = "上传结束时间", name = "uploadEndTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "上传结束时间不能为空")
    private Date uploadEndTime;

}