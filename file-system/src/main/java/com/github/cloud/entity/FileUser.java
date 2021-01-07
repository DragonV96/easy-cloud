package com.github.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;


/**
 * @author : glw
 * @datetime : 2021-01-05 21:09:18
 * @description : 用户文件表对象
 */
@EqualsAndHashCode
@Data
@ToString
public class FileUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户文件主键id
     */
    @TableId(type = IdType.AUTO)
    private Long fileUserId;

    /**
     * 文件id
     */
    private Long fileInfoId;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 上传文件的用户名id
     */
    private Long uploaderId;

    /**
     * 下载次数
     */
    private Integer downloadCount;

}