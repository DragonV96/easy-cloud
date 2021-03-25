package com.github.cloud.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.cloud.config.ProjectConfig;
import com.github.cloud.constant.DateConstant;
import com.github.cloud.constant.InsertConstant;
import com.github.cloud.constant.TableConstant;
import com.github.cloud.mapper.InsertMapper;
import com.github.cloud.service.InsertService;
import com.github.cloud.util.GenerateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author : glw
 * @datetime : 2021/3/24 22:30
 * @Description : 插入数据业务层接口实现类
 */
@Slf4j
@Service
public class InsertServiceImpl implements InsertService {

    @Resource
    private ProjectConfig projectConfig;

    @Autowired
    private InsertMapper insertMapper;

    @Override
    public boolean insertUser() {
        // 外层循环次数
        int parentCount = projectConfig.getVolume() / projectConfig.getMerge();
        int mergeCount = projectConfig.getMerge();
        int mergeLast = mergeCount - 1;
        int totalCount = mergeCount;
        for (int i = 0; i < parentCount; i++) {
            StringBuilder sql = new StringBuilder((TableConstant.DATA_LENGTH + 2) * mergeCount);

            // 插入语句前面部分
            sql.append(InsertConstant.INSERT_INTO)
                    .append(TableConstant.USER)
                    .append(TableConstant.COLUMN)
                    .append(InsertConstant.VALUES);
            for (int j = 0; j < mergeCount; j++) {
                Date date = GenerateUtil.randomTime();
                // 拼接数据部分
                sql.append(InsertConstant.LEFT_BRACKETS)
                        // 用户名
                        .append(InsertConstant.QUOTATION_MARKS)
                        .append(GenerateUtil.randomUserAccount())
                        .append(InsertConstant.QUOTATION_MARKS)
                        .append(InsertConstant.COMMA)
                        // 姓名
                        .append(InsertConstant.QUOTATION_MARKS)
                        .append(GenerateUtil.randomName())
                        .append(InsertConstant.QUOTATION_MARKS)
                        .append(InsertConstant.COMMA)
                        // 性别
                        .append(InsertConstant.QUOTATION_MARKS)
                        .append(GenerateUtil.randomGender())
                        .append(InsertConstant.QUOTATION_MARKS)
                        .append(InsertConstant.COMMA)
                        // 年龄
                        .append(InsertConstant.QUOTATION_MARKS)
                        .append(GenerateUtil.randomAge())
                        .append(InsertConstant.QUOTATION_MARKS)
                        .append(InsertConstant.COMMA)
                        // 邮箱
                        .append(InsertConstant.QUOTATION_MARKS)
                        .append(GenerateUtil.randomEmail())
                        .append(InsertConstant.QUOTATION_MARKS)
                        .append(InsertConstant.COMMA)
                        // 创建时间
                        .append(InsertConstant.QUOTATION_MARKS)
                        .append(date)
                        .append(InsertConstant.QUOTATION_MARKS)
                        .append(InsertConstant.COMMA)
                        // 更新时间（最后一个不拼接逗号）
                        .append(InsertConstant.QUOTATION_MARKS)
                        .append(DateUtil.offset(date, DateField.SECOND, RandomUtil.randomInt(DateConstant.OFFSET)))
                        .append(InsertConstant.QUOTATION_MARKS)
                        .append(InsertConstant.RIGHT_BRACKETS);
                // 最后一个不拼接逗号
                if (j != mergeLast) {
                    sql.append(InsertConstant.COMMA);
                }
            }
            // 插入语句结尾部分
            sql.append(InsertConstant.SEMICOLON);

            long start = System.currentTimeMillis();
            insertMapper.insertData(sql.toString());
            long end = System.currentTimeMillis();
            long time = end - start;
            log.info(" >>>> 第 {} 次执行，当前插入 {} 条数据，耗时：{}ms，QPS：{}条/ms，总共已插入 {} 条数据...", i + 1, mergeCount, time, mergeCount / time, totalCount);

            if (mergeCount < projectConfig.getMaxMerge()) {
                mergeCount += projectConfig.getMerge();
                mergeLast = mergeCount - 1;
            }
            totalCount += mergeCount;
        }

        return true;
    }
}
