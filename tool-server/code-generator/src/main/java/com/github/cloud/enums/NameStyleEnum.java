package com.github.cloud.enums;

import lombok.Getter;

/**
 * @author : glw
 * @date : 2020/12/13
 * @time : 14:40
 * @Description : 命名风格枚举
 */
@Getter
public enum NameStyleEnum {
    ALL_LOWER_TO_CAMEL(1, "全小写下划线转首字母驼峰"),
    ALL_UPPER_TO_CAMEL(2, "全小写下划线转首字母驼峰"),
    DO_NOTHING(3, "原本就是首字母驼峰不转换"),
    ;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 描述
     */
    private String desc;

    NameStyleEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
