package com.github.cloud.exception;


import com.github.cloud.enums.ErrorCode;
import com.github.cloud.enums.ErrorType;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author : glw
 * @date : 2020/11/23
 * @time : 23:17
 * @Description : 统一异常处理
 */
@Getter
public class GlobalException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    private final ErrorType errorType;

    public GlobalException(String message) {
        super(message);
        this.errorType = ErrorCode.ERROR_SYSTEM;
    }

    public GlobalException(ErrorType errorType) {
        super(errorType.getMsg());
        this.errorType = errorType;
    }

    public GlobalException(ErrorType errorType, String message) {
        super(errorType.getMsg());
        this.errorType = errorType;
    }

    public GlobalException(ErrorType errorType, String message, Throwable throwable) {
        super(errorType.getMsg(), throwable);
        this.errorType = errorType;
    }

}
