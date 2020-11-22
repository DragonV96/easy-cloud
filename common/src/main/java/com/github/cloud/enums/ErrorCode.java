package com.github.cloud.enums;

import lombok.Getter;

/**
 * @author : glw
 * @date : 2020/11/22
 * @time : 15:33
 * @Description : 通用错误码枚举
 */
@Getter
public enum  ErrorCode {

    ERROR_SYSTEM(1000001, "系统错误，请稍后重试！")
    ;

    private Integer code;
    private String msg;

    ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
