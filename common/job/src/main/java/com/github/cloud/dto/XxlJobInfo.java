package com.github.cloud.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author : glw
 * @datetime : 2021/5/18 14:53
 * @description : xxl-job dto 对象
 */
@Accessors(chain = true)
@Data
public class XxlJobInfo {

    /**
     * 主键ID（更新必填）
     */
    private int id;

    /**
     * 执行器主键ID（必填）
     */
    private int jobGroup;

    /**
     * 任务执行CRON表达式（必填）
     */
    private String jobCron;

    /**
     * 任务描述
     */
    private String jobDesc;

    private Date addTime;
    private Date updateTime;

    /**
     * 负责人（必填）
     */
    private String author;

    /**
     * 报警邮件（选填）
     */
    private String alarmEmail;

    /**
     * 执行器路由策略（必填）
     * @see com.github.cloud.enums.RouteStrategyEnum
     */
    private String executorRouteStrategy;

    /**
     * 执行器，任务Handler名称（必填）
     */
    private String executorHandler;

    /**
     * 执行器，任务参数（必填）
     */
    private String executorParam;

    /**
     * @see com.github.cloud.enums.BlockStrategyEnum
     * 阻塞处理策略（必填）
     */
    private String executorBlockStrategy;

    /**
     * 任务执行超时时间，单位秒（必填）
     */
    private int executorTimeout;

    /**
     * 失败重试次数（必填）
     */
    private int executorFailRetryCount;

    /**
     * @see com.xxl.job.core.glue.GlueTypeEnum
     * GLUE类型
     */
    private String glueType;
    private String glueSource;		// GLUE源代码
    private String glueRemark;		// GLUE备注
    private Date glueUpdatetime;	// GLUE更新时间

    private String childJobId;		// 子任务ID，多个逗号分隔

    private int triggerStatus;		// 调度状态：0-停止，1-运行
    private long triggerLastTime;	// 上次调度时间
    private long triggerNextTime;	// 下次调度时间
}
