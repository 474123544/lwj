-- MySQL dump 10.13  Distrib 5.7.44, for Linux (x86_64)
--
-- Host: localhost    Database: cl515882190
-- ------------------------------------------------------
-- Server version	5.7.44

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `cl515882190`
--

/*!40000 DROP DATABASE IF EXISTS `cl515882190`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `cl515882190` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `cl515882190`;

--
-- Table structure for table `chat_friend`
--

DROP TABLE IF EXISTS `chat_friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_friend` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `fid` bigint(20) NOT NULL COMMENT '好友id',
  `name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `picture` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `role` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色',
  `tablename` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '表名',
  `alias` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '别名',
  `type` int(11) DEFAULT '0' COMMENT '类型(0:好友申请，1:好友，2:消息)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='好友表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_friend`
--

LOCK TABLES `chat_friend` WRITE;
/*!40000 ALTER TABLE `chat_friend` DISABLE KEYS */;
INSERT INTO `chat_friend` VALUES (1,'2025-03-27 07:44:25',1,1,'名称1','file/chat_friendPicture1.jpg,file/chat_friendPicture2.jpg,file/chat_friendPicture3.jpg','角色1','表名1','别名1',1),(2,'2025-03-27 07:44:25',2,2,'名称2','file/chat_friendPicture2.jpg,file/chat_friendPicture3.jpg,file/chat_friendPicture4.jpg','角色2','表名2','别名2',2),(3,'2025-03-27 07:44:25',3,3,'名称3','file/chat_friendPicture3.jpg,file/chat_friendPicture4.jpg,file/chat_friendPicture5.jpg','角色3','表名3','别名3',3),(4,'2025-03-27 07:44:25',4,4,'名称4','file/chat_friendPicture4.jpg,file/chat_friendPicture5.jpg,file/chat_friendPicture6.jpg','角色4','表名4','别名4',4),(5,'2025-03-27 07:44:25',5,5,'名称5','file/chat_friendPicture5.jpg,file/chat_friendPicture6.jpg,file/chat_friendPicture7.jpg','角色5','表名5','别名5',5),(6,'2025-03-27 07:44:25',6,6,'名称6','file/chat_friendPicture6.jpg,file/chat_friendPicture7.jpg,file/chat_friendPicture8.jpg','角色6','表名6','别名6',6),(7,'2025-03-27 07:44:25',7,7,'名称7','file/chat_friendPicture7.jpg,file/chat_friendPicture8.jpg,file/chat_friendPicture9.jpg','角色7','表名7','别名7',7),(8,'2025-03-27 07:44:25',8,8,'名称8','file/chat_friendPicture8.jpg,file/chat_friendPicture9.jpg,file/chat_friendPicture10.jpg','角色8','表名8','别名8',8),(9,'2025-03-27 07:44:25',9,9,'名称9','file/chat_friendPicture9.jpg,file/chat_friendPicture10.jpg,file/chat_friendPicture11.jpg','角色9','表名9','别名9',9),(10,'2025-03-27 07:44:25',10,10,'名称10','file/chat_friendPicture10.jpg,file/chat_friendPicture11.jpg,file/chat_friendPicture12.jpg','角色10','表名10','别名10',10),(11,'2025-03-27 08:19:05',1743063530818,91,'医生账号1','file/yishengTouxiang1.jpg',NULL,'yisheng',NULL,2),(12,'2025-03-27 08:19:05',91,1743063530818,'11','file/1743063525877.jpg',NULL,'yonghu',NULL,2);
/*!40000 ALTER TABLE `chat_friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_message`
--

DROP TABLE IF EXISTS `chat_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `fid` bigint(20) NOT NULL COMMENT '好友id',
  `content` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内容',
  `format` int(11) DEFAULT NULL COMMENT '格式(1:文字，2:图片)',
  `is_read` int(11) DEFAULT '0' COMMENT '消息已读(0:未读，1:已读)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='消息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_message`
--

LOCK TABLES `chat_message` WRITE;
/*!40000 ALTER TABLE `chat_message` DISABLE KEYS */;
INSERT INTO `chat_message` VALUES (1,'2025-03-27 07:44:25',1,1,'内容1',1,1),(2,'2025-03-27 07:44:25',2,2,'内容2',2,2),(3,'2025-03-27 07:44:25',3,3,'内容3',3,3),(4,'2025-03-27 07:44:25',4,4,'内容4',4,4),(5,'2025-03-27 07:44:25',5,5,'内容5',5,5),(6,'2025-03-27 07:44:25',6,6,'内容6',6,6),(7,'2025-03-27 07:44:25',7,7,'内容7',7,7),(8,'2025-03-27 07:44:25',8,8,'内容8',8,8),(9,'2025-03-27 07:44:25',9,9,'内容9',9,9),(10,'2025-03-27 07:44:25',10,10,'内容10',10,10),(11,'2025-03-27 08:19:05',1743063530818,91,'你好呀',1,1),(12,'2025-03-27 08:21:19',91,1743063530818,'好呀',1,1);
/*!40000 ALTER TABLE `chat_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chufangxinxi`
--

DROP TABLE IF EXISTS `chufangxinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chufangxinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yaopinmingcheng` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '药品名称',
  `danjia` double NOT NULL COMMENT '单价',
  `kaifangshuliang` int(11) NOT NULL COMMENT '开方数量',
  `fuyongfangfa` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '服用方法',
  `qitafeiyong` double DEFAULT NULL COMMENT '其他费用',
  `xujiaojine` double DEFAULT NULL COMMENT '需缴金额',
  `zhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账号',
  `shouji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机',
  `kaifangshijian` datetime DEFAULT NULL COMMENT '开方时间',
  `yishengzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '医生账号',
  `dianhua` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `qita` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '其他',
  `ispay` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '未支付' COMMENT '是否支付',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='处方信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chufangxinxi`
--

LOCK TABLES `chufangxinxi` WRITE;
/*!40000 ALTER TABLE `chufangxinxi` DISABLE KEYS */;
INSERT INTO `chufangxinxi` VALUES (1,'2025-03-27 07:44:24','药品名称1',1,1,'服用方法1',1,1,'账号1','手机1','2025-03-27 15:44:24','医生账号1','电话1','其他1','未支付'),(2,'2025-03-27 07:44:24','药品名称2',2,2,'服用方法2',2,2,'账号2','手机2','2025-03-27 15:44:24','医生账号2','电话2','其他2','未支付'),(3,'2025-03-27 07:44:24','药品名称3',3,3,'服用方法3',3,3,'账号3','手机3','2025-03-27 15:44:24','医生账号3','电话3','其他3','未支付'),(4,'2025-03-27 07:44:24','药品名称4',4,4,'服用方法4',4,4,'账号4','手机4','2025-03-27 15:44:24','医生账号4','电话4','其他4','未支付'),(5,'2025-03-27 07:44:24','药品名称5',5,5,'服用方法5',5,5,'账号5','手机5','2025-03-27 15:44:24','医生账号5','电话5','其他5','未支付'),(6,'2025-03-27 07:44:24','药品名称6',6,6,'服用方法6',6,6,'账号6','手机6','2025-03-27 15:44:24','医生账号6','电话6','其他6','未支付'),(7,'2025-03-27 07:44:24','药品名称7',7,7,'服用方法7',7,7,'账号7','手机7','2025-03-27 15:44:24','医生账号7','电话7','其他7','未支付'),(8,'2025-03-27 07:44:24','药品名称8',8,8,'服用方法8',8,8,'账号8','手机8','2025-03-27 15:44:24','医生账号8','电话8','其他8','未支付'),(9,'2025-03-27 07:44:24','药品名称9',9,9,'服用方法9',9,9,'账号9','手机9','2025-03-27 15:44:24','医生账号9','电话9','其他9','未支付'),(10,'2025-03-27 07:44:24','药品名称10',10,10,'服用方法10',10,10,'账号10','手机10','2025-03-27 15:44:24','医生账号10','电话10','其他10','未支付'),(11,'2025-03-27 08:20:34','药品名称1',1,11,'11',11,22,'11','13623666666','2025-03-27 16:20:27','医生账号1','19819881111','11','已支付');
/*!40000 ALTER TABLE `chufangxinxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chukujilu`
--

DROP TABLE IF EXISTS `chukujilu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chukujilu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yaopinmingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '药品名称',
  `kucun` int(11) DEFAULT NULL COMMENT '出库数量',
  `chukushijian` datetime DEFAULT NULL COMMENT '出库时间',
  `chukubeizhu` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '出库备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='出库记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chukujilu`
--

LOCK TABLES `chukujilu` WRITE;
/*!40000 ALTER TABLE `chukujilu` DISABLE KEYS */;
INSERT INTO `chukujilu` VALUES (1,'2025-03-27 07:44:25','药品名称1',1,'2025-03-27 15:44:25','出库备注1'),(2,'2025-03-27 07:44:25','药品名称2',2,'2025-03-27 15:44:25','出库备注2'),(3,'2025-03-27 07:44:25','药品名称3',3,'2025-03-27 15:44:25','出库备注3'),(4,'2025-03-27 07:44:25','药品名称4',4,'2025-03-27 15:44:25','出库备注4'),(5,'2025-03-27 07:44:25','药品名称5',5,'2025-03-27 15:44:25','出库备注5'),(6,'2025-03-27 07:44:25','药品名称6',6,'2025-03-27 15:44:25','出库备注6'),(7,'2025-03-27 07:44:25','药品名称7',7,'2025-03-27 15:44:25','出库备注7'),(8,'2025-03-27 07:44:25','药品名称8',8,'2025-03-27 15:44:25','出库备注8'),(9,'2025-03-27 07:44:25','药品名称9',9,'2025-03-27 15:44:25','出库备注9'),(10,'2025-03-27 07:44:25','药品名称10',10,'2025-03-27 15:44:25','出库备注10'),(11,'2025-03-27 08:22:12','药品名称9',1,'2025-03-27 16:22:09','');
/*!40000 ALTER TABLE `chukujilu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `value` longtext COLLATE utf8mb4_unicode_ci COMMENT '值',
  `url` longtext COLLATE utf8mb4_unicode_ci COMMENT '链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='轮播图';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` VALUES (1,'2025-03-27 07:44:25','swiper1','file/swiperPicture1.jpg',NULL),(2,'2025-03-27 07:44:25','swiper2','file/swiperPicture2.jpg',NULL),(3,'2025-03-27 07:44:25','swiper3','file/1743063777062.jpg',NULL);
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friendship_link`
--

DROP TABLE IF EXISTS `friendship_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friendship_link` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `link_name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '链接名称',
  `link_image` longtext COLLATE utf8mb4_unicode_ci COMMENT '链接图片',
  `link_url` longtext COLLATE utf8mb4_unicode_ci COMMENT '链接地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='友情链接';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friendship_link`
--

LOCK TABLES `friendship_link` WRITE;
/*!40000 ALTER TABLE `friendship_link` DISABLE KEYS */;
INSERT INTO `friendship_link` VALUES (1,'2025-03-27 07:44:24','CSDN','file/fl1.jpg','https://www.csdn.net/'),(2,'2025-03-27 07:44:24','凤凰资讯','file/fl2.jpg','https://news.ifeng.com/'),(3,'2025-03-27 07:44:24','网易云音乐','file/fl3.jpg','https://music.163.com/'),(4,'2025-03-27 07:44:24','抖音','file/fl4.jpg','https://www.douyin.com/'),(5,'2025-03-27 07:44:24','麦当劳','file/fl5.jpg','https://www.mcdonalds.com.cn/'),(6,'2025-03-27 07:44:24','芒果TV','file/fl6.jpg','https://www.mgtv.com/'),(7,'2025-03-27 07:44:24','天猫','file/fl7.jpg','https://www.tmall.com/'),(8,'2025-03-27 07:44:24','携程','file/fl8.jpg','https://www.ctrip.com/'),(9,'2025-03-27 07:44:24','优酷','file/fl9.jpg','https://www.youku.com/'),(10,'2025-03-27 07:44:24','Bilibili','file/fl10.jpg','https://www.bilibili.com/');
/*!40000 ALTER TABLE `friendship_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jiuzhenqiandao`
--

DROP TABLE IF EXISTS `jiuzhenqiandao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jiuzhenqiandao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jiuzhenbianhao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '就诊编号',
  `yishengzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '医生账号',
  `dianhua` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `jiuzhenshijian` datetime DEFAULT NULL COMMENT '就诊时间',
  `shouji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机',
  `zhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账号',
  `beizhu` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `jiuzhenbianhao` (`jiuzhenbianhao`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='就诊签到';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jiuzhenqiandao`
--

LOCK TABLES `jiuzhenqiandao` WRITE;
/*!40000 ALTER TABLE `jiuzhenqiandao` DISABLE KEYS */;
INSERT INTO `jiuzhenqiandao` VALUES (1,'2025-03-27 07:44:24','1111111111','医生账号1','电话1','2025-03-27 15:44:24','手机1','账号1','备注1'),(2,'2025-03-27 07:44:24','2222222222','医生账号2','电话2','2025-03-27 15:44:24','手机2','账号2','备注2'),(3,'2025-03-27 07:44:24','3333333333','医生账号3','电话3','2025-03-27 15:44:24','手机3','账号3','备注3'),(4,'2025-03-27 07:44:24','4444444444','医生账号4','电话4','2025-03-27 15:44:24','手机4','账号4','备注4'),(5,'2025-03-27 07:44:24','5555555555','医生账号5','电话5','2025-03-27 15:44:24','手机5','账号5','备注5'),(6,'2025-03-27 07:44:24','6666666666','医生账号6','电话6','2025-03-27 15:44:24','手机6','账号6','备注6'),(7,'2025-03-27 07:44:24','7777777777','医生账号7','电话7','2025-03-27 15:44:24','手机7','账号7','备注7'),(8,'2025-03-27 07:44:24','8888888888','医生账号8','电话8','2025-03-27 15:44:24','手机8','账号8','备注8'),(9,'2025-03-27 07:44:24','9999999999','医生账号9','电话9','2025-03-27 15:44:24','手机9','账号9','备注9'),(10,'2025-03-27 07:44:24','11111111110','医生账号10','电话10','2025-03-27 15:44:24','手机10','账号10','备注10'),(11,'2025-03-27 08:20:11','1743063608734','医生账号1','19819881111','2025-03-29 08:00:00','13623666666','11','11');
/*!40000 ALTER TABLE `jiuzhenqiandao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jiuzhentongzhi`
--

DROP TABLE IF EXISTS `jiuzhentongzhi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jiuzhentongzhi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `tongzhibianhao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '通知编号',
  `yishengzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '医生账号',
  `dianhua` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `jiuzhenshijian` datetime DEFAULT NULL COMMENT '就诊时间',
  `tongzhishijian` datetime DEFAULT NULL COMMENT '通知时间',
  `zhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账号',
  `shouji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机',
  `tongzhibeizhu` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '通知备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tongzhibianhao` (`tongzhibianhao`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='就诊通知';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jiuzhentongzhi`
--

LOCK TABLES `jiuzhentongzhi` WRITE;
/*!40000 ALTER TABLE `jiuzhentongzhi` DISABLE KEYS */;
INSERT INTO `jiuzhentongzhi` VALUES (1,'2025-03-27 07:44:24','1111111111','医生账号1','电话1','2025-03-27 15:44:24','2025-03-27 15:44:24','账号1','手机1','通知备注1'),(2,'2025-03-27 07:44:24','2222222222','医生账号2','电话2','2025-03-27 15:44:24','2025-03-27 15:44:24','账号2','手机2','通知备注2'),(3,'2025-03-27 07:44:24','3333333333','医生账号3','电话3','2025-03-27 15:44:24','2025-03-27 15:44:24','账号3','手机3','通知备注3'),(4,'2025-03-27 07:44:24','4444444444','医生账号4','电话4','2025-03-27 15:44:24','2025-03-27 15:44:24','账号4','手机4','通知备注4'),(5,'2025-03-27 07:44:24','5555555555','医生账号5','电话5','2025-03-27 15:44:24','2025-03-27 15:44:24','账号5','手机5','通知备注5'),(6,'2025-03-27 07:44:24','6666666666','医生账号6','电话6','2025-03-27 15:44:24','2025-03-27 15:44:24','账号6','手机6','通知备注6'),(7,'2025-03-27 07:44:24','7777777777','医生账号7','电话7','2025-03-27 15:44:24','2025-03-27 15:44:24','账号7','手机7','通知备注7'),(8,'2025-03-27 07:44:24','8888888888','医生账号8','电话8','2025-03-27 15:44:24','2025-03-27 15:44:24','账号8','手机8','通知备注8'),(9,'2025-03-27 07:44:24','9999999999','医生账号9','电话9','2025-03-27 15:44:24','2025-03-27 15:44:24','账号9','手机9','通知备注9'),(10,'2025-03-27 07:44:24','11111111110','医生账号10','电话10','2025-03-27 15:44:24','2025-03-27 15:44:24','账号10','手机10','通知备注10'),(11,'2025-03-27 08:20:02','1743063594371','医生账号1','19819881111','2025-03-29 08:00:00','2025-03-27 16:19:54','11','13623666666','11');
/*!40000 ALTER TABLE `jiuzhentongzhi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `menujson` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'2025-03-27 07:44:25','[{\"backMenu\":[{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-send\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"users\",\"menu\":\"管理员\",\"menuJump\":\"列表\",\"tableName\":\"users\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"私信\"],\"appFrontIcon\":\"cuIcon-pic\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"yonghu\",\"menu\":\"用户\",\"menuJump\":\"列表\",\"tableName\":\"yonghu\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"私信\",\"预约\"],\"appFrontIcon\":\"cuIcon-addressbook\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"yisheng\",\"menu\":\"医生\",\"menuJump\":\"列表\",\"tableName\":\"yisheng\"}],\"fontClass\":\"icon-user7\",\"menu\":\"用户管理\",\"unicode\":\"&#xef9d;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"药品库存统计\",\"首页总数\",\"首页统计\",\"入库\",\"出库\"],\"appFrontIcon\":\"cuIcon-qrcode\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"药品库存统计\",\"首页总数\",\"首页统计\",\"入库\",\"出库\"],\"classname\":\"yaopinxinxi\",\"menu\":\"药品信息\",\"menuJump\":\"列表\",\"tableName\":\"yaopinxinxi\"}],\"fontClass\":\"icon-common47\",\"menu\":\"药品信息管理\",\"unicode\":\"&#xef63;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-circle\",\"buttons\":[\"查看\",\"修改\",\"删除\"],\"classname\":\"rukujilu\",\"menu\":\"入库记录\",\"menuJump\":\"列表\",\"tableName\":\"rukujilu\"}],\"fontClass\":\"icon-common44\",\"menu\":\"入库记录管理\",\"unicode\":\"&#xef28;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-attentionfavor\",\"buttons\":[\"查看\",\"修改\",\"删除\"],\"classname\":\"chukujilu\",\"menu\":\"出库记录\",\"menuJump\":\"列表\",\"tableName\":\"chukujilu\"}],\"fontClass\":\"icon-common1\",\"menu\":\"出库记录管理\",\"unicode\":\"&#xeda3;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"医生预约统计\",\"首页总数\",\"首页统计\",\"通知\"],\"appFrontIcon\":\"cuIcon-flashlightopen\",\"buttons\":[\"查看\",\"修改\",\"删除\",\"医生预约统计\",\"首页总数\",\"首页统计\"],\"classname\":\"yishengyuyue\",\"menu\":\"医生预约\",\"menuJump\":\"列表\",\"tableName\":\"yishengyuyue\"}],\"fontClass\":\"icon-common38\",\"menu\":\"医生预约管理\",\"unicode\":\"&#xeeb2;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"签到\"],\"appFrontIcon\":\"cuIcon-pic\",\"buttons\":[\"查看\",\"修改\",\"删除\"],\"classname\":\"jiuzhentongzhi\",\"menu\":\"就诊通知\",\"menuJump\":\"列表\",\"tableName\":\"jiuzhentongzhi\"}],\"fontClass\":\"icon-common39\",\"menu\":\"就诊通知管理\",\"unicode\":\"&#xeeba;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"诊断\"],\"appFrontIcon\":\"cuIcon-present\",\"buttons\":[\"查看\",\"修改\",\"删除\"],\"classname\":\"jiuzhenqiandao\",\"menu\":\"就诊签到\",\"menuJump\":\"列表\",\"tableName\":\"jiuzhenqiandao\"}],\"fontClass\":\"icon-common36\",\"menu\":\"就诊签到管理\",\"unicode\":\"&#xee9f;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"开方\"],\"appFrontIcon\":\"cuIcon-vip\",\"buttons\":[\"查看\",\"修改\",\"删除\"],\"classname\":\"zhenduanbingli\",\"menu\":\"诊断病历\",\"menuJump\":\"列表\",\"tableName\":\"zhenduanbingli\"}],\"fontClass\":\"icon-common45\",\"menu\":\"诊断病历管理\",\"unicode\":\"&#xef3b;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"支付\",\"缴费统计\",\"首页总数\",\"首页统计\"],\"appFrontIcon\":\"cuIcon-phone\",\"buttons\":[\"查看\",\"修改\",\"删除\",\"缴费统计\",\"首页总数\",\"首页统计\"],\"classname\":\"chufangxinxi\",\"menu\":\"处方信息\",\"menuJump\":\"列表\",\"tableName\":\"chufangxinxi\"}],\"fontClass\":\"icon-common20\",\"menu\":\"处方信息管理\",\"unicode\":\"&#xee02;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-cardboard\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"paibanxinxi\",\"menu\":\"排班信息\",\"menuJump\":\"列表\",\"tableName\":\"paibanxinxi\"}],\"fontClass\":\"icon-common7\",\"menu\":\"排班信息管理\",\"unicode\":\"&#xedb4;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"回复\"],\"appFrontIcon\":\"cuIcon-keyboard\",\"buttons\":[\"查看\",\"修改\",\"删除\",\"回复\"],\"classname\":\"messages\",\"menu\":\"反馈建议\",\"menuJump\":\"列表\",\"tableName\":\"messages\"}],\"fontClass\":\"icon-common33\",\"menu\":\"反馈建议管理\",\"unicode\":\"&#xee6a;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-copy\",\"buttons\":[\"查看\",\"修改\"],\"classname\":\"config\",\"menu\":\"轮播图\",\"menuJump\":\"列表\",\"tableName\":\"config\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-goodsnew\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"news\",\"menu\":\"健康知识\",\"menuJump\":\"列表\",\"tableName\":\"news\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-rank\",\"buttons\":[\"查看\",\"修改\",\"删除\"],\"classname\":\"syslog\",\"menu\":\"操作日志\",\"menuJump\":\"列表\",\"tableName\":\"syslog\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-present\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"friendshipLink\",\"menu\":\"友情链接\",\"menuJump\":\"列表\",\"tableName\":\"friendship_link\"}],\"fontClass\":\"icon-common41\",\"menu\":\"系统管理\",\"unicode\":\"&#xeede;\"}],\"frontMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-vipcard\",\"buttons\":[\"查看\",\"私信\",\"预约\"],\"classname\":\"yisheng\",\"menu\":\"医生\",\"menuJump\":\"列表\",\"tableName\":\"yisheng\"}],\"menu\":\"医生\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-camera\",\"buttons\":[\"查看\",\"新增\"],\"classname\":\"messages\",\"menu\":\"反馈建议\",\"menuJump\":\"列表\",\"tableName\":\"messages\"}],\"menu\":\"反馈建议\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-brand\",\"buttons\":[\"查看\"],\"classname\":\"news\",\"menu\":\"健康知识\",\"menuJump\":\"列表\",\"tableName\":\"news\"}],\"menu\":\"公告资讯\"}],\"hasBackLogin\":\"是\",\"hasBackRegister\":\"否\",\"hasFrontLogin\":\"否\",\"hasFrontRegister\":\"否\",\"pathName\":\"users\",\"roleName\":\"管理员\",\"tableName\":\"users\"},{\"backMenu\":[{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"医生预约统计\",\"首页总数\",\"首页统计\",\"通知\"],\"appFrontIcon\":\"cuIcon-flashlightopen\",\"buttons\":[\"查看\"],\"classname\":\"yishengyuyue\",\"menu\":\"医生预约\",\"menuJump\":\"列表\",\"tableName\":\"yishengyuyue\"}],\"fontClass\":\"icon-common38\",\"menu\":\"医生预约管理\",\"unicode\":\"&#xeeb2;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"签到\"],\"appFrontIcon\":\"cuIcon-pic\",\"buttons\":[\"查看\",\"签到\"],\"classname\":\"jiuzhentongzhi\",\"menu\":\"就诊通知\",\"menuJump\":\"列表\",\"tableName\":\"jiuzhentongzhi\"}],\"fontClass\":\"icon-common39\",\"menu\":\"就诊通知管理\",\"unicode\":\"&#xeeba;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"诊断\"],\"appFrontIcon\":\"cuIcon-present\",\"buttons\":[\"查看\"],\"classname\":\"jiuzhenqiandao\",\"menu\":\"就诊签到\",\"menuJump\":\"列表\",\"tableName\":\"jiuzhenqiandao\"}],\"fontClass\":\"icon-common36\",\"menu\":\"就诊签到管理\",\"unicode\":\"&#xee9f;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"开方\"],\"appFrontIcon\":\"cuIcon-vip\",\"buttons\":[\"查看\"],\"classname\":\"zhenduanbingli\",\"menu\":\"诊断病历\",\"menuJump\":\"列表\",\"tableName\":\"zhenduanbingli\"}],\"fontClass\":\"icon-common45\",\"menu\":\"诊断病历管理\",\"unicode\":\"&#xef3b;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"支付\",\"缴费统计\",\"首页总数\",\"首页统计\"],\"appFrontIcon\":\"cuIcon-phone\",\"buttons\":[\"查看\",\"支付\"],\"classname\":\"chufangxinxi\",\"menu\":\"处方信息\",\"menuJump\":\"列表\",\"tableName\":\"chufangxinxi\"}],\"fontClass\":\"icon-common20\",\"menu\":\"处方信息管理\",\"unicode\":\"&#xee02;\"}],\"frontMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-vipcard\",\"buttons\":[\"查看\",\"私信\",\"预约\"],\"classname\":\"yisheng\",\"menu\":\"医生\",\"menuJump\":\"列表\",\"tableName\":\"yisheng\"}],\"menu\":\"医生\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-camera\",\"buttons\":[\"查看\",\"新增\"],\"classname\":\"messages\",\"menu\":\"反馈建议\",\"menuJump\":\"列表\",\"tableName\":\"messages\"}],\"menu\":\"反馈建议\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-brand\",\"buttons\":[\"查看\"],\"classname\":\"news\",\"menu\":\"健康知识\",\"menuJump\":\"列表\",\"tableName\":\"news\"}],\"menu\":\"公告资讯\"}],\"hasBackLogin\":\"否\",\"hasBackRegister\":\"否\",\"hasFrontLogin\":\"是\",\"hasFrontRegister\":\"是\",\"pathName\":\"yonghu\",\"roleName\":\"用户\",\"tableName\":\"yonghu\"},{\"backMenu\":[{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"医生预约统计\",\"首页总数\",\"首页统计\",\"通知\"],\"appFrontIcon\":\"cuIcon-flashlightopen\",\"buttons\":[\"查看\",\"审核\",\"医生预约统计\",\"首页总数\",\"首页统计\",\"通知\"],\"classname\":\"yishengyuyue\",\"menu\":\"医生预约\",\"menuJump\":\"列表\",\"tableName\":\"yishengyuyue\"}],\"fontClass\":\"icon-common38\",\"menu\":\"医生预约管理\",\"unicode\":\"&#xeeb2;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"签到\"],\"appFrontIcon\":\"cuIcon-pic\",\"buttons\":[\"查看\"],\"classname\":\"jiuzhentongzhi\",\"menu\":\"就诊通知\",\"menuJump\":\"列表\",\"tableName\":\"jiuzhentongzhi\"}],\"fontClass\":\"icon-common39\",\"menu\":\"就诊通知管理\",\"unicode\":\"&#xeeba;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"诊断\"],\"appFrontIcon\":\"cuIcon-present\",\"buttons\":[\"查看\",\"诊断\"],\"classname\":\"jiuzhenqiandao\",\"menu\":\"就诊签到\",\"menuJump\":\"列表\",\"tableName\":\"jiuzhenqiandao\"}],\"fontClass\":\"icon-common36\",\"menu\":\"就诊签到管理\",\"unicode\":\"&#xee9f;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"开方\"],\"appFrontIcon\":\"cuIcon-vip\",\"buttons\":[\"查看\",\"开方\"],\"classname\":\"zhenduanbingli\",\"menu\":\"诊断病历\",\"menuJump\":\"列表\",\"tableName\":\"zhenduanbingli\"}],\"fontClass\":\"icon-common45\",\"menu\":\"诊断病历管理\",\"unicode\":\"&#xef3b;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"支付\",\"缴费统计\",\"首页总数\",\"首页统计\"],\"appFrontIcon\":\"cuIcon-phone\",\"buttons\":[\"查看\",\"缴费统计\",\"首页总数\",\"首页统计\"],\"classname\":\"chufangxinxi\",\"menu\":\"处方信息\",\"menuJump\":\"列表\",\"tableName\":\"chufangxinxi\"}],\"fontClass\":\"icon-common20\",\"menu\":\"处方信息管理\",\"unicode\":\"&#xee02;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-cardboard\",\"buttons\":[\"查看\"],\"classname\":\"paibanxinxi\",\"menu\":\"排班信息\",\"menuJump\":\"列表\",\"tableName\":\"paibanxinxi\"}],\"fontClass\":\"icon-common7\",\"menu\":\"排班信息管理\",\"unicode\":\"&#xedb4;\"}],\"frontMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-vipcard\",\"buttons\":[\"查看\",\"私信\",\"预约\"],\"classname\":\"yisheng\",\"menu\":\"医生\",\"menuJump\":\"列表\",\"tableName\":\"yisheng\"}],\"menu\":\"医生\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-camera\",\"buttons\":[\"查看\",\"新增\"],\"classname\":\"messages\",\"menu\":\"反馈建议\",\"menuJump\":\"列表\",\"tableName\":\"messages\"}],\"menu\":\"反馈建议\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-brand\",\"buttons\":[\"查看\"],\"classname\":\"news\",\"menu\":\"健康知识\",\"menuJump\":\"列表\",\"tableName\":\"news\"}],\"menu\":\"公告资讯\"}],\"hasBackLogin\":\"是\",\"hasBackRegister\":\"是\",\"hasFrontLogin\":\"否\",\"hasFrontRegister\":\"否\",\"pathName\":\"yisheng\",\"roleName\":\"医生\",\"tableName\":\"yisheng\"}]');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '留言人id',
  `username` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `avatarurl` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '留言内容',
  `cpicture` longtext COLLATE utf8mb4_unicode_ci COMMENT '留言图片',
  `reply` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复内容',
  `rpicture` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='反馈建议';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'2025-03-27 07:44:24',1,'用户名1','file/messagesAvatarurl1.jpg','留言内容1','file/messagesCpicture1.jpg','回复内容1','file/messagesRpicture1.jpg'),(2,'2025-03-27 07:44:24',2,'用户名2','file/messagesAvatarurl2.jpg','留言内容2','file/messagesCpicture2.jpg','回复内容2','file/messagesRpicture2.jpg'),(3,'2025-03-27 07:44:24',3,'用户名3','file/messagesAvatarurl3.jpg','留言内容3','file/messagesCpicture3.jpg','回复内容3','file/messagesRpicture3.jpg'),(4,'2025-03-27 07:44:24',4,'用户名4','file/messagesAvatarurl4.jpg','留言内容4','file/messagesCpicture4.jpg','回复内容4','file/messagesRpicture4.jpg'),(5,'2025-03-27 07:44:24',5,'用户名5','file/messagesAvatarurl5.jpg','留言内容5','file/messagesCpicture5.jpg','回复内容5','file/messagesRpicture5.jpg'),(6,'2025-03-27 07:44:24',6,'用户名6','file/messagesAvatarurl6.jpg','留言内容6','file/messagesCpicture6.jpg','回复内容6','file/messagesRpicture6.jpg'),(7,'2025-03-27 07:44:24',7,'用户名7','file/messagesAvatarurl7.jpg','留言内容7','file/messagesCpicture7.jpg','回复内容7','file/messagesRpicture7.jpg'),(8,'2025-03-27 07:44:24',8,'用户名8','file/messagesAvatarurl8.jpg','留言内容8','file/messagesCpicture8.jpg','回复内容8','file/messagesRpicture8.jpg'),(9,'2025-03-27 07:44:24',9,'用户名9','file/messagesAvatarurl9.jpg','留言内容9','file/messagesCpicture9.jpg','回复内容9','file/messagesRpicture9.jpg'),(10,'2025-03-27 07:44:24',10,'用户名10','file/messagesAvatarurl10.jpg','留言内容10','file/messagesCpicture10.jpg','回复内容10','file/messagesRpicture10.jpg'),(11,'2025-03-27 08:19:17',1743063530818,'11','file/1743063525877.jpg','<p>111</p>',NULL,'<p>11</p>',NULL);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `title` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `introduction` longtext COLLATE utf8mb4_unicode_ci COMMENT '简介',
  `picture` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='健康知识';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,'2025-03-27 07:44:24','余生，愿你活出自己的精彩','曾几何时，为了让自己显得合群，选择去做自己不喜欢的事;为了讨好喜欢的人，一次次地委屈自己;甚至，为了陌生人的一个眼神，就陷入了深深的自我怀疑。','file/newsPicture1.jpg','可是，这样小心翼翼的你，一定很累吧。你以为照顾了所有人的想法，就能得到他们的喜欢;你以为逼着自己合群，就能真的合群。直到后来才发现，在日复一日对自己的为难中，你逐渐弄丢了真实的自己.\n殊不知，人生在世，最不值得的事情，就是在别人的眼光中，迷失自己。其实，正如世界上没有十全十美的人，你也同样无法做到让每个人满意。\n世上没有不快乐的人，只有不肯让自己快乐的心。有一句话说:\n如果一段关系让你变得卑微，你可以选择抽身离去;如果一个人总是让你悲伤哭泣，你要学会放手。\n始终相信，每个人最初的样子，都是最美好的。真正喜欢你的人，会喜欢你原来的样子:真正爱你的从来不需要你的委曲求全。\n后半生，请把身上的枷锁，全部解除。去做喜欢的事，去听自己内心的声音。你会发现，这世上没有什么事情比活出自己更加幸福。\n要相信，真实的自己亦是一道风景线。你若盛开，清风自来。愿你此生尽兴，不负韶华，'),(2,'2025-03-27 07:44:24','岁月无常，好好珍惜','常听到这样的话，如果一切可以重新开始，我一定改掉任性，学会坚持，如果有下辈子，我一定好好珍惜，不会再弄丢原本属于自己的幸福。','file/newsPicture2.jpg','然而，世上有很多事情没有再来一次的机会，一旦错失就意味着无法弥补，正如人与人之间，有的只是一世的缘分，一旦转身，就意味着再无可能。\n这一生只活一次，有些人也只爱一回。\n所以，在拥有时就好好把握，对于来世，可以适当憧憬，但不要一昧去幻想，活着，唯有珍惜身边做好眼前事，才能让明天少一些遗憾。人，\n既然有幸来到这个世上，那就要努力把这辈子活好。岁月其实远没有想象中的那么漫长，若是能爱就好好爱，别让说好相伴一生的人，中途散走;若是当下能相知相守，就别去等待虚无缥缈的未来。\n就像一位网友留言说的:我从来不去期待下辈子，我要的只有今生，世事无常，来世是怎样的光景，谁都无法预知。对我来说，在有限的时光里，善待爱自己及自己爱的人，用心珍惜相聚的每一时刻，努力过好当下就足够了。\n时光匆匆，韶华似水，每个人都只有这短暂的一生。\n有些事，如果喜欢就坚定努力的去做，有些人，如果爱就真诚用心地去爱，别幻想着重来，别总是寄希望于下辈子。\n下辈子，彼此也许再也不会遇见，就算遇见了，谁还会记得谁，今生相互承诺的两个人，来生也许相见不相识。\n想起之前看过的一句话:人生没有假如，我们没有来世，下辈子，你我也许只是大千世界里互不相识的两个陌生人。\n的确，生命只有一次，今生有幸相遇的人，下辈子不一定能遇见，就算能遇见，谁能把谁陪伴，谁又是谁的谁?\n余生不长，趁着岁月未老，好好珍惜这辈子的缘分吧，别等到失去了才悔不该当初。'),(3,'2025-03-27 07:44:24','与其背着烦恼活，不如带着美好过','一直觉得，感情里，无疾而终并不可怕，可怕的是结束后，你还抱着回忆不肯放;岁月中，失去并不可怕，可怕的是失去后，你还揪着曾经不肯忘。\n有些事，明知道不应该继续，却始终没有转身的勇气;有些人，明知道已经成为过去，却还是舍不得放手;有时候，明知道纠缠于往事旧人只会徒增伤悲，却仍然执着的去回忆，去留恋。','file/newsPicture3.jpg','人生短短几十年，为什么要蹉跎浪费在无可挽回的失去上，为什么不酒脱一点，看开一点，活得轻松快乐一点。\n这世间，没有谁的人生是十全十美的，总要面对一些离别，总要经历一些失去，如果总对身边的一切执念太深，只会越活越累。\n时间不停向前，人也不可避免的要向前看。面对一些不如意、不顺心的事，该放的就放，该忘的就忘，别总把自己困在失败的悲伤中。\n生而为人，放不是无能，而是一种洒脱，忘更不是懦弱，而是一种智慧。\n在来去匆匆，聚散不定的现实里，放下不属于自己的感情，才能有新的开始，忘记心中的烦恼，才能收获愉快的心情。\n所以，有些离开的人，该放就放下吧，一直念念不忘，伤害的是自己，有些烦心事，该忘就忘了吧一直耿耿于怀，痛苦的是自己。\n-辈子本就不长，何必让自己活得那么累，过去的情就让它过去，别再留恋，想不通的事就算了，别再纠结。\n人生的旅途，总有些事让人烦，总有些情让人伤。只有学会忘记过往的不如意，忘记一些不必要的烦恼，以轻盈的身心重新出发，才不会被旧人旧事所累。\n记得，每个人心灵的空间都是有限的，当装多了纠结与伤感，就装不下自在与欢快了。要想日子过得简单轻松一点，就要懂得放下执念，忘掉痛苦，清除伤。'),(4,'2025-03-27 07:44:24','理想很丰满，现实很骨感','游戏里的你帅气无比，乘风御剑，通关杀敌无所不能，你满足于那其中的成就感，你满足于通关的畅快淋漓。','file/newsPicture4.jpg','年轻人，可那毕竟不是现实，理想很丰满，现实很骨感这不是玩笑，你不走出来，怎么会知\n你真的想等到某天喜欢上一个姑娘时，才去纠结咖啡钱电影钱你要如何与爸妈张口，才去苦恼为她买礼物你要怎样省吃俭用节衣缩食?\n你没有挥金如土的.魄力，没有殷实的家境，没有既定的光芒锦绣的前程，二十出头的年纪，你不努力，你想拿什么报答那个在你身边用最好的青春陪伴你的姑娘?你又拿什么报答含辛茹苦养育你这么多年的父母?\n生活不是游戏，不会给你那么多反复再来的机会，青春年少风华正茂，年轻人，你不努力，你想指望什么?\n年轻人，走出来奋斗吧!累也好苦也罢。如果你现在对自己各种放纵，你指望以后你用什么条件来放松?\n年轻人，你要努力赚钱，为了自己，也为了你爸妈。为了他们在以后逛商场的时候，可以给自己买东西像给你买东西一样毫不犹豫。为了他们可以在同老友谈起你时可以一脸安详，而不是想着，这个月，儿子交了房租还有钱吃饭么。我们这个年龄的人，已经像吸血鬼一样巴着父母活了二十多年了，还真的要继续吸着他们的血活下去么?\n年轻人，停止期望凡事简单容易。如果总是挑简单的事情做，那你永远成不了大器，生活总会在始料不及时考验你，所以你必须挑战困难，保持坚强，世上任何值得拥有的东西都需要付诸努力，勤奋与自律，倘若自甘平庸，那你就会变得庸碌无为。\n年轻人，与我们一起，挑战一下自己，拥抱一下梦想。你想要的生活，跳起来，一定够得到!'),(5,'2025-03-27 07:44:24','理想是一种精神上的追求','如果前方是一片黑暗，理想是否会黯淡?黑暗终究不能限制理想的步伐，只是需要时间。','file/newsPicture5.jpg','是否会撕裂黑暗的结界?给理想一点时间，是否会完成理想的目标?太多时候，黑给黑暗一缕曙光，暗限制了理想的脚步，时间成了撕裂黑暗的曙光。社会的压抑，未知的迷茫，甚至是事实的打击，都成了所谓的黑暗。\n理想应该是一种精神上的追求，一种我们内心深处的渴望。也许理想的种类太多，不一样的人也有不一样的理想。但唯一的共同点就是我们在追求，追求自己精神上亦或是心中的目标。但总是不尽人意，一来存在太多不确定因素，二来时间还不够。理想并不是一而就的空话。\n想过放弃，因为理想与现实的差距。但又不想放弃，因为理想是精神上的追求心中的渴望。纠结了太多，却是否想过在纠结的时候，已经选择了错过。被刻意选择的错过是什么?是路过。我们在不经意间路过了多少理想，只是因为纠结。如果坚定自己的理想，用时间去行动，是否会少几次刻意的错过:-次，与一位朋友谈到如何乐观，然后追求理想。朋友想了一会儿，给我的回答是正能量与时间。他说，想要乐观就要不断给自己正能量。事物有阴阳两面，如果阳代表乐观，那在我们看待一件事时要看阳面千万别看阴面。这样自己不会消极，某种程度上来说就是一种正能量。想要追求理想，务必要花费时间。正如没有风平浪静的大海，想要追求理想，要时间。给理想一点时间，让自己去追求，不顾切的追求。\n点点滴滴的小事构成了我们的生活，点点滴滴的努力给理想铺平了路。每一次小成功都为理想迈进了一步。每一次失败，都告诉我们一些教训，反方向来说为下一次成功提供了经验。不要觉得失败是可怕的，长远来看，正是失败才有了成功。\n时间在流逝。如果安于现状，就会乐不思。理想需要时间不代表我们可以挥霍，我们能做的是珍惜时间，去为理想拼搏。\n间接意义上讲，理想的时间是我们的努力。\n如果正在路上，一直任性向前走，忘记时间不回头。'),(6,'2025-03-27 07:44:24','人生如梦，梦想是帆','人生如梦，梦想是帆，每个人都有一个只属于自己的梦，但我们同属一个国家，所以每个人的梦又与国家的兴衰荣辱紧密相连。先哲顾炎武曾说:天下兴亡，匹夫有责。只有国家好，大家才能好。','file/newsPicture6.jpg','我依然清楚的记得:\n当甲午战争战败，日寇无礼踏破中国的门户;当八国联军侵入北京，无情掠夺中国的财产:当七七事变发生，中国的老人、妇孺被残忍杀害的时候，我在想那时中国的梦是怎样的!\n我虽不曾亲眼看到，但那却是铁一般的事实。因为从老人们那深邃的眼神中可以感到无尽的愤懑;从他们干瘪的脸颊可以看到深情的泪水，从他们嘹亮的军歌中可以想到那奋勇杀敌时的豪迈;从他们激昂话语中听到那誓要捍卫家园振兴中华的誓言。作为新一代青年的我们难道不应该树立远大的理想，付之以踏实的行动，去继承先辈们的使命。去实现中华民族的伟大崛起和复兴吗?\n有梦才能使中国繁荣!\n在改革开放以来中国取得了一系列的可以载入中国史册的成就。香港、澳门的回归，经济特区的建立，使中国成为发展国家中的经济大国，科技先进国和军事强国。当中国成功举办奥运的时候，当神九飞天的时候，当蛟龙入海的时候，当航母下水的时候，当莫言荣获诺贝尔文学奖的时候。我相信每个人都感觉到了无比的自豪。但是现在的中国与其他发达国家还有很大差距。作为新一代的我们，难道不应该志存高远吗?\n我想有的人会说，我们的力量是有限的。的确个人的力量很渺小，但是中国梦就是因一个个微不足道的个人的梦一直汇集、汇集，然后凝聚成的一个巨大的梦。冯至在《十四行诗》中写道，我们准备着，深深领受，那些意想不到的奇迹，在漫长的岁月里，忽然有彗星的出现，狂风乍起。\n梦想是美丽的，它是美的期望;梦想是阳光的，它使人由浮躁走向踏实;梦想是充满力量的，它可以激发人身体里无限的潜能。我们期盼的是国泰民安、经济发展、政治清明、文化繁荣、社会和谐、生态良\n好、公平正义。这才是中国人伟大的梦'),(7,'2025-03-27 07:44:24','若月亮没来-王宇宙Leto，乔浚丞','《若月亮没来(若是月亮还没来)》是王宇宙Leto，乔浚丞演唱的歌曲，于2024年1月22日上线发行。','file/newsPicture7.jpg','风吹过山 船靠了岸  风光呀 一点点看  我走向北 你去往南\n故事呀 一篇篇翻  好烦 又加班到很晚  你搭上空荡的 地铁已是末班\n好烦 很爱却要分开  恋爱谈不明白  好烦 接近理想好难 却又还很不甘\n如何拥抱平淡  如果 最难得到圆满  不如选择坦然 若是月亮还没来\n路灯也可照窗台  照着白色的山茶花微微开  若是晨风还没来\n晚风也可吹入怀  吹着那一地树影温柔摇摆  你总以为你不够好\n不够苗条和美貌  可是完美谁能达到  做你自己就很好\n烦恼烦恼拥有太少  没有房车没有钞票  可爱你的人永远会 \n把你当做心尖珍宝  我也懂大多数的时候  你只想逃离这世界\n我也懂太多的情绪在  一个人失眠的深夜 你可以是悲伤或者埋怨\n但请不要放弃明天  这一直灰暗的世界  我想看见你的笑脸\n若是月亮还没来  路灯也可照窗台  照着白色的山茶花微微开\n若是晨风还没来  晚风也可吹入怀  吹着那一地树影温柔摇摆\n若是月亮还没来  路灯也可照窗台  照着白色的山茶花微微开\n若是晨风还没来  晚风也可吹入怀  吹着那一地树影温柔摇摆\n李哲今年1月份创作了《若月亮没来》这首歌。随着歌曲的热度不断上升，约他写歌的人越来越多，他的工作变得更加繁忙。\n近年来，李哲凭借对音乐的热爱和不懈的努力，创作了几百首音乐作品，其中不乏广为人知的佳作，例如王栎鑫的《一个人》、何赛飞与叶炫清在央视演唱的《木兰诗》等歌曲，都是由李哲作曲。\n如今，李哲的音乐才华和努力得到了业界的广泛认可，他的音乐作品多次登上各大音乐平台的热歌榜，成为备受瞩目的新生代音乐人。现在，走在街头、坐在车里，经常会听到自己的作品。看到自己的音乐作品受到如此广泛的喜爱和认可，我感到非常有成就感。李哲笑着说，他会努力创作更多优秀音乐作品，为家乡争光。'),(8,'2025-03-27 07:44:24','-米阳光传递一份温暖','黑暗中跌倒，坚强里苦笑。只是因为青春还在，所以梦想一直在路上。当年华未曾老去，青春没有散场，留给幽暗的岁月，还有几分值得珍藏?孤独无助时、伤心难过时总爱恣意挥霍愤怒。伤了、痛了原本以为就都结束了;可是心里还不甘愿放弃，就只为一个理由，有梦想的人永不孤单。','file/newsPicture8.jpg','放进去了的青春，走不出来的悲伤却总是给年少时的我们留下太多无法割舍的情怀。记录岁月的足迹，聆听时光的故事。然轻吟浅唱出，青春嘉年华。\n故事里总会有，那些太多感慨于时间的无情，太多对往事的追忆，太多的太多，而今刻在了心里也停泻在了流年里。一段无法抹去的回忆，也只不过是一场烟火燃放的瞬间，却也一辈子在脑海里兜兜转转。很是怀念那些相遇的日子，邂逅了幸福也留在了时间轨迹里。时而想起，那些曾肩并肩奋斗的日子，那些写满感动与快乐的岁月。\n如今，我们似乎渐行渐远，彼此奋斗着青春的轮回。渐渐的，我们都长大了，时间改变了我们时刻分享彼此快乐与不快乐的习惯，青春的渐逝，岁月的更迭让我们变得都不再联系，身边的朋友也越来越少;那些欢笑，那些幸福的流年也随着我们的陌生而变得更加奢侈。也许多年以后，我们都有了自己的朋友，有了自己关心的人。那时，我们再细数那些癫狂在你生命里一辈子的记忆故事，蓦然发现一切都停留在了回忆里。'),(9,'2025-03-27 07:44:24','自己的路自己走，自己的心自己懂','岛上书店》中有句话:每个人的生命里，都有最艰难的那一年，将人生变得美好而辽阔。\n你有没有经历过那么一年，在走过的岁月中跋涉得异常艰难困苦，却仍无法灭前行的勇气。哪怕所有人都极力劝阻，也要不甘心地试一试;哪怕前方已没有道路，却还在坚定前行;哪怕再看不到任何希望，也要竭尽全力地为自己争取一个机会。','file/newsPicture9.jpg','你开始不动声色的扮演着一个大人，经历着生活日复一日的打。\n每天醒在天还没亮的五六点，挤过早高峰的地下铁，面对成山的工作，加班到深夜倒下就睡更成为了日常，还要应付各色的人际关系，逐渐开始接受努力也不一定有回报的道理。\n这段日子也许很长，但你没有放弃对生活的信心，始终相信人生总会变得一帆风顺，更坚信每件事的最后都会有一个好的结局，如果不是，说明还没到最后。\n于是你树立新的目标，让这一年的奔波更有方向。\n虽然永远无法预料明天是晴是雨，也无法预知你在乎的人是否还在身边，你一直以来的坚持究竟能换来什么。\n但你能决定的是，今天有没有备好雨伞，有没有好好爱人以及是否为自己追求的理想拼尽全力。\n永远不要只看见前方路途遥远而忘了从前的自己坚持了多久才走到了这里，\n今天拼尽全力虽然艰辛万分，在未来都将成为落在你身上的礼物。'),(10,'2025-03-27 07:44:24','愿你有明天可奔赴，有过往可回头','此去经年，用一张离家的车票，换一段有故事可说的人生。\n大概就是那时候怀揣着梦想，背上了行囊，一腔热血决定远行的你。独自拎着一只行李箱，背着双肩包在深夜抵达一个陌生的城市，站在人潮涌动的车站出口，望着宽敞公路上车水马龙的喧嚣，城市里亮如白昼的繁华。','file/newsPicture10.jpg','这世界上的人们都活的匆匆忙忙，每个人看起来都很焦虑，每个人都有自己的苦楚，却往往找不到人可以诉说。也许你的倾诉，对别人来说是一种打扰，而且别人没经历过你的经历，所以无法体会你的心情。我们总是在心情不好的时候拿着手机，翻翻通讯录，然后再默默放下手机，压住心中那股想要找人说说话的想法。\n等到低落的情绪慢慢有些好转，才开始明白，人这一生，无论是什么样的路，都是自己的选择，冲动也好，深思熟虑也罢，自己选的路，不管多难，都要自己走下去。心中的感受，不管是复杂还是简单，都只能自己来懂，别人帮不了你，也安慰不了你，顶多给你一些无济于事的劝告，最后还得靠自己想开。你选的幸福，你自己享受;你选的眼泪，你自己来流，生活就是如此，自己的路，自己走;自己的心，自己懂。'),(11,'2025-03-27 08:23:03','11','','file/1743063782331.jpg','<p>111</p>');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paibanxinxi`
--

DROP TABLE IF EXISTS `paibanxinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paibanxinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yishengzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '医生账号',
  `yishengxingming` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '医生姓名',
  `banci` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '班次',
  `shangbanshijian` datetime NOT NULL COMMENT '上班时间',
  `shangbanshizhang` int(11) NOT NULL COMMENT '上班时长小时',
  `xiujiashijian` date NOT NULL COMMENT '休假时间',
  `fabushijian` datetime DEFAULT NULL COMMENT '发布时间',
  `beizhu` longtext COLLATE utf8mb4_unicode_ci COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='排班信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paibanxinxi`
--

LOCK TABLES `paibanxinxi` WRITE;
/*!40000 ALTER TABLE `paibanxinxi` DISABLE KEYS */;
INSERT INTO `paibanxinxi` VALUES (1,'2025-03-27 07:44:25','医生账号1','医生姓名1','班次1','2025-03-27 15:44:25',1,'2025-03-27','2025-03-27 15:44:25','备注1'),(2,'2025-03-27 07:44:25','医生账号2','医生姓名2','班次2','2025-03-27 15:44:25',2,'2025-03-27','2025-03-27 15:44:25','备注2'),(3,'2025-03-27 07:44:25','医生账号3','医生姓名3','班次3','2025-03-27 15:44:25',3,'2025-03-27','2025-03-27 15:44:25','备注3'),(4,'2025-03-27 07:44:25','医生账号4','医生姓名4','班次4','2025-03-27 15:44:25',4,'2025-03-27','2025-03-27 15:44:25','备注4'),(5,'2025-03-27 07:44:25','医生账号5','医生姓名5','班次5','2025-03-27 15:44:25',5,'2025-03-27','2025-03-27 15:44:25','备注5'),(6,'2025-03-27 07:44:25','医生账号6','医生姓名6','班次6','2025-03-27 15:44:25',6,'2025-03-27','2025-03-27 15:44:25','备注6'),(7,'2025-03-27 07:44:25','医生账号7','医生姓名7','班次7','2025-03-27 15:44:25',7,'2025-03-27','2025-03-27 15:44:25','备注7'),(8,'2025-03-27 07:44:25','医生账号8','医生姓名8','班次8','2025-03-27 15:44:25',8,'2025-03-27','2025-03-27 15:44:25','备注8'),(9,'2025-03-27 07:44:25','医生账号9','医生姓名9','班次9','2025-03-27 15:44:25',9,'2025-03-27','2025-03-27 15:44:25','备注9'),(10,'2025-03-27 07:44:25','医生账号10','医生姓名10','班次10','2025-03-27 15:44:25',10,'2025-03-27','2025-03-27 15:44:25','备注10'),(11,'2025-03-27 08:22:43','医生账号1','医生姓名1','11','2025-03-29 06:00:00',11,'2025-04-05','2025-03-27 16:22:30','<p>11</p>');
/*!40000 ALTER TABLE `paibanxinxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rukujilu`
--

DROP TABLE IF EXISTS `rukujilu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rukujilu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `rukubianhao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '入库编号',
  `yaopinmingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '药品名称',
  `kucun` int(11) DEFAULT NULL COMMENT '入库数量',
  `rukushijian` datetime DEFAULT NULL COMMENT '入库时间',
  `rukubeizhu` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '入库备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `rukubianhao` (`rukubianhao`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='入库记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rukujilu`
--

LOCK TABLES `rukujilu` WRITE;
/*!40000 ALTER TABLE `rukujilu` DISABLE KEYS */;
INSERT INTO `rukujilu` VALUES (1,'2025-03-27 07:44:24','1111111111','药品名称1',1,'2025-03-27 15:44:24','入库备注1'),(2,'2025-03-27 07:44:24','2222222222','药品名称2',2,'2025-03-27 15:44:24','入库备注2'),(3,'2025-03-27 07:44:24','3333333333','药品名称3',3,'2025-03-27 15:44:24','入库备注3'),(4,'2025-03-27 07:44:24','4444444444','药品名称4',4,'2025-03-27 15:44:24','入库备注4'),(5,'2025-03-27 07:44:24','5555555555','药品名称5',5,'2025-03-27 15:44:24','入库备注5'),(6,'2025-03-27 07:44:24','6666666666','药品名称6',6,'2025-03-27 15:44:24','入库备注6'),(7,'2025-03-27 07:44:24','7777777777','药品名称7',7,'2025-03-27 15:44:24','入库备注7'),(8,'2025-03-27 07:44:24','8888888888','药品名称8',8,'2025-03-27 15:44:24','入库备注8'),(9,'2025-03-27 07:44:24','9999999999','药品名称9',9,'2025-03-27 15:44:24','入库备注9'),(10,'2025-03-27 07:44:24','11111111110','药品名称10',10,'2025-03-27 15:44:24','入库备注10'),(11,'2025-03-27 08:22:07','1743063724348','药品名称10',10,'2025-03-27 16:22:04','');
/*!40000 ALTER TABLE `rukujilu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `syslog`
--

DROP TABLE IF EXISTS `syslog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `syslog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `username` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `operation` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户操作',
  `method` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '请求方法',
  `params` longtext COLLATE utf8mb4_unicode_ci COMMENT '请求参数',
  `time` bigint(20) DEFAULT NULL COMMENT '请求时长(毫秒)',
  `ip` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ip地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='操作日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `syslog`
--

LOCK TABLES `syslog` WRITE;
/*!40000 ALTER TABLE `syslog` DISABLE KEYS */;
/*!40000 ALTER TABLE `syslog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `tablename` varchar(100) DEFAULT NULL COMMENT '表名',
  `role` varchar(100) DEFAULT NULL COMMENT '角色',
  `token` varchar(200) NOT NULL COMMENT '密码',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expiratedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='token表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,1,'admin','users','管理员','7tgd4f3jie5lvbh4o5e729arl45vecgo','2025-03-27 08:14:59','2025-03-27 09:21:29'),(2,1743063530818,'11','yonghu','用户','kggedylajishjaak0pay73qbg0osgsmr','2025-03-27 08:18:57','2025-03-27 09:18:57'),(3,91,'医生账号1','yisheng','医生','1vhmtm2ljqpnhlzaq9elv3pkgknj9yl1','2025-03-27 08:19:48','2025-03-27 09:23:18'),(4,1743063817557,'22','yisheng','医生','jeaqa5gv6g6yszap69ucz2zx0zk2qeq3','2025-03-27 08:23:44','2025-03-27 09:23:44');
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `username` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `role` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '管理员' COMMENT '角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2025-03-27 07:44:25','admin','admin','管理员');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yaopinxinxi`
--

DROP TABLE IF EXISTS `yaopinxinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yaopinxinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yaopinmingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '药品名称',
  `guige` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '规格',
  `shengchanriqi` date NOT NULL COMMENT '生产日期',
  `youxiaoqi` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '有效期',
  `danjia` double DEFAULT NULL COMMENT '单价',
  `kucun` int(11) NOT NULL COMMENT '库存',
  `xinzengshijian` datetime DEFAULT NULL COMMENT '新增时间',
  `beizhu` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='药品信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yaopinxinxi`
--

LOCK TABLES `yaopinxinxi` WRITE;
/*!40000 ALTER TABLE `yaopinxinxi` DISABLE KEYS */;
INSERT INTO `yaopinxinxi` VALUES (1,'2025-03-27 07:44:24','药品名称1','规格1','2025-03-27','有效期1',1,1,'2025-03-27 15:44:24','备注1'),(2,'2025-03-27 07:44:24','药品名称2','规格2','2025-03-27','有效期2',2,2,'2025-03-27 15:44:24','备注2'),(3,'2025-03-27 07:44:24','药品名称3','规格3','2025-03-27','有效期3',3,3,'2025-03-27 15:44:24','备注3'),(4,'2025-03-27 07:44:24','药品名称4','规格4','2025-03-27','有效期4',4,4,'2025-03-27 15:44:24','备注4'),(5,'2025-03-27 07:44:24','药品名称5','规格5','2025-03-27','有效期5',5,5,'2025-03-27 15:44:24','备注5'),(6,'2025-03-27 07:44:24','药品名称6','规格6','2025-03-27','有效期6',6,6,'2025-03-27 15:44:24','备注6'),(7,'2025-03-27 07:44:24','药品名称7','规格7','2025-03-27','有效期7',7,7,'2025-03-27 15:44:24','备注7'),(8,'2025-03-27 07:44:24','药品名称8','规格8','2025-03-27','有效期8',8,8,'2025-03-27 15:44:24','备注8'),(9,'2025-03-27 07:44:24','药品名称9','规格9','2025-03-27','有效期9',9,8,'2025-03-27 15:44:24','备注9'),(10,'2025-03-27 07:44:24','药品名称10','规格10','2025-03-27','有效期10',10,20,'2025-03-27 15:44:24','备注10');
/*!40000 ALTER TABLE `yaopinxinxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yisheng`
--

DROP TABLE IF EXISTS `yisheng`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yisheng` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yishengzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '医生账号',
  `mima` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `yishengxingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '医生姓名',
  `touxiang` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `xingbie` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `dianhua` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `yishengjianjie` longtext COLLATE utf8mb4_unicode_ci COMMENT '医生简介',
  PRIMARY KEY (`id`),
  UNIQUE KEY `yishengzhanghao` (`yishengzhanghao`)
) ENGINE=InnoDB AUTO_INCREMENT=1743063817558 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='医生';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yisheng`
--

LOCK TABLES `yisheng` WRITE;
/*!40000 ALTER TABLE `yisheng` DISABLE KEYS */;
INSERT INTO `yisheng` VALUES (91,'2025-03-27 07:44:24','医生账号1','123456','医生姓名1','file/yishengTouxiang1.jpg','男','19819881111','医生简介1'),(92,'2025-03-27 07:44:24','医生账号2','123456','医生姓名2','file/yishengTouxiang2.jpg','男','19819881112','医生简介2'),(93,'2025-03-27 07:44:24','医生账号3','123456','医生姓名3','file/yishengTouxiang3.jpg','男','19819881113','医生简介3'),(94,'2025-03-27 07:44:24','医生账号4','123456','医生姓名4','file/yishengTouxiang4.jpg','男','19819881114','医生简介4'),(95,'2025-03-27 07:44:24','医生账号5','123456','医生姓名5','file/yishengTouxiang5.jpg','男','19819881115','医生简介5'),(96,'2025-03-27 07:44:24','医生账号6','123456','医生姓名6','file/yishengTouxiang6.jpg','男','19819881116','医生简介6'),(97,'2025-03-27 07:44:24','医生账号7','123456','医生姓名7','file/yishengTouxiang7.jpg','男','19819881117','医生简介7'),(98,'2025-03-27 07:44:24','医生账号8','123456','医生姓名8','file/yishengTouxiang8.jpg','男','19819881118','医生简介8'),(99,'2025-03-27 07:44:24','医生账号9','123456','医生姓名9','file/yishengTouxiang9.jpg','男','19819881119','医生简介9'),(100,'2025-03-27 07:44:24','医生账号10','123456','医生姓名10','file/yishengTouxiang10.jpg','男','198198811110','医生简介10'),(1743063817557,'2025-03-27 08:23:37','22','222','李四','file/1743063816216.jpg','男','13923999999',NULL);
/*!40000 ALTER TABLE `yisheng` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yishengyuyue`
--

DROP TABLE IF EXISTS `yishengyuyue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yishengyuyue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yuyuebianhao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '预约编号',
  `yishengzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '医生账号',
  `dianhua` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `yuyueshijian` datetime DEFAULT NULL COMMENT '预约时间',
  `zhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账号',
  `shouji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机',
  `yuyuebeizhu` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '预约备注',
  `sfsh` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '待审核' COMMENT '是否审核',
  `shhf` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复内容',
  PRIMARY KEY (`id`),
  UNIQUE KEY `yuyuebianhao` (`yuyuebianhao`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='医生预约';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yishengyuyue`
--

LOCK TABLES `yishengyuyue` WRITE;
/*!40000 ALTER TABLE `yishengyuyue` DISABLE KEYS */;
INSERT INTO `yishengyuyue` VALUES (1,'2025-03-27 07:44:24','1111111111','医生账号1','电话1','2025-03-27 15:44:24','账号1','手机1','预约备注1','是',''),(2,'2025-03-27 07:44:24','2222222222','医生账号2','电话2','2025-03-27 15:44:24','账号2','手机2','预约备注2','是',''),(3,'2025-03-27 07:44:24','3333333333','医生账号3','电话3','2025-03-27 15:44:24','账号3','手机3','预约备注3','是',''),(4,'2025-03-27 07:44:24','4444444444','医生账号4','电话4','2025-03-27 15:44:24','账号4','手机4','预约备注4','是',''),(5,'2025-03-27 07:44:24','5555555555','医生账号5','电话5','2025-03-27 15:44:24','账号5','手机5','预约备注5','是',''),(6,'2025-03-27 07:44:24','6666666666','医生账号6','电话6','2025-03-27 15:44:24','账号6','手机6','预约备注6','是',''),(7,'2025-03-27 07:44:24','7777777777','医生账号7','电话7','2025-03-27 15:44:24','账号7','手机7','预约备注7','是',''),(8,'2025-03-27 07:44:24','8888888888','医生账号8','电话8','2025-03-27 15:44:24','账号8','手机8','预约备注8','是',''),(9,'2025-03-27 07:44:24','9999999999','医生账号9','电话9','2025-03-27 15:44:24','账号9','手机9','预约备注9','是',''),(10,'2025-03-27 07:44:24','11111111110','医生账号10','电话10','2025-03-27 15:44:24','账号10','手机10','预约备注10','是',''),(11,'2025-03-27 08:19:11','1743063547272','医生账号1','19819881111','2025-03-27 16:19:07','11','13623666666','11','是','通过');
/*!40000 ALTER TABLE `yishengyuyue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yonghu`
--

DROP TABLE IF EXISTS `yonghu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yonghu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `zhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账号',
  `mima` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `xingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `xingbie` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `touxiang` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `shouji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机',
  PRIMARY KEY (`id`),
  UNIQUE KEY `zhanghao` (`zhanghao`)
) ENGINE=InnoDB AUTO_INCREMENT=1743063530819 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yonghu`
--

LOCK TABLES `yonghu` WRITE;
/*!40000 ALTER TABLE `yonghu` DISABLE KEYS */;
INSERT INTO `yonghu` VALUES (81,'2025-03-27 07:44:24','账号1','123456','姓名1','男','file/yonghuTouxiang1.jpg','19819881111'),(82,'2025-03-27 07:44:24','账号2','123456','姓名2','男','file/yonghuTouxiang2.jpg','19819881112'),(83,'2025-03-27 07:44:24','账号3','123456','姓名3','男','file/yonghuTouxiang3.jpg','19819881113'),(84,'2025-03-27 07:44:24','账号4','123456','姓名4','男','file/yonghuTouxiang4.jpg','19819881114'),(85,'2025-03-27 07:44:24','账号5','123456','姓名5','男','file/yonghuTouxiang5.jpg','19819881115'),(86,'2025-03-27 07:44:24','账号6','123456','姓名6','男','file/yonghuTouxiang6.jpg','19819881116'),(87,'2025-03-27 07:44:24','账号7','123456','姓名7','男','file/yonghuTouxiang7.jpg','19819881117'),(88,'2025-03-27 07:44:24','账号8','123456','姓名8','男','file/yonghuTouxiang8.jpg','19819881118'),(89,'2025-03-27 07:44:24','账号9','123456','姓名9','男','file/yonghuTouxiang9.jpg','19819881119'),(90,'2025-03-27 07:44:24','账号10','123456','姓名10','男','file/yonghuTouxiang10.jpg','19819881110'),(1743063530818,'2025-03-27 08:18:50','11','111','张三','男','file/1743063566964.jpg','13623666666');
/*!40000 ALTER TABLE `yonghu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zhenduanbingli`
--

DROP TABLE IF EXISTS `zhenduanbingli`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zhenduanbingli` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `binglibianhao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '病历编号',
  `yishengzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '医生账号',
  `dianhua` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `zhenduanbaogao` longtext COLLATE utf8mb4_unicode_ci COMMENT '诊断报告',
  `zhenduanshijian` datetime DEFAULT NULL COMMENT '诊断时间',
  `zhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账号',
  `shouji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机',
  `zhenduanjianyi` longtext COLLATE utf8mb4_unicode_ci COMMENT '诊断建议',
  PRIMARY KEY (`id`),
  UNIQUE KEY `binglibianhao` (`binglibianhao`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='诊断病历';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zhenduanbingli`
--

LOCK TABLES `zhenduanbingli` WRITE;
/*!40000 ALTER TABLE `zhenduanbingli` DISABLE KEYS */;
INSERT INTO `zhenduanbingli` VALUES (1,'2025-03-27 07:44:24','1111111111','医生账号1','电话1','','2025-03-27 15:44:24','账号1','手机1','诊断建议1'),(2,'2025-03-27 07:44:24','2222222222','医生账号2','电话2','','2025-03-27 15:44:24','账号2','手机2','诊断建议2'),(3,'2025-03-27 07:44:24','3333333333','医生账号3','电话3','','2025-03-27 15:44:24','账号3','手机3','诊断建议3'),(4,'2025-03-27 07:44:24','4444444444','医生账号4','电话4','','2025-03-27 15:44:24','账号4','手机4','诊断建议4'),(5,'2025-03-27 07:44:24','5555555555','医生账号5','电话5','','2025-03-27 15:44:24','账号5','手机5','诊断建议5'),(6,'2025-03-27 07:44:24','6666666666','医生账号6','电话6','','2025-03-27 15:44:24','账号6','手机6','诊断建议6'),(7,'2025-03-27 07:44:24','7777777777','医生账号7','电话7','','2025-03-27 15:44:24','账号7','手机7','诊断建议7'),(8,'2025-03-27 07:44:24','8888888888','医生账号8','电话8','','2025-03-27 15:44:24','账号8','手机8','诊断建议8'),(9,'2025-03-27 07:44:24','9999999999','医生账号9','电话9','','2025-03-27 15:44:24','账号9','手机9','诊断建议9'),(10,'2025-03-27 07:44:24','11111111110','医生账号10','电话10','','2025-03-27 15:44:24','账号10','手机10','诊断建议10'),(11,'2025-03-27 08:20:25','1743063617739','医生账号1','19819881111','file/1743063623763.docx','2025-03-27 16:20:17','11','13623666666','<p>111</p>');
/*!40000 ALTER TABLE `zhenduanbingli` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-03  9:58:39
