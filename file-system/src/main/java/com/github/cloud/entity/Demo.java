package com.github.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author : glw
 * @date : 2020/12/4
 * @time : 23:41
 * @Description : 实体类
 */
@EqualsAndHashCode
@ToString
@Data
public class Demo implements Serializable {

    /**
     * 主键id
     */
    @TableId(type = IdType.INPUT)
    private Integer id;

    /**
     * 名字
     */
    private String name;
}
