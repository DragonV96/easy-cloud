package com.github.cloud.constant;

/**
 * @author : glw
 * @date : 2020/12/11
 * @time : 1:02
 * @Description : 代码生成常量
 */
public interface Constant {

    /**
     * UTF-8 字符集
     */
    String UTF8 = "UTF-8";

    /**
     * 路径分隔符
     */
    String SPLIT = "\\";

    /**
     * 点
     */
    String DOT = ".";

    /**
     * java 文件后缀
     */
    String JAVA_SUFFIX = ".java";

    /**
     * vm 文件后缀
     */
    String VM = ".vm";

    /**
     * .xml.vm 文件后缀
     */
    String XML_VM = ".xml.vm";

    /**
     * xml 文件后缀
     */
    String XML_SUFFIX = ".xml";

    /**
     * 项目根路径
     */
    String PROJECT_PATH = "main\\java";

    /**
     * mybatis 包路径
     */
    String MYBATIS_PATH = "main\\resources\\mapper";

    /**
     * 模板公共路径
     */
    String TEMPLATE_PATH = "template/java/";

    /**
     * 主键标识
     */
    String PRIMARY = "PRI";

    /**
     * 列不能为空标识
     */
    String NOT_NULL = "NO";

    /**
     * 列可以为空标识
     */
    String NULL = "YES";

    /**
     * 去掉表注释后缀 表
     */
    String EXCLUDE = "表";

    /**
     * 创建时间字段
     */
    String CREATE_TIME = "create_time";

    /**
     * 更新时间字段
     */
    String UPDATE_TIME = "update_time";
}
