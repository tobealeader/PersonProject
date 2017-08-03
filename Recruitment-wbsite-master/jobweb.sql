/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : jobweb

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-11-30 13:42:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `legalname` varchar(50) DEFAULT NULL,
  `legalnumber` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `number` varchar(50) DEFAULT NULL,
  `status` varchar(250) DEFAULT NULL,
  `type` varchar(250) DEFAULT NULL,
  `user_userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9BDFD45DC3C45815` (`user_userid`),
  CONSTRAINT `FK9BDFD45DC3C45815` FOREIGN KEY (`user_userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('1', 'c1@qq.com', 'c1', '330327199512033839', '宁波海曙恒飞电子有限公司', '1001', 'true', 'IT/互联网', '2');
INSERT INTO `company` VALUES ('2', 'c2@qq.com', 'c2', '330327199512033839', '北京荣新广育科技有限公司', '1002', 'true', 'IT/互联网', '3');
INSERT INTO `company` VALUES ('3', 'c3@qq.com', 'c3', '330327199512033839', '北京中青中关村软件人才基地', '1003', 'true', 'IT/互联网', '4');
INSERT INTO `company` VALUES ('4', 'c4@qq.om', 'c4', '330327199512033839', '宁波炬星风云文化传媒有限公司', '1004', 'true', 'IT/互联网', '5');
INSERT INTO `company` VALUES ('5', 'c5@qq.com', 'c5', '330327199512033839', '宁波一品乌农业发展有限公司', '1005', 'true', '服务型', '6');

-- ----------------------------
-- Table structure for joblink
-- ----------------------------
DROP TABLE IF EXISTS `joblink`;
CREATE TABLE `joblink` (
  `linkid` int(11) NOT NULL AUTO_INCREMENT,
  `satues` varchar(50) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  `jobmessage_id` int(11) DEFAULT NULL,
  `resume_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`linkid`),
  KEY `FKD8EBD17E0D268AA` (`jobmessage_id`),
  KEY `FKD8EBD177E99CDCA` (`resume_id`),
  CONSTRAINT `FKD8EBD177E99CDCA` FOREIGN KEY (`resume_id`) REFERENCES `resume` (`id`),
  CONSTRAINT `FKD8EBD17E0D268AA` FOREIGN KEY (`jobmessage_id`) REFERENCES `jobmessage` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of joblink
-- ----------------------------
INSERT INTO `joblink` VALUES ('1', 'on', '2016-11-9', '6', '1');
INSERT INTO `joblink` VALUES ('2', 'true', '2016-11-9', '5', '1');
INSERT INTO `joblink` VALUES ('3', 'true', '2016-11-9', '4', '2');
INSERT INTO `joblink` VALUES ('4', 'false', '2016-11-9', '5', '3');
INSERT INTO `joblink` VALUES ('5', 'false', '2016-11-9', '4', '4');
INSERT INTO `joblink` VALUES ('6', 'true', '2016-11-9', '4', '1');
INSERT INTO `joblink` VALUES ('7', 'false', '2016-11-9', '8', '1');
INSERT INTO `joblink` VALUES ('8', 'false', '2016-11-29', '8', '1');

-- ----------------------------
-- Table structure for jobmessage
-- ----------------------------
DROP TABLE IF EXISTS `jobmessage`;
CREATE TABLE `jobmessage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(250) DEFAULT NULL,
  `date` varchar(250) DEFAULT NULL,
  `duty` longtext,
  `position` varchar(250) DEFAULT NULL,
  `tell` varchar(250) DEFAULT NULL,
  `type` varchar(250) DEFAULT NULL,
  `user_userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE40501CAC3C45815` (`user_userid`),
  CONSTRAINT `FKE40501CAC3C45815` FOREIGN KEY (`user_userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jobmessage
