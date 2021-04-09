package com.github.cloud.util;

import cn.hutool.core.io.FileUtil;
import com.github.cloud.enums.FileTypeEnum;

import java.io.File;

/**
 * @author : glw
 * @datetime : 2021/1/4 16:45
 * @description : 媒体文件工具类
 */
public class MimeUtil {

    /** 图片扩展名  */
    public static final String IMAGE_EXTENSION = "bmp,gif,jpg,jpeg,png,tif";

    /** 视频扩展名 */
    public static final String VIDEO_EXTENSION = "swf,flv,avi,mpg,mov,rmvb,mp4,m4a";

    /** 音频扩展名 */
    public static final String AUDIO_EXTENSION = "mp3,wav,aac,wmv,mid,mmf";

    /** 文档扩展名 */
    public static final String DOC_EXTENSION = "txt,doc,docx,ppt,pptx,xls,xlsx,html,htm";

    /** 压缩文件扩展名 */
    public static final String COMPRESSION_EXTENSION = "rar,gz,tar,zip,7z,bz2";

    /** 可执行文件扩展名 */
    public static final String EXECUTE_EXTENSION = "exe,bat,cmd,sh";

    /**
     * 根据文件获取文件类型
     * @param file
     * @return
     */
    public static byte getFileType(File file) {
        String extension = FileUtil.getType(file);
        return getFileType(extension);
    }

    /**
     * 获取文件类型
     * @param extension
     * @return
     */
    public static byte getFileType(String extension) {
        if (IMAGE_EXTENSION.contains(extension)) {
            return FileTypeEnum.IMAGE.getStatus();
        } else if (VIDEO_EXTENSION.contains(extension)) {
            return FileTypeEnum.VIDEO.getStatus();
        } else if (AUDIO_EXTENSION.contains(extension)) {
            return FileTypeEnum.AUDIO.getStatus();
        } else if (DOC_EXTENSION.contains(extension)) {
            return FileTypeEnum.DOC.getStatus();
        } else if (COMPRESSION_EXTENSION.contains(extension)) {
            return FileTypeEnum.COMPRESSION.getStatus();
        } else if (EXECUTE_EXTENSION.contains(extension)) {
            return FileTypeEnum.EXECUTION.getStatus();
        } else {
            return FileTypeEnum.OTHERS.getStatus();
        }
    }
}
