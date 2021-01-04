package com.github.cloud.enums;

import lombok.Getter;

/**
 * @author : glw
 * @datetime : 2020/1/3 23:35
 * @Description : 文件状态枚举
 */
@Getter
public enum FileStatusEnum {

    INIT((byte)1, "初始化"),
    UPLOADING((byte)2, "上传中"),
    FINISH((byte)3, "上传完成"),
    EXCEPTION((byte)4, "上传异常");

    /** 文件状态 */
    private Byte status;

    /** 状态描述 */
    private String desc;

    FileStatusEnum(Byte status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
