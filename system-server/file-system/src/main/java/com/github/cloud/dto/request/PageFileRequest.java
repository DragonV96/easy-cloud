package com.github.cloud.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
* @author : glw
* @datetime : 2021-01-05 21:09:18
* @description : 文件信息分页查询请求对象
*/
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "文件信息新增请求对象")
public class PageFileRequest extends PageRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件类型（1图片；2视频；3音频；4文档；5压缩文件；6可执行文件；7其他）", name = "fileType")
    private Byte fileType;

    @ApiModelProperty(value = "文件大小（字节）", name = "fileSize")
    private Long fileSize;

    @ApiModelProperty(value = "上传耗时净值（毫秒）", name = "duration")
    private Long duration;

    @ApiModelProperty(value = "文件状态（1上传中；2上传完成；3上传异常）", name = "fileStatus")
    private Byte fileStatus;

    @ApiModelProperty(value = "上传开始时间", name = "uploadStartTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadStartTime;

    @ApiModelProperty(value = "上传结束时间", name = "uploadEndTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadEndTime;

}