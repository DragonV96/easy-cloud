package com.github.cloud.config;

import com.github.cloud.constatnt.FileConstant;
import com.github.cloud.entity.Storage;
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
    public Storage uploadFile(MultipartFile file) {
        try {
            long start = System.currentTimeMillis();
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), FileUtils.getExtension(file.getOriginalFilename()), null);
            long end = System.currentTimeMillis();
            log.info("File had been upload successfully！File path = {}", storePath.getFullPath());
            return this.buildStorage(storePath, start, end);
        } catch (IOException e) {
            log.error("FastDFSClient uploadFile file upload fail, exception = {}, cause = {}", e.getMessage(), e.getCause());
            throw new GlobalException(FileErrorCode.UPLOAD_ONCE_FAILED);
        }
    }

    /**
     * 首次上传分片文件
     * @param file
     * @return
     */
    public Storage uploadAppenderFile(MultipartFile file) {
        try {
            long start = System.currentTimeMillis();
            StorePath storePath = appendStorageClient.uploadAppenderFile(FileConstant.DEFAULT_GROUP, file.getInputStream(), file.getSize(), FileUtils.getExtension(file.getOriginalFilename()));
            long end = System.currentTimeMillis();
            log.info("The first slice of file had been upload successfully！File path = {}", storePath.getFullPath());
            return this.buildStorage(storePath, start, end);
        } catch (IOException e) {
            log.error("FastDFSClient uploadAppenderFile file upload fail, exception = {}, cause = {}", e.getMessage(), e.getCause());
            throw new GlobalException(FileErrorCode.UPLOAD_SLICE_FIRST_FAILED);
        }
    }

    /**
     * 断点续传，拼接分片文件
     * @param file
     * @param storePath
     * @return
     */
    public Storage appendFile(MultipartFile file, StorePath storePath) {
        try {
            long start = System.currentTimeMillis();
            appendStorageClient.appendFile(storePath.getGroup(), storePath.getPath(), file.getInputStream(), file.getSize());
            long end = System.currentTimeMillis();
            log.info("The next slice of file had been upload successfully！File path = {}", storePath.getFullPath());
            return this.buildStorage(storePath, start, end);
        } catch (IOException e) {
            log.error("FastDFSClient appendFile file upload fail, exception = {}, cause = {}", e.getMessage(), e.getCause());
            throw new GlobalException(FileErrorCode.UPLOAD_SLICE_APPEND_FAILED);
        }
    }

    /**
     * 断点续传，拼接分片文件
     * @param file
     * @param storePath
     * @param currentChunk
     */
    public void appendFile(MultipartFile file, StorePath storePath, Integer currentChunk) {
        try {
            appendStorageClient.appendFile(storePath.getGroup(), storePath.getPath(), file.getInputStream(), file.getSize());
            log.info("The next slice of file had been upload successfully！Current chunk = {}, file path = {}", currentChunk, storePath.getFullPath());
        } catch (IOException e) {
            log.error("FastDFSClient appendFile file upload fail, exception = {}, cause = {}", e.getMessage(), e.getCause());
            throw new GlobalException(FileErrorCode.UPLOAD_SLICE_APPEND_FAILED);
        }
    }

    /**
     * 批量上传文件
     * @param files
     * @return
     */
    public List<Storage> uploadFileBatch(List<MultipartFile> files) {
        List<Storage> filePathList = new ArrayList<>();
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

    /**
     * 构建 Storage 对象
     * @param storePath
     * @param start
     * @param end
     * @return
     */
    private Storage buildStorage(StorePath storePath, long start, long end) {
        Storage storage = new Storage();
        storage.setStorePath(storePath);
        storage.setStart(start);
        storage.setEnd(end);
        return storage;
    }
}
