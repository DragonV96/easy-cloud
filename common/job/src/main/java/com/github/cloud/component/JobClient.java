package com.github.cloud.component;

import cn.hutool.http.HttpUtil;
import com.github.cloud.config.JobAdminConfig;
import com.github.cloud.config.JobExecutorConfig;
import com.github.cloud.constant.UrlConstant;
import com.github.cloud.dto.XxlJobInfo;
import com.github.cloud.util.JsonUtil;
import com.xxl.job.core.biz.model.ReturnT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : glw
 * @datetime : 2021/5/18 14:51
 * @description : 任务调度客户端
 */
@Slf4j
@Component
public class JobClient {

    @Autowired
    private JobAdminConfig jobAdminConfig;

    @Autowired
    private JobExecutorConfig jobExecutorConfig;

    // TODO 执行器id一般不会变，可优化为缓存到redis或者本地JVM内存

    /**
     * 新增任务
     * @param jobInfo
     * @return
     */
    public ReturnT add(XxlJobInfo jobInfo) {
        log.info("JobClient add, jobInfo = {}", jobInfo);
        this.setGroupId(jobInfo);
        String result = HttpUtil.post(jobAdminConfig.getAddresses() + UrlConstant.ADD,
                JsonUtil.toJson(jobInfo));
        return JsonUtil.fromJson(result, ReturnT.class);
    }

    /**
     * 更新任务
     * @param jobInfo
     * @return
     */
    public ReturnT update(XxlJobInfo jobInfo) {
        log.info("JobClient update, jobInfo = {}", jobInfo);
        this.setGroupId(jobInfo);
        String result = HttpUtil.post(jobAdminConfig.getAddresses() + UrlConstant.UPDATE,
                JsonUtil.toJson(jobInfo));
        return JsonUtil.fromJson(result, ReturnT.class);
    }

    /**
     * 删除任务
     * @param id
     * @return
     */
    public ReturnT remove(Integer id) {
        log.info("JobClient remove, id = {}", id);
        StringBuilder url = new StringBuilder();
        url.append(jobAdminConfig.getAddresses())
                .append(UrlConstant.REMOVE)
                .append(UrlConstant.APPEND)
                .append(UrlConstant.JOB_ID)
                .append(UrlConstant.EQUAL)
                .append(id);
        String result = HttpUtil.get(url.toString());
        return JsonUtil.fromJson(result, ReturnT.class);
    }

    /**
     * 根据任务描述删除任务
     * @param jobDesc
     * @return
     */
    public ReturnT removeByDesc(String jobDesc) {
        log.info("JobClient removeByDesc, jobDesc = {}", jobDesc);
        StringBuilder url = new StringBuilder();
        url.append(jobAdminConfig.getAddresses())
                .append(UrlConstant.REMOVE_BY_DESC)
                .append(UrlConstant.APPEND)
                .append(UrlConstant.JOB_DESC)
                .append(UrlConstant.EQUAL)
                .append(jobDesc);
        String result = HttpUtil.get(url.toString());
        return JsonUtil.fromJson(result, ReturnT.class);
    }

    /**
     * 停止任务
     * @param id
     * @return
     */
    public ReturnT stop(Integer id) {
        log.info("JobClient stop, id = {}", id);
        StringBuilder url = new StringBuilder();
        url.append(jobAdminConfig.getAddresses())
                .append(UrlConstant.STOP)
                .append(UrlConstant.APPEND)
                .append(UrlConstant.JOB_ID)
                .append(UrlConstant.EQUAL)
                .append(id);
        String result = HttpUtil.get(url.toString());
        return JsonUtil.fromJson(result, ReturnT.class);
    }

    /**
     * 开始任务
     * @param id
     * @return
     */
    public ReturnT start(Integer id) {
        log.info("JobClient start, id = {}", id);
        StringBuilder url = new StringBuilder();
        url.append(jobAdminConfig.getAddresses())
                .append(UrlConstant.START)
                .append(UrlConstant.APPEND)
                .append(UrlConstant.JOB_ID)
                .append(UrlConstant.EQUAL)
                .append(id);
        String result = HttpUtil.get(url.toString());
        return JsonUtil.fromJson(result, ReturnT.class);
    }

    /**
     * 新增并立即开始任务
     * @param jobInfo
     * @return
     */
    public ReturnT addAndStart(XxlJobInfo jobInfo) {
        log.info("JobClient addAndStart, jobInfo = {}", jobInfo);
        this.setGroupId(jobInfo);
        String result = HttpUtil.post(jobAdminConfig.getAddresses() + UrlConstant.ADD_AND_START,
                JsonUtil.toJson(jobInfo));
        return JsonUtil.fromJson(result, ReturnT.class);
    }

    /**
     * 批量新增并立即开始任务
     * @param jobInfoList
     * @return
     */
    public ReturnT addAndStartBatch(List<XxlJobInfo> jobInfoList) {
        log.info("JobClient addAndStartBatch, jobInfoList = {}", jobInfoList);
        ReturnT returnT = this.getGroupId(jobExecutorConfig.getAppName());
        int groupId = Integer.parseInt(returnT.getContent().toString());
        jobInfoList.forEach(job -> job.setJobGroup(groupId));
        String result = HttpUtil.post(jobAdminConfig.getAddresses() + UrlConstant.ADD_AND_START_BATCH,
                JsonUtil.toJson(jobInfoList));
        return JsonUtil.fromJson(result, ReturnT.class);
    }

    /**
     * 根据服务名获取执行器id
     * @param appName
     * @return
     */
    private ReturnT getGroupId(String appName) {
        log.info("JobClient getGroupId, appName = {}", appName);
        StringBuilder url = new StringBuilder();
        url.append(jobAdminConfig.getAddresses())
                .append(UrlConstant.GET_GROUP_ID)
                .append(UrlConstant.APPEND)
                .append(UrlConstant.APP_NAME)
                .append(UrlConstant.EQUAL)
                .append(appName);
        String result = HttpUtil.get(url.toString());
        return JsonUtil.fromJson(result, ReturnT.class);
    }

    /**
     * 设置执行器id
     * @param jobInfo
     * @return
     */
    private void setGroupId(XxlJobInfo jobInfo) {
        ReturnT returnT = this.getGroupId(jobExecutorConfig.getAppName());
        int groupId = Integer.parseInt(returnT.getContent().toString());
        jobInfo.setJobGroup(groupId);
    }
}
