package com.github.cloud.service.impl;

import com.github.cloud.entity.LogFileOperate;
import com.github.cloud.mapper.LogFileOperateMapper;
import com.github.cloud.service.LogFileOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* @author : glw
* @datetime : 2021-01-05 21:09:18
* @description : 文件操作日志业务层实现
*/
@Service
public class LogFileOperateServiceImpl implements LogFileOperateService {

    @Autowired
    private LogFileOperateMapper logFileOperateMapper;

    @Override
    public Integer save() {
        // TODO 日志写入
        LogFileOperate entity = new LogFileOperate();
        return logFileOperateMapper.insert(entity);
    }
}
