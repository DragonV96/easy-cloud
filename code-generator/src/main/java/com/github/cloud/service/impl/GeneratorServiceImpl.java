package com.github.cloud.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.github.cloud.config.*;
import com.github.cloud.constant.Constant;
import com.github.cloud.entity.Table;
import com.github.cloud.entity.TableColumn;
import com.github.cloud.enums.DataTypeEnum;
import com.github.cloud.exception.GlobalException;
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

    @Override
    public void outputCode(ProjectConfig projectConfig, PackageConfig packageConfig, SwitchConfig switchConfig, SuffixConfig suffixConfig, TableConfig.AllowTable allowTable){
        VelocityContext velocityContext = VelocityUtil.buildContext(projectConfig, packageConfig, switchConfig, suffixConfig);
        this.fillContext(velocityContext, projectConfig.getDatabaseName(), allowTable);

        List<String> templateList = VelocityUtil.getTemplateList();

        for (String template : templateList) {
            // 生成模板
            StringWriter sw = new StringWriter();
            Template resultTemplate = Velocity.getTemplate(template, Constant.UTF8);
            resultTemplate.merge(velocityContext, sw);
            String fileName = VelocityUtil.getFileName(projectConfig, packageConfig, suffixConfig, allowTable, template);
            FileUtil.writeUtf8String(sw.toString(), fileName);
            log.info("generate file = {}", fileName);
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
     * @param databaseName
     * @param allowTable
     */
    private void fillContext(VelocityContext velocityContext, String databaseName, TableConfig.AllowTable allowTable) {
        Table table = generatorService.queryTable(databaseName, allowTable.getTableName());
        log.info("table = {}", table);
        List<TableColumn> tableColumns = generatorService.queryTableColumn(databaseName, allowTable.getTableName());
        log.info("tableColumns = {}", tableColumns);

        // 表属性
        velocityContext.put("entityClassName", VelocityUtil.getClassName(table.getTableName()));
        velocityContext.put("entityLowerName", StrUtil.toCamelCase(table.getTableName()));
        velocityContext.put("tableComment", table.getTableComment());

        // 字段属性
        this.fillTableColumn(velocityContext, tableColumns, allowTable, table);
        velocityContext.put("columns", tableColumns);
        velocityContext.put("hasDate", table.getHasDate());
        velocityContext.put("hasNotNull", table.getHasNotNull());
        velocityContext.put("hasNotBlank", table.getHasNotBlank());
    }

    /**
     * 填充表字段
     * @param velocityContext
     * @param tableColumns
     * @param allowTable
     * @param table
     */
    private void fillTableColumn(VelocityContext velocityContext, List<TableColumn> tableColumns, TableConfig.AllowTable allowTable, Table table) {
        for (TableColumn column : tableColumns) {
            column.setJavaField(StrUtil.toCamelCase(column.getColumnName()));
            this.fillJavaType(column, table);

            // 校验填充
            this.fillValidate(column, table);

            // 主键填充
            if (Constant.PRIMARY.equals(column.getColumnKey())) {
                column.setIsKey(true);
                column.setIdType(allowTable.getPrimaryKeyType());
                velocityContext.put("idDataType", column.getJavaType());
            }
        }
    }

    /**
     * 根据数据库类型填充 java 对应类型
     * @param column
     * @param table
     */
    private void fillJavaType(TableColumn column, Table table) {
        if (DataTypeEnum.VARCHAR.getDataType().equals(column.getDataType())) {
            column.setJavaType(DataTypeEnum.VARCHAR.getJavaType());
        } else if (DataTypeEnum.INT.getDataType().equals(column.getDataType())) {
            column.setJavaType(DataTypeEnum.INT.getJavaType());
        } else if (DataTypeEnum.BIGINT.getDataType().equals(column.getDataType())) {
            column.setJavaType(DataTypeEnum.BIGINT.getJavaType());
        } else if (DataTypeEnum.TIMESTAMP.getDataType().equals(column.getDataType())) {
            column.setJavaType(DataTypeEnum.TIMESTAMP.getJavaType());
        } else if (DataTypeEnum.DATETIME.getDataType().equals(column.getDataType())) {
            column.setJavaType(DataTypeEnum.DATETIME.getJavaType());
        } else if (DataTypeEnum.CHAR.getDataType().equals(column.getDataType())) {
            column.setJavaType(DataTypeEnum.CHAR.getJavaType());
        } else if (DataTypeEnum.BIT.getDataType().equals(column.getDataType())) {
            column.setJavaType(DataTypeEnum.BIT.getJavaType());
        } else if (DataTypeEnum.TINYINT.getDataType().equals(column.getDataType())) {
            column.setJavaType(DataTypeEnum.TINYINT.getJavaType());
        } else {
            throw new GlobalException("没有此数据库表字段类型！");
        }
        log.info("column.getJavaType() = {}", column.getJavaType());
    }

    /**
     * 根据数据库类型填充数据校验注解
     * @param column
     * @param table
     */
    private void fillValidate(TableColumn column, Table table) {
        if (Constant.NULL.equals(column.getIsNullable())) {
            return;
        }

        if (DataTypeEnum.VARCHAR.getDataType().equals(column.getDataType())
                || DataTypeEnum.CHAR.getDataType().equals(column.getDataType())) {
            table.setHasNotBlank(true);
        } else if (DataTypeEnum.INT.getDataType().equals(column.getDataType())
                || DataTypeEnum.BIGINT.getDataType().equals(column.getDataType())
                || DataTypeEnum.BIT.getDataType().equals(column.getDataType())
                || DataTypeEnum.TINYINT.getDataType().equals(column.getDataType())) {
            table.setHasNotNull(true);
        } else if (DataTypeEnum.TIMESTAMP.getDataType().equals(column.getDataType())
                || DataTypeEnum.DATETIME.getDataType().equals(column.getDataType())) {
            table.setHasDate(true);
        } else {
            throw new GlobalException("没有此数据库表字段类型！");
        }
    }
}
