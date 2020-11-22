package com.github.cloud.dto.request;

import lombok.Data;

/**
 * @author : glw
 * @date : 2020/11/22
 * @time : 12:35
 * @Description : 通用分页实体请求对象
 */
@Data
public class PageRequest {

    private final long current;

    private final long size;

}
