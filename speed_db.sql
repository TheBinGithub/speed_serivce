/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80021
Source Host           : localhost:3306
Source Database       : speed_db

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-11-01 14:36:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_account
-- ----------------------------
DROP TABLE IF EXISTS `tb_account`;
CREATE TABLE `tb_account` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `salt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1575320222104588299 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_belong
-- ----------------------------
DROP TABLE IF EXISTS `tb_belong`;
CREATE TABLE `tb_belong` (
  `belong_id` int NOT NULL AUTO_INCREMENT,
  `belong` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`belong_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_delete
-- ----------------------------
DROP TABLE IF EXISTS `tb_delete`;
CREATE TABLE `tb_delete` (
  `delete_id` int NOT NULL,
  `delete_time` datetime DEFAULT NULL,
  `delete_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`delete_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_file
-- ----------------------------
DROP TABLE IF EXISTS `tb_file`;
CREATE TABLE `tb_file` (
  `file_id` int NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `user_id` int DEFAULT NULL,
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件的存放路径',
  `belong_id` int DEFAULT NULL,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件名称',
  `file_type` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件类型0未知1文本2视频3图片4文档5压缩包6文件夹',
  `file_size` bigint DEFAULT NULL COMMENT '文件大小单位字节',
  `upload_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `hash` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1' COMMENT '文件哈希值',
  `du_you` double DEFAULT NULL,
  `delete_id` int DEFAULT NULL COMMENT '逻辑删除id(默认0不删除，其他则为绑定回收站表)',
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_share
-- ----------------------------
DROP TABLE IF EXISTS `tb_share`;
CREATE TABLE `tb_share` (
  `share_id` int NOT NULL AUTO_INCREMENT,
  `file_id` int NOT NULL COMMENT '文件id',
  `share_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件分享的对外地址',
  `command` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件分享的提取码',
  `user_id` bigint NOT NULL COMMENT '分享的用户id',
  PRIMARY KEY (`share_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `all_space` int NOT NULL,
  `used_space` int NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
