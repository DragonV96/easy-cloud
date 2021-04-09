package com.github.cloud.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author : glw
 * @date : 2021/1/9 16:25
 * @Description : 文件查重查询请求对象
 */
@Data
@ApiModel(value = "文件查重查询请求对象")
public class QueryFileRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件MD5", name = "fileHash", required = true)
    @NotBlank(message = "文件MD5不能为空")
    @Size(max = 32, message = "文件MD5超过长度限制，最大长度为32个字节")
    private String fileHash;

}
