/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 10/09/2018 16:22:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oa_notify
-- ----------------------------
DROP TABLE IF EXISTS `oa_notify`;
CREATE TABLE `oa_notify`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '内容',
  `files` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '附件',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `oa_notify_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '通知通告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oa_notify_record
-- ----------------------------
DROP TABLE IF EXISTS `oa_notify_record`;
CREATE TABLE `oa_notify_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `notify_id` bigint(20) NULL DEFAULT NULL COMMENT '通知通告ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '接受人',
  `is_read` tinyint(1) NULL DEFAULT 0 COMMENT '阅读标记',
  `read_date` date NULL DEFAULT NULL COMMENT '阅读时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `oa_notify_record_notify_id`(`notify_id`) USING BTREE,
  INDEX `oa_notify_record_user_id`(`user_id`) USING BTREE,
  INDEX `oa_notify_record_read_flag`(`is_read`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '通知通告发送记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for p_school_info
-- ----------------------------
DROP TABLE IF EXISTS `p_school_info`;
CREATE TABLE `p_school_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `stu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学校名',
  `stu_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学校地址',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学校信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_school_info
-- ----------------------------
INSERT INTO `p_school_info` VALUES (1, '成都电子科技大学清水河校区', '清水河', NULL);
INSERT INTO `p_school_info` VALUES (2, '四川大学', '一环路', NULL);

-- ----------------------------
-- Table structure for p_student_info
-- ----------------------------
DROP TABLE IF EXISTS `p_student_info`;
CREATE TABLE `p_student_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '是否办理成功：0成功，1初始状态',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `card_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生身份证',
  `telephone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `school_id` int(11) NULL DEFAULT NULL COMMENT '学校ID',
  `dorm_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '寝室编号',
  `is_wb` int(1) NULL DEFAULT NULL COMMENT '是否安装宽带：0是，1否',
  `is_success` int(11) NULL DEFAULT 1 COMMENT '是否办理成功：0已办理，1未办理',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '业务员编号',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '录入时间',
  `is_pay` int(1) NULL DEFAULT 1 COMMENT '是否支付：0已支付，1未支付',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学生信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_student_info
-- ----------------------------
INSERT INTO `p_student_info` VALUES (1, '张三', '123456789009876543', '13800138000', 1, '1101', 0, 1, 1, '2018-09-09 20:38:39', 1);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, 0, '成都科技大学', 2, 1);
INSERT INTO `sys_dept` VALUES (2, 1, '一组', 1, 1);
INSERT INTO `sys_dept` VALUES (3, 0, '管理组', 1, 1);
INSERT INTO `sys_dept` VALUES (4, 0, '移动组', 3, 1);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标签名',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据值',
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述',
  `sort` decimal(10, 0) NULL DEFAULT NULL COMMENT '排序（升序）',
  `parent_id` bigint(64) NULL DEFAULT 0 COMMENT '父级编号',
  `create_by` int(64) NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(64) NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_dict_value`(`value`) USING BTREE,
  INDEX `sys_dict_label`(`name`) USING BTREE,
  INDEX `sys_dict_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 123 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, '正常', '0', 'del_flag', '删除标记', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (3, '显示', '1', 'show_hide', '显示/隐藏', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (4, '隐藏', '0', 'show_hide', '显示/隐藏', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (5, '是', '1', 'yes_no', '是/否', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (6, '否', '0', 'yes_no', '是/否', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (7, '红色', 'red', 'color', '颜色值', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (8, '绿色', 'green', 'color', '颜色值', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (9, '蓝色', 'blue', 'color', '颜色值', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (10, '黄色', 'yellow', 'color', '颜色值', 40, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (11, '橙色', 'orange', 'color', '颜色值', 50, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (12, '默认主题', 'default', 'theme', '主题方案', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (13, '天蓝主题', 'cerulean', 'theme', '主题方案', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (14, '橙色主题', 'readable', 'theme', '主题方案', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (15, '红色主题', 'united', 'theme', '主题方案', 40, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (16, 'Flat主题', 'flat', 'theme', '主题方案', 60, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (17, '国家', '1', 'sys_area_type', '区域类型', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (18, '省份、直辖市', '2', 'sys_area_type', '区域类型', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (19, '地市', '3', 'sys_area_type', '区域类型', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (20, '区县', '4', 'sys_area_type', '区域类型', 40, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (21, '公司', '1', 'sys_office_type', '机构类型', 60, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (22, '部门', '2', 'sys_office_type', '机构类型', 70, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (23, '小组', '3', 'sys_office_type', '机构类型', 80, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (24, '其它', '4', 'sys_office_type', '机构类型', 90, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (25, '综合部', '1', 'sys_office_common', '快捷通用部门', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (26, '开发部', '2', 'sys_office_common', '快捷通用部门', 40, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (27, '人力部', '3', 'sys_office_common', '快捷通用部门', 50, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (28, '一级', '1', 'sys_office_grade', '机构等级', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (29, '二级', '2', 'sys_office_grade', '机构等级', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (30, '三级', '3', 'sys_office_grade', '机构等级', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (31, '四级', '4', 'sys_office_grade', '机构等级', 40, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (32, '所有数据', '1', 'sys_data_scope', '数据范围', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (33, '所在公司及以下数据', '2', 'sys_data_scope', '数据范围', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (34, '所在公司数据', '3', 'sys_data_scope', '数据范围', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (35, '所在部门及以下数据', '4', 'sys_data_scope', '数据范围', 40, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (36, '所在部门数据', '5', 'sys_data_scope', '数据范围', 50, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (37, '仅本人数据', '8', 'sys_data_scope', '数据范围', 90, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (38, '按明细设置', '9', 'sys_data_scope', '数据范围', 100, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (39, '系统管理', '1', 'sys_user_type', '用户类型', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (40, '部门经理', '2', 'sys_user_type', '用户类型', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (41, '普通用户', '3', 'sys_user_type', '用户类型', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (42, '基础主题', 'basic', 'cms_theme', '站点主题', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (43, '蓝色主题', 'blue', 'cms_theme', '站点主题', 20, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (44, '红色主题', 'red', 'cms_theme', '站点主题', 30, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (45, '文章模型', 'article', 'cms_module', '栏目模型', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (46, '图片模型', 'picture', 'cms_module', '栏目模型', 20, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (47, '下载模型', 'download', 'cms_module', '栏目模型', 30, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (48, '链接模型', 'link', 'cms_module', '栏目模型', 40, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (49, '专题模型', 'special', 'cms_module', '栏目模型', 50, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (50, '默认展现方式', '0', 'cms_show_modes', '展现方式', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (51, '首栏目内容列表', '1', 'cms_show_modes', '展现方式', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (52, '栏目第一条内容', '2', 'cms_show_modes', '展现方式', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (53, '发布', '0', 'cms_del_flag', '内容状态', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (54, '删除', '1', 'cms_del_flag', '内容状态', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (55, '审核', '2', 'cms_del_flag', '内容状态', 15, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (56, '首页焦点图', '1', 'cms_posid', '推荐位', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (57, '栏目页文章推荐', '2', 'cms_posid', '推荐位', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (58, '咨询', '1', 'cms_guestbook', '留言板分类', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (59, '建议', '2', 'cms_guestbook', '留言板分类', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (60, '投诉', '3', 'cms_guestbook', '留言板分类', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (61, '其它', '4', 'cms_guestbook', '留言板分类', 40, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (62, '公休', '1', 'oa_leave_type', '请假类型', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (63, '病假', '2', 'oa_leave_type', '请假类型', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (64, '事假', '3', 'oa_leave_type', '请假类型', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (65, '调休', '4', 'oa_leave_type', '请假类型', 40, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (66, '婚假', '5', 'oa_leave_type', '请假类型', 60, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (67, '接入日志', '1', 'sys_log_type', '日志类型', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (68, '异常日志', '2', 'sys_log_type', '日志类型', 40, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (69, '请假流程', 'leave', 'act_type', '流程类型', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (70, '审批测试流程', 'test_audit', 'act_type', '流程类型', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (71, '分类1', '1', 'act_category', '流程分类', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (72, '分类2', '2', 'act_category', '流程分类', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (73, '增删改查', 'crud', 'gen_category', '代码生成分类', 10, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (74, '增删改查（包含从表）', 'crud_many', 'gen_category', '代码生成分类', 20, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (75, '树结构', 'tree', 'gen_category', '代码生成分类', 30, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (76, '=', '=', 'gen_query_type', '查询方式', 10, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (77, '!=', '!=', 'gen_query_type', '查询方式', 20, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (78, '&gt;', '&gt;', 'gen_query_type', '查询方式', 30, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (79, '&lt;', '&lt;', 'gen_query_type', '查询方式', 40, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (80, 'Between', 'between', 'gen_query_type', '查询方式', 50, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (81, 'Like', 'like', 'gen_query_type', '查询方式', 60, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (82, 'Left Like', 'left_like', 'gen_query_type', '查询方式', 70, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (83, 'Right Like', 'right_like', 'gen_query_type', '查询方式', 80, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (84, '文本框', 'input', 'gen_show_type', '字段生成方案', 10, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (85, '文本域', 'textarea', 'gen_show_type', '字段生成方案', 20, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (86, '下拉框', 'select', 'gen_show_type', '字段生成方案', 30, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (87, '复选框', 'checkbox', 'gen_show_type', '字段生成方案', 40, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (88, '单选框', 'radiobox', 'gen_show_type', '字段生成方案', 50, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (89, '日期选择', 'dateselect', 'gen_show_type', '字段生成方案', 60, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (90, '人员选择', 'userselect', 'gen_show_type', '字段生成方案', 70, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (91, '部门选择', 'officeselect', 'gen_show_type', '字段生成方案', 80, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (92, '区域选择', 'areaselect', 'gen_show_type', '字段生成方案', 90, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (93, 'String', 'String', 'gen_java_type', 'Java类型', 10, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (94, 'Long', 'Long', 'gen_java_type', 'Java类型', 20, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (95, '仅持久层', 'dao', 'gen_category', '代码生成分类', 40, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (96, '男', '1', 'sex', '性别', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (97, '女', '2', 'sex', '性别', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (98, 'Integer', 'Integer', 'gen_java_type', 'Java类型', 30, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (99, 'Double', 'Double', 'gen_java_type', 'Java类型', 40, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (100, 'Date', 'java.util.Date', 'gen_java_type', 'Java类型', 50, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (104, 'Custom', 'Custom', 'gen_java_type', 'Java类型', 90, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (105, '会议通告', '1', 'oa_notify_type', '通知通告类型', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (106, '奖惩通告', '2', 'oa_notify_type', '通知通告类型', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (107, '活动通告', '3', 'oa_notify_type', '通知通告类型', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (108, '草稿', '0', 'oa_notify_status', '通知通告状态', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (109, '发布', '1', 'oa_notify_status', '通知通告状态', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (110, '未读', '0', 'oa_notify_read', '通知通告状态', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (111, '已读', '1', 'oa_notify_read', '通知通告状态', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (112, '草稿', '0', 'oa_notify_status', '通知通告状态', 10, 0, 1, NULL, 1, NULL, '', '0');
INSERT INTO `sys_dict` VALUES (113, '删除', '0', 'del_flag', '删除标记', NULL, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `sys_dict` VALUES (118, '关于', 'about', 'blog_type', '博客类型', NULL, NULL, NULL, NULL, NULL, NULL, '全url是:/blog/open/page/about', '');
INSERT INTO `sys_dict` VALUES (119, '交流', 'communication', 'blog_type', '博客类型', NULL, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `sys_dict` VALUES (120, '文章', 'article', 'blog_type', '博客类型', NULL, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `sys_dict` VALUES (121, '编码', 'code', 'hobby', '爱好', NULL, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `sys_dict` VALUES (122, '绘画', 'painting', 'hobby', '爱好', NULL, NULL, NULL, NULL, NULL, NULL, '', '');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) NULL DEFAULT NULL COMMENT '文件类型',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件上传' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户操作',
  `time` int(11) NULL DEFAULT NULL COMMENT '响应时间',
  `method` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 240 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, 1, 'admin', '登录', 4, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 19:33:06');
INSERT INTO `sys_log` VALUES (2, 1, 'admin', '请求访问主页', 11, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 19:33:06');
INSERT INTO `sys_log` VALUES (3, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/message', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select DISTINCT   n.id ,`type`,`title`,`content`,`files`,r.is_read,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify_record r right JOIN oa_notify n on r.notify_id = n.id    WHERE  r.is_read = ?      and r.user_id = ?    order by is_read ASC, update_date DESC        limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist', NULL, '2018-09-09 19:33:07');
INSERT INTO `sys_log` VALUES (4, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/selfList', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select DISTINCT   n.id ,`type`,`title`,`content`,`files`,r.is_read,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify_record r right JOIN oa_notify n on r.notify_id = n.id    WHERE  r.user_id = ?    order by is_read ASC, update_date DESC        limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist', NULL, '2018-09-09 19:33:24');
INSERT INTO `sys_log` VALUES (5, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/list', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select   `id`,`type`,`title`,`content`,`files`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify              order by id desc             limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify\' doesn\'t exist', NULL, '2018-09-09 19:33:24');
INSERT INTO `sys_log` VALUES (6, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/selfList', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select DISTINCT   n.id ,`type`,`title`,`content`,`files`,r.is_read,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify_record r right JOIN oa_notify n on r.notify_id = n.id    WHERE  r.user_id = ?    order by is_read ASC, update_date DESC        limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist', NULL, '2018-09-09 19:33:41');
INSERT INTO `sys_log` VALUES (7, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/list', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select   `id`,`type`,`title`,`content`,`files`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify              order by id desc             limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify\' doesn\'t exist', NULL, '2018-09-09 19:33:41');
INSERT INTO `sys_log` VALUES (8, 1, 'admin', '编辑用户', 11, 'com.admin.system.controller.UserController.edit()', NULL, '127.0.0.1', '2018-09-09 19:34:08');
INSERT INTO `sys_log` VALUES (9, 1, 'admin', '请求访问主页', 6, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 19:35:44');
INSERT INTO `sys_log` VALUES (10, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/message', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select DISTINCT   n.id ,`type`,`title`,`content`,`files`,r.is_read,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify_record r right JOIN oa_notify n on r.notify_id = n.id    WHERE  r.is_read = ?      and r.user_id = ?    order by is_read ASC, update_date DESC        limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist', NULL, '2018-09-09 19:35:44');
INSERT INTO `sys_log` VALUES (11, 1, 'admin', '登录', 4, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 19:37:18');
INSERT INTO `sys_log` VALUES (12, 1, 'admin', '请求访问主页', 7, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 19:37:18');
INSERT INTO `sys_log` VALUES (13, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/message', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select DISTINCT   n.id ,`type`,`title`,`content`,`files`,r.is_read,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify_record r right JOIN oa_notify n on r.notify_id = n.id    WHERE  r.is_read = ?      and r.user_id = ?    order by is_read ASC, update_date DESC        limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist', NULL, '2018-09-09 19:37:18');
INSERT INTO `sys_log` VALUES (14, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/list', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select   `id`,`type`,`title`,`content`,`files`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify              order by id desc             limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify\' doesn\'t exist', NULL, '2018-09-09 19:37:20');
INSERT INTO `sys_log` VALUES (15, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/selfList', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select DISTINCT   n.id ,`type`,`title`,`content`,`files`,r.is_read,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify_record r right JOIN oa_notify n on r.notify_id = n.id    WHERE  r.user_id = ?    order by is_read ASC, update_date DESC        limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist', NULL, '2018-09-09 19:37:20');
INSERT INTO `sys_log` VALUES (16, 1, 'admin', '编辑用户', 10, 'com.admin.system.controller.UserController.edit()', NULL, '127.0.0.1', '2018-09-09 19:37:33');
INSERT INTO `sys_log` VALUES (17, 1, 'admin', '编辑角色', 3, 'com.admin.system.controller.RoleController.edit()', NULL, '127.0.0.1', '2018-09-09 19:37:38');
INSERT INTO `sys_log` VALUES (18, 1, 'admin', '登录', 3, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 19:40:09');
INSERT INTO `sys_log` VALUES (19, 1, 'admin', '请求访问主页', 8, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 19:40:09');
INSERT INTO `sys_log` VALUES (20, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/message', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select DISTINCT   n.id ,`type`,`title`,`content`,`files`,r.is_read,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify_record r right JOIN oa_notify n on r.notify_id = n.id    WHERE  r.is_read = ?      and r.user_id = ?    order by is_read ASC, update_date DESC        limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist', NULL, '2018-09-09 19:40:09');
INSERT INTO `sys_log` VALUES (21, 1, 'admin', '编辑角色', 2, 'com.admin.system.controller.RoleController.edit()', NULL, '127.0.0.1', '2018-09-09 19:40:16');
INSERT INTO `sys_log` VALUES (22, 1, 'admin', '编辑角色', 4, 'com.admin.system.controller.RoleController.edit()', NULL, '127.0.0.1', '2018-09-09 19:41:45');
INSERT INTO `sys_log` VALUES (23, 1, 'admin', '登录', 5, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 19:47:48');
INSERT INTO `sys_log` VALUES (24, 1, 'admin', '请求访问主页', 9, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 19:47:48');
INSERT INTO `sys_log` VALUES (25, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/message', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select DISTINCT   n.id ,`type`,`title`,`content`,`files`,r.is_read,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify_record r right JOIN oa_notify n on r.notify_id = n.id    WHERE  r.is_read = ?      and r.user_id = ?    order by is_read ASC, update_date DESC        limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist', NULL, '2018-09-09 19:47:48');
INSERT INTO `sys_log` VALUES (26, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/selfList', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select DISTINCT   n.id ,`type`,`title`,`content`,`files`,r.is_read,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify_record r right JOIN oa_notify n on r.notify_id = n.id    WHERE  r.user_id = ?    order by is_read ASC, update_date DESC        limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist', NULL, '2018-09-09 19:48:04');
INSERT INTO `sys_log` VALUES (27, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/list', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select   `id`,`type`,`title`,`content`,`files`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify              order by id desc             limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify\' doesn\'t exist', NULL, '2018-09-09 19:48:04');
INSERT INTO `sys_log` VALUES (28, 1, 'admin', '请求访问主页', 6, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 19:51:22');
INSERT INTO `sys_log` VALUES (29, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/message', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select DISTINCT   n.id ,`type`,`title`,`content`,`files`,r.is_read,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify_record r right JOIN oa_notify n on r.notify_id = n.id    WHERE  r.is_read = ?      and r.user_id = ?    order by is_read ASC, update_date DESC        limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist', NULL, '2018-09-09 19:51:22');
INSERT INTO `sys_log` VALUES (30, 1, 'admin', '登录', 18, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 19:57:23');
INSERT INTO `sys_log` VALUES (31, 1, 'admin', '请求访问主页', 62, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 19:57:23');
INSERT INTO `sys_log` VALUES (32, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/message', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select DISTINCT   n.id ,`type`,`title`,`content`,`files`,r.is_read,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify_record r right JOIN oa_notify n on r.notify_id = n.id    WHERE  r.is_read = ?      and r.user_id = ?    order by is_read ASC, update_date DESC        limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist', NULL, '2018-09-09 19:57:24');
INSERT INTO `sys_log` VALUES (33, 1, 'admin', '添加菜单', 0, 'com.admin.system.controller.MenuController.add()', NULL, '127.0.0.1', '2018-09-09 19:57:35');
INSERT INTO `sys_log` VALUES (34, 1, 'admin', '保存菜单', 7, 'com.admin.system.controller.MenuController.save()', NULL, '127.0.0.1', '2018-09-09 19:59:32');
INSERT INTO `sys_log` VALUES (35, 1, 'admin', '添加菜单', 12, 'com.admin.system.controller.MenuController.add()', NULL, '127.0.0.1', '2018-09-09 19:59:37');
INSERT INTO `sys_log` VALUES (36, 1, 'admin', '保存菜单', 6, 'com.admin.system.controller.MenuController.save()', NULL, '127.0.0.1', '2018-09-09 20:01:34');
INSERT INTO `sys_log` VALUES (37, 1, 'admin', '编辑角色', 8, 'com.admin.system.controller.RoleController.edit()', NULL, '127.0.0.1', '2018-09-09 20:02:04');
INSERT INTO `sys_log` VALUES (38, 1, 'admin', '更新角色', 42, 'com.admin.system.controller.RoleController.update()', NULL, '127.0.0.1', '2018-09-09 20:02:09');
INSERT INTO `sys_log` VALUES (39, 1, 'admin', '登录', 5, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 20:02:11');
INSERT INTO `sys_log` VALUES (40, 1, 'admin', '请求访问主页', 7, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 20:02:11');
INSERT INTO `sys_log` VALUES (41, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/message', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select DISTINCT   n.id ,`type`,`title`,`content`,`files`,r.is_read,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify_record r right JOIN oa_notify n on r.notify_id = n.id    WHERE  r.is_read = ?      and r.user_id = ?    order by is_read ASC, update_date DESC        limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist', NULL, '2018-09-09 20:02:11');
INSERT INTO `sys_log` VALUES (42, 1, 'admin', '编辑菜单', 11, 'com.admin.system.controller.MenuController.edit()', NULL, '127.0.0.1', '2018-09-09 20:02:30');
INSERT INTO `sys_log` VALUES (43, 1, 'admin', '更新菜单', 10, 'com.admin.system.controller.MenuController.update()', NULL, '127.0.0.1', '2018-09-09 20:02:50');
INSERT INTO `sys_log` VALUES (44, 1, 'admin', '登录', 5, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 20:02:52');
INSERT INTO `sys_log` VALUES (45, 1, 'admin', '请求访问主页', 6, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 20:02:52');
INSERT INTO `sys_log` VALUES (46, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/message', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select DISTINCT   n.id ,`type`,`title`,`content`,`files`,r.is_read,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify_record r right JOIN oa_notify n on r.notify_id = n.id    WHERE  r.is_read = ?      and r.user_id = ?    order by is_read ASC, update_date DESC        limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist', NULL, '2018-09-09 20:02:52');
INSERT INTO `sys_log` VALUES (47, 1, 'admin', '添加菜单', 5, 'com.admin.system.controller.MenuController.add()', NULL, '127.0.0.1', '2018-09-09 20:03:37');
INSERT INTO `sys_log` VALUES (48, 1, 'admin', '保存菜单', 7, 'com.admin.system.controller.MenuController.save()', NULL, '127.0.0.1', '2018-09-09 20:04:47');
INSERT INTO `sys_log` VALUES (49, 1, 'admin', '添加菜单', 4, 'com.admin.system.controller.MenuController.add()', NULL, '127.0.0.1', '2018-09-09 20:04:56');
INSERT INTO `sys_log` VALUES (50, 1, 'admin', '保存菜单', 6, 'com.admin.system.controller.MenuController.save()', NULL, '127.0.0.1', '2018-09-09 20:05:47');
INSERT INTO `sys_log` VALUES (51, 1, 'admin', '添加菜单', 5, 'com.admin.system.controller.MenuController.add()', NULL, '127.0.0.1', '2018-09-09 20:05:54');
INSERT INTO `sys_log` VALUES (52, 1, 'admin', '保存菜单', 4, 'com.admin.system.controller.MenuController.save()', NULL, '127.0.0.1', '2018-09-09 20:06:58');
INSERT INTO `sys_log` VALUES (53, 1, 'admin', '编辑菜单', 9, 'com.admin.system.controller.MenuController.edit()', NULL, '127.0.0.1', '2018-09-09 20:07:07');
INSERT INTO `sys_log` VALUES (54, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/list', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select   `id`,`type`,`title`,`content`,`files`,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify              order by id desc             limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify\' doesn\'t exist', NULL, '2018-09-09 20:07:16');
INSERT INTO `sys_log` VALUES (55, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/selfList', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select DISTINCT   n.id ,`type`,`title`,`content`,`files`,r.is_read,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify_record r right JOIN oa_notify n on r.notify_id = n.id    WHERE  r.user_id = ?    order by is_read ASC, update_date DESC        limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist', NULL, '2018-09-09 20:07:17');
INSERT INTO `sys_log` VALUES (56, 1, 'admin', '编辑菜单', 10, 'com.admin.system.controller.MenuController.edit()', NULL, '127.0.0.1', '2018-09-09 20:07:32');
INSERT INTO `sys_log` VALUES (57, 1, 'admin', '更新菜单', 5, 'com.admin.system.controller.MenuController.update()', NULL, '127.0.0.1', '2018-09-09 20:07:37');
INSERT INTO `sys_log` VALUES (58, 1, 'admin', '添加菜单', 4, 'com.admin.system.controller.MenuController.add()', NULL, '127.0.0.1', '2018-09-09 20:07:45');
INSERT INTO `sys_log` VALUES (59, 1, 'admin', '保存菜单', 5, 'com.admin.system.controller.MenuController.save()', NULL, '127.0.0.1', '2018-09-09 20:08:50');
INSERT INTO `sys_log` VALUES (60, 1, 'admin', '添加菜单', 6, 'com.admin.system.controller.MenuController.add()', NULL, '127.0.0.1', '2018-09-09 20:09:05');
INSERT INTO `sys_log` VALUES (61, 1, 'admin', '保存菜单', 5, 'com.admin.system.controller.MenuController.save()', NULL, '127.0.0.1', '2018-09-09 20:10:09');
INSERT INTO `sys_log` VALUES (62, 1, 'admin', '编辑角色', 3, 'com.admin.system.controller.RoleController.edit()', NULL, '127.0.0.1', '2018-09-09 20:10:18');
INSERT INTO `sys_log` VALUES (63, 1, 'admin', '更新角色', 16, 'com.admin.system.controller.RoleController.update()', NULL, '127.0.0.1', '2018-09-09 20:10:25');
INSERT INTO `sys_log` VALUES (64, 1, 'admin', '登录', 5, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 20:10:31');
INSERT INTO `sys_log` VALUES (65, 1, 'admin', '请求访问主页', 10, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 20:10:31');
INSERT INTO `sys_log` VALUES (66, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/message', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select DISTINCT   n.id ,`type`,`title`,`content`,`files`,r.is_read,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify_record r right JOIN oa_notify n on r.notify_id = n.id    WHERE  r.is_read = ?      and r.user_id = ?    order by is_read ASC, update_date DESC        limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist', NULL, '2018-09-09 20:10:31');
INSERT INTO `sys_log` VALUES (67, 1, 'admin', '编辑用户', 15, 'com.admin.system.controller.UserController.edit()', NULL, '127.0.0.1', '2018-09-09 20:30:16');
INSERT INTO `sys_log` VALUES (68, 1, 'admin', '更新用户', 15, 'com.admin.system.controller.UserController.update()', NULL, '127.0.0.1', '2018-09-09 20:30:23');
INSERT INTO `sys_log` VALUES (69, 1, 'admin', '编辑用户', 10, 'com.admin.system.controller.UserController.edit()', NULL, '127.0.0.1', '2018-09-09 20:30:25');
INSERT INTO `sys_log` VALUES (70, 1, 'admin', '添加用户', 3, 'com.admin.system.controller.UserController.add()', NULL, '127.0.0.1', '2018-09-09 20:32:09');
INSERT INTO `sys_log` VALUES (71, 1, 'admin', '添加角色', 0, 'com.admin.system.controller.RoleController.add()', NULL, '127.0.0.1', '2018-09-09 20:32:30');
INSERT INTO `sys_log` VALUES (72, 1, 'admin', '保存角色', 8, 'com.admin.system.controller.RoleController.save()', NULL, '127.0.0.1', '2018-09-09 20:33:04');
INSERT INTO `sys_log` VALUES (73, 1, 'admin', '添加角色', 0, 'com.admin.system.controller.RoleController.add()', NULL, '127.0.0.1', '2018-09-09 20:33:05');
INSERT INTO `sys_log` VALUES (74, 1, 'admin', '保存角色', 10, 'com.admin.system.controller.RoleController.save()', NULL, '127.0.0.1', '2018-09-09 20:33:17');
INSERT INTO `sys_log` VALUES (75, 1, 'admin', '编辑角色', 4, 'com.admin.system.controller.RoleController.edit()', NULL, '127.0.0.1', '2018-09-09 20:33:25');
INSERT INTO `sys_log` VALUES (76, 1, 'admin', '更新角色', 14, 'com.admin.system.controller.RoleController.update()', NULL, '127.0.0.1', '2018-09-09 20:33:29');
INSERT INTO `sys_log` VALUES (77, 1, 'admin', '添加角色', 0, 'com.admin.system.controller.RoleController.add()', NULL, '127.0.0.1', '2018-09-09 20:33:31');
INSERT INTO `sys_log` VALUES (78, 1, 'admin', '保存角色', 10, 'com.admin.system.controller.RoleController.save()', NULL, '127.0.0.1', '2018-09-09 20:33:45');
INSERT INTO `sys_log` VALUES (79, 1, 'admin', '添加菜单', 6, 'com.admin.system.controller.MenuController.add()', NULL, '127.0.0.1', '2018-09-09 20:35:04');
INSERT INTO `sys_log` VALUES (80, 1, 'admin', '保存菜单', 7, 'com.admin.system.controller.MenuController.save()', NULL, '127.0.0.1', '2018-09-09 20:36:17');
INSERT INTO `sys_log` VALUES (81, 1, 'admin', '编辑角色', 3, 'com.admin.system.controller.RoleController.edit()', NULL, '127.0.0.1', '2018-09-09 20:36:40');
INSERT INTO `sys_log` VALUES (82, 1, 'admin', '更新角色', 14, 'com.admin.system.controller.RoleController.update()', NULL, '127.0.0.1', '2018-09-09 20:36:43');
INSERT INTO `sys_log` VALUES (83, 1, 'admin', '登录', 5, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 20:36:50');
INSERT INTO `sys_log` VALUES (84, 1, 'admin', '请求访问主页', 9, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 20:36:50');
INSERT INTO `sys_log` VALUES (85, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/message', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select DISTINCT   n.id ,`type`,`title`,`content`,`files`,r.is_read,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify_record r right JOIN oa_notify n on r.notify_id = n.id    WHERE  r.is_read = ?      and r.user_id = ?    order by is_read ASC, update_date DESC        limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist', NULL, '2018-09-09 20:36:51');
INSERT INTO `sys_log` VALUES (86, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/selfList', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select DISTINCT   n.id ,`type`,`title`,`content`,`files`,r.is_read,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify_record r right JOIN oa_notify n on r.notify_id = n.id    WHERE  r.user_id = ?    order by is_read ASC, update_date DESC        limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist', NULL, '2018-09-09 20:40:18');
INSERT INTO `sys_log` VALUES (87, 1, 'admin', '登录', 5, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 20:49:28');
INSERT INTO `sys_log` VALUES (88, 1, 'admin', '请求访问主页', 10, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 20:49:28');
INSERT INTO `sys_log` VALUES (89, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/message', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select DISTINCT   n.id ,`type`,`title`,`content`,`files`,r.is_read,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify_record r right JOIN oa_notify n on r.notify_id = n.id    WHERE  r.is_read = ?      and r.user_id = ?    order by is_read ASC, update_date DESC        limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist', NULL, '2018-09-09 20:49:28');
INSERT INTO `sys_log` VALUES (90, 1, 'admin', '编辑角色', 2, 'com.admin.system.controller.RoleController.edit()', NULL, '127.0.0.1', '2018-09-09 20:49:44');
INSERT INTO `sys_log` VALUES (91, 1, 'admin', '登录', 4, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 20:54:57');
INSERT INTO `sys_log` VALUES (92, 1, 'admin', '请求访问主页', 6, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 20:54:58');
INSERT INTO `sys_log` VALUES (93, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/message', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select DISTINCT   n.id ,`type`,`title`,`content`,`files`,r.is_read,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify_record r right JOIN oa_notify n on r.notify_id = n.id    WHERE  r.is_read = ?      and r.user_id = ?    order by is_read ASC, update_date DESC        limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist', NULL, '2018-09-09 20:54:58');
INSERT INTO `sys_log` VALUES (94, 1, 'admin', '编辑用户', 14, 'com.admin.system.controller.UserController.edit()', NULL, '127.0.0.1', '2018-09-09 20:56:56');
INSERT INTO `sys_log` VALUES (95, 1, 'admin', '请求访问主页', 9, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:07:22');
INSERT INTO `sys_log` VALUES (96, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/message', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select DISTINCT   n.id ,`type`,`title`,`content`,`files`,r.is_read,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify_record r right JOIN oa_notify n on r.notify_id = n.id    WHERE  r.is_read = ?      and r.user_id = ?    order by is_read ASC, update_date DESC        limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist', NULL, '2018-09-09 21:07:22');
INSERT INTO `sys_log` VALUES (97, 1, 'admin', '请求访问主页', 8, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:08:12');
INSERT INTO `sys_log` VALUES (98, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/message', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select DISTINCT   n.id ,`type`,`title`,`content`,`files`,r.is_read,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify_record r right JOIN oa_notify n on r.notify_id = n.id    WHERE  r.is_read = ?      and r.user_id = ?    order by is_read ASC, update_date DESC        limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist', NULL, '2018-09-09 21:08:12');
INSERT INTO `sys_log` VALUES (99, 1, 'admin', '请求访问主页', 8, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:08:14');
INSERT INTO `sys_log` VALUES (100, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/message', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select DISTINCT   n.id ,`type`,`title`,`content`,`files`,r.is_read,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify_record r right JOIN oa_notify n on r.notify_id = n.id    WHERE  r.is_read = ?      and r.user_id = ?    order by is_read ASC, update_date DESC        limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist', NULL, '2018-09-09 21:08:14');
INSERT INTO `sys_log` VALUES (101, 1, 'admin', '登录', 16, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 21:08:56');
INSERT INTO `sys_log` VALUES (102, 1, 'admin', '请求访问主页', 62, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:08:56');
INSERT INTO `sys_log` VALUES (103, 1, 'admin', 'error', NULL, 'http://localhost:8088/oa/notify/message', 'org.springframework.jdbc.BadSqlGrammarException: \r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\r\n### The error may exist in file [D:\\Workspace\\idea\\package\\admin\\admin-web\\target\\classes\\mybatis\\system\\NotifyMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select DISTINCT   n.id ,`type`,`title`,`content`,`files`,r.is_read,`status`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`   from oa_notify_record r right JOIN oa_notify n on r.notify_id = n.id    WHERE  r.is_read = ?      and r.user_id = ?    order by is_read ASC, update_date DESC        limit ?, ?\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'test.oa_notify_record\' doesn\'t exist', NULL, '2018-09-09 21:08:56');
INSERT INTO `sys_log` VALUES (104, 1, 'admin', '登录', 11, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 21:12:02');
INSERT INTO `sys_log` VALUES (105, 1, 'admin', '请求访问主页', 38, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:12:02');
INSERT INTO `sys_log` VALUES (106, 1, 'admin', '编辑角色', 5, 'com.admin.system.controller.RoleController.edit()', NULL, '127.0.0.1', '2018-09-09 21:12:30');
INSERT INTO `sys_log` VALUES (107, 1, 'admin', '编辑菜单', 15, 'com.admin.system.controller.MenuController.edit()', NULL, '127.0.0.1', '2018-09-09 21:14:22');
INSERT INTO `sys_log` VALUES (108, 1, 'admin', '更新菜单', 15, 'com.admin.system.controller.MenuController.update()', NULL, '127.0.0.1', '2018-09-09 21:14:29');
INSERT INTO `sys_log` VALUES (109, 1, 'admin', '登录', 4, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 21:14:45');
INSERT INTO `sys_log` VALUES (110, 1, 'admin', '请求访问主页', 9, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:14:45');
INSERT INTO `sys_log` VALUES (111, 1, 'admin', '登录', 16, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 21:15:10');
INSERT INTO `sys_log` VALUES (112, 1, 'admin', '请求访问主页', 48, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:15:10');
INSERT INTO `sys_log` VALUES (113, 1, 'admin', '请求访问主页', 12, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:16:12');
INSERT INTO `sys_log` VALUES (114, 1, 'admin', '请求访问主页', 10, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:16:18');
INSERT INTO `sys_log` VALUES (115, 1, 'admin', '添加菜单', 8, 'com.admin.system.controller.MenuController.add()', NULL, '127.0.0.1', '2018-09-09 21:16:51');
INSERT INTO `sys_log` VALUES (116, 1, 'admin', '保存菜单', 16, 'com.admin.system.controller.MenuController.save()', NULL, '127.0.0.1', '2018-09-09 21:18:21');
INSERT INTO `sys_log` VALUES (117, 1, 'admin', '编辑角色', 4, 'com.admin.system.controller.RoleController.edit()', NULL, '127.0.0.1', '2018-09-09 21:18:30');
INSERT INTO `sys_log` VALUES (118, 1, 'admin', '更新角色', 35, 'com.admin.system.controller.RoleController.update()', NULL, '127.0.0.1', '2018-09-09 21:18:35');
INSERT INTO `sys_log` VALUES (119, 1, 'admin', '登录', 4, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 21:18:37');
INSERT INTO `sys_log` VALUES (120, 1, 'admin', '请求访问主页', 12, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:18:37');
INSERT INTO `sys_log` VALUES (121, 1, 'admin', '请求访问主页', 8, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:23:21');
INSERT INTO `sys_log` VALUES (122, 1, 'admin', '登录', 10, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 21:23:46');
INSERT INTO `sys_log` VALUES (123, 1, 'admin', '请求访问主页', 46, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:23:46');
INSERT INTO `sys_log` VALUES (124, 1, 'admin', '登录', 15, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 21:27:39');
INSERT INTO `sys_log` VALUES (125, 1, 'admin', '请求访问主页', 59, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:27:39');
INSERT INTO `sys_log` VALUES (126, 1, 'admin', '请求访问主页', 18, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:28:02');
INSERT INTO `sys_log` VALUES (127, 1, 'admin', '登录', 10, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 21:28:37');
INSERT INTO `sys_log` VALUES (128, 1, 'admin', '请求访问主页', 40, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:28:37');
INSERT INTO `sys_log` VALUES (129, 1, 'admin', '请求访问主页', 17, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:29:06');
INSERT INTO `sys_log` VALUES (130, 1, 'admin', '登录', 12, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 21:30:08');
INSERT INTO `sys_log` VALUES (131, 1, 'admin', '请求访问主页', 45, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:30:09');
INSERT INTO `sys_log` VALUES (132, 1, 'admin', '登录', 14, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 21:30:55');
INSERT INTO `sys_log` VALUES (133, 1, 'admin', '请求访问主页', 44, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:30:55');
INSERT INTO `sys_log` VALUES (134, 1, 'admin', '登录', 12, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 21:33:29');
INSERT INTO `sys_log` VALUES (135, 1, 'admin', '请求访问主页', 37, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:33:29');
INSERT INTO `sys_log` VALUES (136, 1, 'admin', '登录', 13, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 21:34:19');
INSERT INTO `sys_log` VALUES (137, 1, 'admin', '请求访问主页', 47, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:34:19');
INSERT INTO `sys_log` VALUES (138, 1, 'admin', '登录', 10, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 21:37:09');
INSERT INTO `sys_log` VALUES (139, 1, 'admin', '请求访问主页', 39, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:37:09');
INSERT INTO `sys_log` VALUES (140, 1, 'admin', '登录', 12, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 21:38:21');
INSERT INTO `sys_log` VALUES (141, 1, 'admin', '请求访问主页', 51, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:38:21');
INSERT INTO `sys_log` VALUES (142, 1, 'admin', '登录', 11, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 21:39:46');
INSERT INTO `sys_log` VALUES (143, 1, 'admin', '请求访问主页', 37, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:39:46');
INSERT INTO `sys_log` VALUES (144, 1, 'admin', '登录', 16, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 21:41:17');
INSERT INTO `sys_log` VALUES (145, 1, 'admin', '请求访问主页', 45, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:41:17');
INSERT INTO `sys_log` VALUES (146, 1, 'admin', '登录', 12, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 21:42:02');
INSERT INTO `sys_log` VALUES (147, 1, 'admin', '请求访问主页', 40, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:42:02');
INSERT INTO `sys_log` VALUES (148, 1, 'admin', '添加菜单', 9, 'com.admin.system.controller.MenuController.add()', NULL, '127.0.0.1', '2018-09-09 21:45:50');
INSERT INTO `sys_log` VALUES (149, 1, 'admin', '保存菜单', 7, 'com.admin.system.controller.MenuController.save()', NULL, '127.0.0.1', '2018-09-09 21:47:04');
INSERT INTO `sys_log` VALUES (150, 1, 'admin', '编辑角色', 5, 'com.admin.system.controller.RoleController.edit()', NULL, '127.0.0.1', '2018-09-09 21:47:09');
INSERT INTO `sys_log` VALUES (151, 1, 'admin', '更新角色', 38, 'com.admin.system.controller.RoleController.update()', NULL, '127.0.0.1', '2018-09-09 21:47:13');
INSERT INTO `sys_log` VALUES (152, 1, 'admin', '登录', 6, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 21:47:17');
INSERT INTO `sys_log` VALUES (153, 1, 'admin', '请求访问主页', 12, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:47:17');
INSERT INTO `sys_log` VALUES (154, 1, 'admin', '登录', 14, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 21:49:06');
INSERT INTO `sys_log` VALUES (155, 1, 'admin', '请求访问主页', 61, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:49:06');
INSERT INTO `sys_log` VALUES (156, 1, 'admin', '请求访问主页', 14, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:49:19');
INSERT INTO `sys_log` VALUES (157, 1, 'admin', '请求访问主页', 10, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:49:23');
INSERT INTO `sys_log` VALUES (158, 1, 'admin', '登录', 12, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 21:50:31');
INSERT INTO `sys_log` VALUES (159, 1, 'admin', '请求访问主页', 39, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:50:31');
INSERT INTO `sys_log` VALUES (160, 1, 'admin', '请求访问主页', 11, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:51:06');
INSERT INTO `sys_log` VALUES (161, 1, 'admin', '登录', 14, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 21:51:36');
INSERT INTO `sys_log` VALUES (162, 1, 'admin', '请求访问主页', 45, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:51:36');
INSERT INTO `sys_log` VALUES (163, 1, 'admin', '登录', 12, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 21:57:03');
INSERT INTO `sys_log` VALUES (164, 1, 'admin', '请求访问主页', 45, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:57:03');
INSERT INTO `sys_log` VALUES (165, 1, 'admin', '登录', 13, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 21:59:53');
INSERT INTO `sys_log` VALUES (166, 1, 'admin', '请求访问主页', 45, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 21:59:53');
INSERT INTO `sys_log` VALUES (167, 1, 'admin', 'error', NULL, 'http://localhost:8088/pack/studentInfo/update', 'org.springframework.jdbc.UncategorizedSQLException: \r\n### Error updating database.  Cause: java.sql.SQLException: sql injection violation, syntax error: ERROR. token : AND, pos : 33 : update p_student_info \n		 SET and `is_pay` = ? \n		where id = ?\r\n### SQL: update p_student_info     SET and `is_pay` = ?    where id = ?\r\n### Cause: java.sql.SQLException: sql injection violation, syntax error: ERROR. token : AND, pos : 33 : update p_student_info \n		 SET and `is_pay` = ? \n		where id = ?\n; uncategorized SQLException for SQL []; SQL state [null]; error code [0]; sql injection violation, syntax error: ERROR. token : AND, pos : 33 : update p_student_info \n		 SET and `is_pay` = ? \n		where id = ?; nested exception is java.sql.SQLException: sql injection violation, syntax error: ERROR. token : AND, pos : 33 : update p_student_info \n		 SET and `is_pay` = ? \n		where id = ?', NULL, '2018-09-09 22:00:18');
INSERT INTO `sys_log` VALUES (168, 1, 'admin', '登录', 12, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-09 22:02:35');
INSERT INTO `sys_log` VALUES (169, 1, 'admin', '请求访问主页', 38, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-09 22:02:35');
INSERT INTO `sys_log` VALUES (170, 1, 'admin', '登录', 11, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-10 09:20:37');
INSERT INTO `sys_log` VALUES (171, 1, 'admin', '请求访问主页', 33, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-10 09:20:37');
INSERT INTO `sys_log` VALUES (172, 1, 'admin', '编辑菜单', 14, 'com.admin.system.controller.MenuController.edit()', NULL, '127.0.0.1', '2018-09-10 09:21:23');
INSERT INTO `sys_log` VALUES (173, 1, 'admin', '更新菜单', 9, 'com.admin.system.controller.MenuController.update()', NULL, '127.0.0.1', '2018-09-10 09:21:30');
INSERT INTO `sys_log` VALUES (174, 1, 'admin', '登录', 5, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-10 09:21:33');
INSERT INTO `sys_log` VALUES (175, 1, 'admin', '请求访问主页', 16, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-10 09:21:33');
INSERT INTO `sys_log` VALUES (176, 1, 'admin', '编辑菜单', 10, 'com.admin.system.controller.MenuController.edit()', NULL, '127.0.0.1', '2018-09-10 09:22:14');
INSERT INTO `sys_log` VALUES (177, 1, 'admin', '更新菜单', 4, 'com.admin.system.controller.MenuController.update()', NULL, '127.0.0.1', '2018-09-10 09:22:32');
INSERT INTO `sys_log` VALUES (178, 1, 'admin', '登录', 4, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-10 09:22:35');
INSERT INTO `sys_log` VALUES (179, 1, 'admin', '请求访问主页', 9, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-10 09:22:35');
INSERT INTO `sys_log` VALUES (180, 1, 'admin', '请求访问主页', 6, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-10 09:27:26');
INSERT INTO `sys_log` VALUES (181, 1, 'admin', '登录', 8, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-10 09:54:44');
INSERT INTO `sys_log` VALUES (182, 1, 'admin', '请求访问主页', 29, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-10 09:54:45');
INSERT INTO `sys_log` VALUES (183, 1, 'admin', '登录', 4, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-10 10:09:08');
INSERT INTO `sys_log` VALUES (184, 1, 'admin', '请求访问主页', 6, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-10 10:09:08');
INSERT INTO `sys_log` VALUES (185, 1, 'admin', '添加用户', 3, 'com.admin.system.controller.UserController.add()', NULL, '127.0.0.1', '2018-09-10 10:09:11');
INSERT INTO `sys_log` VALUES (186, 1, 'admin', '登录', 30, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-10 10:32:02');
INSERT INTO `sys_log` VALUES (187, 1, 'admin', '请求访问主页', 53, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-10 10:32:02');
INSERT INTO `sys_log` VALUES (188, 1, 'admin', '添加用户', 6, 'com.admin.system.controller.UserController.add()', NULL, '127.0.0.1', '2018-09-10 10:32:05');
INSERT INTO `sys_log` VALUES (189, 1, 'admin', '保存用户', 20, 'com.admin.system.controller.UserController.save()', NULL, '127.0.0.1', '2018-09-10 10:34:41');
INSERT INTO `sys_log` VALUES (190, 1, 'admin', '登录', 11, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-10 10:37:41');
INSERT INTO `sys_log` VALUES (191, 1, 'admin', '请求访问主页', 28, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-10 10:37:41');
INSERT INTO `sys_log` VALUES (192, 1, 'admin', '添加用户', 7, 'com.admin.system.controller.UserController.add()', NULL, '127.0.0.1', '2018-09-10 10:37:45');
INSERT INTO `sys_log` VALUES (193, 1, 'admin', '保存用户', 20, 'com.admin.system.controller.UserController.save()', NULL, '127.0.0.1', '2018-09-10 10:38:16');
INSERT INTO `sys_log` VALUES (194, 1, 'admin', '编辑用户', 20, 'com.admin.system.controller.UserController.edit()', NULL, '127.0.0.1', '2018-09-10 10:39:37');
INSERT INTO `sys_log` VALUES (195, 1, 'admin', '更新用户', 20, 'com.admin.system.controller.UserController.update()', NULL, '127.0.0.1', '2018-09-10 10:40:03');
INSERT INTO `sys_log` VALUES (196, 1, 'admin', '编辑用户', 11, 'com.admin.system.controller.UserController.edit()', NULL, '127.0.0.1', '2018-09-10 10:40:08');
INSERT INTO `sys_log` VALUES (197, 1, 'admin', '更新用户', 7, 'com.admin.system.controller.UserController.update()', NULL, '127.0.0.1', '2018-09-10 10:40:26');
INSERT INTO `sys_log` VALUES (198, 1, 'admin', '请求更改用户密码', 0, 'com.admin.system.controller.UserController.resetPwd()', NULL, '127.0.0.1', '2018-09-10 10:41:14');
INSERT INTO `sys_log` VALUES (199, 1, 'admin', 'admin提交更改用户密码', 20, 'com.admin.system.controller.UserController.adminResetPwd()', NULL, '127.0.0.1', '2018-09-10 10:41:19');
INSERT INTO `sys_log` VALUES (200, 1, 'admin', '请求更改用户密码', 0, 'com.admin.system.controller.UserController.resetPwd()', NULL, '127.0.0.1', '2018-09-10 10:41:20');
INSERT INTO `sys_log` VALUES (201, 1, 'admin', 'admin提交更改用户密码', 16, 'com.admin.system.controller.UserController.adminResetPwd()', NULL, '127.0.0.1', '2018-09-10 10:41:23');
INSERT INTO `sys_log` VALUES (202, 1, 'admin', '登录', 4, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-10 10:42:34');
INSERT INTO `sys_log` VALUES (203, 1, 'admin', '请求访问主页', 8, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-10 10:42:34');
INSERT INTO `sys_log` VALUES (204, 1, 'admin', '编辑用户', 16, 'com.admin.system.controller.UserController.edit()', NULL, '127.0.0.1', '2018-09-10 10:42:40');
INSERT INTO `sys_log` VALUES (205, 1, 'admin', '更新用户', 7, 'com.admin.system.controller.UserController.update()', NULL, '127.0.0.1', '2018-09-10 10:42:44');
INSERT INTO `sys_log` VALUES (206, 1, 'admin', '编辑用户', 11, 'com.admin.system.controller.UserController.edit()', NULL, '127.0.0.1', '2018-09-10 10:42:53');
INSERT INTO `sys_log` VALUES (207, 1, 'admin', '更新用户', 16, 'com.admin.system.controller.UserController.update()', NULL, '127.0.0.1', '2018-09-10 10:42:57');
INSERT INTO `sys_log` VALUES (208, 1, 'admin', '编辑用户', 9, 'com.admin.system.controller.UserController.edit()', NULL, '127.0.0.1', '2018-09-10 10:43:02');
INSERT INTO `sys_log` VALUES (209, 1, 'admin', '更新用户', 6, 'com.admin.system.controller.UserController.update()', NULL, '127.0.0.1', '2018-09-10 10:43:06');
INSERT INTO `sys_log` VALUES (210, 1, 'admin', '登录', 16, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-10 15:36:12');
INSERT INTO `sys_log` VALUES (211, 1, 'admin', '请求访问主页', 40, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-10 15:36:12');
INSERT INTO `sys_log` VALUES (212, 1, 'admin', '添加菜单', 6, 'com.admin.system.controller.MenuController.add()', NULL, '127.0.0.1', '2018-09-10 15:52:20');
INSERT INTO `sys_log` VALUES (213, 1, 'admin', '保存菜单', 5, 'com.admin.system.controller.MenuController.save()', NULL, '127.0.0.1', '2018-09-10 15:53:04');
INSERT INTO `sys_log` VALUES (214, 1, 'admin', '编辑角色', 2, 'com.admin.system.controller.RoleController.edit()', NULL, '127.0.0.1', '2018-09-10 15:53:11');
INSERT INTO `sys_log` VALUES (215, 1, 'admin', '更新角色', 32, 'com.admin.system.controller.RoleController.update()', NULL, '127.0.0.1', '2018-09-10 15:53:15');
INSERT INTO `sys_log` VALUES (216, 1, 'admin', '登录', 4, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-10 15:53:18');
INSERT INTO `sys_log` VALUES (217, 1, 'admin', '请求访问主页', 10, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-10 15:53:18');
INSERT INTO `sys_log` VALUES (218, 1, 'admin', '登录', 9, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-10 15:55:38');
INSERT INTO `sys_log` VALUES (219, 1, 'admin', '请求访问主页', 31, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-10 15:55:38');
INSERT INTO `sys_log` VALUES (220, 1, 'admin', '编辑菜单', 12, 'com.admin.system.controller.MenuController.edit()', NULL, '127.0.0.1', '2018-09-10 15:57:08');
INSERT INTO `sys_log` VALUES (221, 1, 'admin', '更新菜单', 9, 'com.admin.system.controller.MenuController.update()', NULL, '127.0.0.1', '2018-09-10 15:57:21');
INSERT INTO `sys_log` VALUES (222, 1, 'admin', '登录', 6, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-10 15:57:28');
INSERT INTO `sys_log` VALUES (223, 1, 'admin', '请求访问主页', 15, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-10 15:57:28');
INSERT INTO `sys_log` VALUES (224, 1, 'admin', '登录', 14, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-10 15:59:06');
INSERT INTO `sys_log` VALUES (225, 1, 'admin', '请求访问主页', 40, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-10 15:59:06');
INSERT INTO `sys_log` VALUES (226, 1, 'admin', '登录', 11, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-10 16:04:45');
INSERT INTO `sys_log` VALUES (227, 1, 'admin', '请求访问主页', 41, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-10 16:04:45');
INSERT INTO `sys_log` VALUES (228, 1, 'admin', '添加菜单', 9, 'com.admin.system.controller.MenuController.add()', NULL, '127.0.0.1', '2018-09-10 16:04:57');
INSERT INTO `sys_log` VALUES (229, 1, 'admin', '保存菜单', 7, 'com.admin.system.controller.MenuController.save()', NULL, '127.0.0.1', '2018-09-10 16:06:08');
INSERT INTO `sys_log` VALUES (230, 1, 'admin', '添加菜单', 6, 'com.admin.system.controller.MenuController.add()', NULL, '127.0.0.1', '2018-09-10 16:06:12');
INSERT INTO `sys_log` VALUES (231, 1, 'admin', '保存菜单', 5, 'com.admin.system.controller.MenuController.save()', NULL, '127.0.0.1', '2018-09-10 16:07:10');
INSERT INTO `sys_log` VALUES (232, 1, 'admin', '添加菜单', 7, 'com.admin.system.controller.MenuController.add()', NULL, '127.0.0.1', '2018-09-10 16:07:16');
INSERT INTO `sys_log` VALUES (233, 1, 'admin', '保存菜单', 5, 'com.admin.system.controller.MenuController.save()', NULL, '127.0.0.1', '2018-09-10 16:08:36');
INSERT INTO `sys_log` VALUES (234, 1, 'admin', '添加菜单', 6, 'com.admin.system.controller.MenuController.add()', NULL, '127.0.0.1', '2018-09-10 16:08:40');
INSERT INTO `sys_log` VALUES (235, 1, 'admin', '保存菜单', 6, 'com.admin.system.controller.MenuController.save()', NULL, '127.0.0.1', '2018-09-10 16:09:19');
INSERT INTO `sys_log` VALUES (236, 1, 'admin', '编辑角色', 3, 'com.admin.system.controller.RoleController.edit()', NULL, '127.0.0.1', '2018-09-10 16:10:10');
INSERT INTO `sys_log` VALUES (237, 1, 'admin', '更新角色', 24, 'com.admin.system.controller.RoleController.update()', NULL, '127.0.0.1', '2018-09-10 16:10:13');
INSERT INTO `sys_log` VALUES (238, 1, 'admin', '登录', 3, 'com.admin.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-10 16:10:15');
INSERT INTO `sys_log` VALUES (239, 1, 'admin', '请求访问主页', 8, 'com.admin.system.controller.LoginController.index()', NULL, '127.0.0.1', '2018-09-10 16:10:15');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 120 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '基础管理', '', '', 0, 'fa fa-bars', 0, '2017-08-09 22:49:47', NULL);
INSERT INTO `sys_menu` VALUES (2, 3, '系统菜单', 'sys/menu/', 'sys:menu:menu', 1, 'fa fa-th-list', 2, '2017-08-09 22:55:15', NULL);
INSERT INTO `sys_menu` VALUES (3, 0, '系统管理', NULL, NULL, 0, 'fa fa-desktop', 1, '2017-08-09 23:06:55', '2017-08-14 14:13:43');
INSERT INTO `sys_menu` VALUES (6, 3, '用户管理', 'sys/user/', 'sys:user:user', 1, 'fa fa-user', 0, '2017-08-10 14:12:11', NULL);
INSERT INTO `sys_menu` VALUES (7, 3, '角色管理', 'sys/role', 'sys:role:role', 1, 'fa fa-paw', 1, '2017-08-10 14:13:19', NULL);
INSERT INTO `sys_menu` VALUES (12, 6, '新增', '', 'sys:user:add', 2, '', 0, '2017-08-14 10:51:35', NULL);
INSERT INTO `sys_menu` VALUES (13, 6, '编辑', '', 'sys:user:edit', 2, '', 0, '2017-08-14 10:52:06', NULL);
INSERT INTO `sys_menu` VALUES (14, 6, '删除', NULL, 'sys:user:remove', 2, NULL, 0, '2017-08-14 10:52:24', NULL);
INSERT INTO `sys_menu` VALUES (15, 7, '新增', '', 'sys:role:add', 2, '', 0, '2017-08-14 10:56:37', NULL);
INSERT INTO `sys_menu` VALUES (20, 2, '新增', '', 'sys:menu:add', 2, '', 0, '2017-08-14 10:59:32', NULL);
INSERT INTO `sys_menu` VALUES (21, 2, '编辑', '', 'sys:menu:edit', 2, '', 0, '2017-08-14 10:59:56', NULL);
INSERT INTO `sys_menu` VALUES (22, 2, '删除', '', 'sys:menu:remove', 2, '', 0, '2017-08-14 11:00:26', NULL);
INSERT INTO `sys_menu` VALUES (24, 6, '批量删除', '', 'sys:user:batchRemove', 2, '', 0, '2017-08-14 17:27:18', NULL);
INSERT INTO `sys_menu` VALUES (25, 6, '停用', NULL, 'sys:user:disable', 2, NULL, 0, '2017-08-14 17:27:43', NULL);
INSERT INTO `sys_menu` VALUES (26, 6, '重置密码', '', 'sys:user:resetPwd', 2, '', 0, '2017-08-14 17:28:34', NULL);
INSERT INTO `sys_menu` VALUES (27, 91, '系统日志', 'common/log', 'common:log', 1, 'fa fa-warning', 0, '2017-08-14 22:11:53', NULL);
INSERT INTO `sys_menu` VALUES (28, 27, '刷新', NULL, 'sys:log:list', 2, NULL, 0, '2017-08-14 22:30:22', NULL);
INSERT INTO `sys_menu` VALUES (29, 27, '删除', NULL, 'sys:log:remove', 2, NULL, 0, '2017-08-14 22:30:43', NULL);
INSERT INTO `sys_menu` VALUES (30, 27, '清空', NULL, 'sys:log:clear', 2, NULL, 0, '2017-08-14 22:31:02', NULL);
INSERT INTO `sys_menu` VALUES (48, 77, '代码生成', 'common/generator', 'common:generator', 1, 'fa fa-code', 3, NULL, NULL);
INSERT INTO `sys_menu` VALUES (55, 7, '编辑', '', 'sys:role:edit', 2, '', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (56, 7, '删除', '', 'sys:role:remove', 2, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (57, 91, '运行监控', '/druid/index.html', '', 1, 'fa fa-caret-square-o-right', 1, NULL, NULL);
INSERT INTO `sys_menu` VALUES (61, 2, '批量删除', '', 'sys:menu:batchRemove', 2, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (62, 7, '批量删除', '', 'sys:role:batchRemove', 2, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (71, 1, '文件管理', '/common/sysFile', 'common:sysFile:sysFile', 1, 'fa fa-folder-open', 2, NULL, NULL);
INSERT INTO `sys_menu` VALUES (72, 77, '计划任务', 'common/job', 'common:taskScheduleJob', 1, 'fa fa-hourglass-1', 4, NULL, NULL);
INSERT INTO `sys_menu` VALUES (73, 3, '组织管理', '/system/sysDept', 'system:sysDept:sysDept', 1, 'fa fa-users', 3, NULL, NULL);
INSERT INTO `sys_menu` VALUES (74, 73, '增加', '/system/sysDept/add', 'system:sysDept:add', 2, NULL, 1, NULL, NULL);
INSERT INTO `sys_menu` VALUES (75, 73, '刪除', 'system/sysDept/remove', 'system:sysDept:remove', 2, NULL, 2, NULL, NULL);
INSERT INTO `sys_menu` VALUES (76, 73, '编辑', '/system/sysDept/edit', 'system:sysDept:edit', 2, NULL, 3, NULL, NULL);
INSERT INTO `sys_menu` VALUES (77, 0, '系统工具', '', '', 0, 'fa fa-gear', 2, NULL, NULL);
INSERT INTO `sys_menu` VALUES (78, 1, '数据字典', '/common/dict', 'common:dict:dict', 1, 'fa fa-book', 1, NULL, NULL);
INSERT INTO `sys_menu` VALUES (79, 78, '增加', '/common/dict/add', 'common:dict:add', 2, NULL, 2, NULL, NULL);
INSERT INTO `sys_menu` VALUES (80, 78, '编辑', '/common/dict/edit', 'common:dict:edit', 2, NULL, 2, NULL, NULL);
INSERT INTO `sys_menu` VALUES (81, 78, '删除', '/common/dict/remove', 'common:dict:remove', 2, '', 3, NULL, NULL);
INSERT INTO `sys_menu` VALUES (83, 78, '批量删除', '/common/dict/batchRemove', 'common:dict:batchRemove', 2, '', 4, NULL, NULL);
INSERT INTO `sys_menu` VALUES (84, 0, '办公管理', '', '', 0, 'fa fa-laptop', 3, NULL, NULL);
INSERT INTO `sys_menu` VALUES (85, 84, '通知公告', 'oa/notify', 'oa:notify:notify', 1, 'fa fa-pencil-square', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (86, 85, '新增', 'oa/notify/add', 'oa:notify:add', 2, 'fa fa-plus', 1, NULL, NULL);
INSERT INTO `sys_menu` VALUES (87, 85, '编辑', 'oa/notify/edit', 'oa:notify:edit', 2, 'fa fa-pencil-square-o', 2, NULL, NULL);
INSERT INTO `sys_menu` VALUES (88, 85, '删除', 'oa/notify/remove', 'oa:notify:remove', 2, 'fa fa-minus', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (89, 85, '批量删除', 'oa/notify/batchRemove', 'oa:notify:batchRemove', 2, '', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (90, 84, '我的通知', 'oa/notify/selfNotify', '', 1, 'fa fa-envelope-square', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (91, 0, '系统监控', '', '', 0, 'fa fa-video-camera', 4, NULL, NULL);
INSERT INTO `sys_menu` VALUES (92, 91, '在线用户', 'sys/online', '', 1, 'fa fa-user', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (104, 77, 'swagger', '/swagger-ui.html', '', 1, '', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (105, 0, '套餐活动管理', '', '', 0, 'fa fa-bookmark', 5, NULL, NULL);
INSERT INTO `sys_menu` VALUES (106, 105, '参与活动用户', '/pack/studentInfo', 'pack:studentInfo:studentInfo', 1, 'fa fa-bars', 1, NULL, NULL);
INSERT INTO `sys_menu` VALUES (112, 105, '收款记录查看', '/pack/studentInfo/payRecord', 'pack:studentInfo:studentInfo', 1, 'fa fa-bars', 3, NULL, NULL);
INSERT INTO `sys_menu` VALUES (113, 106, '编辑', '/pack/studentInfo/edit', 'pack:studentInfo:edit', 2, 'fa fa-pencil-square-o', 1, NULL, NULL);
INSERT INTO `sys_menu` VALUES (114, 112, '编辑', '/pack/studentInfo/payEdit', 'pack:studentInfo:edit', 2, 'fa fa-pencil-square-o', 1, NULL, NULL);
INSERT INTO `sys_menu` VALUES (115, 105, '学校管理', '/pack/schoolInfo', 'pack:schoolInfo:schoolInfo', 1, 'fa fa-bars', 3, NULL, NULL);
INSERT INTO `sys_menu` VALUES (116, 115, '新增', '/system/schoolInfo/add', 'pack:schoolInfo:add', 2, 'fa fa-plus', 1, NULL, NULL);
INSERT INTO `sys_menu` VALUES (117, 115, '编辑', '/system/schoolInfo/edit', 'pack:schoolInfo:edit', 2, 'fa fa-pencil-square-o', 2, NULL, NULL);
INSERT INTO `sys_menu` VALUES (118, 115, '删除', '/system/schoolInfo/remove', 'pack:schoolInfo:remove', 2, 'fa fa-remove', 3, NULL, NULL);
INSERT INTO `sys_menu` VALUES (119, 115, '批量删除', '/system/schoolInfo/batchRemove', 'pack:schoolInfo:batchRemove', 2, 'fa fa-trash-o', 4, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_sign` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色标识',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `user_id_create` bigint(255) NULL DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级用户', 'admin', '拥有最高权限', 2, '2017-08-12 00:43:52', '2017-08-12 19:14:59');
INSERT INTO `sys_role` VALUES (60, '业务用户', NULL, '业务用户', NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (61, '移动用户', NULL, '移动用户', NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (62, '普通管理员', NULL, '普通管理员', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3728 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色与菜单对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (3389, 61, 106);
INSERT INTO `sys_role_menu` VALUES (3390, 61, -1);
INSERT INTO `sys_role_menu` VALUES (3391, 61, 105);
INSERT INTO `sys_role_menu` VALUES (3447, 62, 105);
INSERT INTO `sys_role_menu` VALUES (3453, 62, 106);
INSERT INTO `sys_role_menu` VALUES (3454, 62, -1);
INSERT INTO `sys_role_menu` VALUES (3670, 1, 114);
INSERT INTO `sys_role_menu` VALUES (3671, 1, 113);
INSERT INTO `sys_role_menu` VALUES (3672, 1, 92);
INSERT INTO `sys_role_menu` VALUES (3673, 1, 57);
INSERT INTO `sys_role_menu` VALUES (3674, 1, 30);
INSERT INTO `sys_role_menu` VALUES (3675, 1, 29);
INSERT INTO `sys_role_menu` VALUES (3676, 1, 28);
INSERT INTO `sys_role_menu` VALUES (3677, 1, 90);
INSERT INTO `sys_role_menu` VALUES (3678, 1, 89);
INSERT INTO `sys_role_menu` VALUES (3679, 1, 88);
INSERT INTO `sys_role_menu` VALUES (3680, 1, 87);
INSERT INTO `sys_role_menu` VALUES (3681, 1, 86);
INSERT INTO `sys_role_menu` VALUES (3682, 1, 104);
INSERT INTO `sys_role_menu` VALUES (3683, 1, 72);
INSERT INTO `sys_role_menu` VALUES (3684, 1, 48);
INSERT INTO `sys_role_menu` VALUES (3685, 1, 76);
INSERT INTO `sys_role_menu` VALUES (3686, 1, 75);
INSERT INTO `sys_role_menu` VALUES (3687, 1, 74);
INSERT INTO `sys_role_menu` VALUES (3688, 1, 62);
INSERT INTO `sys_role_menu` VALUES (3689, 1, 56);
INSERT INTO `sys_role_menu` VALUES (3690, 1, 55);
INSERT INTO `sys_role_menu` VALUES (3691, 1, 15);
INSERT INTO `sys_role_menu` VALUES (3692, 1, 26);
INSERT INTO `sys_role_menu` VALUES (3693, 1, 25);
INSERT INTO `sys_role_menu` VALUES (3694, 1, 24);
INSERT INTO `sys_role_menu` VALUES (3695, 1, 14);
INSERT INTO `sys_role_menu` VALUES (3696, 1, 13);
INSERT INTO `sys_role_menu` VALUES (3697, 1, 12);
INSERT INTO `sys_role_menu` VALUES (3698, 1, 61);
INSERT INTO `sys_role_menu` VALUES (3699, 1, 22);
INSERT INTO `sys_role_menu` VALUES (3700, 1, 21);
INSERT INTO `sys_role_menu` VALUES (3701, 1, 20);
INSERT INTO `sys_role_menu` VALUES (3702, 1, 83);
INSERT INTO `sys_role_menu` VALUES (3703, 1, 81);
INSERT INTO `sys_role_menu` VALUES (3704, 1, 80);
INSERT INTO `sys_role_menu` VALUES (3705, 1, 79);
INSERT INTO `sys_role_menu` VALUES (3706, 1, 71);
INSERT INTO `sys_role_menu` VALUES (3707, 1, 112);
INSERT INTO `sys_role_menu` VALUES (3708, 1, 106);
INSERT INTO `sys_role_menu` VALUES (3709, 1, 27);
INSERT INTO `sys_role_menu` VALUES (3710, 1, 91);
INSERT INTO `sys_role_menu` VALUES (3711, 1, 85);
INSERT INTO `sys_role_menu` VALUES (3712, 1, 84);
INSERT INTO `sys_role_menu` VALUES (3713, 1, 77);
INSERT INTO `sys_role_menu` VALUES (3714, 1, 73);
INSERT INTO `sys_role_menu` VALUES (3715, 1, 7);
INSERT INTO `sys_role_menu` VALUES (3716, 1, 6);
INSERT INTO `sys_role_menu` VALUES (3717, 1, 2);
INSERT INTO `sys_role_menu` VALUES (3718, 1, 3);
INSERT INTO `sys_role_menu` VALUES (3719, 1, 78);
INSERT INTO `sys_role_menu` VALUES (3720, 1, 1);
INSERT INTO `sys_role_menu` VALUES (3721, 1, 115);
INSERT INTO `sys_role_menu` VALUES (3722, 1, 119);
INSERT INTO `sys_role_menu` VALUES (3723, 1, 118);
INSERT INTO `sys_role_menu` VALUES (3724, 1, 117);
INSERT INTO `sys_role_menu` VALUES (3725, 1, 116);
INSERT INTO `sys_role_menu` VALUES (3726, 1, 105);
INSERT INTO `sys_role_menu` VALUES (3727, 1, -1);

-- ----------------------------
-- Table structure for sys_task
-- ----------------------------
DROP TABLE IF EXISTS `sys_task`;
CREATE TABLE `sys_task`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务调用的方法名',
  `is_concurrent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务是否有状态',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务描述',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `bean_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `job_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务状态',
  `job_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务分组',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `spring_bean` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Spring bean',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_task
-- ----------------------------
INSERT INTO `sys_task` VALUES (2, '0/10 * * * * ?', 'run1', '1', '', '4028ea815a3d2a8c015a3d2f8d2a0002', 'com.bootdo.common.task.WelcomeJob', '2017-05-19 18:30:56', '0', 'group1', '2017-05-19 18:31:07', NULL, '', 'welcomJob');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录名',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `dept_id` int(20) NULL DEFAULT NULL COMMENT '部门',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态 0:禁用，1:正常',
  `user_id_create` bigint(20) NULL DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `sex` int(1) NULL DEFAULT NULL COMMENT '性别:0男 1女',
  `birth` datetime(0) NULL DEFAULT NULL COMMENT '出身日期',
  `pic_id` int(11) NULL DEFAULT NULL COMMENT '头像',
  `live_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现居住地',
  `hobby` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '爱好',
  `province` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在城市',
  `district` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在地区',
  `school_id` int(11) NULL DEFAULT NULL COMMENT '学校ID',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('01278', 'lisi', '李四', '1cde18eea8464e2fa614902845d1ac0d', 2, '312@QQ.COM', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('1', 'admin', '超级管理员', 'c1cee993ece039aa8b0efbe6f2754082', 3, 'lg932740579@163.com', '18108258001', 1, 1, '2017-08-15 21:40:39', '2017-08-15 21:41:00', 96, '2017-12-14 00:00:00', 138, 'ccc', '122;121;', '北京市', '北京市市辖区', '东城区', NULL);
INSERT INTO `sys_user` VALUES ('1268', 'zhangsan', '张三', '631238c3e0326c0d26360e6f6e448452', 4, 'a.kiss@qq.com', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 143 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户与角色对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (73, 30, 48);
INSERT INTO `sys_user_role` VALUES (74, 30, 49);
INSERT INTO `sys_user_role` VALUES (75, 30, 50);
INSERT INTO `sys_user_role` VALUES (76, 31, 48);
INSERT INTO `sys_user_role` VALUES (77, 31, 49);
INSERT INTO `sys_user_role` VALUES (78, 31, 52);
INSERT INTO `sys_user_role` VALUES (79, 32, 48);
INSERT INTO `sys_user_role` VALUES (80, 32, 49);
INSERT INTO `sys_user_role` VALUES (81, 32, 50);
INSERT INTO `sys_user_role` VALUES (82, 32, 51);
INSERT INTO `sys_user_role` VALUES (83, 32, 52);
INSERT INTO `sys_user_role` VALUES (84, 33, 38);
INSERT INTO `sys_user_role` VALUES (85, 33, 49);
INSERT INTO `sys_user_role` VALUES (86, 33, 52);
INSERT INTO `sys_user_role` VALUES (87, 34, 50);
INSERT INTO `sys_user_role` VALUES (88, 34, 51);
INSERT INTO `sys_user_role` VALUES (89, 34, 52);
INSERT INTO `sys_user_role` VALUES (106, 124, 1);
INSERT INTO `sys_user_role` VALUES (111, 2, 1);
INSERT INTO `sys_user_role` VALUES (113, 131, 48);
INSERT INTO `sys_user_role` VALUES (117, 135, 1);
INSERT INTO `sys_user_role` VALUES (120, 134, 1);
INSERT INTO `sys_user_role` VALUES (121, 134, 48);
INSERT INTO `sys_user_role` VALUES (123, 130, 1);
INSERT INTO `sys_user_role` VALUES (124, NULL, 48);
INSERT INTO `sys_user_role` VALUES (125, 132, 52);
INSERT INTO `sys_user_role` VALUES (126, 132, 49);
INSERT INTO `sys_user_role` VALUES (127, 123, 48);
INSERT INTO `sys_user_role` VALUES (132, 36, 48);
INSERT INTO `sys_user_role` VALUES (140, 1268, 61);
INSERT INTO `sys_user_role` VALUES (141, 1278, 60);
INSERT INTO `sys_user_role` VALUES (142, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
