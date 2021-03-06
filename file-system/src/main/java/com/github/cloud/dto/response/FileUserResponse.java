package com.github.cloud.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * @author : glw
 * @datetime : 2021-01-05 21:09:18
 * @description : 用户文件响应对象
 */
@Data
@ApiModel(value = "用户文件响应对象")
public class FileUserResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户文件主键id", name = "fileUserId")
    private Long fileUserId;

    @ApiModelProperty(value = "文件id", name = "fileInfoId")
    private Long fileInfoId;

    @ApiModelProperty(value = "文件名", name = "fileName")
    private String fileName;

    @ApiModelProperty(value = "上传文件的用户名id", name = "uploaderId")
    private Long uploaderId;

    @ApiModelProperty(value = "下载次数", name = "downloadCount")
    private Integer downloadCount;

    @ApiModelProperty(value = "创建人id", name = "createBy")
    private Long createBy;

    @ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;

    @ApiModelProperty(value = "更新人id", name = "updateBy")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间", name = "updateTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}