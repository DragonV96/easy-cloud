package com.github.cloud.exception;

import com.github.cloud.dto.response.ApiResponse;
import com.github.cloud.enums.ErrorCode;
import com.github.cloud.enums.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

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

    private static final String ERROR_DESC_BEGIN = "[P]";

    @ExceptionHandler(value = {GlobalException.class})
    public ApiResponse<String> globalException(HttpServletRequest request, GlobalException ex) {
        ErrorType errorType = ex.getErrorType();
        log.error("Request path: {}, error code description: {}, error code: {},  exception description: {}, exception cause: {}",
                request.getRequestURI(), errorType.getMsg(), errorType.getCode(), ex.getMessage(), ex.getCause());
        return ApiResponse.error(errorType, ex.getMessage());
    }

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public ApiResponse<String> missingParameterException(MissingServletRequestParameterException ex) {
        log.error("Missing servlet request parameter, exception: {}", ex.getMessage());
        StringBuilder errorInfo = new StringBuilder();
        errorInfo.append(ex.getMessage()).append(FIELD_DESC).append(ex.getParameterName()).append(FIELD_TYPE).append(ex.getParameterType());
        return ApiResponse.error(ErrorCode.PARAM_EMPTY, errorInfo.toString());
    }

    @ExceptionHandler(value = {MultipartException.class})
    public ApiResponse<String> fileLimitException(MultipartException ex) {
        log.error("Upload file size limit, exception: {}", ex.getMessage());
        return ApiResponse.error(ErrorCode.FILE_LIMIT);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ApiResponse<String> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("Service method args of interface were invalid, exception: {}", ex.getMessage());
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        StringBuilder errorInfo = new StringBuilder();
        if (!CollectionUtils.isEmpty(fieldErrors)) {
            fieldErrors.forEach(fieldError -> {
                errorInfo.append(fieldError.getDefaultMessage()).append(FIELD_DESC).append(fieldError.getField());
            });
        }
        return ApiResponse.error(ErrorCode.PARAM_INVALID, errorInfo.toString());
    }

    @ExceptionHandler(value = {DuplicateKeyException.class})
    public ApiResponse<String> duplicateKeyException(DuplicateKeyException ex) {
        log.error("Primary key duplication, exception: {}", ex.getMessage());
        return ApiResponse.error(ErrorCode.DATABASE_DUPLICATE_KEY, ex.getMessage());
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ApiResponse<String> constraintViolationException(ConstraintViolationException ex) {
        log.error("Invalid request parameters, exception: {}", ex.getMessage());
        return ApiResponse.error(ErrorCode.PARAM_INVALID, ex.getMessage());
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ApiResponse<String> illegalArgumentException(IllegalArgumentException ex) {
        log.error("Illegal args of request parameters, exception: {}", ex.getMessage());
        return ApiResponse.error(ErrorCode.PARAM_ILLEGAL, ex.getMessage());
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ApiResponse<String> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("Mismatch args of request parameters and unable to deserialize, exception: {}", ex.getMessage());
        return ApiResponse.error(ErrorCode.PARAM_NOT_MATCH, ex.getMessage());
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ApiResponse<String> dataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.error("Violated Database fields constraints, exception: {}", ex.getMessage());
        return ApiResponse.error(ErrorCode.VIOLATE_FIELD_CONSTRAINTS, ex.getMessage());
    }

    @ExceptionHandler(value = {PersistenceException.class})
    public ApiResponse<String> persistenceException(PersistenceException ex) {
        String localizedMessage = ex.getLocalizedMessage();
        log.error("Database persistence error, exception: {}", localizedMessage);
        return ApiResponse.error(ErrorCode.DATABASE_PERSISTENCE_ERROR,
                ex.getMessage().substring(localizedMessage.lastIndexOf(ERROR_DESC_BEGIN) + ERROR_DESC_BEGIN.length()).trim());
    }

    @ExceptionHandler(value = {Exception.class, Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<String> exception(Exception ex) {
        log.error("Service error, exception type : {}, exception message: {}", ex.getClass(), ex.getMessage());
        ex.printStackTrace();
        return ApiResponse.error();
    }
}
