package com.github.cloud.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.util.RandomUtil;
import com.github.cloud.constant.AccountConstant;
import com.github.cloud.constant.ChineseConstant;
import com.github.cloud.constant.DateConstant;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

/**
 * @author : glw
 * @datetime : 2021/3/24 20:28
 * @Description : 随机数据生成工具类
 */
public class GenerateUtil {

    /**
     * 随机生成完整名字
     * @return
     */
    public static String randomName() {
        int nameLength = RandomUtil.randomInt(ChineseConstant.MIN_LENGTH, ChineseConstant.MAX_LENGTH);
        final StringBuilder name = new StringBuilder(nameLength + 1);
        name.append(randomLastName())
                .append(randomFirstName(nameLength));
        return name.toString();
    }

    /**
     * 随机性别编码（1-男；2-女；3-未知）
     * @return
     */
    public static int randomGender() {
        return RandomUtil.randomInt(ChineseConstant.MIN_GENDER, ChineseConstant.MAX_GENDER);
    }

    /**
     * 随机年龄（12-80）
     * @return
     */
    public static int randomAge() {
        return RandomUtil.randomInt(AccountConstant.MIN_AGE, AccountConstant.MAX_AGE);
    }

    /**
     * 随机用户名账号（6-12个字符）
     * @return
     */
    public static String randomUserAccount() {
        int length = RandomUtil.randomInt(AccountConstant.MIN_ACCOUNT, AccountConstant.MAX_ACCOUNT);
        return RandomUtil.randomString(AccountConstant.USERNAME, length);
    }

    /**
     * 随机邮箱（3-12个字符前缀）
     * @return
     */
    public static String randomEmail() {
        // 邮箱后缀
        int suffixIndex = RandomUtil.randomInt(AccountConstant.EMAIL_TYPE.length);
        String suffix = (AccountConstant.EMAIL_TYPE)[suffixIndex];

        // 邮箱前缀
        int prefixLength = RandomUtil.randomInt(AccountConstant.MIN_ACCOUNT, AccountConstant.MAX_ACCOUNT);
        int strLength = AccountConstant.EMAIL_PREFIX.length();

        final StringBuilder emailStr = new StringBuilder(prefixLength
                + AccountConstant.EMAIL_CODE.length()
                + suffix.length()
                + AccountConstant.EMAIL_SUFFIX.length());
        for (int i = 0; i < prefixLength; i++) {
            int number = RandomUtil.randomInt(strLength);
            emailStr.append(AccountConstant.EMAIL_PREFIX.charAt(number));
        }
        emailStr.append(AccountConstant.EMAIL_CODE)
                .append(suffix)
                .append(AccountConstant.EMAIL_SUFFIX);

        return emailStr.toString();
    }

    /**
     * 随机时间
     * @return
     */
    public static Date randomTime() {
        return RandomUtil.randomDate(DateConstant.START_DATE, DateField.SECOND, DateConstant.MIN, DateConstant.MAX);
    }

    // -------------------- 拆分方法 --------------------

    /**
     * 随机生成姓
     * @return
     */
    public static char randomLastName() {
        int length = ChineseConstant.LAST_NAME.length();
        int randomIndex = RandomUtil.randomInt(length);
        return ChineseConstant.LAST_NAME.charAt(randomIndex);
    }

    /**
     * 随机生成名
     * @param nameLength 名的长度
     * @return
     */
    public static String randomFirstName(int nameLength) {
        if (nameLength > 1) {
            final StringBuilder name = new StringBuilder(nameLength);
            for (int i = 0; i < nameLength; i++) {
                name.append(randomChineseChar());
            }
            return name.toString();
        } else {
            return String.valueOf(randomChineseChar());
        }
    }

    /**
     * 随机生成单个汉字
     * @return
     */
    public static char randomChineseChar() {
        String str = "";
        Random random = new Random();
        int highPos = (ChineseConstant.HIGH_START + Math.abs(random.nextInt(ChineseConstant.HIGH_RANGE)));
        int lowPos = (ChineseConstant.LOW_START + Math.abs(random.nextInt(ChineseConstant.LOW_RANGE)));
        byte[] b = new byte[ChineseConstant.BYTE_LENGTH];
        b[0] = (Integer.valueOf(highPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();
        try {
            str = new String(b, ChineseConstant.CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str.charAt(0);
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(randomName()
                    + ", " + randomGender()
                    + ", " + randomAge()
                    + ", " + randomUserAccount()
                    + ", " + randomEmail()
                    + ", " + randomTime());
        }
    }

}
