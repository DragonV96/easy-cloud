package com.github.cloud.enums;

import lombok.Getter;

/**
 * @author : glw
 * @datetime : 2020/1/5 23:35
 * @Description : 文件操作类型枚举
 */
@Getter
public enum FileOperateEnum {

    UPLOAD((byte)1, "上传"),
    DOWNLOAD((byte)2, "下载"),
    UPDATE((byte)3, "更新"),
    DELETE((byte)4, "删除");

    /** 文件操作类型 */
    private Byte type;

    /** 状态描述 */
    private String desc;

    FileOperateEnum(Byte type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
