package com.github.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @author : glw
 * @datetime : 2021-01-05 21:09:18
 * @description : 文件信息表对象
 */
@Data
public class FileInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件id
     */
    @TableId(type = IdType.INPUT)
    private Long fileInfoId;

    /**
     * 文件类型（1图片；2视频；3音频；4文档；5压缩文件；6可执行文件；7其他）
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
     * 上传耗时净值（毫秒）
     */
    private Long duration;

    /**
     * 文件状态（1上传中；2上传完成；3上传异常）
     */
    private Byte fileStatus;

    /**
     * 分片数量
     */
    private Integer chunks;

    /**
     * 当前分片数
     */
    private Integer currentChunk;

    /**
     * 上传开始时间
     */
    private Date uploadStartTime;

    /**
     * 上传结束时间
     */
    private Date uploadEndTime;

}