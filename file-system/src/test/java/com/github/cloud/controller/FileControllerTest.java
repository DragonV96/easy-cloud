package com.github.cloud.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author : glw
 * @datetime : 2021/1/4 23:02
 * @description : 文件操作控制层单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class FileControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    /**
     * 初始化 mockMvc
     */
    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * 上传文件查重
     * @throws Exception
     */
    @Test
    public void uploadCheck() throws Exception {
        // 存在相同文件
        mockMvc.perform(MockMvcRequestBuilders.get("/file/upload/check")
                .param("fileHash", "698d51a19d8a121ce581499d7b701668")
        );
        // 不存在相同文件
        mockMvc.perform(MockMvcRequestBuilders.get("/file/upload/check")
                .param("fileHash", "698d51a19d8a121ce581499d7b701670")
        );
    }

    /**
     * 上传文件
     * ①一次性上传
     * @throws Exception
     */
    @Test
    public void uploadOnce() throws Exception {
        File file = new File("D:\\tmp\\1.txt");
        MockMultipartFile uploadFile = new MockMultipartFile("file", "1.txt", MediaType.TEXT_PLAIN_VALUE, new FileInputStream(file));
        mockMvc.perform(MockMvcRequestBuilders.multipart("/file/upload")
                .file(uploadFile)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"chunkFileSize\": 24,\n" +
                        "  \"chunks\": 1,\n" +
                        "  \"currentChunk\": 1,\n" +
                        "  \"fileHash\": \"698d51a19d8a121ce581499d7b701668\",\n" +
                        "  \"fileName\": \"1.txt\",\n" +
                        "  \"fileSize\": 24,\n" +
                        "  \"uploadType\": 1,\n" +
                        "  \"uploaderId\": 666\n" +
                        "}")
        );
    }

    /**
     * 上传文件
     * ③秒传，已存在文件
     * @throws Exception
     */
    @Test
    public void uploadExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart("/file/upload")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"chunkFileSize\": 24,\n" +
                        "  \"chunks\": 1,\n" +
                        "  \"currentChunk\": 1,\n" +
                        "  \"fileHash\": \"698d51a19d8a121ce581499d7b701668\",\n" +
                        "  \"fileName\": \"1.txt\",\n" +
                        "  \"fileSize\": 24,\n" +
                        "  \"uploadType\": 3,\n" +
                        "  \"uploaderId\": 666\n" +
                        "}")
        );
    }

}
