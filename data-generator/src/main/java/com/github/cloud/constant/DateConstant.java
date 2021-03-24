package com.github.cloud.constant;

import java.util.Date;

/**
 * @author : glw
 * @datetime : 2021/3/24 22:16
 * @Description : 日期常量
 */
public interface DateConstant {

    /**
     * 起始时间（2018-01-01 00:00:01）
     */
    Date START_DATE = new Date(1514736001000L);

    /**
     * 距离起始时间最小偏移量（s）
     */
    int MIN = 1;

    /**
     * 距离起始时间最大偏移量（s），即 2020-01-01 00:00:00
     */
    int MAX = 94694399;
}
