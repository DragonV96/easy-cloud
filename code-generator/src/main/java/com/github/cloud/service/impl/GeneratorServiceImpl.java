package com.github.cloud.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.github.cloud.config.*;
import com.github.cloud.constant.Constant;
import com.github.cloud.entity.Table;
import com.github.cloud.entity.TableColumn;
import com.github.cloud.enums.DataTypeEnum;
import com.github.cloud.mapper.GeneratorMapper;
import com.github.cloud.service.GeneratorService;
import com.github.cloud.util.VelocityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.List;

/**
 * @author : glw
 * @datetime : 2020/12/16 22:48
 * @description : 代码生成业务层实现
 */
@Slf4j
@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Autowired
    private GeneratorMapper generatorMapper;

    @Autowired
    private GeneratorService generatorService;

    public void outputCode(ProjectConfig projectConfig, PackageConfig packageConfig, SwitchConfig switchConfig, SuffixConfig suffixConfig, TableConfig tableConfig){
        VelocityContext velocityContext = VelocityUtil.buildContext(projectConfig, packageConfig, switchConfig, suffixConfig, tableConfig);

        List<String> templateList = VelocityUtil.getTemplateList();

        for (String template : templateList) {
            // 生成模板
            StringWriter sw = new StringWriter();
            Template resultTemplate = Velocity.getTemplate(template, Constant.UTF8);
            resultTemplate.merge(velocityContext, sw);

            FileUtil.writeUtf8String(sw.toString(), VelocityUtil.getFileName(projectConfig, packageConfig, suffixConfig, tableConfig, template));
        }
    }

    @Override
    public Table queryTable(String databaseName, String tableName) {
        return generatorMapper.queryTable(databaseName, tableName);
    }

    @Override
    public List<TableColumn> queryTableColumn(String databaseName, String tableName) {
        return generatorMapper.queryTableColumn(databaseName, tableName);
    }

    /**
     * 填充表及表字段配置
     * @param velocityContext
     */
    private void fillContext(VelocityContext velocityContext, String databaseName, String tableName) {
        Table table = generatorService.queryTable(databaseName, tableName);
        List<TableColumn> tableColumns = generatorService.queryTableColumn(databaseName, tableName);

        // 表属性
        velocityContext.put("entityClassName", VelocityUtil.getClassName(table.getTableName()));
        velocityContext.put("entityLowerName", StrUtil.toCamelCase(table.getTableName()));
        velocityContext.put("tableComment", table.getTableComment());

        // 字段属性
        this.fillTableColumn(tableColumns);
        velocityContext.put("columns", tableColumns);
    }

    private void fillTableColumn(List<TableColumn> tableColumns, TableConfig tableConfig) {
        for (TableColumn column : tableColumns) {
            column.setJavaField(StrUtil.toCamelCase(column.getColumnName()));
            column.setJavaType(DataTypeEnum.valueOf(column.getDataType()).getJavaType());

            if (Constant.PRIMARY.equals(column.getColumnKey())) {
                column.setIsKey(true);
            }
        }
    }
}
