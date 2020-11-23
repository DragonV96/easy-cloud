package com.github.cloud.exception;

import com.github.cloud.dto.response.ApiResponse;
import com.github.cloud.enums.ErrorCode;
import com.github.cloud.enums.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

/**
 * @author : glw
 * @date : 2020/11/23
 * @time : 23:18
 * @Description : 统一异常处理
 */
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {MultipartException.class})
    public ApiResponse<ErrorType> fileLimitException(MultipartException ex) {
        log.error("Upload file size limit, exception: {}", ex.getMessage());
        return ApiResponse.error(ErrorCode.FILE_LIMIT);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ApiResponse<String> serviceException(MethodArgumentNotValidException ex) {
        log.error("Service method args were invalid, exception:{}", ex.getMessage());
        return ApiResponse.error(ErrorCode.PARAM_INVALID, ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = {Exception.class, Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<ErrorType> exception() {
        return ApiResponse.error();
    }
}
