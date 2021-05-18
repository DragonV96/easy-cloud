package com.xxl.job.admin.service.impl;

import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.admin.dao.XxlJobGroupDao;
import com.xxl.job.admin.dao.XxlJobInfoDao;
import com.xxl.job.admin.service.XxlJobCustomizeService;
import com.xxl.job.admin.service.XxlJobService;
import com.xxl.job.core.biz.model.ReturnT;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : glw
 * @datetime : 2021/4/26 22:57
 * @Description : 自定义业务层接口实现类
 */
@Service
public class XxlJobCustomizeServiceImpl implements XxlJobCustomizeService {

    @Resource
    private XxlJobGroupDao xxlJobGroupDao;

    @Resource
    private XxlJobInfoDao xxlJobInfoDao;

    @Resource
    private XxlJobService xxlJobService;

    @Resource
    private XxlJobCustomizeService xxlJobCustomizeService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ReturnT<String> addAndStart(XxlJobInfo jobInfo) {
        ReturnT<String> add = xxlJobService.add(jobInfo);
        return xxlJobService.start(Integer.parseInt(add.getContent()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ReturnT<String> addAndStartBatch(List<XxlJobInfo> jobInfoList) {
        for (XxlJobInfo xxlJob : jobInfoList) {
            xxlJobCustomizeService.addAndStart(xxlJob);
        }
        return ReturnT.SUCCESS;
    }

    @Override
    public ReturnT<String> removeByDesc(String jobDesc) {
        return ReturnT.SUCCESS;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ReturnT<String> stopAndRemove(int id) {
        xxlJobService.stop(id);
        xxlJobService.remove(id);
        return ReturnT.SUCCESS;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ReturnT<String> stopAndRemoveByDesc(String jobDesc) {
        int id = xxlJobInfoDao.getIdByDesc(jobDesc);
        xxlJobService.stop(id);
        xxlJobService.remove(id);
        return ReturnT.SUCCESS;
    }

    @Override
    public ReturnT<String> getGroupId(String appName) {
        int groupId = xxlJobGroupDao.getGroupId(appName);
        return new ReturnT<>(String.valueOf(groupId));
    }
}
