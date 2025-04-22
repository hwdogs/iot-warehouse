/*
 Navicat Premium Dump SQL

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80037 (8.0.37)
 Source Host           : localhost:3306
 Source Schema         : iot_warehouse

 Target Server Type    : MySQL
 Target Server Version : 80037 (8.0.37)
 File Encoding         : 65001

 Date: 22/04/2025 18:11:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device`  (
  `device_id` int NOT NULL,
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `status` enum('运行中','维护中','离线') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `last_maintenance` date NULL DEFAULT NULL,
  `deleted` smallint NOT NULL DEFAULT 0 COMMENT '0：存在，1：逻辑删除',
  PRIMARY KEY (`device_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES (1001, 'AGV', '运行中', NULL, 0);
INSERT INTO `device` VALUES (1002, 'AGV', '离线', NULL, 0);
INSERT INTO `device` VALUES (2001, '温湿度传感器', '维护中', NULL, 0);
INSERT INTO `device` VALUES (3001, '光照传感器', '离线', NULL, 1);
INSERT INTO `device` VALUES (3002, '光照传感器', '运行中', NULL, 0);
INSERT INTO `device` VALUES (3003, '光照传感器', '运行中', NULL, 0);
INSERT INTO `device` VALUES (3004, '光照传感器', '运行中', NULL, 0);
INSERT INTO `device` VALUES (4001, '气体传感器', '运行中', NULL, 0);

-- ----------------------------
-- Table structure for environment
-- ----------------------------
DROP TABLE IF EXISTS `environment`;
CREATE TABLE `environment`  (
  `sensor_id` int NOT NULL,
  `timestamp` datetime NOT NULL,
  `temperature` decimal(5, 2) NULL DEFAULT NULL COMMENT '温度',
  `humidity` decimal(5, 2) NULL DEFAULT NULL COMMENT '湿度',
  `light` int NULL DEFAULT NULL COMMENT '光照强度',
  `gas` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '气体数据',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '注释',
  `deleted` smallint NOT NULL DEFAULT 0 COMMENT '0：存在，1：逻辑删除',
  PRIMARY KEY (`sensor_id`, `timestamp`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic PARTITION BY RANGE (year(`timestamp`))
PARTITIONS 2
(PARTITION `p2025` VALUES LESS THAN (2026) ENGINE = InnoDB MAX_ROWS = 0 MIN_ROWS = 0 ,
PARTITION `p2026` VALUES LESS THAN (2027) ENGINE = InnoDB MAX_ROWS = 0 MIN_ROWS = 0 )
;

-- ----------------------------
-- Records of environment
-- ----------------------------
INSERT INTO `environment` VALUES (2001, '2025-04-21 17:12:34', 31.20, 55.10, NULL, NULL, NULL, 0);
INSERT INTO `environment` VALUES (2001, '2025-04-21 17:44:43', 31.10, 56.50, NULL, NULL, NULL, 0);
INSERT INTO `environment` VALUES (2001, '2025-04-21 17:45:04', 34.32, 53.00, NULL, NULL, NULL, 0);
INSERT INTO `environment` VALUES (3001, '2025-04-21 17:46:13', NULL, NULL, 100, NULL, NULL, 0);
INSERT INTO `environment` VALUES (3002, '2025-04-21 17:47:12', NULL, NULL, 98, NULL, NULL, 0);
INSERT INTO `environment` VALUES (3002, '2025-04-21 17:47:35', NULL, NULL, 101, NULL, NULL, 0);
INSERT INTO `environment` VALUES (4001, '2025-04-21 17:48:02', NULL, NULL, NULL, '23488', NULL, 0);
INSERT INTO `environment` VALUES (4001, '2025-04-21 17:48:10', NULL, NULL, NULL, '23441', NULL, 0);
INSERT INTO `environment` VALUES (4001, '2025-04-21 17:48:20', NULL, NULL, NULL, '43523', NULL, 0);
INSERT INTO `environment` VALUES (4001, '2025-04-21 18:09:20', NULL, NULL, NULL, '34567', '更新实验111', 1);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goods_id` int NOT NULL AUTO_INCREMENT,
  `rfid_tag` varchar(96) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'UHF RFID标签',
  `category` smallint NOT NULL COMMENT '商品分类',
  `weight` decimal(10, 2) NULL DEFAULT NULL COMMENT '重量（kg）',
  `expire_date` date NULL DEFAULT NULL COMMENT '保质期',
  `deleted` smallint NOT NULL DEFAULT 0 COMMENT '0：存在，1：逻辑删除',
  PRIMARY KEY (`goods_id`) USING BTREE,
  UNIQUE INDEX `rfid_tag`(`rfid_tag` ASC) USING BTREE,
  INDEX `idx_category`(`category` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (-1977823231, '123456bbb', 1, 232.15, '2026-07-12', 1);
INSERT INTO `goods` VALUES (-1776496639, '123456aaa', 1, 103.34, '2025-04-21', 0);

-- ----------------------------
-- Table structure for inventory
-- ----------------------------
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory`  (
  `inventory_id` int NOT NULL AUTO_INCREMENT,
  `goods_id` int NOT NULL,
  `shelf_id` int NOT NULL,
  `quantity` int NULL DEFAULT 0 COMMENT '库存数量',
  `deleted` smallint NOT NULL DEFAULT 0 COMMENT '0：存在，1：逻辑删除',
  PRIMARY KEY (`inventory_id`) USING BTREE,
  INDEX `goods_id`(`goods_id` ASC) USING BTREE,
  INDEX `shelf_id`(`shelf_id` ASC) USING BTREE,
  CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `inventory_ibfk_2` FOREIGN KEY (`shelf_id`) REFERENCES `shelf` (`shelf_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inventory
-- ----------------------------
INSERT INTO `inventory` VALUES (1, -1977823231, 1, 301, 1);
INSERT INTO `inventory` VALUES (2, -1776496639, 3, 201, 0);
INSERT INTO `inventory` VALUES (3, -1776496639, 3, 201, 0);

-- ----------------------------
-- Table structure for operation_log
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log`  (
  `log_id` int NOT NULL AUTO_INCREMENT,
  `operation_type` enum('入库','出库','调拨') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `goods_id` int NOT NULL,
  `device_id` int NULL DEFAULT NULL,
  `operator_id` int NOT NULL,
  `timestamp` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` smallint NOT NULL DEFAULT 0 COMMENT '0：存在，1：逻辑删除',
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `goods_id`(`goods_id` ASC) USING BTREE,
  INDEX `device_id`(`device_id` ASC) USING BTREE,
  INDEX `operation_log_ibfk_3`(`operator_id` ASC) USING BTREE,
  CONSTRAINT `operation_log_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `operation_log_ibfk_2` FOREIGN KEY (`device_id`) REFERENCES `device` (`device_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `operation_log_ibfk_3` FOREIGN KEY (`operator_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 366759938 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of operation_log
-- ----------------------------
INSERT INTO `operation_log` VALUES (1, '调拨', -1776496639, 1001, 303878146, '2025-04-21 19:03:09', 1);
INSERT INTO `operation_log` VALUES (366759937, '入库', -1776496639, 1001, 2111631361, '2025-04-21 19:17:17', 0);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `perm_id` int NOT NULL AUTO_INCREMENT,
  `perm_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '如入库操作',
  `module` enum('库存','设备','报表') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `deleted` smallint NOT NULL DEFAULT 0 COMMENT '0：存在，1：逻辑删除',
  PRIMARY KEY (`perm_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '入库', '库存', 0);
INSERT INTO `permission` VALUES (2, '出库', '库存', 0);
INSERT INTO `permission` VALUES (3, '调拨', '库存', 0);
INSERT INTO `permission` VALUES (4, '运维', '设备', 0);
INSERT INTO `permission` VALUES (5, '修理', '设备', 1);

-- ----------------------------
-- Table structure for shelf
-- ----------------------------
DROP TABLE IF EXISTS `shelf`;
CREATE TABLE `shelf`  (
  `shelf_id` int NOT NULL AUTO_INCREMENT,
  `warehouse_id` int NOT NULL,
  `position_x` decimal(8, 2) NULL DEFAULT NULL,
  `position_y` decimal(8, 2) NULL DEFAULT NULL,
  `position_z` decimal(8, 2) NULL DEFAULT NULL,
  `capacity` int NOT NULL COMMENT '最大存货量',
  `deleted` smallint NOT NULL DEFAULT 0 COMMENT '0：存在，1：逻辑删除',
  PRIMARY KEY (`shelf_id`) USING BTREE,
  INDEX `warehouse_id`(`warehouse_id` ASC) USING BTREE,
  CONSTRAINT `shelf_ibfk_1` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse` (`warehouse_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1432113154 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shelf
-- ----------------------------
INSERT INTO `shelf` VALUES (-1734586367, 2, 221.35, NULL, NULL, 200, 1);
INSERT INTO `shelf` VALUES (1, 1, 204.00, 104.00, 4.00, 99999, 0);
INSERT INTO `shelf` VALUES (2, 1, 123.20, 23.10, 2313.23, 10000, 0);
INSERT INTO `shelf` VALUES (3, 2, 0.00, 112.80, 821.10, 100, 0);
INSERT INTO `shelf` VALUES (459034625, 2, NULL, NULL, NULL, 10, 0);
INSERT INTO `shelf` VALUES (1432113153, 2, NULL, NULL, NULL, 10, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password_hash` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password_confirm` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  `biometric_token` varbinary(255) NULL DEFAULT NULL COMMENT '生物特征加密令牌',
  `role` smallint NOT NULL,
  `deleted` smallint UNSIGNED NOT NULL DEFAULT 0 COMMENT '0：存在，1：逻辑删除',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE,
  INDEX `idx_role`(`role` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 782041091 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'e00cf25ad42683b3df678c61f42c6bda', 'e00cf25ad42683b3df678c61f42c6bda', '3070358878@qq.com', NULL, 0, 0);
INSERT INTO `user` VALUES (2, 'hwsh', '0f68265e9dace56f69272beb58ea85e4', '0f68265e9dace56f69272beb58ea85e4', 'hwsh@qq.com', NULL, 1, 0);
INSERT INTO `user` VALUES (3, '123', '202cb962ac59075b964b07152d234b70', '202cb962ac59075b964b07152d234b70', '123@qq.com', NULL, 1, 1);
INSERT INTO `user` VALUES (303878146, 'test', '098f6bcd4621d373cade4e832627b4f6', '098f6bcd4621d373cade4e832627b4f6', 'test@qq.com', NULL, 1, 0);
INSERT INTO `user` VALUES (412938241, 'test2', 'ad0234829205b9033196ba818f7a872b', 'ad0234829205b9033196ba818f7a872b', 'test2@qq.com', NULL, 1, 0);
INSERT INTO `user` VALUES (584908802, 'test1', '5a105e8b9d40e1329780d62ea2265d8a', '5a105e8b9d40e1329780d62ea2265d8a', 'test1@qq.com', NULL, 2, 0);
INSERT INTO `user` VALUES (782041090, 'test11', 'f696282aa4cd4f614aa995190cf442fe', 'f696282aa4cd4f614aa995190cf442fe', 'test11@qq.com', NULL, 1, 0);
INSERT INTO `user` VALUES (2111631361, 'test3', '8ad8757baa8564dc136c1e07507f4a98', '8ad8757baa8564dc136c1e07507f4a98', 'test3@qq.com', NULL, 1, 0);

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse`  (
  `warehouse_id` int NOT NULL AUTO_INCREMENT COMMENT '仓库ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '仓库名称',
  `location` point NOT NULL COMMENT '地理坐标',
  `area` decimal(10, 2) NULL DEFAULT NULL COMMENT '面积（平方米）',
  `environment_zone` enum('常温区','冷链区','危险品区') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `deleted` smallint NOT NULL DEFAULT 0 COMMENT '0：存在，1：逻辑删除',
  PRIMARY KEY (`warehouse_id`) USING BTREE,
  SPATIAL INDEX `idx_location`(`location`)
) ENGINE = InnoDB AUTO_INCREMENT = 1377628164 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO `warehouse` VALUES (-2145619966, '华北中心仓', ST_GeomFromText('POINT(116.4074 39.9042)'), 250.00, '常温区', 0);
INSERT INTO `warehouse` VALUES (-1835208703, '上海仓库', ST_GeomFromText('POINT(121.4737 31.2304)'), 5000.00, '危险品区', 0);
INSERT INTO `warehouse` VALUES (1, '上海智能仓1号', ST_GeomFromText('POINT(31.2304 121.4737)'), NULL, '常温区', 0);
INSERT INTO `warehouse` VALUES (2, '北京冷链仓', ST_GeomFromText('POINT(39.9042 116.4074)'), NULL, '冷链区', 0);
INSERT INTO `warehouse` VALUES (1377628161, '桂林电子科技大学B区宿舍', ST_GeomFromText('POINT(88.666 66.6666)'), 200.00, '危险品区', 1);
INSERT INTO `warehouse` VALUES (1377628162, '华北中心仓', ST_GeomFromText('POINT(116.4074 39.9042)'), 250.00, '常温区', 0);
INSERT INTO `warehouse` VALUES (1377628163, '华北中心仓', ST_GeomFromText('POINT(116.4074 39.9042)'), 250.00, '常温区', 0);

-- ----------------------------
-- Triggers structure for table operation_log
-- ----------------------------
DROP TRIGGER IF EXISTS `inventory_update`;
delimiter ;;
CREATE TRIGGER `inventory_update` AFTER INSERT ON `operation_log` FOR EACH ROW BEGIN
    IF NEW.`operation_type` = '入库' THEN
        UPDATE `inventory` SET `quantity` = `quantity` + 1 
        WHERE `goods_id` = NEW.`goods_id`;
    ELSEIF NEW.`operation_type` = '出库' THEN
        UPDATE `inventory` SET `quantity` = `quantity` - 1 
        WHERE `goods_id` = NEW.`goods_id`;
    END IF;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
