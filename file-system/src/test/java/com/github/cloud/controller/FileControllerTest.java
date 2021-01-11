package com.github.cloud.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.cloud.AbstractHelper;
import com.github.cloud.dto.response.ApiResponse;
import com.github.cloud.dto.response.PageFileResponse;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * @author : glw
 * @datetime : 2021/1/4 23:02
 * @description : 文件操作控制层单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class FileControllerTest extends AbstractHelper {

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
    public void test001UploadCheck() throws Exception {
        // 存在相同文件
        super.printResult(mockMvc.perform(MockMvcRequestBuilders.get("/file/upload/check")
                .param("fileHash", "698d51a19d8a121ce581499d7b701668")));

        // 不存在相同文件
        super.printResult(mockMvc.perform(MockMvcRequestBuilders.get("/file/upload/check")
                .param("fileHash", "698d51a19d8a121ce581499d7b701670")));
    }

    /**
     * 上传文件
     * ①一次性上传
     * @throws Exception
     */
    @Test
    public void test002UploadOnce() throws Exception {
        File file = new File("D:\\tmp\\1.txt");
        MockMultipartFile uploadFile = new MockMultipartFile("file", "1.txt", MediaType.TEXT_PLAIN_VALUE, new FileInputStream(file));
        super.printResult(mockMvc.perform(MockMvcRequestBuilders.multipart("/file/upload")
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
                        "}")));
    }

    /**
     * 上传文件
     * ③秒传，已存在文件
     * @throws Exception
     */
    @Test
    public void test003UploadExist() throws Exception {
        super.printResult(mockMvc.perform(MockMvcRequestBuilders.multipart("/file/upload")
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
                        "}")));
    }

    /**
     * 修改文件信息
     * @throws Exception
     */
    @Test
    public void test004Update() throws Exception {
        super.printResult(mockMvc.perform(MockMvcRequestBuilders
                .put("/file/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"fileName\": \"655656.txt\",\n" +
                        "  \"fileUserId\": " + this.getOne() +"\n" +
                        "}")));
    }

    /**
     * 删除文件信息
     * @throws Exception
     */
    @Test
    public void test005Delete() throws Exception {
        super.printResult(mockMvc.perform(MockMvcRequestBuilders
                .delete("/file/delete/{id}", this.getOne())));
    }

    /**
     * 获取一个文件记录 id
     * @return
     * @throws Exception
     */
    private Long getOne() throws Exception{
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders
                .get("/file/page")
                .param("current", "1")
                .param("size", "10")
                .param("column", "")
                .param("duration", "")
                .param("fileSize", "")
                .param("fileStatus", "")
                .param("fileType", "")
                .param("order", "")
                .param("uploadEndTime", "")
                .param("uploadStartTime", "")
                .param("uploadedFileSize", "")
        );
        perform.andReturn().getResponse().setCharacterEncoding("UTF-8");
        String response = perform.andReturn().getResponse().getContentAsString();

        ApiResponse apiResponse = JSONUtil.toBean(response, ApiResponse.class);
        String data = JSONUtil.toJsonStr(apiResponse.getData());
        Page page = JSONUtil.toBean(data, Page.class);
        String records = JSONUtil.toJsonStr(page.getRecords());
        JSONArray jsonArray = JSONUtil.parseArray(records);
        List<PageFileResponse> fileInfoList = JSONUtil.toList(jsonArray, PageFileResponse.class);
        PageFileResponse responseEntity = fileInfoList.get(0);

        return responseEntity.getFileUserId();
    }
}
