package com.github.cloud.constatnt;

/**
 * @author : glw
 * @datetime : 2021/1/4 19:41
 * @description : 文件业务全局常量
 */
public interface FileConstant {

    /**
     * 文件初始化分片数量
     */
    Integer INIT_CHUNK = 1;

    /**
     * FastDFS 默认文件分组
     */
    String DEFAULT_GROUP = "group1";

    /**
     * 文件数量为1时
     */
    int UNIQUE = 1;
}
