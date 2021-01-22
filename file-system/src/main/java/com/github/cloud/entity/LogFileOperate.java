package com.github.cloud.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
* @author : glw
* @datetime : 2021-01-05 21:09:18
* @description : 文件操作日志表对象
*/
@Data
public class LogFileOperate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件操作日志id
     */
    @TableId(type = IdType.AUTO)
    private Long logFileOperateId;

    /**
     * 文件id
     */
    private Long fileInfoId;

    /**
     * 文件操作类型（1上传；2下载；3更新；4删除）
     */
    private Byte operateType;

    /**
     * 操作人id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 操作时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}