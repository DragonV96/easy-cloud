package com.github.cloud.enums;

import lombok.Getter;

/**
 * @author : glw
 * @date : 2020/11/22
 * @time : 15:33
 * @Description : 通用错误码枚举
 */
@Getter
public enum  ErrorCode implements ErrorType{

    ERROR_SYSTEM(1000001, "系统错误，请稍后重试！"),
    ERROR_NETWORK(1000002, "网络错误，请稍后重试！"),
    ERROR_BUSY(1000003, "系统繁忙，请稍后重试！"),

    SERVER_NOT_EXIST(1000101, "没有此服务，请联系管理员！"),
    SERVER_TIMEOUT(1000102, "服务超时，请稍后重试！"),

    PARAM_EMPTY(1000201, "请求参数为空，请确认参数后重试！"),
    PARAM_INVALID(1000202, "请求参数校验不通过，请确认参数正常后重试！"),
    PARAM_ERROR(1000203, "请求参数错误，请确认参数无误后重试！"),

    DATABASE_DUPLICATE_KEY(1000301, "数据库主键冲突，请联系管理员！"),

    FILE_LIMIT(1000401, "上传文件超过限制大小，请重新上传！"),
    ;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误码描述
     */
    private String msg;

    ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
