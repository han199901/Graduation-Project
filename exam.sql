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

 Date: 28/04/2021 13:50:37
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
  `deleted` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES (1, '张雁潮班', 0);

-- ----------------------------
-- Table structure for exam_paper
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper`;
CREATE TABLE `exam_paper`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '试卷名称',
  `subject_id` int(11) NULL DEFAULT NULL COMMENT '学科',
  `paper_type` int(11) NULL DEFAULT NULL COMMENT '试卷类型( 1固定试卷  2临时试卷 3班级试卷 4.时段试卷 5.推送试卷)',
  `grade_level` int(11) NULL DEFAULT NULL COMMENT '级别',
  `score` int(11) NULL DEFAULT NULL COMMENT '试卷总分(千分制)',
  `question_count` int(11) NULL DEFAULT NULL COMMENT '题目数量',
  `suggest_time` int(11) NULL DEFAULT NULL COMMENT '建议时长(分钟)',
  `limit_start_time` datetime(0) NULL DEFAULT NULL COMMENT '时段试卷 开始时间',
  `limit_end_time` datetime(0) NULL DEFAULT NULL COMMENT '时段试卷 结束时间',
  `frame_text_content_id` int(11) NULL DEFAULT NULL COMMENT '试卷框架 内容为JSON',
  `create_user` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `deleted` bit(1) NULL DEFAULT NULL,
  `task_exam_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of exam_paper
-- ----------------------------
INSERT INTO `exam_paper` VALUES (1, 'aa', 1, 1, 1, 10, 1, 1, NULL, NULL, 7, 1, '2021-04-26 15:27:20', b'1', NULL);
INSERT INTO `exam_paper` VALUES (2, 'aa', 1, 1, 1, 10, 1, 11, NULL, NULL, 8, 1, '2021-04-26 15:38:53', b'1', NULL);
INSERT INTO `exam_paper` VALUES (3, 'aaa', 1, 6, 1, 10, 1, 11, NULL, NULL, 10, 1, '2021-04-26 16:35:01', b'1', 1);
INSERT INTO `exam_paper` VALUES (4, 'test', 1, 1, 1, 10, 1, 11, NULL, NULL, 13, 1, '2021-04-26 21:57:33', b'0', NULL);

-- ----------------------------
-- Table structure for exam_paper_answer
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper_answer`;
CREATE TABLE `exam_paper_answer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `exam_paper_id` int(11) NULL DEFAULT NULL,
  `paper_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '试卷名称',
  `paper_type` int(11) NULL DEFAULT NULL COMMENT '试卷类型( 1固定试卷  2临时试卷 3班级试卷 4.时段试卷 )',
  `subject_id` int(11) NULL DEFAULT NULL COMMENT '学科',
  `system_score` int(11) NULL DEFAULT NULL COMMENT '系统判定得分',
  `user_score` int(11) NULL DEFAULT NULL COMMENT '最终得分(千分制)',
  `paper_score` int(11) NULL DEFAULT NULL COMMENT '试卷总分',
  `question_correct` int(11) NULL DEFAULT NULL COMMENT '做对题目数量',
  `question_count` int(11) NULL DEFAULT NULL COMMENT '题目总数量',
  `do_time` int(11) NULL DEFAULT NULL COMMENT '做题时间(秒)',
  `status` int(11) NULL DEFAULT NULL COMMENT '试卷状态(1待判分 2完成)',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '学生',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '提交时间',
  `task_exam_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of exam_paper_answer
-- ----------------------------
INSERT INTO `exam_paper_answer` VALUES (1, 4, 'test', 1, 1, 10, 10, 10, 1, 1, 2, 2, 2, '2021-04-27 10:50:49', NULL);
INSERT INTO `exam_paper_answer` VALUES (2, 4, 'test', 1, 1, 0, 0, 10, 0, 1, 1, 2, 2, '2021-04-27 14:10:26', NULL);

