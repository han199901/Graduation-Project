/*
 Navicat Premium Data Transfer

 Source Server         : libraryDB
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : exam

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 16/04/2021 09:11:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_type` int(11) NULL DEFAULT NULL COMMENT '1.单选题  2.多选题  3.判断题 4.填空题 5.简答题',
  `subject_id` int(11) NULL DEFAULT NULL COMMENT '学科',
  `score` int(11) NULL DEFAULT NULL COMMENT '题目总分(千分制)',
  `grade_level` int(11) NULL DEFAULT NULL COMMENT '级别',
  `difficult` int(11) NULL DEFAULT NULL COMMENT '题目难度',
  `correct` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '正确答案',
  `info_text_content_id` int(11) NULL DEFAULT NULL COMMENT '题目  填空、 题干、解析、答案等信息',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `status` int(11) NULL DEFAULT NULL COMMENT '1.正常',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for relation
-- ----------------------------
DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tid` int(11) NULL DEFAULT NULL COMMENT '老师id',
  `sid` int(11) NULL DEFAULT NULL COMMENT '学生id',
  `cid` int(11) NULL DEFAULT NULL COMMENT '班级id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `age` int(11) NULL DEFAULT NULL,
  `sex` int(11) NULL DEFAULT NULL COMMENT '1.男 2女',
  `birth_day` datetime(0) NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role` int(11) NULL DEFAULT NULL COMMENT '1.学生 2.老师 3.管理员',
  `status` int(11) NULL DEFAULT NULL COMMENT '1.启用 2禁用',
  `image_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `modify_time` datetime(0) NULL DEFAULT NULL,
  `last_active_time` datetime(0) NULL DEFAULT NULL,
  `deleted` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除',
  `enabled` tinyint(1) NULL DEFAULT NULL,
  `locked` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

SET FOREIGN_KEY_CHECKS = 1;
