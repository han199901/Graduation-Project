/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : exam

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 17/04/2021 18:17:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for exam_paper
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper`;
CREATE TABLE `exam_paper` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '试卷名称',
  `subject_id` int DEFAULT NULL COMMENT '学科',
  `paper_type` int DEFAULT NULL COMMENT '试卷类型( 1固定试卷  2临时试卷 3班级试卷 4.时段试卷 5.推送试卷)',
  `grade_level` int DEFAULT NULL COMMENT '级别',
  `score` int DEFAULT NULL COMMENT '试卷总分(千分制)',
  `question_count` int DEFAULT NULL COMMENT '题目数量',
  `suggest_time` int DEFAULT NULL COMMENT '建议时长(分钟)',
  `limit_start_time` datetime DEFAULT NULL COMMENT '时段试卷 开始时间',
  `limit_end_time` datetime DEFAULT NULL COMMENT '时段试卷 结束时间',
  `frame_text_content_id` int DEFAULT NULL COMMENT '试卷框架 内容为JSON',
  `create_user` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `task_exam_id` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for exam_paper_answer
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper_answer`;
CREATE TABLE `exam_paper_answer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `exam_paper_id` int DEFAULT NULL,
  `paper_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '试卷名称',
  `paper_type` int DEFAULT NULL COMMENT '试卷类型( 1固定试卷  2临时试卷 3班级试卷 4.时段试卷 )',
  `subject_id` int DEFAULT NULL COMMENT '学科',
  `system_score` int DEFAULT NULL COMMENT '系统判定得分',
  `user_score` int DEFAULT NULL COMMENT '最终得分(千分制)',
  `paper_score` int DEFAULT NULL COMMENT '试卷总分',
  `question_correct` int DEFAULT NULL COMMENT '做对题目数量',
  `question_count` int DEFAULT NULL COMMENT '题目总数量',
  `do_time` int DEFAULT NULL COMMENT '做题时间(秒)',
  `status` int DEFAULT NULL COMMENT '试卷状态(1待判分 2完成)',
  `create_user` int DEFAULT NULL COMMENT '学生',
  `create_time` datetime DEFAULT NULL COMMENT '提交时间',
  `task_exam_id` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for exam_paper_question_customer_answer
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper_question_customer_answer`;
CREATE TABLE `exam_paper_question_customer_answer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question_id` int DEFAULT NULL COMMENT '题目Id',
  `exam_paper_id` int DEFAULT NULL COMMENT '答案Id',
  `exam_paper_answer_id` int DEFAULT NULL,
  `question_type` int DEFAULT NULL COMMENT '题型',
  `subject_id` int DEFAULT NULL COMMENT '学科',
  `customer_score` int DEFAULT NULL COMMENT '得分',
  `question_score` int DEFAULT NULL COMMENT '题目原始分数',
  `question_text_content_id` int DEFAULT NULL COMMENT '问题内容',
  `answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '做题答案',
  `text_content_id` int DEFAULT NULL COMMENT '做题内容',
  `do_right` bit(1) DEFAULT NULL COMMENT '是否正确',
  `create_user` int DEFAULT NULL COMMENT '做题人',
  `create_time` datetime DEFAULT NULL,
  `item_order` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question_type` int DEFAULT NULL COMMENT '1.单选题  2.多选题  3.判断题 4.填空题 5.简答题',
  `subject_id` int DEFAULT NULL COMMENT '学科',
  `score` int DEFAULT NULL COMMENT '题目总分(千分制)',
  `grade_level` int DEFAULT NULL COMMENT '级别',
  `difficult` int DEFAULT NULL COMMENT '题目难度',
  `correct` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '正确答案',
  `info_text_content_id` int DEFAULT NULL COMMENT '题目  填空、 题干、解析、答案等信息',
  `create_user` int DEFAULT NULL COMMENT '创建人',
  `status` int DEFAULT NULL COMMENT '1.正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for relation
-- ----------------------------
DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tid` int DEFAULT NULL COMMENT '老师id',
  `sid` int DEFAULT NULL COMMENT '学生id',
  `cid` int DEFAULT NULL COMMENT '班级id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for task_exam
-- ----------------------------
DROP TABLE IF EXISTS `task_exam`;
CREATE TABLE `task_exam` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `grade_level` int DEFAULT NULL COMMENT '级别',
  `frame_text_content_id` int DEFAULT NULL COMMENT '任务框架 内容为JSON',
  `create_user` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `create_user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for task_exam_customer_answer
-- ----------------------------
DROP TABLE IF EXISTS `task_exam_customer_answer`;
CREATE TABLE `task_exam_customer_answer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `task_exam_id` int DEFAULT NULL,
  `create_user` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `text_content_id` int DEFAULT NULL COMMENT '任务完成情况(Json)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for text_content
-- ----------------------------
DROP TABLE IF EXISTS `text_content`;
CREATE TABLE `text_content` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '真实姓名',
  `age` int DEFAULT NULL,
  `sex` int DEFAULT NULL COMMENT '1.男 2女',
  `birth_day` datetime DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `role` int DEFAULT NULL COMMENT '1.学生 2.老师 3.管理员',
  `status` int DEFAULT NULL COMMENT '1.启用 2禁用',
  `image_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像地址',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `last_active_time` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `enabled` tinyint(1) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT;

SET FOREIGN_KEY_CHECKS = 1;
