package com.github.cloud.util;

import com.alibaba.fastjson.JSON;

/**
 * @author : glw
 * @datetime : 2021/5/18 15:27
 * @description : Json 工具类
 */
public class JsonUtil {

    /**
     * 对象转 json
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        if (object instanceof String
                || object instanceof Integer
                || object instanceof Boolean
                || object instanceof Long
                || object instanceof Byte
                || object instanceof Short
                || object instanceof Float
                || object instanceof Double) {
            return String.valueOf(object);
        }
        return JSON.toJSONString(object);
    }

    /**
     * json 转对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }
}
