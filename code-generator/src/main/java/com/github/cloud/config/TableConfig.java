package com.github.cloud.config;

import com.github.cloud.entity.TableColumnEntity;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author : glw
 * @date : 2020/12/9
 * @time : 23:34
 * @Description : 表属性配置
 */
@Getter
@Configuration
public class TableConfig {

    /**
     * 表对应实体类名（首字母大写驼峰）
     */
    private String entityClassName;

    /**
     * 表对应实体类注释
     */
    private String comment;

    /**
     * 实体类对应包路径
     */
    private String entityPackageName;

    /** 表列信息 */
    private List<TableColumnEntity> columns;

}
