/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MariaDB
 Source Server Version : 100428
 Source Host           : localhost:3306
 Source Schema         : xiaoxiang_lectopic

 Target Server Type    : MariaDB
 Target Server Version : 100428
 File Encoding         : 65001

 Date: 04/05/2023 11:53:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_schedule
-- ----------------------------
DROP TABLE IF EXISTS `t_schedule`;
CREATE TABLE `t_schedule`  (
  `schedule_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID编号，唯一',
  `select_id` int(11) NULL DEFAULT NULL COMMENT '对应的选择',
  `p1` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '进度1',
  `p2` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '进度2',
  `p3` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '进度3',
  `p4` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '进度4',
  `p5` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '进度5',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`schedule_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_schedule
-- ----------------------------

-- ----------------------------
-- Table structure for t_select
-- ----------------------------
DROP TABLE IF EXISTS `t_select`;
CREATE TABLE `t_select`  (
  `select_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，唯一',
  `student_id` int(11) NULL DEFAULT NULL COMMENT '学生ID',
  `topic_id` int(11) NULL DEFAULT NULL COMMENT '选题ID',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态（0正常 1失败）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`select_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_select
-- ----------------------------

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student`  (
  `user_id` int(10) UNSIGNED NOT NULL COMMENT '绑定的用户ID',
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键，唯一',
  `active` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否激活：1激活，0禁用',
  `number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学号',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `grade` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '年级（年）',
  `major` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专业',
  `college` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '院系',
  `classes` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '班级',
  `create_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建者',
  `update_by` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '更新者',
  `create_timestamp` bigint(20) UNSIGNED NOT NULL COMMENT '创建时间',
  `update_timestamp` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后修改时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_student
-- ----------------------------

-- ----------------------------
-- Table structure for t_system
-- ----------------------------
DROP TABLE IF EXISTS `t_system`;
CREATE TABLE `t_system`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '值',
  `create_by` int(10) UNSIGNED NOT NULL COMMENT '创建者',
  `update_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新者',
  `create_timestamp` bigint(20) UNSIGNED NOT NULL COMMENT '创建时间',
  `update_timestamp` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system
-- ----------------------------

-- ----------------------------
-- Table structure for t_teacher
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher`  (
  `user_id` int(10) UNSIGNED NOT NULL COMMENT '绑定的用户ID',
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键，唯一',
  `active` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否激活：1激活，0禁用',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职称',
  `college` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '院系',
  `section` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '教研室',
  `profile` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个人简介',
  `create_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建者',
  `update_by` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '更新者',
  `create_timestamp` bigint(20) UNSIGNED NOT NULL COMMENT '创建时间',
  `update_timestamp` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后修改时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_teacher
-- ----------------------------

-- ----------------------------
-- Table structure for t_topic
-- ----------------------------
DROP TABLE IF EXISTS `t_topic`;
CREATE TABLE `t_topic`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键，唯一',
  `active` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '账号是否激活：1激活，0禁用',
  `teacher_id` int(10) UNSIGNED NOT NULL COMMENT '选题老师',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '选题名称',
  `description` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '选题简介',
  `category` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '选题分类',
  `restriction` int(10) UNSIGNED NOT NULL COMMENT '限制人数',
  `selected` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '选题已选人数',
  `create_by` int(10) UNSIGNED NOT NULL COMMENT '创建者',
  `update_by` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '更新者',
  `create_timestamp` bigint(20) UNSIGNED NOT NULL COMMENT '创建时间',
  `update_timestamp` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后修改时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_topic
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID，自动生成，唯一',
  `active` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '账号是否激活：1激活，0禁用',
  `account` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号，登录用，不可修改',
  `passwd` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码，登录用，MD5加密',
  `role` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '角色，0：学生，1：老师，2：管理员',
  `nick` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `signature` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '签名',
  `sex` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '性别，0：未设置，1：男，2：女',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `has_new_msg` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '新消息计数',
  `create_by` int(10) UNSIGNED NOT NULL COMMENT '创建者',
  `update_by` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '更新者',
  `create_timestamp` bigint(20) UNSIGNED NOT NULL COMMENT '创建时间',
  `update_timestamp` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后修改时间',
  `last_login_timestamp` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后登录时间',
  `last_login_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '最后登录IP',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`, `account`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (105, 1, 'admin', '27csvtirid3bl2m2e84rj4upd4', 2, 'admin', '', '', '', 0, 'h000.gif', 0, 0, NULL, 1683101824764, 0, 1683172142398, '192.168.70.128', NULL);

SET FOREIGN_KEY_CHECKS = 1;
