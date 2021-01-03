package com.github.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;


/**
* @author : glw
* @datetime : 2021-01-03 18:17:23
* @description : 文件状态表对象
*/
@EqualsAndHashCode
@Data
@ToString
public class FileStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件状态id
     */
    @TableId(type = IdType.AUTO)
    private Integer fileStatusId;

    /**
     * 文件id
     */
    private Long fileInfoId;

    /**
     * 文件状态（1初始化 2上传中 3上传完成 4上传异常）
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

}