package com.github.cloud.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author : glw
 * @date : 2020/12/4
 * @time : 23:41
 * @Description : 更新请求实体类
 */
@EqualsAndHashCode
@ToString
@Data
public class UpdateDemo implements Serializable {

    private Integer id;

    private String name;
}
