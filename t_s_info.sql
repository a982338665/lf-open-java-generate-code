/*
 Navicat Premium Data Transfer

 Source Server         : 公司测试库
 Source Server Type    : MySQL
 Source Server Version : 50633
 Source Host           : 172.30.10.41:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50633
 File Encoding         : 65001

 Date: 19/03/2021 15:54:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_s_info
-- ----------------------------
DROP TABLE IF EXISTS `t_s_info`;
CREATE TABLE `t_s_info`  (
  `id` int(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `age` int(18) NULL DEFAULT NULL COMMENT '年龄',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '性别：1-男 0-女',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL,
  `salary` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_s_info
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
