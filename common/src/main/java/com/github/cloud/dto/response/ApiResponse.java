package com.github.cloud.dto.response;

import com.github.cloud.enums.ErrorCode;
import com.github.cloud.enums.ErrorType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author : glw
 * @date : 2020/11/22
 * @time : 13:39
 * @Description : 通用实体返回对象
 */
@EqualsAndHashCode
@ToString
@Data
@ApiModel(value = "通用实体返回对象", description = "通用实体返回对象")
public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "响应码", name = "code")
    private Integer code;

    @ApiModelProperty(value = "响应描述信息", name = "msg")
    private String msg;

    @ApiModelProperty(value = "响应数据", name = "data")
    private T data;

    private static final int SUCCESS_CODE = 200;
    private static final String SUCCESS_MSG = "执行成功";

    private ApiResponse() {
        this.code = SUCCESS_CODE;
        this.msg = SUCCESS_MSG;
    }

    private ApiResponse(T data) {
        this.code = SUCCESS_CODE;
        this.msg = SUCCESS_MSG;
        this.data = data;
    }

    private ApiResponse(ErrorType errorType) {
        this.code = errorType.getCode();
        this.msg = errorType.getMsg();
    }

    private ApiResponse(ErrorType errorType, T data) {
        this.code = errorType.getCode();
        this.msg = errorType.getMsg();
        this.data = data;
    }

    /**
     * 返回成功 + 数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>(data);
    }

    /**
     * 返回成功
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<T>();
    }

    /**
     * 返回失败及对应错误码
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> error(ErrorType errorType) {
        return new ApiResponse<T>(errorType);
    }

    /**
     * 返回失败及对应错误码 + 数据
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> error(ErrorType errorType, T data) {
        return new ApiResponse<T>(errorType, data);
    }

    /**
     * 默认返回系统异常
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> error() {
        return new ApiResponse<T>(ErrorCode.ERROR_SYSTEM);
    }
}
