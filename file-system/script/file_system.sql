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
  `file_name` varchar(100) NOT NULL COMMENT '文件名',
  `file_type` tinyint(4) NOT NULL COMMENT '文件类型（1图片 2视频 3音频 4文档 5压缩文件 6可执行文件 7其他）',
  `dfs_group` varchar(32) DEFAULT NULL COMMENT 'fastDFS文件组',
  `dfs_path` varchar(255) DEFAULT NULL COMMENT 'fastDFS文件地址',
  `file_hash` varchar(32) NOT NULL COMMENT '文件MD5',
  `uploaded_file_size` bigint(20) DEFAULT 0 COMMENT '已上传文件大小（字节）',
  `file_size` bigint(20) DEFAULT 0 COMMENT '文件大小（字节）',
  `uploader_id` bigint(20) NOT NULL COMMENT '上传文件的用户名id',
  `duration` bigint(20) DEFAULT NULL COMMENT '上传耗时净值（毫秒）',
  `upload_start_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传开始时间',
  `upload_end_time` timestamp(0)  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传结束时间',
  PRIMARY KEY (`file_info_id`) USING BTREE,
  UNIQUE KEY `hash_index` (`file_hash`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='文件信息表';

-- ----------------------------
-- Table structure for `file_info`
-- ----------------------------
DROP TABLE IF EXISTS `file_status`;
CREATE TABLE `file_status` (
  `file_status_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件状态id',
  `file_info_id` bigint(20) NOT NULL COMMENT '文件id',
  `file_status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '文件状态（1初始化 2上传中 3上传完成 4上传异常）',
  `chunks` int(11) DEFAULT 1 COMMENT '分片数量',
  `current_chunk` int(11) DEFAULT NULL COMMENT '当前分片数',
  `download_count` int(11) DEFAULT NULL COMMENT '下载次数',
  PRIMARY KEY (`file_status_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='文件状态表';
