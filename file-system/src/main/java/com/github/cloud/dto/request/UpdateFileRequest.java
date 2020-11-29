package com.github.cloud.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @author : glw
 * @date : 2020/11/29
 * @time : 16:22
 * @Description : 更新文件信息请求对象
 */
@EqualsAndHashCode
@Data
@ToString
@ApiModel(value = "更新文件信息请求对象")
public class UpdateFileRequest {

    @ApiModelProperty(value = "文件 id", name = "fileId", required = true)
    @NotNull
    private Long fileId;

    @ApiModelProperty(value = "文件名", name = "fileName")
    private Long fileName;

    @ApiModelProperty(value = "文件类型（0图片 1视频 2音频 3文档 4压缩文件 5可执行文件 6文件夹 7其他）", name = "fileType")
    private Byte fileType;

    @ApiModelProperty(value = "文件状态（0初始化 1上传中 2上传完成 3上传异常）", name = "fileStatus")
    private Byte fileStatus;

    @ApiModelProperty(value = "fastDFS 文件组", name = "group")
    @NotBlank(message = "fastDFS 文件组不能为空")
    private String group;

    @ApiModelProperty(value = "fastDFS 文件地址", name = "storagePath")
    private String storagePath;

    @ApiModelProperty(value = "文件MD5", name = "fileHash")
    private String fileHash;

    @ApiModelProperty(value = "分片数量", name = "chunks")
    private Integer chunks;

    @ApiModelProperty(value = "当前分片数", name = "currentChunk")
    private Integer currentChunk;

    @ApiModelProperty(value = "文件大小（字节）", name = "fileSize")
    private Long fileSize;
}
