package com.github.cloud.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author : glw
 * @date : 2020/12/6
 * @time : 16:09
 * @Description : 响应对象
 */
@EqualsAndHashCode
@ToString
@Data
public class DemoResponse implements Serializable {

    private Integer id;

    private String name;
}
