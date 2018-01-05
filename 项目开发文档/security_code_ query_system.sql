/*
Navicat MySQL Data Transfer

Source Server         : 本地MySQL
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : security_code_query_system

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2018-01-03 08:38:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for code
-- ----------------------------
DROP TABLE IF EXISTS `code`;
CREATE TABLE `code` (
  `id` varchar(255) NOT NULL,
  `code` varchar(255) DEFAULT NULL COMMENT '产品防伪码',
  `company` varchar(255) DEFAULT NULL COMMENT '产品生产公司',
  `queryTimes` int(11) DEFAULT '0' COMMENT '这个防伪码被查询的次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of code
-- ----------------------------
INSERT INTO `code` VALUES ('1', 'chuizi001001', '锤子科技有限公司', '0');
INSERT INTO `code` VALUES ('10', 'chuizi001010', '锤子科技有限公司', '0');
INSERT INTO `code` VALUES ('11', 'chuizi001011', '锤子科技有限公司', '0');
INSERT INTO `code` VALUES ('12', 'chuizi001012', '锤子科技有限公司', '0');
INSERT INTO `code` VALUES ('2', 'chuizi001002', '锤子科技有限公司', '0');
INSERT INTO `code` VALUES ('3', 'chuizi001003', '锤子科技有限公司', '0');
INSERT INTO `code` VALUES ('4', 'chuizi001004', '锤子科技有限公司', '0');
INSERT INTO `code` VALUES ('5', 'chuizi001005', '锤子科技有限公司', '0');
INSERT INTO `code` VALUES ('6', 'chuizi001006', '锤子科技有限公司', '0');
INSERT INTO `code` VALUES ('7', 'chuizi001007', '锤子科技有限公司', '0');
INSERT INTO `code` VALUES ('8', 'chuizi001008', '锤子科技有限公司', '0');
INSERT INTO `code` VALUES ('9', 'chuizi001009', '锤子科技有限公司', '0');

-- ----------------------------
-- Table structure for query
-- ----------------------------
DROP TABLE IF EXISTS `query`;
CREATE TABLE `query` (
  `id` varchar(255) NOT NULL,
  `code` varchar(255) DEFAULT NULL COMMENT '防伪码',
  `email` varchar(255) DEFAULT NULL COMMENT '查询防伪码提供的邮箱，用于接受查询结果',
  `time` varchar(255) DEFAULT NULL COMMENT '本次查询的时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of query
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL COMMENT '管理员用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '管理员密码',
  `email` varchar(255) DEFAULT NULL COMMENT '管理员安全验证邮箱',
  `securityCode` varchar(255) DEFAULT NULL COMMENT '安全验证码（用于登录验证等操作）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'xiaodong', '123456', 'zhuxiaodongwx@126.com', '2048');
