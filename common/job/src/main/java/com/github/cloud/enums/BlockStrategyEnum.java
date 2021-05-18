package com.github.cloud.enums;

/**
 * @author : glw
 * @datetime : 2021/5/18 15:00
 * @description : 阻塞处理策略枚举
 */
public enum BlockStrategyEnum {

    /**
     * 单机串行
     */
    SERIAL_EXECUTION,

    /**
     * 丢弃后续调用
     */
    DISCARD_LATER,

    /**
     * 覆盖之前调度
     */
    COVER_EARLY
}
