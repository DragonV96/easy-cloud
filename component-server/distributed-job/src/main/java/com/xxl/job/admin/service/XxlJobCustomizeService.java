package com.xxl.job.admin.service;

import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.core.biz.model.ReturnT;

/**
 * @author : glw
 * @datetime : 2021/4/26 22:56
 * @Description : 自定义业务层接口
 */
public interface XxlJobCustomizeService {

    /**
     * 添加并立即启动任务
     * @param jobInfo
     * @return 任务id
     */
    ReturnT<String> addAndStart(XxlJobInfo jobInfo);

    /**
     * 根据任务描述删除任务
     * @param jobDesc
     * @return
     */
    ReturnT<String> removeByDesc(String jobDesc);

    /**
     * 停止并删除任务
     * @param id
     * @return
     */
    ReturnT<String> stopAndRemove(int id);

    /**
     * 根据任务描述停止并删除任务
     * @param jobDesc
     * @return
     */
    ReturnT<String> stopAndRemoveByDesc(String jobDesc);

    /**
     * 根据服务名获取执行器id
     * @param appName
     * @return
     */
    ReturnT<String> getGroupId(String appName);
}
