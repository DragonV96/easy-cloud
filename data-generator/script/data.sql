DROP DATABASE IF EXISTS data_optimize;
-- 创建数据库
create database data_optimize DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;
USE data_optimize;

-- 创建用户表
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表主键',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `real_name` varchar(10) NOT NULL COMMENT '姓名',
  `gender` tinyint(4) NOT NULL COMMENT '性别',
  `age` tinyint(4) NOT NULL COMMENT '年龄',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户表';