package com.github.cloud.enums;

/**
 * @author : glw
 * @date : 2020/11/23
 * @time : 23:11
 * @Description : 错误码接口
 */
public interface ErrorType {

    /**
     * 返回错误码
     * @return
     */
    Integer getCode();

    /**
     * 返回错误码描述
     * @return
     */
    String getMsg();
}
