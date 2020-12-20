package com.github.cloud.service;

import com.github.cloud.config.*;

/**
 * @author : glw
 * @datetime : 2020/12/18 00:01
 * @description : 代码生成业务层接口
 */
public interface GeneratorService {

    /**
     * 输出代码到文件
     * @param projectConfig
     * @param packageConfig
     * @param switchConfig
     * @param suffixConfig
     * @param tableConfig
     */
    void outputCode(ProjectConfig projectConfig, PackageConfig packageConfig, SwitchConfig switchConfig, SuffixConfig suffixConfig, TableConfig tableConfig);

}
