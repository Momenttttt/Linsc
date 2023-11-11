/*
 Navicat Premium Data Transfer

 Source Server         : Basic
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : localhost:3306
 Source Schema         : sign_in_application

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 09/11/2023 13:23:32
*/

-- 创建库
create database if not exists sign_in_application;

-- 切换库
use sign_in_application;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sg_class_info
-- ----------------------------
DROP TABLE IF EXISTS `sg_class_info`;
CREATE TABLE `sg_class_info`
(
    `id`          bigint                                                       NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`        varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '班级名',
    `profile`     varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL     DEFAULT NULL COMMENT '班级简介',
    `code`        int                                                          NOT NULL COMMENT '班级码',
    `create_user` bigint                                                       NOT NULL COMMENT '创建者',
    `create_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`   tinyint                                                      NOT NULL DEFAULT 0 COMMENT '是否删除（0 - 未删除，1 - 删除）',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `class_info_create_user` (`create_user` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '班级信息'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sg_signin_info
-- ----------------------------
DROP TABLE IF EXISTS `sg_signin_info`;
CREATE TABLE `sg_signin_info`
(
    `id`          bigint                                                        NOT NULL COMMENT 'id',
    `class_id`    bigint                                                        NOT NULL COMMENT '班级id',
    `profile`     varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL     DEFAULT NULL COMMENT '签到简介',
    `location`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL     DEFAULT NULL COMMENT '地理位置',
    `expire_time` int                                                           NOT NULL DEFAULT -1 COMMENT '过期时间(s)',
    `create_user` bigint                                                        NOT NULL COMMENT '创建者',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`   tinyint                                                       NOT NULL COMMENT '是否删除（0 - 未删除（默认），1 - 删除）',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `signin_info_class_id` (`class_id` ASC) USING BTREE,
    INDEX `signin_info_create_user` (`create_user` ASC) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '签到信息'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sg_signin_record
-- ----------------------------
DROP TABLE IF EXISTS `sg_signin_record`;
CREATE TABLE `sg_signin_record`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT 'id',
    `signin_user` bigint                                                        NOT NULL COMMENT '签到者',
    `location`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL     DEFAULT NULL COMMENT '地理位置',
    `status`      tinyint                                                       NOT NULL DEFAULT 0 COMMENT '状态（0 - 缺勤（默认），1 - 出勤，2 - 请假，3 - 补签）',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`   tinyint                                                       NOT NULL DEFAULT 0 COMMENT '是否删除（0 - 未删除，1 - 删除）',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `signin_record_signin_user` (`signin_user` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '签到记录'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sg_uc_ref
-- ----------------------------
DROP TABLE IF EXISTS `sg_uc_ref`;
CREATE TABLE `sg_uc_ref`
(
    `id`          bigint                                                       NOT NULL AUTO_INCREMENT COMMENT 'id',
    `class_id`    bigint                                                       NOT NULL COMMENT '班级id',
    `user_id`     bigint                                                       NOT NULL COMMENT '用户id',
    `role`        varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '身份（student|assistant|teacher）',
    `create_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`   tinyint                                                      NOT NULL DEFAULT 0 COMMENT '是否删除（0 - 未删除，1 - 删除）',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uniqueUser` (`class_id` ASC, `user_id` ASC) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '成员班级关系'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sg_user
-- ----------------------------
DROP TABLE IF EXISTS `sg_user`;
CREATE TABLE `sg_user`
(
    `id`           bigint                                                        NOT NULL AUTO_INCREMENT COMMENT 'id',
    `open_id`      varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL     DEFAULT NULL COMMENT '微信 opne_id',
    `union_id`     varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL     DEFAULT NULL COMMENT '微信 union_id',
    `account`      varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '账号',
    `password`     varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '密码',
    `avatar`       varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL     DEFAULT NULL COMMENT '用户头像',
    `name`         varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL     DEFAULT NULL COMMENT '用户名',
    `personal_sgn` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL     DEFAULT NULL COMMENT '个性签名',
    `sex`          tinyint                                                       NULL     DEFAULT 0 COMMENT '性别（0 - 女（默认），1 - 男）',
    `role`         varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci   NOT NULL DEFAULT 'user' COMMENT '角色（admin|user（默认））',
    `status`       tinyint                                                       NOT NULL DEFAULT 1 COMMENT '状态（0 - 封禁，1 - 正常（默认））',
    `create_time`  datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`    tinyint                                                       NOT NULL DEFAULT 0 COMMENT '是否删除（0 - 未删除，1 - 删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '用户'
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
