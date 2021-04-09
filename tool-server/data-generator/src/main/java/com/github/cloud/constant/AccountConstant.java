package com.github.cloud.constant;

/**
 * @author : glw
 * @datetime : 2021/3/24 21:14
 * @Description : 账户常量
 */
public interface AccountConstant {

    // -------------------- 账户 --------------------

    /**
     * 用户名账号
     */
    String USERNAME = "0123456789abcdefghijklmnopqrstuvwxyz";

    /**
     * 用户名账号最小长度（包含）
     */
    int MIN_ACCOUNT = 6;

    /**
     * 用户名账号最大长度（不包含）
     */
    int MAX_ACCOUNT = 13;

    // -------------------- 邮箱 --------------------

    /**
     * 邮箱前缀
     */
    String EMAIL_PREFIX = "0123456789abcdefghijklmnopqrstuvwxyz";

    /**
     * 邮箱符号
     */
    String EMAIL_CODE = "@";

    /**
     * 邮箱后缀
     */
    String[] EMAIL_TYPE = {
            "qq",
            "163",
            "126",
            "gmail"
    };

    /**
     * 邮箱后缀符号
     */
    String EMAIL_SUFFIX = ".com";

    /**
     * 邮箱前缀最小长度（包含）
     */
    int MIN_EMAIL = 3;

    /**
     * 邮箱前缀最大长度（不包含）
     */
    int MAX_EMAIL = 12;

    // -------------------- 年龄 --------------------

    /**
     * 最小年龄（包含）
     */
    int MIN_AGE = 12;

    /**
     * 最大年龄（不包含）
     */
    int MAX_AGE = 81;
}
