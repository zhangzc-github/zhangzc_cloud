/*
 Navicat Premium Data Transfer

 Source Server         : my-mysql
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : pigxx

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 07/09/2022 17:00:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `sort_order` int NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` char(1) DEFAULT '0',
  `parent_id` bigint DEFAULT NULL,
  `tenant_id` bigint DEFAULT NULL,
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES (1, '山东', 1, '2018-01-22 19:00:23', '2019-05-18 14:56:06', '0', 0, 1);
INSERT INTO `sys_dept` VALUES (2, '沙县国际', 2, '2018-01-22 19:00:38', '2019-05-18 14:12:07', '0', 0, 1);
INSERT INTO `sys_dept` VALUES (3, '潍坊', 3, '2018-01-22 19:00:44', '2019-05-18 14:56:11', '0', 1, 1);
INSERT INTO `sys_dept` VALUES (4, '高新', 4, '2018-01-22 19:00:52', '2019-05-18 14:56:09', '0', 3, 1);
INSERT INTO `sys_dept` VALUES (5, '院校', 5, '2018-01-22 19:00:57', '2019-05-18 14:56:13', '0', 4, 1);
INSERT INTO `sys_dept` VALUES (6, '潍院', 6, '2018-01-22 19:01:06', '2019-05-18 14:56:16', '1', 5, 1);
INSERT INTO `sys_dept` VALUES (7, '山东沙县', 7, '2018-01-22 19:01:57', '2019-05-18 14:12:17', '0', 2, 1);
INSERT INTO `sys_dept` VALUES (8, '潍坊沙县', 8, '2018-01-22 19:02:03', '2019-05-18 14:12:19', '0', 7, 1);
INSERT INTO `sys_dept` VALUES (15, '租户默认部门', 0, '2022-07-06 08:30:59', NULL, '0', 0, 1544598217314631684);
COMMIT;

-- ----------------------------
-- Table structure for sys_dept_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_relation`;
CREATE TABLE `sys_dept_relation` (
  `ancestor` bigint NOT NULL COMMENT '祖先节点',
  `descendant` bigint NOT NULL COMMENT '后代节点',
  PRIMARY KEY (`ancestor`,`descendant`) USING BTREE,
  KEY `idx1` (`ancestor`) USING BTREE,
  KEY `idx2` (`descendant`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门关系表';

-- ----------------------------
-- Records of sys_dept_relation
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept_relation` VALUES (1, 1);
INSERT INTO `sys_dept_relation` VALUES (1, 3);
INSERT INTO `sys_dept_relation` VALUES (1, 4);
INSERT INTO `sys_dept_relation` VALUES (1, 5);
INSERT INTO `sys_dept_relation` VALUES (2, 2);
INSERT INTO `sys_dept_relation` VALUES (2, 7);
INSERT INTO `sys_dept_relation` VALUES (2, 8);
INSERT INTO `sys_dept_relation` VALUES (2, 11);
INSERT INTO `sys_dept_relation` VALUES (2, 12);
INSERT INTO `sys_dept_relation` VALUES (3, 3);
INSERT INTO `sys_dept_relation` VALUES (3, 4);
INSERT INTO `sys_dept_relation` VALUES (3, 5);
INSERT INTO `sys_dept_relation` VALUES (4, 4);
INSERT INTO `sys_dept_relation` VALUES (4, 5);
INSERT INTO `sys_dept_relation` VALUES (5, 5);
INSERT INTO `sys_dept_relation` VALUES (7, 7);
INSERT INTO `sys_dept_relation` VALUES (7, 8);
INSERT INTO `sys_dept_relation` VALUES (7, 11);
INSERT INTO `sys_dept_relation` VALUES (7, 12);
INSERT INTO `sys_dept_relation` VALUES (8, 8);
INSERT INTO `sys_dept_relation` VALUES (15, 15);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `system_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
  `del_flag` char(1) DEFAULT '0',
  `tenant_id` bigint NOT NULL DEFAULT '0' COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_dict_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES (1, 'log_type', '日志类型', '2019-03-19 11:06:44', '2019-03-19 11:06:44', '异常、正常', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (2, 'social_type', '社交登录', '2019-03-19 11:09:44', '2019-03-19 11:09:44', '微信、QQ', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (3, 'leave_status', '请假状态', '2019-03-19 11:09:44', '2019-03-19 11:09:44', '未提交、审批中、完成、驳回', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (4, 'job_type', '定时任务类型', '2019-03-19 11:22:21', '2019-03-19 11:22:21', 'quartz', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (5, 'job_status', '定时任务状态', '2019-03-19 11:24:57', '2019-03-19 11:24:57', '发布状态、运行状态', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (6, 'job_execute_status', '定时任务执行状态', '2019-03-19 11:26:15', '2019-03-19 11:26:15', '正常、异常', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (7, 'misfire_policy', '定时任务错失执行策略', '2019-03-19 11:27:19', '2019-03-19 11:27:19', '周期', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (8, 'gender', '性别', '2019-03-27 13:44:06', '2019-03-27 13:44:06', '微信用户性别', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (9, 'subscribe', '订阅状态', '2019-03-27 13:48:33', '2019-03-27 13:48:33', '公众号订阅状态', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (10, 'response_type', '回复', '2019-03-28 21:29:21', '2019-03-28 21:29:21', '微信消息是否已回复', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (11, 'param_type', '参数配置', '2019-04-29 18:20:47', '2019-04-29 18:20:47', '检索、原文、报表、安全、文档、消息、其他', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (12, 'status_type', '租户状态', '2019-05-15 16:31:08', '2019-05-15 16:31:08', '租户状态', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (13, 'dict_type', '字典类型', '2019-05-16 14:16:20', '2019-05-16 14:20:16', '系统类不能修改', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (14, 'channel_status', '支付渠道状态', '2019-05-30 16:14:43', '2019-05-30 16:14:43', '支付渠道状态（0-正常，1-冻结）', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (15, 'channel_id', '渠道编码ID', '2019-05-30 18:59:12', '2019-05-30 18:59:12', '不同的支付方式', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (16, 'order_status', '订单状态', '2019-06-27 08:17:40', '2019-06-27 08:17:40', '支付订单状态', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (17, 'grant_types', '授权类型', '2019-08-13 07:34:10', '2019-08-13 07:34:10', NULL, '1', '0', 1);
INSERT INTO `sys_dict` VALUES (18, 'style_type', '前端风格', '2020-02-07 03:49:28', '2020-02-07 03:50:40', '0-Avue 1-element', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (19, 'captcha_flag_types', '验证码开关', '2020-11-18 06:53:25', '2020-11-18 06:53:25', '是否校验验证码', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (20, 'enc_flag_types', '前端密码加密', '2020-11-18 06:54:44', '2020-11-18 06:54:44', '前端密码是否加密传输', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (140, 'log_type', '日志类型', '2019-03-19 11:06:44', '2019-03-19 11:06:44', '异常、正常', '1', '0', 1544598217314631684);
INSERT INTO `sys_dict` VALUES (141, 'social_type', '社交登录', '2019-03-19 11:09:44', '2019-03-19 11:09:44', '微信、QQ', '1', '0', 1544598217314631684);
INSERT INTO `sys_dict` VALUES (142, 'leave_status', '请假状态', '2019-03-19 11:09:44', '2019-03-19 11:09:44', '未提交、审批中、完成、驳回', '1', '0', 1544598217314631684);
INSERT INTO `sys_dict` VALUES (143, 'job_type', '定时任务类型', '2019-03-19 11:22:21', '2019-03-19 11:22:21', 'quartz', '1', '0', 1544598217314631684);
INSERT INTO `sys_dict` VALUES (144, 'job_status', '定时任务状态', '2019-03-19 11:24:57', '2019-03-19 11:24:57', '发布状态、运行状态', '1', '0', 1544598217314631684);
INSERT INTO `sys_dict` VALUES (145, 'job_execute_status', '定时任务执行状态', '2019-03-19 11:26:15', '2019-03-19 11:26:15', '正常、异常', '1', '0', 1544598217314631684);
INSERT INTO `sys_dict` VALUES (146, 'misfire_policy', '定时任务错失执行策略', '2019-03-19 11:27:19', '2019-03-19 11:27:19', '周期', '1', '0', 1544598217314631684);
INSERT INTO `sys_dict` VALUES (147, 'gender', '性别', '2019-03-27 13:44:06', '2019-03-27 13:44:06', '微信用户性别', '1', '0', 1544598217314631684);
INSERT INTO `sys_dict` VALUES (148, 'subscribe', '订阅状态', '2019-03-27 13:48:33', '2019-03-27 13:48:33', '公众号订阅状态', '1', '0', 1544598217314631684);
INSERT INTO `sys_dict` VALUES (149, 'response_type', '回复', '2019-03-28 21:29:21', '2019-03-28 21:29:21', '微信消息是否已回复', '1', '0', 1544598217314631684);
INSERT INTO `sys_dict` VALUES (150, 'param_type', '参数配置', '2019-04-29 18:20:47', '2019-04-29 18:20:47', '检索、原文、报表、安全、文档、消息、其他', '1', '0', 1544598217314631684);
INSERT INTO `sys_dict` VALUES (151, 'status_type', '租户状态', '2019-05-15 16:31:08', '2019-05-15 16:31:08', '租户状态', '1', '0', 1544598217314631684);
INSERT INTO `sys_dict` VALUES (152, 'dict_type', '字典类型', '2019-05-16 14:16:20', '2019-05-16 14:20:16', '系统类不能修改', '1', '0', 1544598217314631684);
INSERT INTO `sys_dict` VALUES (153, 'channel_status', '支付渠道状态', '2019-05-30 16:14:43', '2019-05-30 16:14:43', '支付渠道状态（0-正常，1-冻结）', '1', '0', 1544598217314631684);
INSERT INTO `sys_dict` VALUES (154, 'channel_id', '渠道编码ID', '2019-05-30 18:59:12', '2019-05-30 18:59:12', '不同的支付方式', '1', '0', 1544598217314631684);
INSERT INTO `sys_dict` VALUES (155, 'order_status', '订单状态', '2019-06-27 08:17:40', '2019-06-27 08:17:40', '支付订单状态', '1', '0', 1544598217314631684);
INSERT INTO `sys_dict` VALUES (156, 'grant_types', '授权类型', '2019-08-13 07:34:10', '2019-08-13 07:34:10', NULL, '1', '0', 1544598217314631684);
INSERT INTO `sys_dict` VALUES (157, 'style_type', '前端风格', '2020-02-07 03:49:28', '2020-02-07 03:50:40', '0-Avue 1-element', '1', '0', 1544598217314631684);
INSERT INTO `sys_dict` VALUES (158, 'captcha_flag_types', '验证码开关', '2020-11-18 06:53:25', '2020-11-18 06:53:25', '是否校验验证码', '1', '0', 1544598217314631684);
INSERT INTO `sys_dict` VALUES (159, 'enc_flag_types', '前端密码加密', '2020-11-18 06:54:44', '2020-11-18 06:54:44', '前端密码是否加密传输', '1', '0', 1544598217314631684);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `dict_id` bigint NOT NULL,
  `value` varchar(100) DEFAULT NULL,
  `label` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `sort_order` int NOT NULL DEFAULT '0' COMMENT '排序（升序）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `del_flag` char(1) DEFAULT '0',
  `tenant_id` bigint NOT NULL DEFAULT '0' COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_dict_value` (`value`) USING BTREE,
  KEY `sys_dict_label` (`label`) USING BTREE,
  KEY `sys_dict_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1061 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典项';

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_item` VALUES (1, 1, '9', '异常', 'log_type', '日志异常', 1, '2019-03-19 11:08:59', '2019-03-25 12:49:13', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (2, 1, '0', '正常', 'log_type', '日志正常', 0, '2019-03-19 11:09:17', '2019-03-25 12:49:18', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (3, 2, 'WX', '微信', 'social_type', '微信登录', 0, '2019-03-19 11:10:02', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (4, 2, 'QQ', 'QQ', 'social_type', 'QQ登录', 1, '2019-03-19 11:10:14', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (5, 3, '0', '未提交', 'leave_status', '未提交', 0, '2019-03-19 11:18:34', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (6, 3, '1', '审批中', 'leave_status', '审批中', 1, '2019-03-19 11:18:45', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (7, 3, '2', '完成', 'leave_status', '完成', 2, '2019-03-19 11:19:02', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (8, 3, '9', '驳回', 'leave_status', '驳回', 9, '2019-03-19 11:19:20', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (9, 4, '1', 'java类', 'job_type', 'java类', 1, '2019-03-19 11:22:37', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (10, 4, '2', 'spring bean', 'job_type', 'spring bean容器实例', 2, '2019-03-19 11:23:05', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (11, 4, '9', '其他', 'job_type', '其他类型', 9, '2019-03-19 11:23:31', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (12, 4, '3', 'Rest 调用', 'job_type', 'Rest 调用', 3, '2019-03-19 11:23:57', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (13, 4, '4', 'jar', 'job_type', 'jar类型', 4, '2019-03-19 11:24:20', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (14, 5, '1', '未发布', 'job_status', '未发布', 1, '2019-03-19 11:25:18', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (15, 5, '2', '运行中', 'job_status', '运行中', 2, '2019-03-19 11:25:31', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (16, 5, '3', '暂停', 'job_status', '暂停', 3, '2019-03-19 11:25:42', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (17, 6, '0', '正常', 'job_execute_status', '正常', 0, '2019-03-19 11:26:27', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (18, 6, '1', '异常', 'job_execute_status', '异常', 1, '2019-03-19 11:26:41', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (19, 7, '1', '错失周期立即执行', 'misfire_policy', '错失周期立即执行', 1, '2019-03-19 11:27:45', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (20, 7, '2', '错失周期执行一次', 'misfire_policy', '错失周期执行一次', 2, '2019-03-19 11:27:57', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (21, 7, '3', '下周期执行', 'misfire_policy', '下周期执行', 3, '2019-03-19 11:28:08', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (22, 8, '1', '男', 'gender', '微信-男', 0, '2019-03-27 13:45:13', '2019-03-27 13:45:13', '微信-男', '0', 1);
INSERT INTO `sys_dict_item` VALUES (23, 8, '2', '女', 'gender', '女-微信', 1, '2019-03-27 13:45:34', '2019-03-27 13:45:34', '女-微信', '0', 1);
INSERT INTO `sys_dict_item` VALUES (24, 8, '0', '未知', 'gender', 'x性别未知', 3, '2019-03-27 13:45:57', '2019-03-27 13:45:57', 'x性别未知', '0', 1);
INSERT INTO `sys_dict_item` VALUES (25, 9, '0', '未关注', 'subscribe', '公众号-未关注', 0, '2019-03-27 13:49:07', '2019-03-27 13:49:07', '公众号-未关注', '0', 1);
INSERT INTO `sys_dict_item` VALUES (26, 9, '1', '已关注', 'subscribe', '公众号-已关注', 1, '2019-03-27 13:49:26', '2019-03-27 13:49:26', '公众号-已关注', '0', 1);
INSERT INTO `sys_dict_item` VALUES (27, 10, '0', '未回复', 'response_type', '微信消息-未回复', 0, '2019-03-28 21:29:47', '2019-03-28 21:29:47', '微信消息-未回复', '0', 1);
INSERT INTO `sys_dict_item` VALUES (28, 10, '1', '已回复', 'response_type', '微信消息-已回复', 1, '2019-03-28 21:30:08', '2019-03-28 21:30:08', '微信消息-已回复', '0', 1);
INSERT INTO `sys_dict_item` VALUES (29, 11, '1', '检索', 'param_type', '检索', 0, '2019-04-29 18:22:17', '2019-04-29 18:22:17', '检索', '0', 1);
INSERT INTO `sys_dict_item` VALUES (30, 11, '2', '原文', 'param_type', '原文', 0, '2019-04-29 18:22:27', '2019-04-29 18:22:27', '原文', '0', 1);
INSERT INTO `sys_dict_item` VALUES (31, 11, '3', '报表', 'param_type', '报表', 0, '2019-04-29 18:22:36', '2019-04-29 18:22:36', '报表', '0', 1);
INSERT INTO `sys_dict_item` VALUES (32, 11, '4', '安全', 'param_type', '安全', 0, '2019-04-29 18:22:46', '2019-04-29 18:22:46', '安全', '0', 1);
INSERT INTO `sys_dict_item` VALUES (33, 11, '5', '文档', 'param_type', '文档', 0, '2019-04-29 18:22:56', '2019-04-29 18:22:56', '文档', '0', 1);
INSERT INTO `sys_dict_item` VALUES (34, 11, '6', '消息', 'param_type', '消息', 0, '2019-04-29 18:23:05', '2019-04-29 18:23:05', '消息', '0', 1);
INSERT INTO `sys_dict_item` VALUES (35, 11, '9', '其他', 'param_type', '其他', 0, '2019-04-29 18:23:16', '2019-04-29 18:23:16', '其他', '0', 1);
INSERT INTO `sys_dict_item` VALUES (36, 11, '0', '默认', 'param_type', '默认', 0, '2019-04-29 18:23:30', '2019-04-29 18:23:30', '默认', '0', 1);
INSERT INTO `sys_dict_item` VALUES (37, 12, '0', '正常', 'status_type', '状态正常', 0, '2019-05-15 16:31:34', '2019-05-16 22:30:46', '状态正常', '0', 1);
INSERT INTO `sys_dict_item` VALUES (38, 12, '9', '冻结', 'status_type', '状态冻结', 1, '2019-05-15 16:31:56', '2019-05-16 22:30:50', '状态冻结', '0', 1);
INSERT INTO `sys_dict_item` VALUES (39, 13, '1', '系统类', 'dict_type', '系统类字典', 0, '2019-05-16 14:20:40', '2019-05-16 14:20:40', '不能修改删除', '0', 1);
INSERT INTO `sys_dict_item` VALUES (40, 13, '0', '业务类', 'dict_type', '业务类字典', 0, '2019-05-16 14:20:59', '2019-05-16 14:20:59', '可以修改', '0', 1);
INSERT INTO `sys_dict_item` VALUES (41, 14, '0', '正常', 'channel_status', '支付渠道状态正常', 0, '2019-05-30 16:16:51', '2019-05-30 16:16:51', NULL, '0', 1);
INSERT INTO `sys_dict_item` VALUES (42, 14, '1', '冻结', 'channel_status', '支付渠道冻结', 0, '2019-05-30 16:17:08', '2019-05-30 16:17:08', NULL, '0', 1);
INSERT INTO `sys_dict_item` VALUES (43, 15, 'ALIPAY_WAP', '支付宝wap支付', 'channel_id', '支付宝扫码支付', 0, '2019-05-30 19:03:16', '2019-06-18 13:51:42', '支付宝wap支付', '0', 1);
INSERT INTO `sys_dict_item` VALUES (44, 15, 'WEIXIN_MP', '微信公众号支付', 'channel_id', '微信公众号支付', 1, '2019-05-30 19:08:08', '2019-06-18 13:51:53', '微信公众号支付', '0', 1);
INSERT INTO `sys_dict_item` VALUES (45, 16, '1', '支付成功', 'order_status', '支付成功', 1, '2019-06-27 08:18:26', '2019-06-27 08:18:26', '订单支付成功', '0', 1);
INSERT INTO `sys_dict_item` VALUES (46, 16, '2', '支付完成', 'order_status', '订单支付完成', 2, '2019-06-27 08:18:44', '2019-06-27 08:18:44', '订单支付完成', '0', 1);
INSERT INTO `sys_dict_item` VALUES (47, 16, '0', '待支付', 'order_status', '订单待支付', 0, '2019-06-27 08:19:02', '2019-06-27 08:19:02', '订单待支付', '0', 1);
INSERT INTO `sys_dict_item` VALUES (48, 16, '-1', '支付失败', 'order_status', '订单支付失败', 3, '2019-06-27 08:19:37', '2019-06-27 08:19:37', '订单支付失败', '0', 1);
INSERT INTO `sys_dict_item` VALUES (49, 2, 'GITEE', '码云', 'social_type', '码云', 2, '2019-06-28 09:59:12', '2019-06-28 09:59:12', '码云', '0', 1);
INSERT INTO `sys_dict_item` VALUES (50, 2, 'OSC', '开源中国', 'social_type', '开源中国登录', 0, '2019-06-28 10:04:32', '2019-06-28 10:04:32', 'http://gitee.huaxiadaowei.com/#/authredirect', '0', 1);
INSERT INTO `sys_dict_item` VALUES (51, 17, 'password', '密码模式', 'grant_types', '支持oauth密码模式', 0, '2019-08-13 07:35:28', '2019-08-13 07:35:28', NULL, '0', 1);
INSERT INTO `sys_dict_item` VALUES (52, 17, 'authorization_code', '授权码模式', 'grant_types', 'oauth2 授权码模式', 1, '2019-08-13 07:36:07', '2019-08-13 07:36:07', NULL, '0', 1);
INSERT INTO `sys_dict_item` VALUES (53, 17, 'client_credentials', '客户端模式', 'grant_types', 'oauth2 客户端模式', 2, '2019-08-13 07:36:30', '2019-08-13 07:36:30', NULL, '0', 1);
INSERT INTO `sys_dict_item` VALUES (54, 17, 'refresh_token', '刷新模式', 'grant_types', 'oauth2 刷新token', 3, '2019-08-13 07:36:54', '2019-08-13 07:36:54', NULL, '0', 1);
INSERT INTO `sys_dict_item` VALUES (55, 17, 'implicit', '简化模式', 'grant_types', 'oauth2 简化模式', 4, '2019-08-13 07:39:32', '2019-08-13 07:39:32', NULL, '0', 1);
INSERT INTO `sys_dict_item` VALUES (56, 18, '0', 'Avue', 'style_type', 'Avue风格', 0, '2020-02-07 03:52:52', '2020-02-07 03:52:52', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (57, 18, '1', 'element', 'style_type', 'element-ui', 1, '2020-02-07 03:53:12', '2020-02-07 03:53:12', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (58, 19, '0', '关', 'captcha_flag_types', '不校验验证码', 0, '2020-11-18 06:53:58', '2020-11-18 06:53:58', '不校验验证码 -0', '0', 1);
INSERT INTO `sys_dict_item` VALUES (59, 19, '1', '开', 'captcha_flag_types', '校验验证码', 1, '2020-11-18 06:54:15', '2020-11-18 06:54:15', '不校验验证码-1', '0', 1);
INSERT INTO `sys_dict_item` VALUES (60, 20, '0', '否', 'enc_flag_types', '不加密', 0, '2020-11-18 06:55:31', '2020-11-18 06:55:31', '不加密-0', '0', 1);
INSERT INTO `sys_dict_item` VALUES (61, 20, '1', '是', 'enc_flag_types', '加密', 1, '2020-11-18 06:55:51', '2020-11-18 06:55:51', '加密-1', '0', 1);
INSERT INTO `sys_dict_item` VALUES (1000, 140, '9', '异常', 'log_type', '日志异常', 1, '2019-03-19 11:08:59', '2019-03-25 12:49:13', '', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1001, 140, '0', '正常', 'log_type', '日志正常', 0, '2019-03-19 11:09:17', '2019-03-25 12:49:18', '', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1002, 141, 'WX', '微信', 'social_type', '微信登录', 0, '2019-03-19 11:10:02', '2019-03-25 12:49:36', '', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1003, 141, 'QQ', 'QQ', 'social_type', 'QQ登录', 1, '2019-03-19 11:10:14', '2019-03-25 12:49:36', '', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1004, 141, 'GITEE', '码云', 'social_type', '码云', 2, '2019-06-28 09:59:12', '2019-06-28 09:59:12', '码云', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1005, 141, 'OSC', '开源中国', 'social_type', '开源中国登录', 0, '2019-06-28 10:04:32', '2019-06-28 10:04:32', 'http://gitee.huaxiadaowei.com/#/authredirect', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1006, 142, '0', '未提交', 'leave_status', '未提交', 0, '2019-03-19 11:18:34', '2019-03-25 12:49:36', '', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1007, 142, '1', '审批中', 'leave_status', '审批中', 1, '2019-03-19 11:18:45', '2019-03-25 12:49:36', '', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1008, 142, '2', '完成', 'leave_status', '完成', 2, '2019-03-19 11:19:02', '2019-03-25 12:49:36', '', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1009, 142, '9', '驳回', 'leave_status', '驳回', 9, '2019-03-19 11:19:20', '2019-03-25 12:49:36', '', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1010, 143, '1', 'java类', 'job_type', 'java类', 1, '2019-03-19 11:22:37', '2019-03-25 12:49:36', '', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1011, 143, '2', 'spring bean', 'job_type', 'spring bean容器实例', 2, '2019-03-19 11:23:05', '2019-03-25 12:49:36', '', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1012, 143, '9', '其他', 'job_type', '其他类型', 9, '2019-03-19 11:23:31', '2019-03-25 12:49:36', '', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1013, 143, '3', 'Rest 调用', 'job_type', 'Rest 调用', 3, '2019-03-19 11:23:57', '2019-03-25 12:49:36', '', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1014, 143, '4', 'jar', 'job_type', 'jar类型', 4, '2019-03-19 11:24:20', '2019-03-25 12:49:36', '', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1015, 144, '1', '未发布', 'job_status', '未发布', 1, '2019-03-19 11:25:18', '2019-03-25 12:49:36', '', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1016, 144, '2', '运行中', 'job_status', '运行中', 2, '2019-03-19 11:25:31', '2019-03-25 12:49:36', '', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1017, 144, '3', '暂停', 'job_status', '暂停', 3, '2019-03-19 11:25:42', '2019-03-25 12:49:36', '', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1018, 145, '0', '正常', 'job_execute_status', '正常', 0, '2019-03-19 11:26:27', '2019-03-25 12:49:36', '', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1019, 145, '1', '异常', 'job_execute_status', '异常', 1, '2019-03-19 11:26:41', '2019-03-25 12:49:36', '', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1020, 146, '1', '错失周期立即执行', 'misfire_policy', '错失周期立即执行', 1, '2019-03-19 11:27:45', '2019-03-25 12:49:36', '', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1021, 146, '2', '错失周期执行一次', 'misfire_policy', '错失周期执行一次', 2, '2019-03-19 11:27:57', '2019-03-25 12:49:36', '', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1022, 146, '3', '下周期执行', 'misfire_policy', '下周期执行', 3, '2019-03-19 11:28:08', '2019-03-25 12:49:36', '', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1023, 147, '1', '男', 'gender', '微信-男', 0, '2019-03-27 13:45:13', '2019-03-27 13:45:13', '微信-男', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1024, 147, '2', '女', 'gender', '女-微信', 1, '2019-03-27 13:45:34', '2019-03-27 13:45:34', '女-微信', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1025, 147, '0', '未知', 'gender', 'x性别未知', 3, '2019-03-27 13:45:57', '2019-03-27 13:45:57', 'x性别未知', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1026, 148, '0', '未关注', 'subscribe', '公众号-未关注', 0, '2019-03-27 13:49:07', '2019-03-27 13:49:07', '公众号-未关注', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1027, 148, '1', '已关注', 'subscribe', '公众号-已关注', 1, '2019-03-27 13:49:26', '2019-03-27 13:49:26', '公众号-已关注', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1028, 149, '0', '未回复', 'response_type', '微信消息-未回复', 0, '2019-03-28 21:29:47', '2019-03-28 21:29:47', '微信消息-未回复', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1029, 149, '1', '已回复', 'response_type', '微信消息-已回复', 1, '2019-03-28 21:30:08', '2019-03-28 21:30:08', '微信消息-已回复', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1030, 150, '1', '检索', 'param_type', '检索', 0, '2019-04-29 18:22:17', '2019-04-29 18:22:17', '检索', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1031, 150, '2', '原文', 'param_type', '原文', 0, '2019-04-29 18:22:27', '2019-04-29 18:22:27', '原文', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1032, 150, '3', '报表', 'param_type', '报表', 0, '2019-04-29 18:22:36', '2019-04-29 18:22:36', '报表', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1033, 150, '4', '安全', 'param_type', '安全', 0, '2019-04-29 18:22:46', '2019-04-29 18:22:46', '安全', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1034, 150, '5', '文档', 'param_type', '文档', 0, '2019-04-29 18:22:56', '2019-04-29 18:22:56', '文档', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1035, 150, '6', '消息', 'param_type', '消息', 0, '2019-04-29 18:23:05', '2019-04-29 18:23:05', '消息', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1036, 150, '9', '其他', 'param_type', '其他', 0, '2019-04-29 18:23:16', '2019-04-29 18:23:16', '其他', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1037, 150, '0', '默认', 'param_type', '默认', 0, '2019-04-29 18:23:30', '2019-04-29 18:23:30', '默认', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1038, 151, '0', '正常', 'status_type', '状态正常', 0, '2019-05-15 16:31:34', '2019-05-16 22:30:46', '状态正常', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1039, 151, '9', '冻结', 'status_type', '状态冻结', 1, '2019-05-15 16:31:56', '2019-05-16 22:30:50', '状态冻结', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1040, 152, '1', '系统类', 'dict_type', '系统类字典', 0, '2019-05-16 14:20:40', '2019-05-16 14:20:40', '不能修改删除', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1041, 152, '0', '业务类', 'dict_type', '业务类字典', 0, '2019-05-16 14:20:59', '2019-05-16 14:20:59', '可以修改', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1042, 153, '0', '正常', 'channel_status', '支付渠道状态正常', 0, '2019-05-30 16:16:51', '2019-05-30 16:16:51', NULL, '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1043, 153, '1', '冻结', 'channel_status', '支付渠道冻结', 0, '2019-05-30 16:17:08', '2019-05-30 16:17:08', NULL, '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1044, 154, 'ALIPAY_WAP', '支付宝wap支付', 'channel_id', '支付宝扫码支付', 0, '2019-05-30 19:03:16', '2019-06-18 13:51:42', '支付宝wap支付', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1045, 154, 'WEIXIN_MP', '微信公众号支付', 'channel_id', '微信公众号支付', 1, '2019-05-30 19:08:08', '2019-06-18 13:51:53', '微信公众号支付', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1046, 155, '1', '支付成功', 'order_status', '支付成功', 1, '2019-06-27 08:18:26', '2019-06-27 08:18:26', '订单支付成功', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1047, 155, '2', '支付完成', 'order_status', '订单支付完成', 2, '2019-06-27 08:18:44', '2019-06-27 08:18:44', '订单支付完成', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1048, 155, '0', '待支付', 'order_status', '订单待支付', 0, '2019-06-27 08:19:02', '2019-06-27 08:19:02', '订单待支付', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1049, 155, '-1', '支付失败', 'order_status', '订单支付失败', 3, '2019-06-27 08:19:37', '2019-06-27 08:19:37', '订单支付失败', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1050, 156, 'password', '密码模式', 'grant_types', '支持oauth密码模式', 0, '2019-08-13 07:35:28', '2019-08-13 07:35:28', NULL, '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1051, 156, 'authorization_code', '授权码模式', 'grant_types', 'oauth2 授权码模式', 1, '2019-08-13 07:36:07', '2019-08-13 07:36:07', NULL, '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1052, 156, 'client_credentials', '客户端模式', 'grant_types', 'oauth2 客户端模式', 2, '2019-08-13 07:36:30', '2019-08-13 07:36:30', NULL, '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1053, 156, 'refresh_token', '刷新模式', 'grant_types', 'oauth2 刷新token', 3, '2019-08-13 07:36:54', '2019-08-13 07:36:54', NULL, '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1054, 156, 'implicit', '简化模式', 'grant_types', 'oauth2 简化模式', 4, '2019-08-13 07:39:32', '2019-08-13 07:39:32', NULL, '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1055, 157, '0', 'Avue', 'style_type', 'Avue风格', 0, '2020-02-07 03:52:52', '2020-02-07 03:52:52', '', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1056, 157, '1', 'element', 'style_type', 'element-ui', 1, '2020-02-07 03:53:12', '2020-02-07 03:53:12', '', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1057, 158, '0', '关', 'captcha_flag_types', '不校验验证码', 0, '2020-11-18 06:53:58', '2020-11-18 06:53:58', '不校验验证码 -0', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1058, 158, '1', '开', 'captcha_flag_types', '校验验证码', 1, '2020-11-18 06:54:15', '2020-11-18 06:54:15', '不校验验证码-1', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1059, 159, '0', '否', 'enc_flag_types', '不加密', 0, '2020-11-18 06:55:31', '2020-11-18 06:55:31', '不加密-0', '0', 1544598217314631684);
INSERT INTO `sys_dict_item` VALUES (1060, 159, '1', '是', 'enc_flag_types', '加密', 1, '2020-11-18 06:55:51', '2020-11-18 06:55:51', '加密-1', '0', 1544598217314631684);
COMMIT;

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `file_name` varchar(100) DEFAULT NULL,
  `bucket_name` varchar(200) DEFAULT NULL,
  `original` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `file_size` bigint DEFAULT NULL COMMENT '文件大小',
  `create_user` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  `update_user` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0',
  `tenant_id` int DEFAULT NULL COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文件管理表';

-- ----------------------------
-- Records of sys_file
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` char(1) DEFAULT '0',
  `title` varchar(255) DEFAULT NULL,
  `service_id` varchar(32) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remote_addr` varchar(255) DEFAULT NULL,
  `user_agent` varchar(1000) DEFAULT NULL,
  `request_uri` varchar(255) DEFAULT NULL,
  `method` varchar(10) DEFAULT NULL,
  `params` text,
  `time` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '执行时间',
  `del_flag` char(1) DEFAULT '0',
  `exception` text,
  `tenant_id` int DEFAULT '0' COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_log_create_by` (`create_by`) USING BTREE,
  KEY `sys_log_request_uri` (`request_uri`) USING BTREE,
  KEY `sys_log_type` (`type`) USING BTREE,
  KEY `sys_log_create_date` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_log` VALUES (1, '9', '登录失败', 'pig', 'admin', '2022-07-10 08:09:46', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', '/oauth/token', 'POST', 'randomStr=%5BblockPuzzle%5D&password=%5BrKu1/348LvKp0rsVC06eCA==%5D&code=%5BKeOzJQHIhrCBVaBH4sFyRovBVi7idYhFWMHKpAaqQpmJe0blh1ObbpvyjygxJVR1Mtynxacvx1z4oAP3BzpgxhDUpt4J0vk/WBU30QT6NUI=%5D&grant_type=%5Bpassword%5D&username=%5Badmin%5D', '0', '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (2, '0', '登录成功', 'pig', 'admin', '2022-07-10 08:10:01', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', '/oauth/token', 'POST', 'randomStr=%5BblockPuzzle%5D&password=%5B123456%5D&code=%5Bs/nyJzHcofi+uXA9M3SxwPXhT0TOBtYR7hvsK2X5Y4Ci9HMlKtEedmE/ub0rdjBv8bLQn3pMClwzOHYdNTvE2oct9xUeiHIIXSJ/LCaosbk=%5D&grant_type=%5Bpassword%5D&username=%5Badmin%5D', '0', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (3, '0', '登录成功', 'pig', 'admin', '2022-07-11 06:40:24', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', '/oauth/token', 'POST', 'randomStr=%5BblockPuzzle%5D&password=%5B123456%5D&code=%5BwIEJsXIifd+0EHab4/0bv0GZF95FuZ1FSKhU8VkUO2Iko75Ah9ddlqoqafXQ5/+IoR6pIRno9vq7Xu1pGtr5n4k4tWfXYD+3DJ471crE1Sc=%5D&grant_type=%5Bpassword%5D&username=%5Badmin%5D', '0', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (4, '0', '登录成功', 'pig', 'admin', '2022-07-22 07:44:30', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', '/oauth/token', 'POST', 'randomStr=%5BblockPuzzle%5D&password=%5B123456%5D&code=%5Bbaickh54Qo2NJqahOOCwCYJF9mlLRPEFWX7lVMlu1zTCAWaZRj9FUIs/qa7xOxCBEUJ1a2V/iBvQQloFeppIq8OAXmBInMEbS/MIFXQNtT4=%5D&grant_type=%5Bpassword%5D&username=%5Badmin%5D', '0', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (5, '0', '退出成功', 'pig', 'admin', '2022-07-22 08:00:03', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', '/token/logout', 'DELETE', 'Bearer b89567dc-8578-4c94-930b-5465ab24c7f2', '0', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (6, '0', '登录成功', 'pig', 'admin', '2022-07-22 08:00:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', '/oauth/token', 'POST', 'randomStr=%5BblockPuzzle%5D&password=%5B123456%5D&code=%5BPYkg3AXgpnHh3l7tct4dmAGQ6CyCTZ7yctnLSUACTEOO/iI7gLiXpXUA8E8tG5thViGB4u8xfy0UBuW2bt8Cpw==%5D&grant_type=%5Bpassword%5D&username=%5Badmin%5D', '0', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (7, '0', '退出成功', 'pig', 'admin', '2022-07-22 08:51:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 51098569-d150-4305-865a-586cce3ec999', '0', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (8, '0', '登录成功', 'pig', 'admin', '2022-07-22 08:51:43', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', '/oauth/token', 'POST', 'randomStr=%5BblockPuzzle%5D&password=%5B123456%5D&code=%5B5euj4ruyPyXX5d5EaAeASODreUiHQcf6m+7tfMMN+U/4qR4KaydMgnCaVn83V+M/BGvgQLxEfRuDumfuCaFtIeiI1eryzPOmhE1sd4Y4h0E=%5D&grant_type=%5Bpassword%5D&username=%5Badmin%5D', '0', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (9, '0', '登录成功', 'test', 'admin', '2022-07-22 09:06:18', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', '/oauth/token', 'POST', 'password=%5B123456%5D&grant_type=%5Bpassword%5D&scope=%5Bserver%5D&username=%5Badmin%5D', '0', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (10, '0', '登录成功', 'test', 'admin', '2022-07-22 09:07:35', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', '/oauth/token', 'POST', 'password=%5B123456%5D&grant_type=%5Bpassword%5D&scope=%5Bserver%5D&username=%5Badmin%5D', '0', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (11, '0', '登录成功', 'test', 'admin', '2022-07-22 09:29:03', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', '/oauth/token', 'POST', 'password=%5B123456%5D&grant_type=%5Bpassword%5D&scope=%5Bserver%5D&username=%5Badmin%5D', '0', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (12, '0', '登录成功', 'test', 'admin', '2022-07-22 09:29:29', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', '/oauth/token', 'POST', 'password=%5B123456%5D&grant_type=%5Bpassword%5D&scope=%5Bserver%5D&username=%5Badmin%5D', '0', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (13, '0', '登录成功', 'test', 'admin', '2022-07-22 09:40:37', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', '/oauth/token', 'POST', 'password=%5B123456%5D&grant_type=%5Bpassword%5D&scope=%5Bserver%5D&username=%5Badmin%5D', '0', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (14, '0', '登录成功', 'test', 'admin', '2022-07-22 10:28:41', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', '/oauth/token', 'POST', 'password=%5B123456%5D&grant_type=%5Bpassword%5D&scope=%5Bserver%5D&username=%5Badmin%5D', '0', '0', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `name` varchar(32) DEFAULT NULL,
  `permission` varchar(32) DEFAULT NULL,
  `path` varchar(128) DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL COMMENT '父菜单ID',
  `icon` varchar(32) DEFAULT NULL,
  `sort_order` int DEFAULT '1' COMMENT '排序值',
  `keep_alive` char(1) DEFAULT '0',
  `type` char(1) DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0',
  `tenant_id` bigint unsigned DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10512 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1000, '权限管理', NULL, '/user', -1, 'icon-quanxianguanli', 0, '0', '0', '2018-09-28 08:29:53', '2020-03-24 08:56:32', '0', 1);
INSERT INTO `sys_menu` VALUES (1100, '用户管理', NULL, '/admin/user/index', 1000, 'icon-yonghuguanli', 1, '0', '0', '2017-11-02 22:24:37', '2020-03-24 08:56:33', '0', 1);
INSERT INTO `sys_menu` VALUES (1101, '用户新增', 'sys_user_add', NULL, 1100, NULL, NULL, '0', '1', '2017-11-08 09:52:09', '2020-03-24 08:56:34', '0', 1);
INSERT INTO `sys_menu` VALUES (1102, '用户修改', 'sys_user_edit', NULL, 1100, NULL, NULL, '0', '1', '2017-11-08 09:52:48', '2020-03-24 08:56:35', '0', 1);
INSERT INTO `sys_menu` VALUES (1103, '用户删除', 'sys_user_del', NULL, 1100, NULL, NULL, '0', '1', '2017-11-08 09:54:01', '2020-03-24 08:56:37', '0', 1);
INSERT INTO `sys_menu` VALUES (1200, '菜单管理', NULL, '/admin/menu/index', 1000, 'icon-caidanguanli', 2, '0', '0', '2017-11-08 09:57:27', '2020-03-24 08:56:38', '0', 1);
INSERT INTO `sys_menu` VALUES (1201, '菜单新增', 'sys_menu_add', NULL, 1200, NULL, NULL, '0', '1', '2017-11-08 10:15:53', '2020-03-24 08:56:39', '0', 1);
INSERT INTO `sys_menu` VALUES (1202, '菜单修改', 'sys_menu_edit', NULL, 1200, NULL, NULL, '0', '1', '2017-11-08 10:16:23', '2020-03-24 08:56:40', '0', 1);
INSERT INTO `sys_menu` VALUES (1203, '菜单删除', 'sys_menu_del', NULL, 1200, NULL, NULL, '0', '1', '2017-11-08 10:16:43', '2020-03-24 08:56:41', '0', 1);
INSERT INTO `sys_menu` VALUES (1300, '角色管理', NULL, '/admin/role/index', 1000, 'icon-jiaoseguanli', 3, '0', '0', '2017-11-08 10:13:37', '2020-03-24 08:56:42', '0', 1);
INSERT INTO `sys_menu` VALUES (1301, '角色新增', 'sys_role_add', NULL, 1300, NULL, NULL, '0', '1', '2017-11-08 10:14:18', '2020-03-24 08:56:43', '0', 1);
INSERT INTO `sys_menu` VALUES (1302, '角色修改', 'sys_role_edit', NULL, 1300, NULL, NULL, '0', '1', '2017-11-08 10:14:41', '2020-03-24 08:56:43', '0', 1);
INSERT INTO `sys_menu` VALUES (1303, '角色删除', 'sys_role_del', NULL, 1300, NULL, NULL, '0', '1', '2017-11-08 10:14:59', '2020-03-24 08:56:45', '0', 1);
INSERT INTO `sys_menu` VALUES (1304, '分配权限', 'sys_role_perm', NULL, 1300, NULL, NULL, '0', '1', '2018-04-20 07:22:55', '2020-03-24 08:56:46', '0', 1);
INSERT INTO `sys_menu` VALUES (1400, '部门管理', NULL, '/admin/dept/index', 1000, 'icon-web-icon-', 4, '0', '0', '2018-01-20 13:17:19', '2020-03-24 08:56:47', '0', 1);
INSERT INTO `sys_menu` VALUES (1401, '部门新增', 'sys_dept_add', NULL, 1400, NULL, NULL, '0', '1', '2018-01-20 14:56:16', '2020-03-24 08:56:48', '0', 1);
INSERT INTO `sys_menu` VALUES (1402, '部门修改', 'sys_dept_edit', NULL, 1400, NULL, NULL, '0', '1', '2018-01-20 14:56:59', '2020-03-24 08:56:48', '0', 1);
INSERT INTO `sys_menu` VALUES (1403, '部门删除', 'sys_dept_del', NULL, 1400, NULL, NULL, '0', '1', '2018-01-20 14:57:28', '2020-03-24 08:56:51', '0', 1);
INSERT INTO `sys_menu` VALUES (1500, '租户管理', '', '/admin/tenant/index', 1000, 'icon-erji-zuhushouye', 5, '0', '0', '2018-01-20 13:17:19', '2020-03-24 08:56:49', '0', 1);
INSERT INTO `sys_menu` VALUES (1501, '租户新增', 'admin_systenant_add', NULL, 1500, '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:56:52', '0', 1);
INSERT INTO `sys_menu` VALUES (1502, '租户修改', 'admin_systenant_edit', NULL, 1500, '1', 1, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:56:53', '0', 1);
INSERT INTO `sys_menu` VALUES (1503, '租户删除', 'admin_systenant_del', NULL, 1500, '1', 2, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:56:54', '0', 1);
INSERT INTO `sys_menu` VALUES (2000, '系统管理', NULL, '/admin', -1, 'icon-xitongpeizhi', 1, '0', '0', '2017-11-07 20:56:00', '2020-03-24 08:56:55', '0', 1);
INSERT INTO `sys_menu` VALUES (2100, '日志管理', NULL, '/admin/log/index', 2000, 'icon-rizhi', 5, '0', '0', '2017-11-20 14:06:22', '2020-03-24 08:56:56', '0', 1);
INSERT INTO `sys_menu` VALUES (2101, '日志删除', 'sys_log_del', NULL, 2100, NULL, NULL, '0', '1', '2017-11-20 20:37:37', '2020-03-24 08:56:58', '0', 1);
INSERT INTO `sys_menu` VALUES (2200, '字典管理', NULL, '/admin/dict/index', 2000, 'icon-navicon-zdgl', 6, '0', '0', '2017-11-29 11:30:52', '2020-03-24 08:56:58', '0', 1);
INSERT INTO `sys_menu` VALUES (2201, '字典删除', 'sys_dict_del', NULL, 2200, NULL, NULL, '0', '1', '2017-11-29 11:30:11', '2020-03-24 08:56:59', '0', 1);
INSERT INTO `sys_menu` VALUES (2202, '字典新增', 'sys_dict_add', NULL, 2200, NULL, NULL, '0', '1', '2018-05-11 22:34:55', '2020-03-24 08:57:01', '0', 1);
INSERT INTO `sys_menu` VALUES (2203, '字典修改', 'sys_dict_edit', NULL, 2200, NULL, NULL, '0', '1', '2018-05-11 22:36:03', '2020-03-24 08:57:09', '0', 1);
INSERT INTO `sys_menu` VALUES (2210, '参数管理', NULL, '/admin/param/index', 2000, 'icon-canshu', 7, '1', '0', '2019-04-29 22:16:50', '2020-03-24 08:57:10', '0', 1);
INSERT INTO `sys_menu` VALUES (2211, '参数新增', 'admin_syspublicparam_add', NULL, 2210, NULL, 1, '0', '1', '2019-04-29 22:17:36', '2020-03-24 08:57:11', '0', 1);
INSERT INTO `sys_menu` VALUES (2212, '参数删除', 'admin_syspublicparam_del', NULL, 2210, NULL, 1, '0', '1', '2019-04-29 22:17:55', '2020-03-24 08:57:12', '0', 1);
INSERT INTO `sys_menu` VALUES (2213, '参数编辑', 'admin_syspublicparam_edit', NULL, 2210, NULL, 1, '0', '1', '2019-04-29 22:18:14', '2020-03-24 08:57:13', '0', 1);
INSERT INTO `sys_menu` VALUES (2300, '代码生成', '', '/gen/index', 9000, 'icon-weibiaoti46', 1, '0', '0', '2018-01-20 13:17:19', '2020-03-24 08:57:14', '0', 1);
INSERT INTO `sys_menu` VALUES (2400, '终端管理', '', '/admin/client/index', 2000, 'icon-bangzhushouji', 9, '1', '0', '2018-01-20 13:17:19', '2020-03-24 08:57:15', '0', 1);
INSERT INTO `sys_menu` VALUES (2401, '客户端新增', 'sys_client_add', NULL, 2400, '1', NULL, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:16', '0', 1);
INSERT INTO `sys_menu` VALUES (2402, '客户端修改', 'sys_client_edit', NULL, 2400, NULL, NULL, '0', '1', '2018-05-15 21:37:06', '2020-03-24 08:57:16', '0', 1);
INSERT INTO `sys_menu` VALUES (2403, '客户端删除', 'sys_client_del', NULL, 2400, NULL, NULL, '0', '1', '2018-05-15 21:39:16', '2020-03-24 08:57:17', '0', 1);
INSERT INTO `sys_menu` VALUES (2500, '密钥管理', '', '/admin/social/index', 2000, 'icon-miyue', 10, '0', '0', '2018-01-20 13:17:19', '2020-03-24 08:57:18', '0', 1);
INSERT INTO `sys_menu` VALUES (2501, '密钥新增', 'sys_social_details_add', NULL, 2500, '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:19', '0', 1);
INSERT INTO `sys_menu` VALUES (2502, '密钥修改', 'sys_social_details_edit', NULL, 2500, '1', 1, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:19', '0', 1);
INSERT INTO `sys_menu` VALUES (2503, '密钥删除', 'sys_social_details_del', NULL, 2500, '1', 2, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:23', '0', 1);
INSERT INTO `sys_menu` VALUES (2600, '令牌管理', NULL, '/admin/token/index', 2000, 'icon-denglvlingpai', 11, '0', '0', '2018-09-04 05:58:41', '2020-03-24 08:57:24', '0', 1);
INSERT INTO `sys_menu` VALUES (2601, '令牌删除', 'sys_token_del', NULL, 2600, NULL, 1, '0', '1', '2018-09-04 05:59:50', '2020-03-24 08:57:24', '0', 1);
INSERT INTO `sys_menu` VALUES (2700, '动态路由', NULL, '/admin/route/index', 2000, 'icon-luyou', 12, '0', '0', '2018-09-04 05:58:41', '2020-03-24 08:57:25', '0', 1);
INSERT INTO `sys_menu` VALUES (2800, 'Quartz管理', '', '/daemon/job-manage/index', 2000, 'icon-guanwangfangwen', 8, '0', '0', '2018-01-20 13:17:19', '2020-03-24 08:57:26', '0', 1);
INSERT INTO `sys_menu` VALUES (2810, '任务新增', 'job_sys_job_add', NULL, 2800, '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:26', '0', 1);
INSERT INTO `sys_menu` VALUES (2820, '任务修改', 'job_sys_job_edit', NULL, 2800, '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:27', '0', 1);
INSERT INTO `sys_menu` VALUES (2830, '任务删除', 'job_sys_job_del', NULL, 2800, '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:28', '0', 1);
INSERT INTO `sys_menu` VALUES (2840, '任务暂停', 'job_sys_job_shutdown_job', NULL, 2800, '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:28', '0', 1);
INSERT INTO `sys_menu` VALUES (2850, '任务开始', 'job_sys_job_start_job', NULL, 2800, '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:29', '0', 1);
INSERT INTO `sys_menu` VALUES (2860, '任务刷新', 'job_sys_job_refresh_job', NULL, 2800, '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:30', '0', 1);
INSERT INTO `sys_menu` VALUES (2870, '执行任务', 'job_sys_job_run_job', NULL, 2800, '1', 0, '0', '1', '2019-08-08 15:35:18', '2020-03-24 08:57:31', '0', 1);
INSERT INTO `sys_menu` VALUES (3000, '系统监控', NULL, '/daemon', -1, 'icon-msnui-supervise', 2, '0', '0', '2018-07-27 01:13:21', '2020-03-24 08:57:31', '0', 1);
INSERT INTO `sys_menu` VALUES (3100, '服务监控', NULL, 'http://127.0.0.1:5001', 3000, 'icon-server', 0, '0', '0', '2018-06-26 10:50:32', '2020-03-24 08:57:32', '0', 1);
INSERT INTO `sys_menu` VALUES (3101, '流量监控', NULL, 'http://127.0.0.1:5020', 3000, 'icon-liuliang', 0, '0', '0', '2018-06-26 10:50:32', '2020-03-24 08:57:32', '0', 1);
INSERT INTO `sys_menu` VALUES (3110, '缓存监控', NULL, '/monitor/redis/index', 3000, 'icon-qingchuhuancun', 1, '1', '0', '2019-05-08 23:51:27', '2020-03-24 08:57:33', '0', 1);
INSERT INTO `sys_menu` VALUES (3200, '接口文档', NULL, 'http://127.0.0.1:9999/swagger-ui/index.html', 3000, 'icon-wendang', 1, '0', '0', '2018-06-26 10:50:32', '2020-03-24 08:57:34', '0', 1);
INSERT INTO `sys_menu` VALUES (3300, '事务监控', NULL, '/tx/index', 3000, 'icon-gtsquanjushiwufuwuGTS', 5, '0', '0', '2018-08-19 11:02:39', '2020-03-24 08:57:34', '0', 1);
INSERT INTO `sys_menu` VALUES (3400, '在线事务', NULL, '/tx/model', 3000, 'icon-online', 6, '0', '0', '2018-08-19 11:32:04', '2020-03-24 08:57:35', '0', 1);
INSERT INTO `sys_menu` VALUES (3500, '文档扩展', NULL, 'http://127.0.0.1:9999/doc.html', 3000, 'icon-wendang', 2, '0', '0', '2018-06-26 10:50:32', '2020-03-24 08:57:36', '0', 1);
INSERT INTO `sys_menu` VALUES (3600, 'Quartz日志', '', '/daemon/job-log/index', 3000, 'icon-gtsquanjushiwufuwuGTS', 8, '0', '0', '2018-01-20 13:17:19', '2020-03-24 08:57:37', '0', 1);
INSERT INTO `sys_menu` VALUES (3700, '注册配置', NULL, '', 3000, 'icon-line', 10, '0', '0', '2018-01-25 11:08:52', '2020-03-24 08:57:37', '1', 1);
INSERT INTO `sys_menu` VALUES (4000, '协同管理', NULL, '/activti', -1, 'icon-kuaisugongzuoliu_o', 3, '0', '0', '2018-09-26 01:38:13', '2020-03-24 08:57:39', '0', 1);
INSERT INTO `sys_menu` VALUES (4100, '模型管理', NULL, '/activiti/index', 4000, 'icon-weibiaoti13', 1, '0', '0', '2018-09-26 01:39:07', '2020-03-24 08:57:40', '0', 1);
INSERT INTO `sys_menu` VALUES (4101, '模型管理', 'act_model_manage', NULL, 4100, '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:41', '0', 1);
INSERT INTO `sys_menu` VALUES (4200, '流程管理', '/activiti/process', '/activiti/process', 4000, 'icon-liucheng', 2, '0', '0', '2018-09-26 06:41:05', '2020-03-24 08:57:42', '0', 1);
INSERT INTO `sys_menu` VALUES (4201, '流程管理', 'act_process_manage', NULL, 4200, '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:42', '0', 1);
INSERT INTO `sys_menu` VALUES (4300, '请假管理', '/activiti/leave', '/activiti/leave', 4000, 'icon-qingjia', 3, '0', '0', '2018-01-20 13:17:19', '2020-03-24 08:57:43', '0', 1);
INSERT INTO `sys_menu` VALUES (4301, '请假新增', 'act_leavebill_add', NULL, 4300, '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:44', '0', 1);
INSERT INTO `sys_menu` VALUES (4302, '请假修改', 'act_leavebill_edit', NULL, 4300, '1', 1, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:45', '0', 1);
INSERT INTO `sys_menu` VALUES (4303, '请假删除', 'act_leavebill_del', NULL, 4300, '1', 2, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:46', '0', 1);
INSERT INTO `sys_menu` VALUES (4400, '待办任务', '/activiti/task', '/activiti/task', 4000, 'icon-renwu', 4, '0', '0', '2018-09-27 09:52:31', '2020-03-24 08:57:48', '0', 1);
INSERT INTO `sys_menu` VALUES (4401, '流程管理', 'act_task_manage', NULL, 4400, '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:57:50', '0', 1);
INSERT INTO `sys_menu` VALUES (5000, '支付管理', NULL, '/pay', -1, 'icon-pay6zhifu', 4, '0', '0', '2019-05-30 15:28:03', '2020-03-24 08:57:51', '0', 1);
INSERT INTO `sys_menu` VALUES (5100, '渠道管理', NULL, '/pay/paychannel/index', 5000, 'icon-zhifuqudaoguanli', 1, '0', '0', '2019-05-30 15:32:17', '2020-03-24 08:57:52', '0', 1);
INSERT INTO `sys_menu` VALUES (5110, '增加渠道', 'pay_paychannel_add', NULL, 5100, NULL, 1, '0', '1', '2019-05-30 15:46:14', '2020-03-24 08:58:07', '0', 1);
INSERT INTO `sys_menu` VALUES (5120, '编辑渠道', 'pay_paychannel_edit', NULL, 5100, NULL, 1, '0', '1', '2019-05-30 15:46:35', '2020-03-24 08:58:08', '0', 1);
INSERT INTO `sys_menu` VALUES (5130, '删除渠道', 'pay_paychannel_del', NULL, 5100, NULL, 1, '0', '1', '2019-05-30 15:47:08', '2020-03-24 08:58:09', '0', 1);
INSERT INTO `sys_menu` VALUES (5200, '收银台', NULL, '/pay/cd/index', 5000, 'icon-shouyintai', 0, '0', '0', '2019-05-30 19:44:00', '2020-03-24 08:58:09', '0', 1);
INSERT INTO `sys_menu` VALUES (5300, '商品订单', '', '/pay/goods/index', 5000, 'icon-dingdan', 2, '0', '0', '2018-01-20 13:17:19', '2020-03-24 08:58:10', '0', 1);
INSERT INTO `sys_menu` VALUES (5310, '商品订单新增', 'generator_paygoodsorder_add', NULL, 5300, '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:11', '0', 1);
INSERT INTO `sys_menu` VALUES (5320, '商品订单修改', 'generator_paygoodsorder_edit', NULL, 5300, '1', 1, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:13', '0', 1);
INSERT INTO `sys_menu` VALUES (5330, '商品订单删除', 'generator_paygoodsorder_del', NULL, 5300, '1', 2, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:14', '0', 1);
INSERT INTO `sys_menu` VALUES (5400, '支付订单', '', '/pay/orders/index', 5000, 'icon-zhifu', 3, '0', '0', '2018-01-20 13:17:19', '2020-03-24 08:58:14', '0', 1);
INSERT INTO `sys_menu` VALUES (5410, '支付订单新增', 'generator_paytradeorder_add', NULL, 5400, '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:15', '0', 1);
INSERT INTO `sys_menu` VALUES (5420, '支付订单修改', 'generator_paytradeorder_edit', NULL, 5400, '1', 1, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:16', '0', 1);
INSERT INTO `sys_menu` VALUES (5430, '支付订单删除', 'generator_paytradeorder_del', NULL, 5400, '1', 2, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:18', '0', 1);
INSERT INTO `sys_menu` VALUES (5500, '回调记录', '', '/pay/notify/index', 5000, 'icon-huitiao', 4, '0', '0', '2018-01-20 13:17:19', '2020-03-24 08:58:19', '0', 1);
INSERT INTO `sys_menu` VALUES (5510, '记录新增', 'generator_paynotifyrecord_add', NULL, 5500, '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:19', '0', 1);
INSERT INTO `sys_menu` VALUES (5520, '记录修改', 'generator_paynotifyrecord_edit', NULL, 5500, '1', 1, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:20', '0', 1);
INSERT INTO `sys_menu` VALUES (5530, '记录删除', 'generator_paynotifyrecord_del', NULL, 5500, '1', 2, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:21', '0', 1);
INSERT INTO `sys_menu` VALUES (6000, '微信管理', NULL, '/mp', -1, 'icon-gongzhonghao', 4, '0', '0', '2018-09-26 01:38:13', '2020-03-24 08:58:21', '0', 1);
INSERT INTO `sys_menu` VALUES (6100, '账号管理', '', '/mp/wxaccount/index', 6000, 'icon-weixincaidan', 8, '0', '0', '2018-01-20 13:17:19', '2020-03-24 08:58:22', '0', 1);
INSERT INTO `sys_menu` VALUES (6101, '公众号新增', 'mp_wxaccount_add', '', 6100, '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:23', '0', 1);
INSERT INTO `sys_menu` VALUES (6102, '公众号修改', 'mp_wxaccount_edit', NULL, 6100, '1', 1, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:23', '0', 1);
INSERT INTO `sys_menu` VALUES (6103, '公众号删除', 'mp_wxaccount_del', NULL, 6100, '1', 2, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:25', '0', 1);
INSERT INTO `sys_menu` VALUES (6200, '粉丝管理', '', '/mp/wxaccountfans/index', 6000, 'icon-fensiguanli', 8, '0', '0', '2018-01-20 13:17:19', '2020-03-24 08:58:26', '0', 1);
INSERT INTO `sys_menu` VALUES (6201, '粉丝新增', 'mp_wxaccountfans_add', NULL, 6200, '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:27', '0', 1);
INSERT INTO `sys_menu` VALUES (6202, '粉丝修改', 'mp_wxaccountfans_edit', NULL, 6200, '1', 1, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:30', '0', 1);
INSERT INTO `sys_menu` VALUES (6203, '粉丝删除', 'mp_wxaccountfans_del', NULL, 6200, '1', 2, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:31', '0', 1);
INSERT INTO `sys_menu` VALUES (6204, '粉丝同步', 'mp_wxaccountfans_sync', NULL, 6200, '1', 3, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:31', '0', 1);
INSERT INTO `sys_menu` VALUES (6300, '消息管理', '', '/mp/wxfansmsg/index', 6000, 'icon-xiaoxiguanli', 8, '0', '0', '2018-01-20 13:17:19', '2020-03-24 08:58:31', '0', 1);
INSERT INTO `sys_menu` VALUES (6301, '消息新增', 'mp_wxmsg_add', NULL, 6300, '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:32', '0', 1);
INSERT INTO `sys_menu` VALUES (6302, '消息修改', 'mp_wxmsg_edit', NULL, 6300, '1', 1, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:33', '0', 1);
INSERT INTO `sys_menu` VALUES (6303, '消息删除', 'mp_wxmsg_del', NULL, 6300, '1', 2, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:33', '0', 1);
INSERT INTO `sys_menu` VALUES (6400, '菜单设置', NULL, '/mp/wxmenu/index', 6000, 'icon-anniu_weixincaidanlianjie', 6, '0', '0', '2019-03-29 15:20:12', '2020-03-24 08:58:38', '0', 1);
INSERT INTO `sys_menu` VALUES (6401, '保存', 'mp_wxmenu_add', NULL, 6400, NULL, 1, '0', '1', '2019-03-29 15:43:22', '2020-03-24 08:58:38', '0', 1);
INSERT INTO `sys_menu` VALUES (6402, '发布', 'mp_wxmenu_push', NULL, 6400, NULL, 1, '0', '1', '2019-03-29 15:43:54', '2020-03-24 08:58:39', '0', 1);
INSERT INTO `sys_menu` VALUES (6403, '删除', 'mp_wxmenu_del', NULL, 6400, NULL, 1, '0', '1', '2019-03-29 15:43:54', '2020-03-24 08:58:39', '0', 1);
INSERT INTO `sys_menu` VALUES (6500, '运营数据', NULL, '/mp/wxstatistics/index', 6000, 'icon-zhexiantu', 7, '0', '0', '2019-04-14 00:15:35', '2020-03-24 08:58:40', '0', 1);
INSERT INTO `sys_menu` VALUES (6600, '素材管理', NULL, '/mp/wxmaterial/index', 6000, 'icon-sucaisads', 999, '0', '0', '2020-04-27 15:25:17', '2020-05-09 03:16:13', '0', 1);
INSERT INTO `sys_menu` VALUES (6601, '素材维护', 'mp_wxmaterial_add', NULL, 6600, NULL, 1, '0', '1', '2019-03-29 15:43:54', '2020-03-24 08:58:39', '0', 1);
INSERT INTO `sys_menu` VALUES (6602, '素材删除', 'mp_wxmaterial_del', NULL, 6600, NULL, 1, '0', '1', '2019-03-29 15:43:54', '2020-03-24 08:58:39', '0', 1);
INSERT INTO `sys_menu` VALUES (6700, '自动回复', NULL, '/mp/wxautoreply/index', 6000, 'icon-huifu', 998, '0', '0', '2020-04-27 15:25:17', '2020-05-09 03:16:16', '0', 1);
INSERT INTO `sys_menu` VALUES (6701, '新增回复', 'mp_wxautoreply_add', NULL, 6700, NULL, 1, '0', '1', '2019-03-29 15:43:54', '2020-03-24 08:58:39', '0', 1);
INSERT INTO `sys_menu` VALUES (6702, '编辑回复', 'mp_wxautoreply_edit', NULL, 6700, NULL, 1, '0', '1', '2019-03-29 15:43:54', '2020-03-24 08:58:39', '0', 1);
INSERT INTO `sys_menu` VALUES (6703, '删除回复', 'mp_wxautoreply_del', NULL, 6700, NULL, 1, '0', '1', '2019-03-29 15:43:54', '2020-03-24 08:58:39', '0', 1);
INSERT INTO `sys_menu` VALUES (7000, '报表设计', NULL, 'http://127.0.0.1:5006/ureport/designer', -1, 'icon-icon-p_mrpbaobiao', 9, '0', '0', '2019-08-12 09:35:16', '2020-03-24 08:58:48', '0', 1);
INSERT INTO `sys_menu` VALUES (8000, '文件管理', NULL, '/admin/file/index', 2000, 'icon-wenjianguanli', 6, '0', '0', '2019-06-25 12:44:46', '2020-03-24 08:58:41', '0', 1);
INSERT INTO `sys_menu` VALUES (8001, '删除文件', 'sys_file_del', NULL, 8000, NULL, 1, '0', '1', '2019-06-25 13:41:41', '2020-03-24 08:58:42', '0', 1);
INSERT INTO `sys_menu` VALUES (9000, '开发平台', NULL, '/gen', -1, 'icon-shejiyukaifa-', 9, '0', '0', '2019-08-12 09:35:16', '2020-03-24 08:58:48', '0', 1);
INSERT INTO `sys_menu` VALUES (9001, '表单管理', '', '/gen/form', 9000, 'icon-record', 3, '0', '0', '2018-01-20 13:17:19', '2020-03-24 08:58:44', '0', 1);
INSERT INTO `sys_menu` VALUES (9002, '表单新增', 'gen_form_add', NULL, 9001, '1', 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:45', '0', 1);
INSERT INTO `sys_menu` VALUES (9003, '表单修改', 'gen_form_edit', NULL, 9001, '1', 1, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:46', '0', 1);
INSERT INTO `sys_menu` VALUES (9004, '表单删除', 'gen_form_del', NULL, 9001, '1', 2, '0', '1', '2018-05-15 21:35:18', '2020-03-24 08:58:47', '0', 1);
INSERT INTO `sys_menu` VALUES (9005, '数据源管理', NULL, '/gen/datasource', 9000, 'icon-mysql', 0, '0', '0', '2019-08-12 09:42:11', '2020-03-24 08:58:49', '0', 1);
INSERT INTO `sys_menu` VALUES (9006, '表单设计', NULL, '/gen/design', 9000, 'icon-biaodanbiaoqian', 2, '0', '0', '2019-08-16 10:08:56', '2020-03-24 08:58:53', '0', 1);
INSERT INTO `sys_menu` VALUES (10385, '权限管理', NULL, '/user', -1, 'icon-quanxianguanli', 0, '0', '0', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10386, '用户管理', NULL, '/admin/user/index', 10385, 'icon-yonghuguanli', 1, '0', '0', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10387, '用户新增', 'sys_user_add', NULL, 10386, NULL, 1, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10388, '用户修改', 'sys_user_edit', NULL, 10386, NULL, 1, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10389, '用户删除', 'sys_user_del', NULL, 10386, NULL, 1, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10390, '菜单管理', NULL, '/admin/menu/index', 10385, 'icon-caidanguanli', 2, '0', '0', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10391, '菜单新增', 'sys_menu_add', NULL, 10390, NULL, 1, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10392, '菜单修改', 'sys_menu_edit', NULL, 10390, NULL, 1, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10393, '菜单删除', 'sys_menu_del', NULL, 10390, NULL, 1, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10394, '角色管理', NULL, '/admin/role/index', 10385, 'icon-jiaoseguanli', 3, '0', '0', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10395, '角色新增', 'sys_role_add', NULL, 10394, NULL, 1, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10396, '角色修改', 'sys_role_edit', NULL, 10394, NULL, 1, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10397, '角色删除', 'sys_role_del', NULL, 10394, NULL, 1, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10398, '分配权限', 'sys_role_perm', NULL, 10394, NULL, 1, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10399, '部门管理', NULL, '/admin/dept/index', 10385, 'icon-web-icon-', 4, '0', '0', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10400, '部门新增', 'sys_dept_add', NULL, 10399, NULL, 1, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10401, '部门修改', 'sys_dept_edit', NULL, 10399, NULL, 1, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10402, '部门删除', 'sys_dept_del', NULL, 10399, NULL, 1, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10403, '租户管理', '', '/admin/tenant/index', 10385, 'icon-erji-zuhushouye', 5, '0', '0', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10404, '租户新增', 'admin_systenant_add', NULL, 10403, '1', 0, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10405, '租户修改', 'admin_systenant_edit', NULL, 10403, '1', 1, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10406, '租户删除', 'admin_systenant_del', NULL, 10403, '1', 2, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10407, '系统管理', NULL, '/admin', -1, 'icon-xitongpeizhi', 1, '0', '0', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10408, '日志管理', NULL, '/admin/log/index', 10407, 'icon-rizhi', 5, '0', '0', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10409, '日志删除', 'sys_log_del', NULL, 10408, NULL, 1, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10410, '文件管理', NULL, '/admin/file/index', 10407, 'icon-wenjianguanli', 6, '0', '0', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10411, '删除文件', 'sys_file_del', NULL, 10410, NULL, 1, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10412, '字典管理', NULL, '/admin/dict/index', 10407, 'icon-navicon-zdgl', 6, '0', '0', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10413, '字典删除', 'sys_dict_del', NULL, 10412, NULL, 1, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10414, '字典新增', 'sys_dict_add', NULL, 10412, NULL, 1, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10415, '字典修改', 'sys_dict_edit', NULL, 10412, NULL, 1, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10416, '参数管理', NULL, '/admin/param/index', 10407, 'icon-canshu', 7, '1', '0', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10417, '参数新增', 'admin_syspublicparam_add', NULL, 10416, NULL, 1, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10418, '参数删除', 'admin_syspublicparam_del', NULL, 10416, NULL, 1, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10419, '参数编辑', 'admin_syspublicparam_edit', NULL, 10416, NULL, 1, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10420, 'Quartz管理', '', '/daemon/job-manage/index', 10407, 'icon-guanwangfangwen', 8, '0', '0', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10421, '任务修改', 'job_sys_job_edit', NULL, 10420, '1', 0, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10422, '任务删除', 'job_sys_job_del', NULL, 10420, '1', 0, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10423, '任务暂停', 'job_sys_job_shutdown_job', NULL, 10420, '1', 0, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10424, '任务开始', 'job_sys_job_start_job', NULL, 10420, '1', 0, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10425, '任务刷新', 'job_sys_job_refresh_job', NULL, 10420, '1', 0, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10426, '执行任务', 'job_sys_job_run_job', NULL, 10420, '1', 0, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10427, '任务新增', 'job_sys_job_add', NULL, 10420, '1', 0, '0', '1', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10428, '终端管理', '', '/admin/client/index', 10407, 'icon-bangzhushouji', 9, '1', '0', '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10429, '客户端新增', 'sys_client_add', NULL, 10428, '1', 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10430, '客户端修改', 'sys_client_edit', NULL, 10428, NULL, 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10431, '客户端删除', 'sys_client_del', NULL, 10428, NULL, 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10432, '密钥管理', '', '/admin/social/index', 10407, 'icon-miyue', 10, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10433, '密钥新增', 'sys_social_details_add', NULL, 10432, '1', 0, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10434, '密钥修改', 'sys_social_details_edit', NULL, 10432, '1', 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10435, '密钥删除', 'sys_social_details_del', NULL, 10432, '1', 2, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10436, '令牌管理', NULL, '/admin/token/index', 10407, 'icon-denglvlingpai', 11, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10437, '令牌删除', 'sys_token_del', NULL, 10436, NULL, 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10438, '动态路由', NULL, '/admin/route/index', 10407, 'icon-luyou', 12, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10439, '系统监控', NULL, '/daemon', -1, 'icon-msnui-supervise', 2, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10440, '服务监控', NULL, 'http://127.0.0.1:5001', 10439, 'icon-server', 0, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10441, '流量监控', NULL, 'http://127.0.0.1:5020', 10439, 'icon-liuliang', 0, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10442, '缓存监控', NULL, '/monitor/redis/index', 10439, 'icon-qingchuhuancun', 1, '1', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10443, '接口文档', NULL, 'http://127.0.0.1:9999/swagger-ui/index.html', 10439, 'icon-wendang', 1, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10444, '文档扩展', NULL, 'http://127.0.0.1:9999/doc.html', 10439, 'icon-wendang', 2, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10445, '事务监控', NULL, '/tx/index', 10439, 'icon-gtsquanjushiwufuwuGTS', 5, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10446, '在线事务', NULL, '/tx/model', 10439, 'icon-online', 6, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10447, 'Quartz日志', '', '/daemon/job-log/index', 10439, 'icon-gtsquanjushiwufuwuGTS', 8, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10448, '协同管理', NULL, '/activti', -1, 'icon-kuaisugongzuoliu_o', 3, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10449, '模型管理', NULL, '/activiti/index', 10448, 'icon-weibiaoti13', 1, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10450, '模型管理', 'act_model_manage', NULL, 10449, '1', 0, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10451, '流程管理', '/activiti/process', '/activiti/process', 10448, 'icon-liucheng', 2, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10452, '流程管理', 'act_process_manage', NULL, 10451, '1', 0, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10453, '请假管理', '/activiti/leave', '/activiti/leave', 10448, 'icon-qingjia', 3, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10454, '请假新增', 'act_leavebill_add', NULL, 10453, '1', 0, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10455, '请假修改', 'act_leavebill_edit', NULL, 10453, '1', 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10456, '请假删除', 'act_leavebill_del', NULL, 10453, '1', 2, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10457, '待办任务', '/activiti/task', '/activiti/task', 10448, 'icon-renwu', 4, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10458, '流程管理', 'act_task_manage', NULL, 10457, '1', 0, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10459, '微信管理', NULL, '/mp', -1, 'icon-gongzhonghao', 4, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10460, '菜单设置', NULL, '/mp/wxmenu/index', 10459, 'icon-anniu_weixincaidanlianjie', 6, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10461, '保存', 'mp_wxmenu_add', NULL, 10460, NULL, 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10462, '发布', 'mp_wxmenu_push', NULL, 10460, NULL, 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10463, '删除', 'mp_wxmenu_del', NULL, 10460, NULL, 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10464, '运营数据', NULL, '/mp/wxstatistics/index', 10459, 'icon-zhexiantu', 7, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10465, '粉丝管理', '', '/mp/wxaccountfans/index', 10459, 'icon-fensiguanli', 8, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10466, '粉丝新增', 'mp_wxaccountfans_add', NULL, 10465, '1', 0, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10467, '粉丝修改', 'mp_wxaccountfans_edit', NULL, 10465, '1', 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10468, '粉丝删除', 'mp_wxaccountfans_del', NULL, 10465, '1', 2, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10469, '粉丝同步', 'mp_wxaccountfans_sync', NULL, 10465, '1', 3, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10470, '消息管理', '', '/mp/wxfansmsg/index', 10459, 'icon-xiaoxiguanli', 8, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10471, '消息新增', 'mp_wxmsg_add', NULL, 10470, '1', 0, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10472, '消息修改', 'mp_wxmsg_edit', NULL, 10470, '1', 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10473, '消息删除', 'mp_wxmsg_del', NULL, 10470, '1', 2, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10474, '账号管理', '', '/mp/wxaccount/index', 10459, 'icon-weixincaidan', 8, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10475, '公众号新增', 'mp_wxaccount_add', '', 10474, '1', 0, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10476, '公众号修改', 'mp_wxaccount_edit', NULL, 10474, '1', 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10477, '公众号删除', 'mp_wxaccount_del', NULL, 10474, '1', 2, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10478, '自动回复', NULL, '/mp/wxautoreply/index', 10459, 'icon-huifu', 998, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10479, '新增回复', 'mp_wxautoreply_add', NULL, 10478, NULL, 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10480, '编辑回复', 'mp_wxautoreply_edit', NULL, 10478, NULL, 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10481, '删除回复', 'mp_wxautoreply_del', NULL, 10478, NULL, 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10482, '素材管理', NULL, '/mp/wxmaterial/index', 10459, 'icon-sucaisads', 999, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10483, '素材维护', 'mp_wxmaterial_add', NULL, 10482, NULL, 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10484, '素材删除', 'mp_wxmaterial_del', NULL, 10482, NULL, 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10485, '支付管理', NULL, '/pay', -1, 'icon-pay6zhifu', 4, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10486, '收银台', NULL, '/pay/cd/index', 10485, 'icon-shouyintai', 0, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10487, '渠道管理', NULL, '/pay/paychannel/index', 10485, 'icon-zhifuqudaoguanli', 1, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10488, '编辑渠道', 'pay_paychannel_edit', NULL, 10487, NULL, 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10489, '删除渠道', 'pay_paychannel_del', NULL, 10487, NULL, 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10490, '增加渠道', 'pay_paychannel_add', NULL, 10487, NULL, 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10491, '商品订单', '', '/pay/goods/index', 10485, 'icon-dingdan', 2, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10492, '商品订单新增', 'generator_paygoodsorder_add', NULL, 10491, '1', 0, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10493, '商品订单修改', 'generator_paygoodsorder_edit', NULL, 10491, '1', 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10494, '商品订单删除', 'generator_paygoodsorder_del', NULL, 10491, '1', 2, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10495, '支付订单', '', '/pay/orders/index', 10485, 'icon-zhifu', 3, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10496, '支付订单新增', 'generator_paytradeorder_add', NULL, 10495, '1', 0, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10497, '支付订单修改', 'generator_paytradeorder_edit', NULL, 10495, '1', 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10498, '支付订单删除', 'generator_paytradeorder_del', NULL, 10495, '1', 2, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10499, '回调记录', '', '/pay/notify/index', 10485, 'icon-huitiao', 4, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10500, '记录新增', 'generator_paynotifyrecord_add', NULL, 10499, '1', 0, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10501, '记录修改', 'generator_paynotifyrecord_edit', NULL, 10499, '1', 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10502, '记录删除', 'generator_paynotifyrecord_del', NULL, 10499, '1', 2, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10503, '开发平台', NULL, '/gen', -1, 'icon-shejiyukaifa-', 9, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10504, '数据源管理', NULL, '/gen/datasource', 10503, 'icon-mysql', 0, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10505, '代码生成', '', '/gen/index', 10503, 'icon-weibiaoti46', 1, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10506, '表单设计', NULL, '/gen/design', 10503, 'icon-biaodanbiaoqian', 2, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10507, '表单管理', '', '/gen/form', 10503, 'icon-record', 3, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10508, '表单新增', 'gen_form_add', NULL, 10507, '1', 0, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10509, '表单修改', 'gen_form_edit', NULL, 10507, '1', 1, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10510, '表单删除', 'gen_form_del', NULL, 10507, '1', 2, '0', '1', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
INSERT INTO `sys_menu` VALUES (10511, '报表设计', NULL, 'http://127.0.0.1:5006/ureport/designer', -1, 'icon-icon-p_mrpbaobiao', 9, '0', '0', '2022-07-06 08:31:00', NULL, '0', 1544598217314631684);
COMMIT;

-- ----------------------------
-- Table structure for sys_oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client_details`;
CREATE TABLE `sys_oauth_client_details` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `client_id` varchar(32) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int DEFAULT NULL,
  `refresh_token_validity` int DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  `del_flag` char(1) DEFAULT '0',
  `tenant_id` bigint NOT NULL DEFAULT '0' COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='终端信息表';

-- ----------------------------
-- Records of sys_oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `sys_oauth_client_details` VALUES (1, 'app', NULL, 'app', 'server', 'password,refresh_token,authorization_code,client_credentials,implicit', NULL, NULL, 43200, 2592001, '{\"enc_flag\":\"1\",\"captcha_flag\":\"1\"}', 'true', '0', 1);
INSERT INTO `sys_oauth_client_details` VALUES (2, 'daemon', NULL, 'daemon', 'server', 'password,refresh_token', NULL, NULL, NULL, NULL, '{\"enc_flag\":\"1\",\"captcha_flag\":\"1\"}', 'true', '0', 1);
INSERT INTO `sys_oauth_client_details` VALUES (3, 'gen', NULL, 'gen', 'server', 'password,refresh_token', NULL, NULL, NULL, NULL, '{\"enc_flag\":\"1\",\"captcha_flag\":\"1\"}', 'true', '0', 1);
INSERT INTO `sys_oauth_client_details` VALUES (4, 'mp', NULL, 'mp', 'server', 'password,refresh_token', NULL, NULL, NULL, NULL, '{\"enc_flag\":\"1\",\"captcha_flag\":\"1\"}', 'true', '0', 1);
INSERT INTO `sys_oauth_client_details` VALUES (5, 'pig', NULL, 'pig', 'server', 'password,refresh_token,authorization_code,client_credentials,app', 'http://localhost:4040/sso1/login,http://localhost:4041/sso1/login,http://localhost:8080/renren-admin/sys/oauth2-sso,http://localhost:8090/sys/oauth2-sso', NULL, NULL, NULL, '{\"enc_flag\":\"1\",\"captcha_flag\":\"1\"}', 'false', '0', 1);
INSERT INTO `sys_oauth_client_details` VALUES (6, 'test', NULL, 'test', 'server', 'password,refresh_token', NULL, NULL, NULL, NULL, '{ \"enc_flag\":\"1\",\"captcha_flag\":\"0\"}', 'true', '0', 1);
INSERT INTO `sys_oauth_client_details` VALUES (22, 'app', NULL, 'app', 'server', NULL, NULL, NULL, 43200, 2592001, '{\"enc_flag\":\"1\",\"captcha_flag\":\"1\"}', 'true', '0', 1544598217314631684);
INSERT INTO `sys_oauth_client_details` VALUES (23, 'daemon', NULL, 'daemon', 'server', NULL, NULL, NULL, NULL, NULL, '{\"enc_flag\":\"1\",\"captcha_flag\":\"1\"}', 'true', '0', 1544598217314631684);
INSERT INTO `sys_oauth_client_details` VALUES (24, 'gen', NULL, 'gen', 'server', NULL, NULL, NULL, NULL, NULL, '{\"enc_flag\":\"1\",\"captcha_flag\":\"1\"}', 'true', '0', 1544598217314631684);
INSERT INTO `sys_oauth_client_details` VALUES (25, 'mp', NULL, 'mp', 'server', NULL, NULL, NULL, NULL, NULL, '{\"enc_flag\":\"1\",\"captcha_flag\":\"1\"}', 'true', '0', 1544598217314631684);
INSERT INTO `sys_oauth_client_details` VALUES (26, 'pig1', NULL, 'pig', 'server', NULL, 'http://localhost:4040/sso1/login,http://localhost:4041/sso1/login,http://localhost:8080/renren-admin/sys/oauth2-sso,http://localhost:8090/sys/oauth2-sso', NULL, NULL, NULL, '{\"enc_flag\":\"1\",\"captcha_flag\":\"1\"}', 'false', '0', 1544598217314631684);
INSERT INTO `sys_oauth_client_details` VALUES (27, 'test', NULL, 'test', 'server', NULL, NULL, NULL, NULL, NULL, '{ \"enc_flag\":\"1\",\"captcha_flag\":\"0\"}', 'true', '0', 1544598217314631684);
COMMIT;

-- ----------------------------
-- Table structure for sys_public_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_public_param`;
CREATE TABLE `sys_public_param` (
  `public_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `public_name` varchar(128) DEFAULT NULL,
  `public_key` varchar(128) DEFAULT NULL,
  `public_value` varchar(128) DEFAULT NULL,
  `status` char(1) DEFAULT '0',
  `validate_code` varchar(64) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `public_type` char(1) DEFAULT '0',
  `system` char(1) DEFAULT '0',
  `del_flag` char(1) DEFAULT '0',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`public_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公共参数配置表';

-- ----------------------------
-- Records of sys_public_param
-- ----------------------------
BEGIN;
INSERT INTO `sys_public_param` VALUES (1, '租户默认来源', 'TENANT_DEFAULT_ID', '1', '0', '', '2020-05-12 04:03:46', '2020-06-20 08:56:30', '2', '0', '1', 1);
INSERT INTO `sys_public_param` VALUES (2, '租户默认部门名称', 'TENANT_DEFAULT_DEPTNAME', '租户默认部门', '0', '', '2020-05-12 03:36:32', NULL, '2', '1', '0', 1);
INSERT INTO `sys_public_param` VALUES (3, '租户默认账户', 'TENANT_DEFAULT_USERNAME', 'admin', '0', '', '2020-05-12 04:05:04', NULL, '2', '1', '0', 1);
INSERT INTO `sys_public_param` VALUES (4, '租户默认密码', 'TENANT_DEFAULT_PASSWORD', '123456', '0', '', '2020-05-12 04:05:24', NULL, '2', '1', '0', 1);
INSERT INTO `sys_public_param` VALUES (5, '租户默认角色编码', 'TENANT_DEFAULT_ROLECODE', 'ROLE_ADMIN', '0', '', '2020-05-12 04:05:57', NULL, '2', '1', '0', 1);
INSERT INTO `sys_public_param` VALUES (6, '租户默认角色名称', 'TENANT_DEFAULT_ROLENAME', '租户默认角色', '0', '', '2020-05-12 04:06:19', NULL, '2', '1', '0', 1);
INSERT INTO `sys_public_param` VALUES (7, '表前缀', 'GEN_TABLE_PREFIX', 'tb_', '0', '', '2020-05-12 04:23:04', NULL, '9', '1', '0', 1);
INSERT INTO `sys_public_param` VALUES (8, '接口文档不显示的字段', 'GEN_HIDDEN_COLUMNS', 'tenant_id', '0', '', '2020-05-12 04:25:19', NULL, '9', '1', '0', 1);
INSERT INTO `sys_public_param` VALUES (16, '租户默认部门名称', 'TENANT_DEFAULT_DEPTNAME', '租户默认部门', '0', '', '2020-05-12 03:36:32', NULL, '2', '1', '0', 1544598217314631684);
INSERT INTO `sys_public_param` VALUES (17, '租户默认账户', 'TENANT_DEFAULT_USERNAME', 'admin', '0', '', '2020-05-12 04:05:04', NULL, '2', '1', '0', 1544598217314631684);
INSERT INTO `sys_public_param` VALUES (18, '租户默认密码', 'TENANT_DEFAULT_PASSWORD', '123456', '0', '', '2020-05-12 04:05:24', NULL, '2', '1', '0', 1544598217314631684);
INSERT INTO `sys_public_param` VALUES (19, '租户默认角色编码', 'TENANT_DEFAULT_ROLECODE', 'ROLE_ADMIN', '0', '', '2020-05-12 04:05:57', NULL, '2', '1', '0', 1544598217314631684);
INSERT INTO `sys_public_param` VALUES (20, '租户默认角色名称', 'TENANT_DEFAULT_ROLENAME', '租户默认角色', '0', '', '2020-05-12 04:06:19', NULL, '2', '1', '0', 1544598217314631684);
INSERT INTO `sys_public_param` VALUES (21, '表前缀', 'GEN_TABLE_PREFIX', 'tb_', '0', '', '2020-05-12 04:23:04', NULL, '9', '1', '0', 1544598217314631684);
INSERT INTO `sys_public_param` VALUES (22, '接口文档不显示的字段', 'GEN_HIDDEN_COLUMNS', 'tenant_id', '0', '', '2020-05-12 04:25:19', NULL, '9', '1', '0', 1544598217314631684);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) DEFAULT NULL,
  `role_code` varchar(64) DEFAULT NULL,
  `role_desc` varchar(255) DEFAULT NULL,
  `ds_type` char(1) DEFAULT '2',
  `ds_scope` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` char(1) DEFAULT '0',
  `tenant_id` bigint DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE,
  KEY `role_idx1_role_code` (`role_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '管理员', 'ROLE_ADMIN', '管理员', '0', '2', '2017-10-29 15:45:51', '2018-12-26 14:09:11', '0', 1);
INSERT INTO `sys_role` VALUES (8, '租户默认角色', 'ROLE_ADMIN', NULL, '2', NULL, '2022-07-06 08:30:59', NULL, '0', 1544598217314631684);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色菜单表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (1, 1000);
INSERT INTO `sys_role_menu` VALUES (1, 1100);
INSERT INTO `sys_role_menu` VALUES (1, 1101);
INSERT INTO `sys_role_menu` VALUES (1, 1102);
INSERT INTO `sys_role_menu` VALUES (1, 1103);
INSERT INTO `sys_role_menu` VALUES (1, 1200);
INSERT INTO `sys_role_menu` VALUES (1, 1201);
INSERT INTO `sys_role_menu` VALUES (1, 1202);
INSERT INTO `sys_role_menu` VALUES (1, 1203);
INSERT INTO `sys_role_menu` VALUES (1, 1300);
INSERT INTO `sys_role_menu` VALUES (1, 1301);
INSERT INTO `sys_role_menu` VALUES (1, 1302);
INSERT INTO `sys_role_menu` VALUES (1, 1303);
INSERT INTO `sys_role_menu` VALUES (1, 1304);
INSERT INTO `sys_role_menu` VALUES (1, 1400);
INSERT INTO `sys_role_menu` VALUES (1, 1401);
INSERT INTO `sys_role_menu` VALUES (1, 1402);
INSERT INTO `sys_role_menu` VALUES (1, 1403);
INSERT INTO `sys_role_menu` VALUES (1, 1500);
INSERT INTO `sys_role_menu` VALUES (1, 1501);
INSERT INTO `sys_role_menu` VALUES (1, 1502);
INSERT INTO `sys_role_menu` VALUES (1, 1503);
INSERT INTO `sys_role_menu` VALUES (1, 2000);
INSERT INTO `sys_role_menu` VALUES (1, 2100);
INSERT INTO `sys_role_menu` VALUES (1, 2101);
INSERT INTO `sys_role_menu` VALUES (1, 2200);
INSERT INTO `sys_role_menu` VALUES (1, 2201);
INSERT INTO `sys_role_menu` VALUES (1, 2202);
INSERT INTO `sys_role_menu` VALUES (1, 2203);
INSERT INTO `sys_role_menu` VALUES (1, 2210);
INSERT INTO `sys_role_menu` VALUES (1, 2211);
INSERT INTO `sys_role_menu` VALUES (1, 2212);
INSERT INTO `sys_role_menu` VALUES (1, 2213);
INSERT INTO `sys_role_menu` VALUES (1, 2300);
INSERT INTO `sys_role_menu` VALUES (1, 2400);
INSERT INTO `sys_role_menu` VALUES (1, 2401);
INSERT INTO `sys_role_menu` VALUES (1, 2402);
INSERT INTO `sys_role_menu` VALUES (1, 2403);
INSERT INTO `sys_role_menu` VALUES (1, 2500);
INSERT INTO `sys_role_menu` VALUES (1, 2501);
INSERT INTO `sys_role_menu` VALUES (1, 2502);
INSERT INTO `sys_role_menu` VALUES (1, 2503);
INSERT INTO `sys_role_menu` VALUES (1, 2600);
INSERT INTO `sys_role_menu` VALUES (1, 2601);
INSERT INTO `sys_role_menu` VALUES (1, 2700);
INSERT INTO `sys_role_menu` VALUES (1, 2800);
INSERT INTO `sys_role_menu` VALUES (1, 2810);
INSERT INTO `sys_role_menu` VALUES (1, 2820);
INSERT INTO `sys_role_menu` VALUES (1, 2830);
INSERT INTO `sys_role_menu` VALUES (1, 2840);
INSERT INTO `sys_role_menu` VALUES (1, 2850);
INSERT INTO `sys_role_menu` VALUES (1, 2860);
INSERT INTO `sys_role_menu` VALUES (1, 2870);
INSERT INTO `sys_role_menu` VALUES (1, 3000);
INSERT INTO `sys_role_menu` VALUES (1, 3100);
INSERT INTO `sys_role_menu` VALUES (1, 3101);
INSERT INTO `sys_role_menu` VALUES (1, 3110);
INSERT INTO `sys_role_menu` VALUES (1, 3200);
INSERT INTO `sys_role_menu` VALUES (1, 3300);
INSERT INTO `sys_role_menu` VALUES (1, 3400);
INSERT INTO `sys_role_menu` VALUES (1, 3500);
INSERT INTO `sys_role_menu` VALUES (1, 3600);
INSERT INTO `sys_role_menu` VALUES (1, 4000);
INSERT INTO `sys_role_menu` VALUES (1, 4100);
INSERT INTO `sys_role_menu` VALUES (1, 4101);
INSERT INTO `sys_role_menu` VALUES (1, 4200);
INSERT INTO `sys_role_menu` VALUES (1, 4201);
INSERT INTO `sys_role_menu` VALUES (1, 4300);
INSERT INTO `sys_role_menu` VALUES (1, 4301);
INSERT INTO `sys_role_menu` VALUES (1, 4302);
INSERT INTO `sys_role_menu` VALUES (1, 4303);
INSERT INTO `sys_role_menu` VALUES (1, 4400);
INSERT INTO `sys_role_menu` VALUES (1, 4401);
INSERT INTO `sys_role_menu` VALUES (1, 5000);
INSERT INTO `sys_role_menu` VALUES (1, 5100);
INSERT INTO `sys_role_menu` VALUES (1, 5110);
INSERT INTO `sys_role_menu` VALUES (1, 5120);
INSERT INTO `sys_role_menu` VALUES (1, 5130);
INSERT INTO `sys_role_menu` VALUES (1, 5200);
INSERT INTO `sys_role_menu` VALUES (1, 5300);
INSERT INTO `sys_role_menu` VALUES (1, 5310);
INSERT INTO `sys_role_menu` VALUES (1, 5320);
INSERT INTO `sys_role_menu` VALUES (1, 5330);
INSERT INTO `sys_role_menu` VALUES (1, 5400);
INSERT INTO `sys_role_menu` VALUES (1, 5410);
INSERT INTO `sys_role_menu` VALUES (1, 5420);
INSERT INTO `sys_role_menu` VALUES (1, 5430);
INSERT INTO `sys_role_menu` VALUES (1, 5500);
INSERT INTO `sys_role_menu` VALUES (1, 5510);
INSERT INTO `sys_role_menu` VALUES (1, 5520);
INSERT INTO `sys_role_menu` VALUES (1, 5530);
INSERT INTO `sys_role_menu` VALUES (1, 6000);
INSERT INTO `sys_role_menu` VALUES (1, 6100);
INSERT INTO `sys_role_menu` VALUES (1, 6101);
INSERT INTO `sys_role_menu` VALUES (1, 6102);
INSERT INTO `sys_role_menu` VALUES (1, 6103);
INSERT INTO `sys_role_menu` VALUES (1, 6200);
INSERT INTO `sys_role_menu` VALUES (1, 6201);
INSERT INTO `sys_role_menu` VALUES (1, 6202);
INSERT INTO `sys_role_menu` VALUES (1, 6203);
INSERT INTO `sys_role_menu` VALUES (1, 6204);
INSERT INTO `sys_role_menu` VALUES (1, 6300);
INSERT INTO `sys_role_menu` VALUES (1, 6301);
INSERT INTO `sys_role_menu` VALUES (1, 6302);
INSERT INTO `sys_role_menu` VALUES (1, 6303);
INSERT INTO `sys_role_menu` VALUES (1, 6304);
INSERT INTO `sys_role_menu` VALUES (1, 6305);
INSERT INTO `sys_role_menu` VALUES (1, 6400);
INSERT INTO `sys_role_menu` VALUES (1, 6401);
INSERT INTO `sys_role_menu` VALUES (1, 6402);
INSERT INTO `sys_role_menu` VALUES (1, 6500);
INSERT INTO `sys_role_menu` VALUES (1, 6600);
INSERT INTO `sys_role_menu` VALUES (1, 6601);
INSERT INTO `sys_role_menu` VALUES (1, 6602);
INSERT INTO `sys_role_menu` VALUES (1, 6700);
INSERT INTO `sys_role_menu` VALUES (1, 6701);
INSERT INTO `sys_role_menu` VALUES (1, 6702);
INSERT INTO `sys_role_menu` VALUES (1, 6703);
INSERT INTO `sys_role_menu` VALUES (1, 7000);
INSERT INTO `sys_role_menu` VALUES (1, 8000);
INSERT INTO `sys_role_menu` VALUES (1, 8001);
INSERT INTO `sys_role_menu` VALUES (1, 9000);
INSERT INTO `sys_role_menu` VALUES (1, 9001);
INSERT INTO `sys_role_menu` VALUES (1, 9002);
INSERT INTO `sys_role_menu` VALUES (1, 9003);
INSERT INTO `sys_role_menu` VALUES (1, 9004);
INSERT INTO `sys_role_menu` VALUES (1, 9005);
INSERT INTO `sys_role_menu` VALUES (1, 9006);
INSERT INTO `sys_role_menu` VALUES (8, 10385);
INSERT INTO `sys_role_menu` VALUES (8, 10386);
INSERT INTO `sys_role_menu` VALUES (8, 10387);
INSERT INTO `sys_role_menu` VALUES (8, 10388);
INSERT INTO `sys_role_menu` VALUES (8, 10389);
INSERT INTO `sys_role_menu` VALUES (8, 10390);
INSERT INTO `sys_role_menu` VALUES (8, 10391);
INSERT INTO `sys_role_menu` VALUES (8, 10392);
INSERT INTO `sys_role_menu` VALUES (8, 10393);
INSERT INTO `sys_role_menu` VALUES (8, 10394);
INSERT INTO `sys_role_menu` VALUES (8, 10395);
INSERT INTO `sys_role_menu` VALUES (8, 10396);
INSERT INTO `sys_role_menu` VALUES (8, 10397);
INSERT INTO `sys_role_menu` VALUES (8, 10398);
INSERT INTO `sys_role_menu` VALUES (8, 10399);
INSERT INTO `sys_role_menu` VALUES (8, 10400);
INSERT INTO `sys_role_menu` VALUES (8, 10401);
INSERT INTO `sys_role_menu` VALUES (8, 10402);
INSERT INTO `sys_role_menu` VALUES (8, 10403);
INSERT INTO `sys_role_menu` VALUES (8, 10404);
INSERT INTO `sys_role_menu` VALUES (8, 10405);
INSERT INTO `sys_role_menu` VALUES (8, 10406);
INSERT INTO `sys_role_menu` VALUES (8, 10407);
INSERT INTO `sys_role_menu` VALUES (8, 10408);
INSERT INTO `sys_role_menu` VALUES (8, 10409);
INSERT INTO `sys_role_menu` VALUES (8, 10410);
INSERT INTO `sys_role_menu` VALUES (8, 10411);
INSERT INTO `sys_role_menu` VALUES (8, 10412);
INSERT INTO `sys_role_menu` VALUES (8, 10413);
INSERT INTO `sys_role_menu` VALUES (8, 10414);
INSERT INTO `sys_role_menu` VALUES (8, 10415);
INSERT INTO `sys_role_menu` VALUES (8, 10416);
INSERT INTO `sys_role_menu` VALUES (8, 10417);
INSERT INTO `sys_role_menu` VALUES (8, 10418);
INSERT INTO `sys_role_menu` VALUES (8, 10419);
INSERT INTO `sys_role_menu` VALUES (8, 10420);
INSERT INTO `sys_role_menu` VALUES (8, 10421);
INSERT INTO `sys_role_menu` VALUES (8, 10422);
INSERT INTO `sys_role_menu` VALUES (8, 10423);
INSERT INTO `sys_role_menu` VALUES (8, 10424);
INSERT INTO `sys_role_menu` VALUES (8, 10425);
INSERT INTO `sys_role_menu` VALUES (8, 10426);
INSERT INTO `sys_role_menu` VALUES (8, 10427);
INSERT INTO `sys_role_menu` VALUES (8, 10428);
INSERT INTO `sys_role_menu` VALUES (8, 10429);
INSERT INTO `sys_role_menu` VALUES (8, 10430);
INSERT INTO `sys_role_menu` VALUES (8, 10431);
INSERT INTO `sys_role_menu` VALUES (8, 10432);
INSERT INTO `sys_role_menu` VALUES (8, 10433);
INSERT INTO `sys_role_menu` VALUES (8, 10434);
INSERT INTO `sys_role_menu` VALUES (8, 10435);
INSERT INTO `sys_role_menu` VALUES (8, 10436);
INSERT INTO `sys_role_menu` VALUES (8, 10437);
INSERT INTO `sys_role_menu` VALUES (8, 10438);
INSERT INTO `sys_role_menu` VALUES (8, 10439);
INSERT INTO `sys_role_menu` VALUES (8, 10440);
INSERT INTO `sys_role_menu` VALUES (8, 10441);
INSERT INTO `sys_role_menu` VALUES (8, 10442);
INSERT INTO `sys_role_menu` VALUES (8, 10443);
INSERT INTO `sys_role_menu` VALUES (8, 10444);
INSERT INTO `sys_role_menu` VALUES (8, 10445);
INSERT INTO `sys_role_menu` VALUES (8, 10446);
INSERT INTO `sys_role_menu` VALUES (8, 10447);
INSERT INTO `sys_role_menu` VALUES (8, 10448);
INSERT INTO `sys_role_menu` VALUES (8, 10449);
INSERT INTO `sys_role_menu` VALUES (8, 10450);
INSERT INTO `sys_role_menu` VALUES (8, 10451);
INSERT INTO `sys_role_menu` VALUES (8, 10452);
INSERT INTO `sys_role_menu` VALUES (8, 10453);
INSERT INTO `sys_role_menu` VALUES (8, 10454);
INSERT INTO `sys_role_menu` VALUES (8, 10455);
INSERT INTO `sys_role_menu` VALUES (8, 10456);
INSERT INTO `sys_role_menu` VALUES (8, 10457);
INSERT INTO `sys_role_menu` VALUES (8, 10458);
INSERT INTO `sys_role_menu` VALUES (8, 10459);
INSERT INTO `sys_role_menu` VALUES (8, 10460);
INSERT INTO `sys_role_menu` VALUES (8, 10461);
INSERT INTO `sys_role_menu` VALUES (8, 10462);
INSERT INTO `sys_role_menu` VALUES (8, 10463);
INSERT INTO `sys_role_menu` VALUES (8, 10464);
INSERT INTO `sys_role_menu` VALUES (8, 10465);
INSERT INTO `sys_role_menu` VALUES (8, 10466);
INSERT INTO `sys_role_menu` VALUES (8, 10467);
INSERT INTO `sys_role_menu` VALUES (8, 10468);
INSERT INTO `sys_role_menu` VALUES (8, 10469);
INSERT INTO `sys_role_menu` VALUES (8, 10470);
INSERT INTO `sys_role_menu` VALUES (8, 10471);
INSERT INTO `sys_role_menu` VALUES (8, 10472);
INSERT INTO `sys_role_menu` VALUES (8, 10473);
INSERT INTO `sys_role_menu` VALUES (8, 10474);
INSERT INTO `sys_role_menu` VALUES (8, 10475);
INSERT INTO `sys_role_menu` VALUES (8, 10476);
INSERT INTO `sys_role_menu` VALUES (8, 10477);
INSERT INTO `sys_role_menu` VALUES (8, 10478);
INSERT INTO `sys_role_menu` VALUES (8, 10479);
INSERT INTO `sys_role_menu` VALUES (8, 10480);
INSERT INTO `sys_role_menu` VALUES (8, 10481);
INSERT INTO `sys_role_menu` VALUES (8, 10482);
INSERT INTO `sys_role_menu` VALUES (8, 10483);
INSERT INTO `sys_role_menu` VALUES (8, 10484);
INSERT INTO `sys_role_menu` VALUES (8, 10485);
INSERT INTO `sys_role_menu` VALUES (8, 10486);
INSERT INTO `sys_role_menu` VALUES (8, 10487);
INSERT INTO `sys_role_menu` VALUES (8, 10488);
INSERT INTO `sys_role_menu` VALUES (8, 10489);
INSERT INTO `sys_role_menu` VALUES (8, 10490);
INSERT INTO `sys_role_menu` VALUES (8, 10491);
INSERT INTO `sys_role_menu` VALUES (8, 10492);
INSERT INTO `sys_role_menu` VALUES (8, 10493);
INSERT INTO `sys_role_menu` VALUES (8, 10494);
INSERT INTO `sys_role_menu` VALUES (8, 10495);
INSERT INTO `sys_role_menu` VALUES (8, 10496);
INSERT INTO `sys_role_menu` VALUES (8, 10497);
INSERT INTO `sys_role_menu` VALUES (8, 10498);
INSERT INTO `sys_role_menu` VALUES (8, 10499);
INSERT INTO `sys_role_menu` VALUES (8, 10500);
INSERT INTO `sys_role_menu` VALUES (8, 10501);
INSERT INTO `sys_role_menu` VALUES (8, 10502);
INSERT INTO `sys_role_menu` VALUES (8, 10503);
INSERT INTO `sys_role_menu` VALUES (8, 10504);
INSERT INTO `sys_role_menu` VALUES (8, 10505);
INSERT INTO `sys_role_menu` VALUES (8, 10506);
INSERT INTO `sys_role_menu` VALUES (8, 10507);
INSERT INTO `sys_role_menu` VALUES (8, 10508);
INSERT INTO `sys_role_menu` VALUES (8, 10509);
INSERT INTO `sys_role_menu` VALUES (8, 10510);
INSERT INTO `sys_role_menu` VALUES (8, 10511);
COMMIT;

-- ----------------------------
-- Table structure for sys_route_conf
-- ----------------------------
DROP TABLE IF EXISTS `sys_route_conf`;
CREATE TABLE `sys_route_conf` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `route_name` varchar(30) DEFAULT NULL,
  `route_id` varchar(30) DEFAULT NULL,
  `predicates` json DEFAULT NULL COMMENT '断言',
  `filters` json DEFAULT NULL COMMENT '过滤器',
  `uri` varchar(50) DEFAULT NULL,
  `sort_order` int DEFAULT '0' COMMENT '排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` char(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='路由配置表';

-- ----------------------------
-- Records of sys_route_conf
-- ----------------------------
BEGIN;
INSERT INTO `sys_route_conf` VALUES (1, '工作流管理模块', 'pigx-oa-platform', '[{\"args\": {\"_genkey_0\": \"/act/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-oa-platform', 0, '2019-10-16 16:44:41', '2022-07-22 07:57:52', '1');
INSERT INTO `sys_route_conf` VALUES (2, '认证中心', 'cloud-auth', '[{\"args\": {\"_genkey_0\": \"/auth/**\"}, \"name\": \"Path\"}]', '[{\"args\": {}, \"name\": \"ValidateCodeGatewayFilter\"}, {\"args\": {}, \"name\": \"PasswordDecoderFilter\"}]', 'lb://cloud-auth', 0, '2019-10-16 16:44:41', '2022-07-22 07:57:52', '1');
INSERT INTO `sys_route_conf` VALUES (3, '代码生成模块', 'pigx-codegen', '[{\"args\": {\"_genkey_0\": \"/gen/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-codegen', 0, '2019-10-16 16:44:41', '2022-07-22 07:57:52', '1');
INSERT INTO `sys_route_conf` VALUES (4, 'elastic-job定时任务模块', 'pigx-daemon-elastic-job', '[{\"args\": {\"_genkey_0\": \"/daemon/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-daemon-elastic-job', 0, '2019-10-16 16:44:41', '2022-07-22 07:57:52', '1');
INSERT INTO `sys_route_conf` VALUES (5, 'quartz定时任务模块', 'pigx-daemon-quartz', '[{\"args\": {\"_genkey_0\": \"/job/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-daemon-quartz', 0, '2019-10-16 16:44:41', '2022-07-22 07:57:52', '1');
INSERT INTO `sys_route_conf` VALUES (6, '分布式事务模块', 'pigx-tx-manager', '[{\"args\": {\"_genkey_0\": \"/tx/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-tx-manager', 0, '2019-10-16 16:44:41', '2022-07-22 07:57:52', '1');
INSERT INTO `sys_route_conf` VALUES (7, '通用权限模块', 'cloud-upms-biz', '[{\"args\": {\"_genkey_0\": \"/admin/**\"}, \"name\": \"Path\"}]', '[{\"args\": {\"key-resolver\": \"#{@remoteAddrKeyResolver}\", \"redis-rate-limiter.burstCapacity\": \"1000\", \"redis-rate-limiter.replenishRate\": \"1000\"}, \"name\": \"RequestRateLimiter\"}]', 'lb://cloud-upms-biz', 0, '2019-10-16 16:44:41', '2022-07-22 07:57:52', '1');
INSERT INTO `sys_route_conf` VALUES (8, '工作流长链接支持1', 'pigx-oa-platform-ws-1', '[{\"args\": {\"_genkey_0\": \"/act/ws/info/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-oa-platform', 0, '2019-10-16 16:44:41', '2022-07-22 07:57:52', '1');
INSERT INTO `sys_route_conf` VALUES (9, '工作流长链接支持2', 'pigx-oa-platform-ws-2', '[{\"args\": {\"_genkey_0\": \"/act/ws/**\"}, \"name\": \"Path\"}]', '[]', 'lb:ws://pigx-oa-platform', 100, '2019-10-16 16:44:41', '2022-07-22 07:57:52', '1');
INSERT INTO `sys_route_conf` VALUES (10, '微信公众号管理', 'pigx-mp-platform', '[{\"args\": {\"_genkey_0\": \"/mp/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-mp-platform', 0, '2019-10-16 16:44:41', '2022-07-22 07:57:52', '1');
INSERT INTO `sys_route_conf` VALUES (11, '支付管理', 'pigx-pay-platform', '[{\"args\": {\"_genkey_0\": \"/pay/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-pay-platform', 0, '2019-10-16 16:44:41', '2022-07-22 07:57:52', '1');
INSERT INTO `sys_route_conf` VALUES (12, '监控管理', 'pigx-monitor', '[{\"args\": {\"_genkey_0\": \"/monitor/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-monitor', 0, '2019-10-16 16:44:41', '2022-07-22 07:57:52', '1');
INSERT INTO `sys_route_conf` VALUES (13, '工作流管理模块', 'pigx-oa-platform', '[{\"args\": {\"_genkey_0\": \"/act/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-oa-platform', 0, '2022-07-22 07:57:52', NULL, '0');
INSERT INTO `sys_route_conf` VALUES (14, '认证中心', 'cloud-auth', '[{\"args\": {\"_genkey_0\": \"/auth/**\"}, \"name\": \"Path\"}]', '[{\"args\": {}, \"name\": \"ValidateCodeGatewayFilter\"}, {\"args\": {}, \"name\": \"PasswordDecoderFilter\"}]', 'lb://cloud-auth', 0, '2022-07-22 07:57:52', NULL, '0');
INSERT INTO `sys_route_conf` VALUES (15, '代码生成模块', 'pigx-codegen', '[{\"args\": {\"_genkey_0\": \"/gen/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-codegen', 0, '2022-07-22 07:57:52', NULL, '0');
INSERT INTO `sys_route_conf` VALUES (16, 'elastic-job定时任务模块', 'pigx-daemon-elastic-job', '[{\"args\": {\"_genkey_0\": \"/daemon/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-daemon-elastic-job', 0, '2022-07-22 07:57:52', NULL, '0');
INSERT INTO `sys_route_conf` VALUES (17, 'quartz定时任务模块', 'pigx-daemon-quartz', '[{\"args\": {\"_genkey_0\": \"/job/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-daemon-quartz', 0, '2022-07-22 07:57:52', NULL, '0');
INSERT INTO `sys_route_conf` VALUES (18, '分布式事务模块', 'pigx-tx-manager', '[{\"args\": {\"_genkey_0\": \"/tx/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-tx-manager', 0, '2022-07-22 07:57:52', NULL, '0');
INSERT INTO `sys_route_conf` VALUES (19, '通用权限模块', 'cloud-upms-biz', '[{\"args\": {\"_genkey_0\": \"/admin/**\"}, \"name\": \"Path\"}]', '[{\"args\": {\"key-resolver\": \"#{@remoteAddrKeyResolver}\", \"redis-rate-limiter.burstCapacity\": \"1000\", \"redis-rate-limiter.replenishRate\": \"1000\"}, \"name\": \"RequestRateLimiter\"}]', 'lb://cloud-upms-biz', 0, '2022-07-22 07:57:52', NULL, '0');
INSERT INTO `sys_route_conf` VALUES (20, '工作流长链接支持1', 'pigx-oa-platform-ws-1', '[{\"args\": {\"_genkey_0\": \"/act/ws/info/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-oa-platform', 0, '2022-07-22 07:57:52', NULL, '0');
INSERT INTO `sys_route_conf` VALUES (21, '工作流长链接支持2', 'pigx-oa-platform-ws-2', '[{\"args\": {\"_genkey_0\": \"/act/ws/**\"}, \"name\": \"Path\"}]', '[]', 'lb:ws://pigx-oa-platform', 0, '2022-07-22 07:57:52', NULL, '0');
INSERT INTO `sys_route_conf` VALUES (22, '微信公众号管理', 'pigx-mp-platform', '[{\"args\": {\"_genkey_0\": \"/mp/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-mp-platform', 0, '2022-07-22 07:57:52', NULL, '0');
INSERT INTO `sys_route_conf` VALUES (23, '支付管理', 'pigx-pay-platform', '[{\"args\": {\"_genkey_0\": \"/pay/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-pay-platform', 0, '2022-07-22 07:57:52', NULL, '0');
INSERT INTO `sys_route_conf` VALUES (24, '监控管理', 'pigx-monitor', '[{\"args\": {\"_genkey_0\": \"/monitor/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-monitor', 0, '2022-07-22 07:57:52', NULL, '0');
INSERT INTO `sys_route_conf` VALUES (25, '接口文档', 'open-api', '[{\"args\": {\"_genkey_0\": \"/v3/api-docs/**\"}, \"name\": \"Path\"}]', '[{\"args\": {\"_genkey_0\": \"/v3/api-docs/(?<path>.*)\", \"_genkey_1\": \"/${path}/${path}/v3/api-docs\"}, \"name\": \"RewritePath\"}]', 'lb://cloud-gateway', 0, '2022-07-22 07:57:52', '2022-07-22 08:14:05', '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_social_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_social_details`;
CREATE TABLE `sys_social_details` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主鍵',
  `type` varchar(16) DEFAULT NULL,
  `remark` varchar(64) DEFAULT NULL,
  `app_id` varchar(64) DEFAULT NULL,
  `app_secret` varchar(64) DEFAULT NULL,
  `redirect_url` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0',
  `tenant_id` int NOT NULL DEFAULT '0' COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统社交登录账号表';

-- ----------------------------
-- Records of sys_social_details
-- ----------------------------
BEGIN;
INSERT INTO `sys_social_details` VALUES (1, 'WX', '微信互联参数', 'wxd1678d3f83b1d83a', '6ddb043f94da5d2172926abe8533504f', 'daoweicloud.com', '2018-08-16 14:24:25', '2019-03-02 09:43:13', '0', 1);
INSERT INTO `sys_social_details` VALUES (2, 'GITEE', '码云登录', '8fc54e0e76e7842cf767c3ae3b9fdc48c03cefed27aa565ff7b2a39d142d9892', 'c544469ce78a67d9fcf9b28cd9f310b73f5cbc46a1b993e0802ad61517deb221', 'http://gitee.huaxiadaowei.com/#/authredirect', '2019-06-28 09:59:55', '2019-06-28 09:59:55', '0', 1);
INSERT INTO `sys_social_details` VALUES (3, 'OSC', '开源中国', 'neIIqlwGsjsfsA6uxNqD', 'aOPhRuOOJNXV1x7JrTJ9qIyRCAPXoO0l', 'http://gitee.huaxiadaowei.com/#/authredirect', '2019-06-28 10:05:37', '2019-06-28 10:05:37', '0', 1);
INSERT INTO `sys_social_details` VALUES (4, 'MINI', '小程序', 'wx6832be859d0e1cf5', '08036aef810dcb2f8ae31510910ba631', NULL, '2019-11-02 22:08:03', '2019-11-02 22:10:53', '0', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_tenant
-- ----------------------------
DROP TABLE IF EXISTS `sys_tenant`;
CREATE TABLE `sys_tenant` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '租户id',
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `status` char(1) DEFAULT '0',
  `del_flag` char(1) DEFAULT '0',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1544598217314631685 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='租户表';

-- ----------------------------
-- Records of sys_tenant
-- ----------------------------
BEGIN;
INSERT INTO `sys_tenant` VALUES (1, '北京分公司', '1', '2019-05-15 00:00:00', '2029-05-15 00:00:00', '0', '0', '2019-05-15 15:44:57', '2019-05-18 14:47:30');
INSERT INTO `sys_tenant` VALUES (1544598217314631684, 'test', '2', '2022-07-06 12:00:00', '2023-07-06 12:00:00', '0', '0', '2022-07-06 08:30:59', '2022-07-06 08:30:59');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `lock_flag` char(1) DEFAULT '0',
  `del_flag` char(1) DEFAULT '0',
  `wx_openid` varchar(32) DEFAULT NULL COMMENT '微信登录openId',
  `mini_openid` varchar(32) DEFAULT NULL COMMENT '小程序openId',
  `qq_openid` varchar(32) DEFAULT NULL COMMENT 'QQ openId',
  `gitee_login` varchar(100) DEFAULT NULL COMMENT '码云 标识',
  `osc_id` varchar(100) DEFAULT NULL COMMENT '开源中国 标识',
  `tenant_id` bigint NOT NULL DEFAULT '0' COMMENT '所属租户',
  PRIMARY KEY (`user_id`) USING BTREE,
  KEY `user_wx_openid` (`wx_openid`) USING BTREE,
  KEY `user_qq_openid` (`qq_openid`) USING BTREE,
  KEY `user_idx1_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$IVzj1Wd.ZQdOIWdb1htQjexU94uoNeuk1crlQ9ExVupPi0Iy1uv.C', '', '17034642888', '/admin/sys-file/lengleng/c5a85e0770cd4fe78bc14b63b3bd05ae.jpg', 1, '2018-04-20 07:15:18', '2019-11-02 22:12:11', '0', '0', 'o_0FT0uyg_H1vVy2H0JpSwlVGhWQ', 'oBxPy5E-v82xWGsfzZVzkD3wEX64', NULL, 'log4j', '2303656', 1);
INSERT INTO `sys_user` VALUES (8, 'admin', '$2a$10$3mMUEi5IziyGzaLnoXKSnu7rbzJNCze7g9Dtrylza4.UDzI8IqbiK', NULL, NULL, NULL, 15, '2022-07-06 08:30:59', NULL, '0', '0', NULL, NULL, NULL, NULL, NULL, 1544598217314631684);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (8, 8);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;