package com.github.cloud.enums;

import lombok.Getter;

/**
 * @author : glw
 * @datetime : 2021/1/3 23:33
 * @Description : 文件类型枚举
 */
@Getter
public enum FileTypeEnum {

    IMAGE((byte)1, "图片"),
    VIDEO((byte)2, "视频"),
    AUDIO((byte)3, "音频"),
    DOC((byte)4, "文档"),
    COMPRESSION((byte)5, "压缩文件"),
    EXECUTION((byte)6, "可执行文件"),
    OTHERS((byte)7, "其他");

    /** 文件类型 */
    private Byte status;

    /** 状态描述 */
    private String desc;

    FileTypeEnum(Byte status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
