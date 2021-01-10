package com.github.cloud.service.impl;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.cloud.config.FastDFSClient;
import com.github.cloud.constatnt.FileConstant;
import com.github.cloud.dto.request.AddFileRequest;
import com.github.cloud.dto.request.PageFileInfoRequest;
import com.github.cloud.dto.request.QueryFileRequest;
import com.github.cloud.dto.response.FileInfoResponse;
import com.github.cloud.entity.FileInfo;
import com.github.cloud.entity.Storage;
import com.github.cloud.enums.FileErrorCode;
import com.github.cloud.enums.FileStatusEnum;
import com.github.cloud.enums.UploadTypeEnum;
import com.github.cloud.exception.GlobalException;
import com.github.cloud.service.FileInfoService;
import com.github.cloud.service.FileService;
import com.github.cloud.service.FileUserService;
import com.github.cloud.util.MimeUtil;
import com.github.tobato.fastdfs.domain.StorePath;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FastDFSClient fastDFSClient;

    @Autowired
    private FileInfoService fileInfoService;

    @Autowired
    private FileUserService fileUserService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean upload(MultipartFile file, AddFileRequest request) {
        if (UploadTypeEnum.UPLOAD_ONCE.getType().equals(request.getUploadType())) {
            this.uploadOnce(file, request);
        } else if (UploadTypeEnum.UPLOAD_SLICE.getType().equals(request.getUploadType())) {
            this.uploadSlice(file, request);
        } else if (UploadTypeEnum.UPLOAD_EXIST.getType().equals(request.getUploadType())) {
            this.uploadExist(request);
        } else {
            throw new GlobalException(FileErrorCode.UPLOAD_TYPE_ERROR);
        }
        return true;
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long id) {
        // 先执行文件删除再删除文件用户表，否则找不到记录
        this.deleteFile(id);

        boolean delete = fileUserService.delete(id);
        if (!delete) {
            throw new GlobalException(FileErrorCode.DELETE_FILE_RECORD_FAILED);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBatch(List<Long> ids) {
        // 先执行文件删除再删除文件用户表，否则找不到记录
        for (Long id : ids) {
            this.deleteFile(id);
        }

        boolean delete = fileInfoService.deleteBatch(ids);
        if (!delete) {
            throw new GlobalException(FileErrorCode.DELETE_FILE_RECORD_FAILED);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void uploadOnce(MultipartFile file, AddFileRequest request) {
        this.checkFile(file);
        Storage storage = fastDFSClient.uploadFile(file);
        this.insertFile(file, request, storage, false);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void uploadSlice(MultipartFile file, AddFileRequest request) {
        this.checkFile(file);

        if (request.getCurrentChunk().equals(FileConstant.INIT_CHUNK)) {
            Storage storage = fastDFSClient.uploadAppenderFile(file);
            this.insertFile(file, request, storage, true);
        } else if (request.getCurrentChunk().compareTo(FileConstant.INIT_CHUNK) > 0) {
            this.appendFile(file, request);
        } else {
            throw new GlobalException(FileErrorCode.UPLOAD_SLICE_ERROR);
        }
    }

    @Override
    public void uploadExist(AddFileRequest request) {
        Long id = fileInfoService.queryIdByHash(request.getFileHash());
        request.setFileInfoId(id);
        fileUserService.save(request);
    }

    @Override
    public boolean uploadCheck(QueryFileRequest request) {
        Long id = fileInfoService.queryIdByHash(request.getFileHash());
        return id != null && id > 0;
    }

    /**
     * 检查上传的文件是否有效
     * @param file
     */
    private void checkFile(MultipartFile file) {
        if (null == file || file.isEmpty()) {
            throw new GlobalException(FileErrorCode.UPLOAD_FILE_EMPTY);
        }
    }

    /**
     * 填充文件对象并插入记录
     * @param file
     * @param request
     * @param storage
     * @param isSlice 是否分片
     */
    private void insertFile(MultipartFile file, AddFileRequest request, Storage storage, boolean isSlice) {
        File tempFile = null;
        long id = IdWorker.getId();

        try {
            tempFile = new File(file.getName());
            file.transferTo(tempFile);
            request.setFileType(MimeUtil.getFileType(tempFile));

            request.setFileInfoId(id);
            request.setDfsGroup(storage.getStorePath().getGroup());
            request.setDfsPath(storage.getStorePath().getPath());
            request.setUploadedFileSize(file.getSize());
            request.setDuration(storage.getEnd() - storage.getStart());
            request.setUploadStartTime(new Date(storage.getStart()));
            request.setUploadEndTime(new Date(storage.getEnd()));

            // 是否分片
            if (isSlice) {
                request.setFileStatus(FileStatusEnum.UPLOADING.getStatus());
            } else {
                request.setFileStatus(FileStatusEnum.FINISH.getStatus());
            }
        } catch (IOException e) {
            request.setFileStatus(FileStatusEnum.EXCEPTION.getStatus());
        } finally {
            if (FileUtil.exist(tempFile)) {
                tempFile.delete();
            }
        }

        boolean save = fileInfoService.save(request) && fileUserService.save(request);
        if (!save) {
            throw new GlobalException(FileErrorCode.UPLOAD_FILE_RECORD_SAVE_FAILED);
        }
    }

    /**
     * 断点续传，填充文件对象并更新记录
     * @param file
     * @param request
     */
    private void appendFile(MultipartFile file, AddFileRequest request) {
        QueryWrapper<FileInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_hash", request.getFileHash());
        FileInfo entity = fileInfoService.getOne(queryWrapper);

        Storage storage = fastDFSClient.appendFile(file, new StorePath(entity.getDfsGroup(), entity.getDfsPath()));
        long uploadFileSize = entity.getUploadedFileSize() + request.getChunkFileSize();

        FileInfo update = new FileInfo();
        update.setFileInfoId(entity.getFileInfoId());
        update.setCurrentChunk(request.getCurrentChunk());
        update.setUploadEndTime(new Date(storage.getEnd()));
        update.setUploadedFileSize(uploadFileSize);
        update.setDuration(entity.getDuration() + storage.getEnd() - storage.getStart());

        // 上传完成
        if (uploadFileSize == entity.getFileSize()) {
            update.setFileStatus(FileStatusEnum.FINISH.getStatus());
        }

        fileInfoService.updateById(update);
    }

    /**
     * 根据文件用户表主键 id 删除文件
     * @param id
     */
    private void deleteFile(Long id) {
        // 如果只存在唯一一个 id 与文件 id 关联，则删除文件与对应的文件记录
        Long fileInfoId = fileUserService.queryFileInfoIdsById(id);
        List<Long> idList = fileUserService.queryIdsByFileInfoId(fileInfoId);
        if (null != idList && idList.size() == FileConstant.UNIQUE) {
            FileInfo fileInfo = fileInfoService.getById(fileInfoId);
            if (null == fileInfo) {
                throw new GlobalException(FileErrorCode.DELETE_FILE_NOT_EXIST);
            }
            // 删除文件
            fastDFSClient.deleteFile(new StorePath(fileInfo.getDfsGroup(), fileInfo.getDfsPath()));

            // 删除记录
            boolean delete = fileInfoService.delete(fileInfoId);
            if (!delete) {
                throw new GlobalException(FileErrorCode.DELETE_FILE_INFO_RECORD_FAILED);
            }
        }
    }
}
