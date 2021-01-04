package com.github.cloud.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.cloud.config.FastDFSClient;
import com.github.cloud.dto.request.*;
import com.github.cloud.dto.response.FileInfoResponse;
import com.github.cloud.entity.FileInfo;
import com.github.cloud.enums.FileErrorCode;
import com.github.cloud.enums.FileStatusEnum;
import com.github.cloud.exception.GlobalException;
import com.github.cloud.service.FileInfoService;
import com.github.cloud.service.FileService;
import com.github.cloud.service.FileStatusService;
import com.github.cloud.util.MimeUtil;
import com.github.tobato.fastdfs.domain.StorePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author : glw
 * @datetime : 2021/1/4 16:05
 * @description : 文件业务层实现
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FastDFSClient fastDFSClient;

    @Autowired
    private FileInfoService fileInfoService;

    @Autowired
    private FileStatusService fileStatusService;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public boolean upload(MultipartFile file, AddFileRequest request) {
        if (null == file || file.isEmpty()) {
            throw new GlobalException(FileErrorCode.UPLOAD_FILE_EMPTY);
        }
        long start = System.currentTimeMillis();
        StorePath storePath = fastDFSClient.uploadFile(file);
        long end = System.currentTimeMillis();

        AddFileInfoRequest fileInfo = request.getFileInfo();
        AddFileStatusRequest fileStatus = request.getFileStatus();

        File tempFile = null;
        try {
            tempFile = new File(file.getName());
            file.transferTo(tempFile);
            fileInfo.setFileType(MimeUtil.getFileType(tempFile));
            fileInfo.setFileHash(SecureUtil.md5(tempFile));
        } catch (IOException e) {
            throw new GlobalException(FileErrorCode.UPLOAD_FAIL, e.getMessage());
        } finally {
            if (FileUtil.exist(tempFile)) {
                tempFile.delete();
            }
        }
        long id = IdWorker.getId();

        fileInfo.setFileInfoId(id);
        fileInfo.setDfsGroup(storePath.getGroup());
        fileInfo.setDfsPath(storePath.getPath());
        fileInfo.setUploadedFileSize(file.getSize());
        fileInfo.setDuration(end - start);
        fileInfo.setUploadStartTime(new Date(start));
        fileInfo.setUploadEndTime(new Date(end));

        boolean save = fileInfoService.save(fileInfo);

        fileStatus.setFileInfoId(id);
        fileStatus.setFileStatus(FileStatusEnum.FINISH.getStatus());
        save = save && fileStatusService.save(fileStatus);

        if (!save) {
            throw new GlobalException(FileErrorCode.UPLOAD_FILE__RECORD_SAVE_FAILED);
        }
        return save;
    }

    @Override
    public boolean download(Long id) {
        return false;
    }

    @Override
    public IPage<FileInfo> page(PageFileInfoRequest request) {
        return null;
    }

    @Override
    public FileInfoResponse detail(Long id) {
        return null;
    }

    @Override
    public boolean update(UpdateFileInfoRequest request) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean deleteBatch(List<Long> ids) {
        return false;
    }
}
