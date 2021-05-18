package com.github.cloud.enums;

/**
 * @author : glw
 * @datetime : 2021/5/18 14:56
 * @description : 路由策略枚举
 */
public enum  RouteStrategyEnum {
    /**
     * 第一个
     */
    FIRST,

    /**
     * 最后一个
     */
    LAST,

    /**
     * 轮询
     */
    ROUND,

    /**
     * 随机
     */
    RANDOM,

    /**
     * 一致性HASH
     */
    CONSISTENT_HASH,

    /**
     * 最不经常使用
     */
    LEAST_FREQUENTLY_USED,

    /**
     * 最近最久未使用
     */
    LEAST_RECENTLY_USED,

    /**
     * 故障转移
     */
    FAILOVER,

    /**
     * 忙碌转移
     */
    BUSYOVER,

    /**
     * 分片广播
     */
    SHARDING_BROADCAST
}
