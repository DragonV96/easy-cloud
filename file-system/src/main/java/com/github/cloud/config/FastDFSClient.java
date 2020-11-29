package com.github.cloud.config;

import com.github.cloud.enums.FileErrorCode;
import com.github.cloud.exception.GlobalException;
import com.github.cloud.utils.FileUtils;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.AppendFileStorageClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : glw
 * @date : 2020/11/29
 * @time : 23:51
 * @Description : FastDFS 客户端
 */
@Slf4j
@Component
public class FastDFSClient {

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private AppendFileStorageClient appendStorageClient;

    /**
     * 上传文件
     * @param file
     * @return
     */
    public StorePath uploadFile(MultipartFile file) {
        if (null == file || file.isEmpty()) {
            return null;
        }
        try {
            return storageClient.uploadFile(file.getInputStream(), file.getSize(), FileUtils.getExtension(file.getOriginalFilename()), null);
        } catch (IOException e) {
            log.error("FastDFSClient uploadFile file upload fail, exception = {}, cause = {}", e.getMessage(), e.getCause());
            throw new GlobalException(FileErrorCode.UPLOAD_FAIL);
        }
    }

    /**
     * 批量上传文件
     * @param files
     * @return
     */
    public List<StorePath> uploadFileBatch(List<MultipartFile> files) {
        List<StorePath> filePathList = new ArrayList<>();
        for (MultipartFile file : files) {
            filePathList.add(this.uploadFile(file));
        }
        return filePathList;
    }

    /**
     * 下载文件
     * @param storePath
     * @return
     */
    public byte[] downloadFile(StorePath storePath) {
        DownloadByteArray callback = new DownloadByteArray();
        return storageClient.downloadFile(storePath.getGroup(), storePath.getPath(), callback);
    }

    /**
     * 批量下载文件
     * @param files
     * @return
     */
    public List<byte[]> downloadFileBatch(List<StorePath> files) {
        List<byte[]> filePathList = new ArrayList<>();
        for (StorePath file : files) {
            filePathList.add(this.downloadFile(file));
        }
        return filePathList;
    }

    /**
     * 删除文件
     * @param storePath
     * @return
     * @throws IOException
     */
    public void deleteFile(StorePath storePath) {
        storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
    }

    /**
     * 批量删除文件
     * @param storePaths
     * @return
     * @throws IOException
     */
    public void deleteFileBatch(List<StorePath> storePaths) {
        for (StorePath storePath : storePaths) {
            this.deleteFile(storePath);
        }
    }
}
