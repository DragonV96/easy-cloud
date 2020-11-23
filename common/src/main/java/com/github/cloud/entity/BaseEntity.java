package com.github.cloud.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : glw
 * @date : 2020/11/23
 * @time : 23:59
 * @Description : 操作记录基类对象
 */
@EqualsAndHashCode
@ToString
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建者
     */
    private Long creatorId;

    /**
     * 修改者
     */
    private Long updaterId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后一次更新时间
     */
    private Date updateTime;
}
