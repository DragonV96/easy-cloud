package com.github.cloud.entity;

import com.github.tobato.fastdfs.domain.StorePath;
import lombok.Data;

/**
 * @author : glw
 * @datetime : 2021/1/6 22:34
 * @description : 二次封装 StorePath
 */
@Data
public class Storage {

    /**
     * FastDFS 返回的信息对象
     */
    private StorePath storePath;

    /**
     * 上传开始时间
     */
    private long start;

    /**
     * 上传结束时间
     */
    private long end;
}
