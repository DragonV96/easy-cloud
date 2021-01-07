package com.github.cloud.exception;

import com.github.cloud.dto.response.ApiResponse;
import com.github.cloud.enums.ErrorCode;
import com.github.cloud.enums.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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

    private static final String FIELD_DESC = "，字段是";
    private static final String FIELD_TYPE = "，字段类型是";

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public ApiResponse missingParameterException(MissingServletRequestParameterException ex) {
        log.error("Missing servlet request parameter, exception: {}", ex.getMessage());
        StringBuilder errorInfo = new StringBuilder();
        errorInfo.append(ex.getMessage()).append(FIELD_DESC).append(ex.getParameterName()).append(FIELD_TYPE).append(ex.getParameterType());
        return ApiResponse.error(ErrorCode.PARAM_EMPTY, errorInfo.toString());
    }

    @ExceptionHandler(value = {MultipartException.class})
    public ApiResponse<ErrorType> fileLimitException(MultipartException ex) {
        log.error("Upload file size limit, exception: {}", ex.getMessage());
        return ApiResponse.error(ErrorCode.FILE_LIMIT);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ApiResponse<String> serviceException(MethodArgumentNotValidException ex) {
        log.error("Service method args were invalid, exception: {}", ex.getMessage());
        FieldError fieldError = ex.getBindingResult().getFieldError();
        StringBuilder errorInfo = new StringBuilder();
        errorInfo.append(fieldError.getDefaultMessage()).append(FIELD_DESC).append(fieldError.getField());
        return ApiResponse.error(ErrorCode.PARAM_INVALID, errorInfo.toString());
    }

    @ExceptionHandler(value = {DuplicateKeyException.class})
    public ApiResponse duplicateKeyException(DuplicateKeyException ex) {
        log.error("primary key duplication exception: {}", ex.getMessage());
        return ApiResponse.error(ErrorCode.DATABASE_DUPLICATE_KEY);
    }

    @ExceptionHandler(value = {Exception.class, Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<ErrorType> exception(Exception ex) {
        log.error("Service error, exception: {}", ex.getMessage());
        return ApiResponse.error();
    }
}
