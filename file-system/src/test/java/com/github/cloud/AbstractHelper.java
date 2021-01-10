package com.github.cloud;

import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : glw
 * @datetime : 2021/1/10 23:58
 * @Description : 方便测试
 */
public abstract class AbstractHelper {

    private final static String UTF8 = "UTF-8";

    /**
     * 以 UTF-8 格式打印测试结果
     * @param resultActions
     * @throws Exception
     */
    protected void printResult(ResultActions resultActions) throws Exception {
        resultActions.andReturn().getResponse().setCharacterEncoding(UTF8);
        resultActions.andDo(print())
                .andExpect(status().isOk());
    }
}
