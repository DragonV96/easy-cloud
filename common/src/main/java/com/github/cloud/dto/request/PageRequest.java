package com.github.cloud.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : glw
 * @date : 2020/11/22
 * @time : 12:35
 * @Description : 通用分页实体请求对象
 */
@Data
@ApiModel(value = "通用分页实体请求对象", description = "通用分页实体请求对象")
public class PageRequest {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "当前页码", name = "current", required = true)
    private long current = 1;

    @ApiModelProperty(value = "当前页大小", name = "size", required = true)
    private long size = 10;

    @ApiModelProperty(value = "排序字段", name = "column")
    private String column;

    @ApiModelProperty(value = "排序顺序（ASC 或 DESC）", name = "order")
    private String order;
}