-- ----------------------------
INSERT INTO `jobmessage` VALUES ('1', '海曙区镇明路332号 ( 宁波 - 海曙 - 鼓楼 )', '2016-11-9', '1、按照领班安排认真做好桌椅、餐厅卫生，餐厅铺台，准备好各种用品，确保正常营业使用。\n2、接待顾客应主动、热情、礼貌、耐心、周到，使顾客有宾至如归之感；\n3、运用礼貌语言，为客人提供最佳服务，', '餐厅服务员+底薪+提成', '17858936127', '服务员', '6');
INSERT INTO `jobmessage` VALUES ('2', '海曙区镇明路332号 ( 宁波 - 海曙 - 鼓楼 )', '2016-11-9', '1、按照领班安排认真做好桌椅、餐厅卫生，餐厅铺台，准备好各种用品，确保正常营业使用。\n2、接待顾客应主动、热情、礼貌、耐心、周到，使顾客有宾至如归之感；\n3、运用礼貌语言，为客人提供最佳服务，\n4、善于向顾客介绍和推销本餐厅饮品及特色菜点；', '餐厅前台+底薪+提成', '17858936127', '服务员', '6');
INSERT INTO `jobmessage` VALUES ('3', ' 宁波海曙药行街35号灵桥广场6楼F9 ', '2016-11-9', '公司热爱相关行业人员，为人踏实肯干，虚心好学，动手能力强，敬业爱岗。（热爱本行工作者无经验可培养）主要工作范围：智能化弱电施工、综合布线，门禁，监控、智能产品安装调试维修等等', '监控报警安装技术员及学徒', '17858936127', '服务员', '2');
INSERT INTO `jobmessage` VALUES ('4', ' 宁波海曙药行街35号灵桥广场6楼F9', '2016-11-9', '英语水平要在6级以上，非诚勿扰', '英语翻译员', '17858936127', '翻译', '2');
INSERT INTO `jobmessage` VALUES ('5', '北京市海淀区中关村 ( 北京 )', '2016-11-9', '1、理工科专业毕业，所含专业包括计算机（网络)、电子信息、软件工程、（电气）自动化、测控、生仪、机电等。\n.2、有计算机语言基础，如：JAVA、.C语言、C++、C#、.Net、PHP、html、asp等\n3、在京工作一年后要求回当地工作的，可申请调回当地会城市的分公司或合作企业工作。\n4、入职前同意参加中心统一组织的三到四个月的岗前项目实训。', '软件工程师.Java急招', '17858936127', 'IT/互联网', '3');
INSERT INTO `jobmessage` VALUES ('6', '北京中青中关村软件人才基地', '2016-11-9', '职位描述：在互联网时代，javaEE技术体系毫无疑问的成为了服务器端编程领域的王者，在未来新的业务领域有着更辉煌的发展前景，可以从事金融、互联网、电商、医疗等行业的核心软件系统开发。构建基于Hadoop、spark、Storm等大数据核心技术的商业支撑系统。', 'Java大数据开发定岗生', '17858936127', 'IT/互联网', '4');
INSERT INTO `jobmessage` VALUES ('7', '宁波鄞州前河南路雷孟德旅游大厦703室', '2016-11-9', '1. 负责电脑、服务器、网络设备、办公设备的安装、维护和故障处理；\n2. 负责对公司网络服务和服务器的日常管理和维护；\n3. 负责办公室电脑及相关硬件设备的管理和维护；确保办公区域网络稳定；\n4. 负责办公各类软件的维护和更新；\n5. 公司数据库的管理和维护，电脑等电子设备的盘点和管理；\n6. 完成领导交办的其他工作。', '网络运营', '17858936127', 'IT/互联网', '5');
INSERT INTO `jobmessage` VALUES ('8', '北京市海淀区中关村 ', '2016-11-9', '需要精通程序语言', '程序员', '17642345623', 'IT/互联网', '2');

-- ----------------------------
-- Table structure for resume
-- ----------------------------
DROP TABLE IF EXISTS `resume`;
CREATE TABLE `resume` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(200) DEFAULT NULL,
  `birth` varchar(50) DEFAULT NULL,
  `date` varchar(200) DEFAULT NULL,
  `education` varchar(50) DEFAULT NULL,
  `english` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `photo` varchar(50) DEFAULT NULL,
  `project` longtext,
  `remail` varchar(200) DEFAULT NULL,
  `remajor` varchar(200) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `tell` varchar(50) DEFAULT NULL,
  `university` varchar(50) DEFAULT NULL,
  `user_userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK91B2B44DC3C45815` (`user_userid`),
  CONSTRAINT `FK91B2B44DC3C45815` FOREIGN KEY (`user_userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resume
-- ----------------------------
INSERT INTO `resume` VALUES ('1', '宁波', '1995', '2016-11-9', '本科', '英语四级', '东东', 'java开发', '精通java开发', '1427594010@qq.com', '计算机', 'boy', '17858936127', '宁波理工', '9');
INSERT INTO `resume` VALUES ('2', '宁波', '1994', '2016-11-9', '本科', '英语六级', '芳芳', '翻译', '精通英语翻译', '1427594010@qq.com', '英语专业', 'girl', '17858936127', '宁波理工', '10');
INSERT INTO `resume` VALUES ('3', '宁波', '1994', '2016-11-9', '本科', '英语六级', '芳芳', 'javaweb开发', '精通javaweb开发', '1427594010@qq.com', '计算机专业', 'girl', '17858936127', '宁波理工', '10');
INSERT INTO `resume` VALUES ('4', '天水', '1997', '2016-11-9', '本科', '英语四级', '飒飒', '翻译员啊', '做好人', '111@qq.com', '翻译', 'girl', '17858923345', '阿斯顿', '9');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `photo` varchar(250) DEFAULT NULL,
  `userdate` varchar(30) DEFAULT NULL,
  `useremail` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `userpwd` varchar(50) DEFAULT NULL,
  `usertype` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', 'upload/1480431734738bei.jpg', '2016-11-9', 'c1@qq.com', 'c1', 'c1', 'company');
INSERT INTO `user` VALUES ('3', 'upload/1478692718550bei.jpg', '2016-11-9', 'c2@qq.com', 'c2', 'c2', 'company');
INSERT INTO `user` VALUES ('4', null, '2016-11-9', 'c3@qq.com', 'c3', 'c3', 'company');
INSERT INTO `user` VALUES ('5', 'upload/1478692862516bei.jpg', '2016-11-9', 'c4@qq.com', 'c4', 'c4', 'company');
INSERT INTO `user` VALUES ('6', null, '2016-11-9', 'c5@qq.com', 'c5', 'c5', 'company');
INSERT INTO `user` VALUES ('7', null, '2016-11-9', 'c6@qq.com', 'c6', 'c6', 'company');
INSERT INTO `user` VALUES ('8', null, '2016-11-9', 'c7@qq.com', 'c7', 'c7', 'company');
INSERT INTO `user` VALUES ('9', 'upload/1478698252790b1.jpg', '2016-11-9', 'u1@qq.com', 'u1', 'u1', 'person');
INSERT INTO `user` VALUES ('10', 'upload/1478693819204bei.jpg', '2016-11-9', 'u2@qq.com', 'u2', 'u2', 'person');
INSERT INTO `user` VALUES ('11', null, '2016-11-9', 'u3@qq.com', 'u3', 'u3', 'person');
