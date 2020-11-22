package com.github.cloud.dto.response;

import lombok.Data;

/**
 * @author : glw
 * @date : 2020/11/22
 * @time : 13:39
 * @Description : 通用实体返回对象
 */
@Data
public class ApiResponse<T> {

    private Integer code;

    private String msg;

    private T data;


}
