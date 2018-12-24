/*
 Navicat Premium Data Transfer

 Source Server         : ferby
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : jdmarket

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 23/12/2018 14:41:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for addressee
-- ----------------------------
DROP TABLE IF EXISTS `addressee`;
CREATE TABLE `addressee`  (
  `addressee_id` int(10) NOT NULL AUTO_INCREMENT,
  `addressee_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `addressee_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `addressee_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id` int(10) NULL DEFAULT NULL,
  `createTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`addressee_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of addressee
-- ----------------------------
INSERT INTO `addressee` VALUES (1, '吕洋', '15143933787', '吉林省白山市江源区', 1, '2018-12-20');

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'admin');

-- ----------------------------
-- Table structure for advertisement
-- ----------------------------
DROP TABLE IF EXISTS `advertisement`;
CREATE TABLE `advertisement`  (
  `advertise_id` int(10) NOT NULL AUTO_INCREMENT,
  `advertise_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `advertise_thumbnail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `advertise_range` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `advertise_link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `advertise_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `advertise_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `beginTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `endTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `updateTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`advertise_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of advertisement
-- ----------------------------
INSERT INTO `advertisement` VALUES (2, 'test', '95d25f04-dd71-4ea5-86d8-817ec59f8d293d0ebb8b4f6e0290.jpg', '首页', 'http://localhost/homePage', '', '图片', '2018-12-21', '2018-12-22', '2018-12-21 9:11:37', '2018-12-21 9:11:37');
INSERT INTO `advertisement` VALUES (3, 'test', 'b6c23674-6b34-4d57-8537-92e3790f18218e35770b69d3bf35.jpg', '首页', 'http://localhost/homePage', '', '图片', '2018-12-21', '2018-12-22', '2018-12-21 9:12:1', '2018-12-21 9:12:1');
INSERT INTO `advertisement` VALUES (4, 'test', 'ef7a261b-41ed-4ec6-9b88-13cfafff45548d04775ba5b6bc3f.jpg', '首页', 'http://localhost/homePage', '', '图片', '2018-12-21', '2018-12-22', '2018-12-21 9:12:28', '2018-12-21 9:12:28');
INSERT INTO `advertisement` VALUES (5, 'test', '', '所有栏目', 'http://localhost/homePage', 'test', '文本', '2018-12-21', '2018-12-22', '2018-12-21 9:12:58', '2018-12-21 9:12:58');
INSERT INTO `advertisement` VALUES (6, 'test', '', '所有栏目', 'http://localhost/homePage', 'test1', '文本', '2018-12-21', '2018-12-22', '2018-12-21 9:13:15', '2018-12-21 9:13:40');
INSERT INTO `advertisement` VALUES (7, 'test2', '', '所有栏目', 'http://localhost/homePage', 'test2', '文本', '2018-12-21', '2018-12-22', '2018-12-21 9:13:33', '2018-12-21 9:13:33');
INSERT INTO `advertisement` VALUES (8, 'test', 'f66c3209-82bd-44bd-ae81-747b66d131d15b0fca0fN8d5600df.jpg', '搜索页', 'http://localhost/homePage', '', '图片', '2018-12-21', '2018-12-22', '2018-12-21 9:14:45', '2018-12-21 9:14:45');

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand`  (
  `brand_id` int(10) NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `brand_company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `brand_englishName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `brand_pinyin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `brand_joinTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`brand_id`) USING BTREE,
  UNIQUE INDEX `brand_name`(`brand_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES (1, '苹果', 'apple.com', 'apple', 'pingguo', '2018-12-12 21:32:39');
INSERT INTO `brand` VALUES (2, ' 华为', 'huawei.com', 'HUAWEI', 'huawei', '2018-12-12 21:59:51');
INSERT INTO `brand` VALUES (3, 'OPPO', 'oppo.com', 'OPPO', 'OPPO', '2018-12-12 22:4:3');
INSERT INTO `brand` VALUES (4, '小米', 'xiaomi.com', 'XIAOMI', 'xiaomi', '2018-12-12 22:4:23');
INSERT INTO `brand` VALUES (5, '三星', 'sumsung.com', 'SUMSUNG', 'sanxing', '2018-12-12 22:4:53');
INSERT INTO `brand` VALUES (6, '一加', 'oneplus.com', 'ONEPLUS', 'yijia', '2018-12-12 22:5:12');
INSERT INTO `brand` VALUES (7, '魅族', 'meizu.com', 'MEIZU', 'meizu', '2018-12-12 22:5:35');
INSERT INTO `brand` VALUES (8, '诺基亚', 'nokia.com', 'NOKIA', 'nuojiya', '2018-12-12 22:6:5');
INSERT INTO `brand` VALUES (9, '360', '360.com', '360', '360', '2018-12-12 22:6:32');
INSERT INTO `brand` VALUES (10, '索尼', 'sony.com', 'sony', 'suoni', '2018-12-12 22:13:13');
INSERT INTO `brand` VALUES (11, '努比亚', 'nubia.com', 'nubia', 'nubiya', '2018-12-12 22:13:35');
INSERT INTO `brand` VALUES (12, '联想', 'lenovo.com', 'lenovo', 'lianxiang', '2018-12-12 22:14:3');
INSERT INTO `brand` VALUES (13, '美图', 'meitu.com', 'meitu', 'meitu', '2018-12-12 22:14:20');
INSERT INTO `brand` VALUES (14, '中兴', 'zte.com', 'zte', 'zhongxing', '2018-12-12 22:14:43');
INSERT INTO `brand` VALUES (15, '酷派', 'coolpad.com', 'coolpad', 'kupai', '2018-12-12 22:15:13');
INSERT INTO `brand` VALUES (16, '金立', 'ginoee.com', 'ginoee', 'jinli', '2018-12-12 22:15:47');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `parentId` int(10) NULL DEFAULT NULL,
  `pinyin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `grade` int(10) NULL DEFAULT NULL,
  `createTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (3, '华为mate20', 2, 'huaweimate20', 2, '2018-12-10 10:8:23');
INSERT INTO `category` VALUES (4, '荣耀9', 2, 'rongyao9', 2, '2018-12-10 10:8:34');
INSERT INTO `category` VALUES (8, '笔记本', 6, 'bijiben', 2, '2018-12-10 10:19:43');
INSERT INTO `category` VALUES (9, '台式', 6, 'taishi', 2, '2018-12-10 10:19:54');
INSERT INTO `category` VALUES (10, '手机', 0, 'shouji', 0, '2018-12-10 21:10:23');
INSERT INTO `category` VALUES (11, '电脑', 0, 'diannao', 0, '2018-12-10 21:10:44');
INSERT INTO `category` VALUES (12, '家居', 0, 'jiaju', 0, '2018-12-10 21:10:52');
INSERT INTO `category` VALUES (13, '服装', 0, 'fuzhuang', 0, '2018-12-10 21:11:4');
INSERT INTO `category` VALUES (14, '汽车', 0, 'qiche', 0, '2018-12-10 21:12:51');
INSERT INTO `category` VALUES (15, '母婴', 0, 'muying', 0, '2018-12-10 21:12:57');
INSERT INTO `category` VALUES (16, '礼品鲜花', 0, 'lipinxianhua', 0, '2018-12-10 21:13:17');
INSERT INTO `category` VALUES (17, '玩具', 0, 'wanju', 0, '2018-12-10 21:13:23');
INSERT INTO `category` VALUES (18, '工业品', 0, 'gongyepin', 0, '2018-12-10 21:14:38');
INSERT INTO `category` VALUES (19, '食品', 0, 'shipin', 0, '2018-12-10 21:20:30');
INSERT INTO `category` VALUES (20, '珠宝', 0, 'zhubao', 0, '2018-12-10 21:20:36');
INSERT INTO `category` VALUES (21, '手机通讯', 10, 'shoujitongxun', 1, '2018-12-12 20:53:49');
INSERT INTO `category` VALUES (22, '手机配件', 10, 'shoujipeijian', 1, '2018-12-12 20:54:0');
INSERT INTO `category` VALUES (23, '游戏手机', 21, 'youxishouji', 2, '2018-12-12 20:54:18');
INSERT INTO `category` VALUES (24, '储存卡', 22, 'chucunka', 2, '2018-12-12 20:54:30');
INSERT INTO `category` VALUES (25, '老人机', 21, 'laorenji', 2, '2018-12-12 21:2:3');
INSERT INTO `category` VALUES (26, '电脑整机', 11, 'diannaozhengji', 1, '2018-12-21 18:30:25');
INSERT INTO `category` VALUES (27, '电脑配件', 11, 'diannaopeijian', 1, '2018-12-21 18:30:37');
INSERT INTO `category` VALUES (28, '台式电脑', 26, 'taishidiannao', 2, '2018-12-21 18:30:53');

-- ----------------------------
-- Table structure for commentimage
-- ----------------------------
DROP TABLE IF EXISTS `commentimage`;
CREATE TABLE `commentimage`  (
  `commentImage_id` int(10) NOT NULL AUTO_INCREMENT,
  `comment_thumbnail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `comment_id` int(10) NULL DEFAULT NULL,
  `product_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`commentImage_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `comment_id` int(10) NOT NULL AUTO_INCREMENT,
  `comment_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `product_id` int(255) NULL DEFAULT NULL,
  `comment_grade` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES (1, '你好，毛泽东', 'ferby', 2, '好评', '2018-12-23 11:49:18');
INSERT INTO `comments` VALUES (2, '你好，***', 'ferby', 2, '好评', '2018-12-23 11:49:40');
INSERT INTO `comments` VALUES (3, 'asd', 'ferby', 2, '好评', '2018-12-23 14:36:7');

-- ----------------------------
-- Table structure for operationlogs
-- ----------------------------
DROP TABLE IF EXISTS `operationlogs`;
CREATE TABLE `operationlogs`  (
  `operation_id` int(10) NOT NULL AUTO_INCREMENT,
  `operation_person` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `operation_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `operation_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `opeartion_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`operation_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of operationlogs
-- ----------------------------
INSERT INTO `operationlogs` VALUES (1, 'admin', '添加了id为荣耀8X的商品', '添加成功', '2018-12-19 18:59:20');
INSERT INTO `operationlogs` VALUES (2, 'admin', '添加了id为荣耀8X的商品', '添加成功', '2018-12-19 19:1:29');
INSERT INTO `operationlogs` VALUES (3, 'admin', '添加了id为0的轮播图', '添加成功', '2018-12-19 19:2:31');
INSERT INTO `operationlogs` VALUES (4, 'admin', '添加了id为0的轮播图', '添加成功', '2018-12-19 19:3:57');
INSERT INTO `operationlogs` VALUES (5, 'admin', '删除了id为1的轮播图', '删除成功', '2018-12-19 19:15:56');
INSERT INTO `operationlogs` VALUES (6, 'admin', '删除了id为2的轮播图', '删除成功', '2018-12-19 19:15:58');
INSERT INTO `operationlogs` VALUES (7, 'admin', '添加了id为0的轮播图', '添加成功', '2018-12-19 19:16:25');
INSERT INTO `operationlogs` VALUES (8, 'admin', '添加了id为1商品的幻夜黑颜色和全网通(6+64G)规格', '添加成功', '2018-12-19 19:18:44');
INSERT INTO `operationlogs` VALUES (9, 'admin', '添加了id为1商品的魅海蓝颜色和全网通(6+128G)规格', '添加成功', '2018-12-19 19:20:23');
INSERT INTO `operationlogs` VALUES (10, 'admin', '更新specificationid为1的状态为下架', '更新成功', '2018-12-19 19:21:35');
INSERT INTO `operationlogs` VALUES (11, 'admin', '添加了id为小米8的商品', '添加成功', '2018-12-19 19:36:50');
INSERT INTO `operationlogs` VALUES (12, 'admin', '添加了id为2商品的黑色颜色和6+64G规格', '添加成功', '2018-12-19 19:38:23');
INSERT INTO `operationlogs` VALUES (13, 'admin', '添加了id为2商品的白色颜色和6+64G规格', '添加成功', '2018-12-19 19:39:21');
INSERT INTO `operationlogs` VALUES (14, 'admin', '添加了id为2商品的黑色颜色和6+128G规格', '添加成功', '2018-12-19 19:40:14');
INSERT INTO `operationlogs` VALUES (15, 'admin', '添加了id为0的图片', '添加成功', '2018-12-19 19:42:15');
INSERT INTO `operationlogs` VALUES (16, 'admin', '添加了id为0的图片', '添加成功', '2018-12-19 19:42:25');
INSERT INTO `operationlogs` VALUES (17, 'admin', '添加了id为0的图片', '添加成功', '2018-12-19 19:49:54');
INSERT INTO `operationlogs` VALUES (18, 'admin', '添加了id为0的图片', '添加成功', '2018-12-19 19:50:2');
INSERT INTO `operationlogs` VALUES (19, 'admin', '删除了id为3的轮播图', '删除成功', '2018-12-19 20:2:54');
INSERT INTO `operationlogs` VALUES (20, 'admin', '添加了id为1商品的魅海蓝颜色和全网通(6+128G)规格', '添加成功', '2018-12-19 20:4:12');
INSERT INTO `operationlogs` VALUES (21, 'admin', '添加了id为0的广告', '添加成功', '2018-12-19 20:6:57');
INSERT INTO `operationlogs` VALUES (22, 'admin', '修改了id为1的广告', '更新成功', '2018-12-19 20:7:35');
INSERT INTO `operationlogs` VALUES (23, 'admin', '添加了id为0的轮播图', '添加成功', '2018-12-21 8:42:27');
INSERT INTO `operationlogs` VALUES (24, 'admin', '删除了id为4的轮播图', '删除成功', '2018-12-21 8:56:29');
INSERT INTO `operationlogs` VALUES (25, 'admin', '添加了id为0的轮播图', '添加成功', '2018-12-21 8:56:53');
INSERT INTO `operationlogs` VALUES (26, 'admin', '添加了id为0的轮播图', '添加成功', '2018-12-21 8:58:11');
INSERT INTO `operationlogs` VALUES (27, 'admin', '添加了id为0的轮播图', '添加成功', '2018-12-21 8:58:33');
INSERT INTO `operationlogs` VALUES (28, 'admin', '删除了id为6的轮播图', '删除成功', '2018-12-21 9:0:22');
INSERT INTO `operationlogs` VALUES (29, 'admin', '添加了id为0的轮播图', '添加成功', '2018-12-21 9:0:45');
INSERT INTO `operationlogs` VALUES (30, 'admin', '删除了id为5的轮播图', '删除成功', '2018-12-21 9:2:23');
INSERT INTO `operationlogs` VALUES (31, 'admin', '删除了id为7的轮播图', '删除成功', '2018-12-21 9:2:25');
INSERT INTO `operationlogs` VALUES (32, 'admin', '删除了id为8的轮播图', '删除成功', '2018-12-21 9:2:26');
INSERT INTO `operationlogs` VALUES (33, 'admin', '添加了id为0的轮播图', '添加成功', '2018-12-21 9:3:5');
INSERT INTO `operationlogs` VALUES (34, 'admin', '删除了id为9的轮播图', '删除成功', '2018-12-21 9:5:35');
INSERT INTO `operationlogs` VALUES (35, 'admin', '添加了id为0的轮播图', '添加成功', '2018-12-21 9:6:11');
INSERT INTO `operationlogs` VALUES (36, 'admin', '删除了id为10的轮播图', '删除成功', '2018-12-21 9:8:56');
INSERT INTO `operationlogs` VALUES (37, 'admin', '添加了id为0的轮播图', '添加成功', '2018-12-21 9:9:24');
INSERT INTO `operationlogs` VALUES (38, 'admin', '添加了id为0的轮播图', '添加成功', '2018-12-21 9:9:48');
INSERT INTO `operationlogs` VALUES (39, 'admin', '添加了id为0的轮播图', '添加成功', '2018-12-21 9:10:3');
INSERT INTO `operationlogs` VALUES (40, 'admin', '添加了id为0的轮播图', '添加成功', '2018-12-21 9:10:19');
INSERT INTO `operationlogs` VALUES (41, 'admin', '添加了id为0的轮播图', '添加成功', '2018-12-21 9:10:37');
INSERT INTO `operationlogs` VALUES (42, 'admin', '删除了id为1的广告', '删除成功', '2018-12-21 9:11:9');
INSERT INTO `operationlogs` VALUES (43, 'admin', '添加了id为0的广告', '添加成功', '2018-12-21 9:11:37');
INSERT INTO `operationlogs` VALUES (44, 'admin', '添加了id为0的广告', '添加成功', '2018-12-21 9:12:1');
INSERT INTO `operationlogs` VALUES (45, 'admin', '添加了id为0的广告', '添加成功', '2018-12-21 9:12:28');
INSERT INTO `operationlogs` VALUES (46, 'admin', '添加了id为0的广告', '添加成功', '2018-12-21 9:12:58');
INSERT INTO `operationlogs` VALUES (47, 'admin', '添加了id为0的广告', '添加成功', '2018-12-21 9:13:15');
INSERT INTO `operationlogs` VALUES (48, 'admin', '添加了id为0的广告', '添加成功', '2018-12-21 9:13:33');
INSERT INTO `operationlogs` VALUES (49, 'admin', '修改了id为6的广告', '更新成功', '2018-12-21 9:13:40');
INSERT INTO `operationlogs` VALUES (50, 'admin', '添加了id为0的广告', '添加成功', '2018-12-21 9:14:45');
INSERT INTO `operationlogs` VALUES (51, 'admin', '删除了id为1的图片', '删除成功', '2018-12-21 9:33:28');
INSERT INTO `operationlogs` VALUES (52, 'admin', '删除了id为2的图片', '删除成功', '2018-12-21 9:33:30');
INSERT INTO `operationlogs` VALUES (53, 'admin', '添加了id为0的图片', '添加成功', '2018-12-21 9:33:41');
INSERT INTO `operationlogs` VALUES (54, 'admin', '添加了id为0的图片', '添加成功', '2018-12-21 9:33:51');
INSERT INTO `operationlogs` VALUES (55, 'admin', '删除了id为5的图片', '删除成功', '2018-12-21 9:54:48');
INSERT INTO `operationlogs` VALUES (56, 'admin', '删除了id为6的图片', '删除成功', '2018-12-21 9:54:51');
INSERT INTO `operationlogs` VALUES (57, 'admin', '添加了id为0的图片', '添加成功', '2018-12-21 9:55:0');
INSERT INTO `operationlogs` VALUES (58, 'admin', '添加了id为0的图片', '添加成功', '2018-12-21 9:55:9');
INSERT INTO `operationlogs` VALUES (59, 'admin', '更新specificationid为1的状态为上架', '更新成功', '2018-12-21 9:58:41');
INSERT INTO `operationlogs` VALUES (60, 'admin', '更新specificationid为1的状态为下架', '更新成功', '2018-12-21 10:5:39');
INSERT INTO `operationlogs` VALUES (61, 'admin', '更新id为1的规格', '更新成功', '2018-12-21 10:23:7');
INSERT INTO `operationlogs` VALUES (62, 'admin', '更新specificationid为1的状态为上架', '更新成功', '2018-12-21 10:23:19');
INSERT INTO `operationlogs` VALUES (63, 'admin', '更新id为1的规格', '更新成功', '2018-12-21 10:23:57');
INSERT INTO `operationlogs` VALUES (64, 'admin', '更新specificationid为1的状态为下架', '更新成功', '2018-12-21 10:24:34');
INSERT INTO `operationlogs` VALUES (65, 'admin', '更新specificationid为1的状态为上架', '更新成功', '2018-12-21 12:25:41');
INSERT INTO `operationlogs` VALUES (66, 'admin', '更新specificationid为1的状态为下架', '更新成功', '2018-12-21 12:25:56');
INSERT INTO `operationlogs` VALUES (67, 'admin', '添加名称为电脑整机的分类', '添加成功', '2018-12-21 18:30:25');
INSERT INTO `operationlogs` VALUES (68, 'admin', '添加名称为电脑配件的分类', '添加成功', '2018-12-21 18:30:37');
INSERT INTO `operationlogs` VALUES (69, 'admin', '添加名称为台式电脑的分类', '添加成功', '2018-12-21 18:30:53');
INSERT INTO `operationlogs` VALUES (70, 'admin', '添加了id为Dell的商品', '添加成功', '2018-12-21 18:31:29');
INSERT INTO `operationlogs` VALUES (71, 'admin', '添加了id为3商品的颜色和i7CPU规格', '添加成功', '2018-12-21 18:35:5');
INSERT INTO `operationlogs` VALUES (72, 'admin', '更新订单号为1545323122759的状态为已发货', '更新成功', '2018-12-21 23:0:26');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `orderId` bigint(255) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `addressee_id` int(10) NULL DEFAULT NULL,
  `order_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `order_totalPrice` double NULL DEFAULT NULL,
  `specification_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `updateTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`orderId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (7, '1545323122759', 1, '已完成', 6997, '5,1,', '2018-12-21 0:25:22', '2018-12-21 0:25:22');
INSERT INTO `orders` VALUES (8, '1545371708701', 1, '已下单', 5598, '5,', '2018-12-21 13:55:8', '2018-12-21 13:55:8');
INSERT INTO `orders` VALUES (10, '1545387526257', 1, '已下单', 8397, '5,', '2018-12-21 18:18:46', '2018-12-21 18:18:46');
INSERT INTO `orders` VALUES (11, '1545388605797', 1, '已下单', 10000, '7,', '2018-12-21 18:36:45', '2018-12-21 18:36:45');
INSERT INTO `orders` VALUES (12, '1545544096197', 1, '已下单', 2799, '5,', '2018-12-23 13:48:16', '2018-12-23 13:48:16');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int(10) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `product_introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `product_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `product_price` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `product_describe` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `product_category` int(10) NULL DEFAULT NULL,
  `product_brand` int(10) NULL DEFAULT NULL,
  `product_joinTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `updateTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `product_englishName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pinyin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '荣耀8X', '千元屏霸 91%屏占比 2000万AI双摄 4GB+64GB 幻夜黑 移动联通电信4G全面屏手机 双卡双待', '上架', '1399.0~1899.0', '机身颜色:幻夜黑;机身长度（mm）:160.4mm备注:实际尺寸依配置、制造工艺、测量方法的不同可能有所差异。;机身宽度（mm）:76.6mm备注:实际尺寸依配置、制造工艺、测量方法的不同可能有所差异。;机身厚度（mm）:7.8mm备注:实际尺寸依配置、制造工艺、测量方法的不同可能有所差异。;机身重量（g）:约175克（含电池）备注:实际重量依配置、制造工艺、测量方法的不同可能有所差异。;输入方式:触控;运营商标志或内容:其他;机身材质分类:金属边框,玻璃后盖;机身材质:工艺;卡托:金属+塑胶; 后盖:玻璃 ;边框:铝合金;屏占比:91%（可视区域/屏幕尺寸）;', 23, 2, '2018-12-19 19:1:29', '2018-12-19 19:1:29', 'honor8x', 'rongyao8X');
INSERT INTO `product` VALUES (2, '小米8', '全面屏游戏智能手机 6GB+64GB 黑色 全网通4G 双卡双待', '上架', '2299.0~2799.0', '品牌:小米（MI）;型号:小米8;入网型号:以官网信息为准;上市年份:2018年;上市月份:5月;机身颜色:黑色;机身长度（mm）:154.9;机身宽度（mm）:74.8;机身厚度（mm）:7.6;机身重量（g）:175;运营商标志或内容:无;机身材质分类:玻璃后盖;', 23, 4, '2018-12-19 19:36:50', '2018-12-19 19:36:50', ' xiaomi8', 'xiaomi8');
INSERT INTO `product` VALUES (3, 'Dell', '美国产品', '上架', '0~10000.0', '\n						请使用分号分割每一条描述\n   					', 28, 1, '2018-12-21 18:31:29', '2018-12-21 18:31:29', 'Dell', 'Dell');

-- ----------------------------
-- Table structure for productimage
-- ----------------------------
DROP TABLE IF EXISTS `productimage`;
CREATE TABLE `productimage`  (
  `productImage_id` int(10) NOT NULL AUTO_INCREMENT,
  `product_id` int(10) NULL DEFAULT NULL,
  `thumbnail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`productImage_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of productimage
-- ----------------------------
INSERT INTO `productimage` VALUES (3, 2, '9f92fbf8-3440-4969-9051-3dbe50d74b155b0fca0fN8d5600df (1).jpg', '2018-12-19 19:49:54');
INSERT INTO `productimage` VALUES (4, 2, 'bba80a5c-fc9c-466b-9a89-fdfaa9f1f11f5b0fca5aN0ecb5412.jpg', '2018-12-19 19:50:2');
INSERT INTO `productimage` VALUES (7, 1, '9b4c74e4-58e3-4577-b766-c8b6890f51c2b3bbe526b3c366fb.jpg', '2018-12-21 9:55:0');
INSERT INTO `productimage` VALUES (8, 1, '0b717b70-a445-4cc5-8660-4cb04e57ad46a8985cd000c6d4d2.jpg', '2018-12-21 9:55:9');

-- ----------------------------
-- Table structure for productspecification
-- ----------------------------
DROP TABLE IF EXISTS `productspecification`;
CREATE TABLE `productspecification`  (
  `specification_id` int(10) NOT NULL AUTO_INCREMENT,
  `product_id` int(10) NULL DEFAULT NULL,
  `product_color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `product_specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `product_thumbnail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `createTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `updateTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`specification_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of productspecification
-- ----------------------------
INSERT INTO `productspecification` VALUES (1, 1, '幻夜黑', '全网通(6+64G)', '1840e541-39ff-42b8-918e-f6d8e047be945b179c4bNfa68bba4.jpg', '下架', 1399, '2018-12-19 19:18:44', '2018-12-21 12:25:56');
INSERT INTO `productspecification` VALUES (2, 1, '魅海蓝', '全网通(6+128G)', 'd76ce621-e674-4bc8-b2b8-af327b0b36cb3beefdbe9a01a28d (1).jpg', '上架', 1599, '2018-12-19 19:20:23', '2018-12-19 19:20:23');
INSERT INTO `productspecification` VALUES (3, 2, '黑色', '6+64G', 'e6698435-59b7-4f7e-9982-099951d3338c5b0fca0fN8d5600df (1).jpg', '上架', 2499, '2018-12-19 19:38:23', '2018-12-19 19:38:23');
INSERT INTO `productspecification` VALUES (4, 2, '白色', '6+64G', 'e576d555-35e2-4901-84c8-2846a0125a9f5b0fca5aN0ecb5412.jpg', '上架', 2299, '2018-12-19 19:39:21', '2018-12-19 19:39:21');
INSERT INTO `productspecification` VALUES (5, 2, '黑色', '6+128G', '22763d72-388b-4b64-9832-ba98e4ce870f5b0fca0fN8d5600df (1).jpg', '上架', 2799, '2018-12-19 19:40:14', '2018-12-19 19:40:14');
INSERT INTO `productspecification` VALUES (6, 1, '魅海蓝', '全网通(6+64G)', '63b8a2e9-3751-4338-b217-3de2c7f494073beefdbe9a01a28d (1).jpg', '上架', 1899, '2018-12-19 20:4:12', '2018-12-19 20:4:12');
INSERT INTO `productspecification` VALUES (7, 3, '', 'i7CPU', 'ac20fa39-c57d-4331-8b78-e46db6f117abb999a9014c086e062550d0020f087bf40bd1cbfb.jpg', '上架', 10000, '2018-12-21 18:35:5', '2018-12-21 18:35:5');

-- ----------------------------
-- Table structure for rotationchart
-- ----------------------------
DROP TABLE IF EXISTS `rotationchart`;
CREATE TABLE `rotationchart`  (
  `chart_id` int(10) NOT NULL AUTO_INCREMENT,
  `chart_thumbnail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `chart_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `chart_link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`chart_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rotationchart
-- ----------------------------
INSERT INTO `rotationchart` VALUES (11, '3d634ff9-d41a-4724-b853-b4efc8ca58a7timg (3).jpg', 'test', 'http://localhost/homePage', 'test', '2018-12-21 9:9:24');
INSERT INTO `rotationchart` VALUES (12, '3c04d3fb-72b7-4fa4-84cf-dc0983920694timg (5).jpg', 'test', 'http://localhost/homePage', 'test', '2018-12-21 9:9:48');
INSERT INTO `rotationchart` VALUES (13, '37b68278-a646-4a76-a498-b2bdb69f0db1timg (4).jpg', 'test', 'http://localhost/homePage', 'test', '2018-12-21 9:10:3');
INSERT INTO `rotationchart` VALUES (14, '7b18a3f4-aeee-4f24-8cba-a968d9ba6a81timg.jpg', 'test', 'http://localhost/homePage', 'test', '2018-12-21 9:10:19');
INSERT INTO `rotationchart` VALUES (15, '63f35949-cca2-4249-977e-c7c823184a65timg (1).jpg', 'test', 'http://localhost/homePage', 'test', '2018-12-21 9:10:37');

-- ----------------------------
-- Table structure for sensitiveword
-- ----------------------------
DROP TABLE IF EXISTS `sensitiveword`;
CREATE TABLE `sensitiveword`  (
  `sensitive_id` int(10) NOT NULL AUTO_INCREMENT,
  `sensitive_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sensitive_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sensitiveword
-- ----------------------------
INSERT INTO `sensitiveword` VALUES (2, '习近平', '2018-12-9 13:3:50');
INSERT INTO `sensitiveword` VALUES (4, '草泥马', '2018-12-9 13:5:17');
INSERT INTO `sensitiveword` VALUES (5, '二逼', '2018-12-9 13:5:17');
INSERT INTO `sensitiveword` VALUES (6, 'fuck', '2018-12-9 13:5:17');

-- ----------------------------
-- Table structure for shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart`  (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NULL DEFAULT NULL,
  `specification_id` int(11) NULL DEFAULT NULL,
  `id` int(11) NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT NULL,
  `total_price` double NULL DEFAULT NULL,
  PRIMARY KEY (`cart_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------
INSERT INTO `shoppingcart` VALUES (3, 2, 5, 1, 2, 5598);
INSERT INTO `shoppingcart` VALUES (4, 1, 1, 1, 1, 1399);
INSERT INTO `shoppingcart` VALUES (5, 3, 7, 1, 1, 10000);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `updateTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'ferby', 'jNCB1l4NY5Mfeic1lVvEAw==', '15143933787', '1549424192@163.com', 'VIP', NULL, '2018-12-10 20:30:44');
INSERT INTO `users` VALUES (2, 'yang', 'jNCB1l4NY5Mfeic1lVvEAw==', '13044369653', '1549424192@qq.com', '封号', NULL, '2018年12月3日17时6分55秒');
INSERT INTO `users` VALUES (3, 'ferbylv', 'MYR5fg1Jrs3pcw1XTXMVNQ==', '18844633453', '1549424192@qq.com', '普通用户', NULL, '2018年12月3日16时50分14秒');
INSERT INTO `users` VALUES (4, 'test', 'MYR5fg1Jrs3pcw1XTXMVNQ==', '18844632951', '1549424192@qq.com', '普通用户', '2018-12-10 21:27:5', '2018-12-10 21:31:22');

SET FOREIGN_KEY_CHECKS = 1;
