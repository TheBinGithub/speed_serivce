/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80021
Source Host           : localhost:3306
Source Database       : speed_db

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-10-20 14:28:44
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
-- Records of tb_account
-- ----------------------------
INSERT INTO `tb_account` VALUES ('1575320222104588295', '7C6449D0789AF37D0AAC20095F9B553A', '22', 'B61D1DBF-3265-487D-B305-47B29F1B74B3');
INSERT INTO `tb_account` VALUES ('1575320222104588296', 'F405DFE6AFEBE2B600C7F6D5311F53F0', 'd', 'E58FA7D1-B13A-4F5B-828C-7D10138A9259');
INSERT INTO `tb_account` VALUES ('1575320222104588297', '823ABDA8EEDDECCED52CFAE557B79753', 'dafran', 'E9E07892-0B4D-4886-88D7-6D2CE281E5CB');
INSERT INTO `tb_account` VALUES ('1575320222104588298', '04E40ABF04504A2EECA58590A087A7B1', '123', 'BC30444D-BBD4-4BBD-AF8C-76BDED2CE8BC');

-- ----------------------------
-- Table structure for tb_file
-- ----------------------------
DROP TABLE IF EXISTS `tb_file`;
CREATE TABLE `tb_file` (
  `file_id` int NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `user_id` int DEFAULT NULL,
  `du_you` double DEFAULT NULL,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件名称',
  `file_type` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件类型0未知1文本2视频3图片4文档5压缩包6文件夹',
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件的存放路径',
  `file_size` bigint DEFAULT NULL COMMENT '文件大小单位字节',
  `hash` varchar(255) DEFAULT NULL COMMENT '文件哈希值',
  `belong` varchar(255) DEFAULT NULL,
  `upload_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_file
-- ----------------------------

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
  `user_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `all_space` int NOT NULL,
  `used_space` int NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
