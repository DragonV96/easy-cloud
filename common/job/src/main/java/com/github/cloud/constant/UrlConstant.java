package com.github.cloud.constant;

/**
 * @author : glw
 * @datetime : 2021/5/18 15:16
 * @description : 任务服务调度 url
 */
public interface UrlConstant {

    /**
     * 新增任务
     */
    String ADD = "/job/customize/add";

    /**
     * 更新任务
     */
    String UPDATE = "/job/customize/update";

    /**
     * 删除任务
     */
    String REMOVE = "/job/customize/remove";

    /**
     * 根据任务描述删除任务
     */
    String REMOVE_BY_DESC = "/job/customize/removeByDesc";

    /**
     * 停止任务
     */
    String STOP = "/job/customize/stop";

    /**
     * 开始任务
     */
    String START = "/job/customize/start";

    /**
     * 添加并立即开始任务
     */
    String ADD_AND_START = "/job/customize/addAndStart";

    /**
     * 批量添加并立即开始任务
     */
    String ADD_AND_START_BATCH = "/job/customize/batch/addAndStart";

    /**
     * 根据服务名获取执行器id
     */
    String GET_GROUP_ID = "/job/customize/getGroupId";

    /**
     * GET 连接字符
     */
    String APPEND = "?";

    /**
     * GET 等于字符
     */
    String EQUAL = "=";

    /**
     * GET 拼接字符
     */
    String AND = "&";

    /**
     * 服务名字段
     */
    String APP_NAME = "appName";

    /**
     * 任务 id 字段
     */
    String JOB_ID = "id";

    /**
     * 任务描述字段
     */
    String JOB_DESC = "jobDesc";
}
