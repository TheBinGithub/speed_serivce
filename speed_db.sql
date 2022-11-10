/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80021
Source Host           : localhost:3306
Source Database       : speed_db

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-11-10 14:24:19
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
  `belong_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1575320222104588300 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_account
-- ----------------------------
INSERT INTO `tb_account` VALUES ('1575320222104588299', '7000C191831EB2A37721452568982303', 'admin', '8A3E7194-0047-4A5D-9264-107D6C724CBF', '');

-- ----------------------------
-- Table structure for tb_belong
-- ----------------------------
DROP TABLE IF EXISTS `tb_belong`;
CREATE TABLE `tb_belong` (
  `belong_id` int NOT NULL AUTO_INCREMENT,
  `belong` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`belong_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_belong
-- ----------------------------
INSERT INTO `tb_belong` VALUES ('26', 'gggg');
INSERT INTO `tb_belong` VALUES ('27', 'aa');
INSERT INTO `tb_belong` VALUES ('28', 'gg');

-- ----------------------------
-- Table structure for tb_delete
-- ----------------------------
DROP TABLE IF EXISTS `tb_delete`;
CREATE TABLE `tb_delete` (
  `delete_id` int NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `file_id` int DEFAULT NULL,
  `delete_time` bigint DEFAULT NULL,
  PRIMARY KEY (`delete_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_delete
-- ----------------------------

-- ----------------------------
-- Table structure for tb_file
-- ----------------------------
DROP TABLE IF EXISTS `tb_file`;
CREATE TABLE `tb_file` (
  `file_id` int NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `user_id` bigint DEFAULT NULL,
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件的存放路径',
  `belong_id` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件名称',
  `file_type` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件类型0未知1文本2视频3图片4文档5压缩包6文件夹',
  `file_size` bigint DEFAULT NULL COMMENT '文件大小单位字节',
  `upload_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `hash` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1' COMMENT '文件哈希值',
  `du_you` int DEFAULT NULL,
  `delete_id` int DEFAULT NULL COMMENT '逻辑删除id(默认0不删除，其他则为绑定回收站表)',
  `folder_belong_id` int DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_file
-- ----------------------------
INSERT INTO `tb_file` VALUES ('77', '1575320222104588299', null, '', 'gggg', 'folder', '0', '2022-11-09 18:32', null, '0', '0', '26');
INSERT INTO `tb_file` VALUES ('78', '1575320222104588299', null, '26\\', 'aa', 'folder', '0', '2022-11-09 18:00', null, '0', '0', '27');
INSERT INTO `tb_file` VALUES ('82', '1575320222104588299', null, '', 'gg', 'folder', '0', '2022-11-09 19:57', null, '0', '0', '28');

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
-- Records of tb_share
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `all_space` int NOT NULL,
  `used_space` int NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