-- ----------------------------
-- Table structure for exam_paper_question_customer_answer
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper_question_customer_answer`;
CREATE TABLE `exam_paper_question_customer_answer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NULL DEFAULT NULL COMMENT '题目Id',
  `exam_paper_id` int(11) NULL DEFAULT NULL COMMENT '答案Id',
  `exam_paper_answer_id` int(11) NULL DEFAULT NULL,
  `question_type` int(11) NULL DEFAULT NULL COMMENT '题型',
  `subject_id` int(11) NULL DEFAULT NULL COMMENT '学科',
  `customer_score` int(11) NULL DEFAULT NULL COMMENT '得分',
  `question_score` int(11) NULL DEFAULT NULL COMMENT '题目原始分数',
  `question_text_content_id` int(11) NULL DEFAULT NULL COMMENT '问题内容',
  `answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '做题答案',
  `text_content_id` int(11) NULL DEFAULT NULL COMMENT '做题内容',
  `do_right` bit(1) NULL DEFAULT NULL COMMENT '是否正确',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '做题人',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `item_order` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of exam_paper_question_customer_answer
-- ----------------------------
INSERT INTO `exam_paper_question_customer_answer` VALUES (1, 7, 4, 1, 3, 1, 10, 10, 9, 'A', NULL, b'1', 2, '2021-04-27 10:50:49', 1);
INSERT INTO `exam_paper_question_customer_answer` VALUES (2, 7, 4, 2, 3, 1, 0, 10, 9, 'B', NULL, b'0', 2, '2021-04-27 14:10:26', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (1, 1, 1, 10, 1, 5, 'A', NULL, 1, 1, '2021-04-26 14:44:14', 1);
INSERT INTO `question` VALUES (2, 1, 1, 0, 1, 5, 'A', NULL, 1, 1, '2021-04-26 14:48:14', 1);
INSERT INTO `question` VALUES (3, 1, 1, 0, 1, 1, 'A', NULL, 1, 1, '2021-04-26 15:00:44', 1);
INSERT INTO `question` VALUES (4, 1, 1, 0, 1, 1, 'A', NULL, 1, 1, '2021-04-26 15:02:26', 1);
INSERT INTO `question` VALUES (5, 1, 1, 0, 1, 1, 'A', NULL, 1, 1, '2021-04-26 15:08:24', 1);
INSERT INTO `question` VALUES (6, 1, 1, 10, 1, 1, 'A', 6, 1, 1, '2021-04-26 15:09:54', 0);
INSERT INTO `question` VALUES (7, 3, 1, 10, 1, 1, 'A', 9, 1, 1, '2021-04-26 16:19:47', 0);
INSERT INTO `question` VALUES (8, 2, 1, 10, 1, 5, 'A,B,C', 12, 1, 1, '2021-04-26 21:56:23', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of relation
-- ----------------------------
INSERT INTO `relation` VALUES (1, 3, 2, 1);

-- ----------------------------
-- Table structure for task_exam
-- ----------------------------
DROP TABLE IF EXISTS `task_exam`;
CREATE TABLE `task_exam`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `grade_level` int(11) NULL DEFAULT NULL COMMENT '级别',
  `frame_text_content_id` int(11) NULL DEFAULT NULL COMMENT '任务框架 内容为JSON',
  `create_user` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `deleted` bit(1) NULL DEFAULT NULL,
  `create_user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of task_exam
-- ----------------------------
INSERT INTO `task_exam` VALUES (1, 'aaavvvas', 1, 11, 1, '2021-04-26 18:07:50', b'1', 'admin');

-- ----------------------------
-- Table structure for task_exam_customer_answer
-- ----------------------------
DROP TABLE IF EXISTS `task_exam_customer_answer`;
CREATE TABLE `task_exam_customer_answer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_exam_id` int(11) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `text_content_id` int(11) NULL DEFAULT NULL COMMENT '任务完成情况(Json)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of task_exam_customer_answer
-- ----------------------------

