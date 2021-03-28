package com.github.cloud.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : glw
 * @datetime : 2021/3/26 13:45
 * @Description : 每日执行计划
 */
@Slf4j
@Component
public class DailyJob {

    @XxlJob(value = "daily")
    public ReturnT<String> dailyJob(String param) {
        log.info("执行每日计划...");
        return new ReturnT("执行每日计划");
    }
}
