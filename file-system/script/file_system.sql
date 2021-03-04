show variables like 'sql_mode';
set session sql_mode="ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION";

DROP DATABASE IF EXISTS file_system;
CREATE DATABASE file_system;
USE file_system;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for `file_info`
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info` (
  `file_info_id` bigint(20) NOT NULL COMMENT '文件id',
  `file_type` tinyint(4) NOT NULL COMMENT '文件类型（1图片；2视频；3音频；4文档；5压缩文件；6可执行文件；7其他）',
  `dfs_group` varchar(32) NOT NULL COMMENT 'fastDFS文件组',
  `dfs_path` varchar(255) NOT NULL COMMENT 'fastDFS文件地址',
  `file_hash` varchar(32) NOT NULL COMMENT '文件MD5',
  `uploaded_file_size` bigint(20) NOT NULL DEFAULT 0 COMMENT '已上传文件大小（字节）',
  `file_size` bigint(20) NOT NULL DEFAULT 0 COMMENT '文件大小（字节）',
  `duration` bigint(20) DEFAULT NULL COMMENT '上传耗时净值（毫秒）',
  `file_status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '文件状态（1上传中；2上传完成；3上传异常）',
  `chunks` int(11) NOT NULL DEFAULT 1 COMMENT '分片数量',
  `current_chunk` int(11) NOT NULL COMMENT '当前分片数',
  `upload_start_time` timestamp(0) NOT NULL COMMENT '上传开始时间',
  `upload_end_time` timestamp(0) NOT NULL COMMENT '上传结束时间',
  PRIMARY KEY (`file_info_id`) USING BTREE,
  UNIQUE KEY `hash_index` (`file_hash`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='文件信息表';

-- ----------------------------
-- Table structure for `file_user`
-- ----------------------------
DROP TABLE IF EXISTS `file_user`;
CREATE TABLE `file_user` (
  `file_user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户文件主键id',
  `file_info_id` bigint(20) NOT NULL COMMENT '文件id',
  `file_name` varchar(100) NOT NULL COMMENT '文件名',
  `uploader_id` bigint(20) NOT NULL COMMENT '上传文件的用户名id',
  `download_count` int(11) NOT NULL DEFAULT 0 COMMENT '下载次数',
  `create_by` bigint(20) NOT NULL COMMENT '创建人id',
  `create_time` timestamp(0) NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) NOT NULL COMMENT '更新人id',
  `update_time` timestamp(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`file_user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户文件表';

-- ----------------------------
-- Table structure for `log_file_operate`
-- ----------------------------
DROP TABLE IF EXISTS `log_file_operate`;
CREATE TABLE `log_file_operate` (
  `log_file_operate_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文件操作日志id',
  `file_info_id` bigint(20) NOT NULL COMMENT '文件id',
  `operate_type` tinyint(4) NOT NULL COMMENT '文件操作类型（1上传；2下载；3更新；4删除）',
  `create_by` bigint(20) NOT NULL COMMENT '操作人id',
  `create_time` timestamp(0) NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`log_file_operate_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='文件操作日志表';