-- ----------------------------
-- Table structure for text_content
-- ----------------------------
DROP TABLE IF EXISTS `text_content`;
CREATE TABLE `text_content`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of text_content
-- ----------------------------
INSERT INTO `text_content` VALUES (1, '{\"titleContent\":\"请问请问\",\"analyze\":\"aabbccdd\",\"questionItemObjects\":[{\"prefix\":\"A\",\"content\":\"aa\",\"score\":null},{\"prefix\":\"B\",\"content\":\"bb\",\"score\":null},{\"prefix\":\"C\",\"content\":\"cc\",\"score\":null},{\"prefix\":\"D\",\"content\":\"dd\",\"score\":null}],\"correct\":\"A\"}', '2021-04-26 14:44:14');
INSERT INTO `text_content` VALUES (2, '{\"titleContent\":\"aaaa\",\"analyze\":\"123\",\"questionItemObjects\":[{\"prefix\":\"A\",\"content\":\"1\",\"score\":null},{\"prefix\":\"B\",\"content\":\"2\",\"score\":null},{\"prefix\":\"C\",\"content\":\"3\",\"score\":null},{\"prefix\":\"D\",\"content\":\"4\",\"score\":null}],\"correct\":\"A\"}', '2021-04-26 14:48:14');
INSERT INTO `text_content` VALUES (3, '{\"titleContent\":\"123123\",\"analyze\":\"111\",\"questionItemObjects\":[{\"prefix\":\"A\",\"content\":\"1\",\"score\":null},{\"prefix\":\"B\",\"content\":\"1\",\"score\":null},{\"prefix\":\"C\",\"content\":\"1\",\"score\":null},{\"prefix\":\"D\",\"content\":\"1\",\"score\":null}],\"correct\":\"A\"}', '2021-04-26 15:00:44');
INSERT INTO `text_content` VALUES (4, '{\"titleContent\":\"<p>题干</p>\",\"analyze\":\"111\",\"questionItemObjects\":[{\"prefix\":\"A\",\"content\":\"1\",\"score\":null},{\"prefix\":\"B\",\"content\":\"1\",\"score\":null},{\"prefix\":\"C\",\"content\":\"1\",\"score\":null},{\"prefix\":\"D\",\"content\":\"1\",\"score\":null}],\"correct\":\"A\"}', '2021-04-26 15:02:26');
INSERT INTO `text_content` VALUES (5, '{\"titleContent\":\"啊的沙发上地方\",\"analyze\":\"asdf\",\"questionItemObjects\":[{\"prefix\":\"A\",\"content\":\"a\",\"score\":null},{\"prefix\":\"B\",\"content\":\"b\",\"score\":null},{\"prefix\":\"C\",\"content\":\"c\",\"score\":null},{\"prefix\":\"D\",\"content\":\"d\",\"score\":null}],\"correct\":\"A\"}', '2021-04-26 15:08:24');
INSERT INTO `text_content` VALUES (6, '{\"titleContent\":\"啊电饭锅八十多发\",\"analyze\":\"asdf\",\"questionItemObjects\":[{\"prefix\":\"A\",\"content\":\"aa\",\"score\":null},{\"prefix\":\"B\",\"content\":\"adsf\",\"score\":null},{\"prefix\":\"C\",\"content\":\"adsf\",\"score\":null},{\"prefix\":\"D\",\"content\":\"adf\",\"score\":null}],\"correct\":\"A\"}', '2021-04-26 15:09:54');
INSERT INTO `text_content` VALUES (7, '[{\"name\":\"timu 1\",\"questionItems\":[{\"id\":6,\"itemOrder\":1}]}]', '2021-04-26 15:27:20');
INSERT INTO `text_content` VALUES (8, '[{\"name\":\"aa\",\"questionItems\":[{\"id\":6,\"itemOrder\":1}]}]', '2021-04-26 15:38:53');
INSERT INTO `text_content` VALUES (9, '{\"titleContent\":\"<p>panduanti tigan</p>\",\"analyze\":\"jiexi\",\"questionItemObjects\":[{\"prefix\":\"A\",\"content\":\"是\",\"score\":null},{\"prefix\":\"B\",\"content\":\"否\",\"score\":null}],\"correct\":\"A\"}', '2021-04-26 16:19:47');
INSERT INTO `text_content` VALUES (10, '[{\"name\":\"111\",\"questionItems\":[{\"id\":7,\"itemOrder\":1}]}]', '2021-04-26 16:35:01');
INSERT INTO `text_content` VALUES (11, '[{\"examPaperId\":3,\"examPaperName\":\"aaa\",\"itemOrder\":null}]', '2021-04-26 18:07:50');
INSERT INTO `text_content` VALUES (12, '{\"titleContent\":\"duoxuan\",\"analyze\":\"jiex\",\"questionItemObjects\":[{\"prefix\":\"A\",\"content\":\"aa\",\"score\":null},{\"prefix\":\"B\",\"content\":\"bb\",\"score\":null},{\"prefix\":\"C\",\"content\":\"cc\",\"score\":null},{\"prefix\":\"D\",\"content\":\"dd\",\"score\":null}],\"correct\":\"\"}', '2021-04-26 21:56:23');
INSERT INTO `text_content` VALUES (13, '[{\"name\":\"1\",\"questionItems\":[{\"id\":7,\"itemOrder\":1}]}]', '2021-04-26 21:57:33');

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456', 'admin', NULL, NULL, NULL, NULL, 3, 1, NULL, NULL, NULL, NULL, 0, 1, 0);
INSERT INTO `user` VALUES (2, 'student', '123456', 'student', NULL, 1, '2021-04-08 12:00:00', NULL, 1, 1, 'http://image.hanblog.fun/FtcYaGvDfBMWT7HwBPI4W_fSraPM', NULL, '2021-04-27 18:42:44', NULL, 0, 1, 0);
INSERT INTO `user` VALUES (3, 'teacher', '123456', 'teacher', 11, 1, '2021-04-09 12:00:00', '11', 2, 1, NULL, NULL, '2021-04-19 16:49:39', NULL, 0, 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
