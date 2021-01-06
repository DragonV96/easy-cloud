package com.github.cloud.enums;

import lombok.Getter;

/**
 * @author : glw
 * @datetime : 2021/1/6 21:44
 * @description : 文件上传类型枚举
 */
@Getter
public enum UploadTypeEnum {
    UPLOAD_ONCE(1, "一次性上传"),
    UPLOAD_SLICE(2, "分片上传"),
    UPLOAD_EXIST(3, "秒传，已存在文件"),
    ;

    /** 上传类型 */
    private Integer type;

    /** 状态描述 */
    private String desc;

    UploadTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
