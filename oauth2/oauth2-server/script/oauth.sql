DROP DATABASE IF EXISTS oauth;
CREATE DATABASE oauth;
USE oauth;

SET NAMES utf8mb4;

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authorities
-- ----------------------------
DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
    `username` VARCHAR ( 50 ) NOT NULL,
    `authority` VARCHAR ( 50 ) NOT NULL,
    UNIQUE KEY `ix_auth_username` ( `username`, `authority` ),
    CONSTRAINT `fk_authorities_users` FOREIGN KEY ( `username` ) REFERENCES `users` ( `username` )
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
	`userId` VARCHAR ( 256 ) DEFAULT NULL,
	`clientId` VARCHAR ( 256 ) DEFAULT NULL,
	`partnerKey` VARCHAR ( 32 ) DEFAULT NULL,
	`scope` VARCHAR ( 256 ) DEFAULT NULL,
	`status` VARCHAR ( 10 ) DEFAULT NULL,
	`expiresAt` datetime DEFAULT NULL,
	`lastModifiedAt` datetime DEFAULT NULL 
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
	`client_id` VARCHAR ( 255 ) NOT NULL,
	`resource_ids` VARCHAR ( 255 ) DEFAULT NULL,
	`client_secret` VARCHAR ( 255 ) DEFAULT NULL,
	`scope` VARCHAR ( 255 ) DEFAULT NULL,
	`authorized_grant_types` VARCHAR ( 255 ) DEFAULT NULL,
	`web_server_redirect_uri` VARCHAR ( 255 ) DEFAULT NULL,
	`authorities` VARCHAR ( 255 ) DEFAULT NULL,
	`access_token_validity` INT ( 11 ) DEFAULT NULL,
	`refresh_token_validity` INT ( 11 ) DEFAULT NULL,
	`additional_information` VARCHAR ( 4096 ) DEFAULT NULL,
	`autoapprove` VARCHAR ( 255 ) DEFAULT NULL,
	PRIMARY KEY ( `client_id` ) 
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
    `code` VARCHAR ( 255 ) DEFAULT NULL,
    `authentication` BLOB
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
	`username` VARCHAR ( 50 ) NOT NULL,
	`password` VARCHAR ( 100 ) NOT NULL,
	`enabled` TINYINT ( 1 ) NOT NULL,
	PRIMARY KEY ( `username` ) 
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;

-- 用户 writer 密码均为 writer，用户 reader 密码均为 reader
INSERT INTO `users` VALUES ('reader', '$2a$04$C6pPJvC1v6.enW6ZZxX.luTdpSI/1gcgTVN7LhvQV6l/AfmzNU/3i', 1);
INSERT INTO `users` VALUES ('writer', '$2a$04$M9t2oVs3/VIreBMocOujqOaB/oziWL0SnlWdt8hV4YnlhQrORA0fS', 1);

INSERT INTO `authorities` VALUES ('reader', 'READ');
INSERT INTO `authorities` VALUES ('writer', 'READ,WRITE');

INSERT INTO `authorities` VALUES ('writer', 'READ,WRITE');
INSERT INTO `oauth_client_details` VALUES ('file-system1', 'file-system', '1234', 'FOO', 'password,refresh_token', '', 'READ,WRITE', 7200, NULL, NULL, 'true');
INSERT INTO `oauth_client_details` VALUES ('file-system2', 'file-system', '1234', 'FOO', 'client_credentials,refresh_token', '', 'READ,WRITE', 7200, NULL, NULL, 'true');
INSERT INTO `oauth_client_details` VALUES ('file-system3', 'file-system', '1234', 'FOO', 'authorization_code,refresh_token', 'https://baidu.com,http://localhost:8082/ui/login,http://localhost:8083/ui/login,http://localhost:8082/ui/remoteCall', 'READ,WRITE', 7200, NULL, NULL, 'false');