package com.github.cloud.enums;

import lombok.Getter;

/**
 * @author : glw
 * @date : 2020/11/29
 * @time : 23:56
 * @Description : 文件服务错误码
 */
@Getter
public enum FileErrorCode implements ErrorType{

    UPLOAD_FAIL(1001001, "文件上传失败，请稍后重试！"),
    UPLOAD_FILE_EMPTY(1001002, "上传文件为空，请检查后重试！"),
    UPLOAD_FILE__RECORD_SAVE_FAILED(1001003, "上传的文件记录入库失败，请稍后重试！"),
    UPLOAD_TYPE_ERROR(1001004, "文件的上传类型错误，请联系管理员！"),
    UPLOAD_ONCE_FAILED(1001005, "一次性上传文件失败，请稍后重试！"),
    UPLOAD_SLICE_FIRST_FAILED(1001006, "首次分片上传文件失败，请稍后重试！"),
    UPLOAD_SLICE_APPEND_FAILED(1001007, "断点续传文件失败，请稍后重试！"),
    UPLOAD_SLICE_ERROR(1001008, "断点续传文件参数错误，请稍后重试！"),

    DOWNLOAD_FAIL(1001101, "文件下载失败，请稍后重试！"),

    DELETE_FAIL(1001201, "文件删除失败，请稍后重试！"),
    DELETE_FILE__RECORD_FAILED(1001202, "文件记录删除失败，请稍后重试！"),
    ;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误码描述
     */
    private String msg;

    FileErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
