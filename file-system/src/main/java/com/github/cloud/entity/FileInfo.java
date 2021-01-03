package com.github.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


/**
* @author : glw
* @datetime : 2021-01-03 18:17:22
* @description : 文件信息表对象
*/
@EqualsAndHashCode
@Data
@ToString
public class FileInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件id
     */
    @TableId(type = IdType.INPUT)
    private Long fileInfoId;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件类型（1图片 2视频 3音频 4文档 5压缩文件 6可执行文件 7其他）
     */
    private Byte fileType;

    /**
     * fastDFS文件组
     */
    private String dfsGroup;

    /**
     * fastDFS文件地址
     */
    private String dfsPath;

    /**
     * 文件MD5
     */
    private String fileHash;

    /**
     * 已上传文件大小（字节）
     */
    private Long uploadedFileSize;

    /**
     * 文件大小（字节）
     */
    private Long fileSize;

    /**
     * 上传文件的用户名id
     */
    private Long uploaderId;

    /**
     * 上传耗时净值（毫秒）
     */
    private Long duration;

    /**
     * 上传开始时间
     */
    private Date uploadStartTime;

    /**
     * 上传结束时间
     */
    private Date uploadEndTime;

}