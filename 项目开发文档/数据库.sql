/*
MySQL Backup
Source Server Version: 5.5.49
Source Database: security_code_ query_system
Date: 2017/12/27 22:02:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `code`
-- ----------------------------
DROP TABLE IF EXISTS `code`;
CREATE TABLE `code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '产品防伪码',
  `company` varchar(255) DEFAULT NULL COMMENT '产品生产公司',
  `queryTimes` int(11) DEFAULT '0' COMMENT '这个防伪码被查询的次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `query`
-- ----------------------------
DROP TABLE IF EXISTS `query`;
CREATE TABLE `query` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '防伪码',
  `email` varchar(255) DEFAULT NULL COMMENT '查询防伪码提供的邮箱，用于接受查询结果',
  `time` varchar(255) DEFAULT NULL COMMENT '本次查询的时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user`
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
--  Records 
-- ----------------------------
INSERT INTO `code` VALUES ('1','chuizi001001','锤子科技有限公司','0'), ('2','chuizi001002','锤子科技有限公司','0'), ('3','chuizi001003','锤子科技有限公司','0'), ('4','chuizi001004','锤子科技有限公司','0'), ('5','chuizi001005','锤子科技有限公司','0'), ('6','chuizi001006','锤子科技有限公司','0'), ('7','chuizi001007','锤子科技有限公司','0'), ('8','chuizi001008','锤子科技有限公司','0'), ('9','chuizi001009','锤子科技有限公司','0'), ('10','chuizi001010','锤子科技有限公司','0'), ('11','chuizi001011','锤子科技有限公司','0'), ('12','chuizi001012','锤子科技有限公司','0');
INSERT INTO `user` VALUES ('1','xiaodong','123456','zhuxiaodongwx@126.com','2048');
