package com.github.cloud.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author : glw
 * @datetime : 2021/1/7 20:19
 * @description : 自动填充公共字段
 */
@Component
public class BaseEntityHandler implements MetaObjectHandler {

    private final static String CREATE_BY = "createBy";
    private final static String CREATE_TIME = "createTime";
    private final static String UPDATE_BY = "updateBy";
    private final static String UPDATE_TIME = "updateTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = this.getUser();
        Date now = new Date();
        if(metaObject.hasGetter(CREATE_BY)) {
            this.setFieldValByName(CREATE_BY, userId, metaObject);
        }
        if(metaObject.hasGetter(CREATE_TIME)) {
            this.setFieldValByName(CREATE_TIME, now, metaObject);
        }
        if(metaObject.hasGetter(UPDATE_BY)) {
            this.setFieldValByName(UPDATE_BY, userId, metaObject);
        }
        if(metaObject.hasGetter(UPDATE_TIME)) {
            this.setFieldValByName(UPDATE_TIME, now, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long userId = this.getUser();
        Date now = new Date();
        if(metaObject.hasGetter(UPDATE_BY)) {
            this.setFieldValByName(UPDATE_BY, userId, metaObject);
        }
        if(metaObject.hasGetter(UPDATE_TIME)) {
            this.setFieldValByName(UPDATE_TIME, now, metaObject);
        }
    }

    /**
     * 获取请求中的 HEADER 用户 token 参数
     * @return
     */
    private Long getUser() {
        // TODO 请求头里取 用户 token ，暂时写死
        return 888L;
    }
}
