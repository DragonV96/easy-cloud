package com.xxl.job.admin.controller;

import com.xxl.job.admin.controller.annotation.PermissionLimit;
import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.admin.service.XxlJobCustomizeService;
import com.xxl.job.admin.service.XxlJobService;
import com.xxl.job.core.biz.model.ReturnT;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : glw
 * @datetime : 2021/4/25 23:58
 * @Description : 自定义任务调度接口
 */
@RestController
@RequestMapping("/job/customize")
public class JobCustomizeController {

    @Resource
    private XxlJobService xxlJobService;

    @Resource
    private XxlJobCustomizeService xxlJobCustomizeService;

    @PermissionLimit(limit = false)
    @PostMapping("/add")
    public ReturnT<String> add(@RequestBody XxlJobInfo jobInfo) {
        return xxlJobService.add(jobInfo);
    }

    @PermissionLimit(limit = false)
    @PostMapping("/update")
    public ReturnT<String> update(@RequestBody XxlJobInfo jobInfo) {
        return xxlJobService.update(jobInfo);
    }

    @PermissionLimit(limit = false)
    @GetMapping("/remove/{id}")
    public ReturnT<String> remove(@PathVariable("id") Integer id) {
        return xxlJobService.remove(id);
    }

    @PermissionLimit(limit = false)
    @GetMapping("/removeByDesc")
    public ReturnT<String> removeByDesc(String jobDesc) {
        return xxlJobCustomizeService.removeByDesc(jobDesc);
    }

    @PermissionLimit(limit = false)
    @GetMapping("/stop/{id}")
    public ReturnT<String> stop(@PathVariable("id") Integer id) {
        return xxlJobService.stop(id);
    }

    @PermissionLimit(limit = false)
    @GetMapping("/start/{id}")
    public ReturnT<String> start(@PathVariable("id") Integer id) {
        return xxlJobService.start(id);
    }

    @PermissionLimit(limit = false)
    @PostMapping("/addAndStart")
    public ReturnT<String> addAndStart(@RequestBody XxlJobInfo jobInfo) {
        return xxlJobCustomizeService.addAndStart(jobInfo);
    }

    @PermissionLimit(limit = false)
    @PostMapping("/batch/addAndStart")
    public ReturnT<String> addAndStartBatch(@RequestBody List<XxlJobInfo> jobInfoList) {
        return xxlJobCustomizeService.addAndStartBatch(jobInfoList);
    }

    @PermissionLimit(limit = false)
    @GetMapping("/stopAndRemove")
    public ReturnT<String> stopAndRemove(int id) {
        return xxlJobCustomizeService.stopAndRemove(id);
    }

    @PermissionLimit(limit = false)
    @GetMapping("/stopAndRemoveByDesc")
    public ReturnT<String> stopAndRemoveByDesc(String jobDesc) {
        return xxlJobCustomizeService.stopAndRemoveByDesc(jobDesc);
    }

    @PermissionLimit(limit = false)
    @GetMapping("/getGroupId")
    public ReturnT<String> getGroupId(String appName) {
        return xxlJobCustomizeService.getGroupId(appName);
    }
}
