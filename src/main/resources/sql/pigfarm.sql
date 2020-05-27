/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : pigfarm

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2020-05-27 13:14:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for expend
-- ----------------------------
DROP TABLE IF EXISTS `expend`;
CREATE TABLE `expend` (
  `e_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `e_purpose` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用途',
  `e_money` decimal(5,2) DEFAULT NULL COMMENT '支出金额',
  `e_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '支出时间(年月日)',
  `s_id` int(11) DEFAULT NULL COMMENT '付钱的人ID',
  `u_number` char(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作人唯一编号',
  `p_id` int(6) DEFAULT NULL COMMENT '猪场ID',
  PRIMARY KEY (`e_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='日常支出';

-- ----------------------------
-- Records of expend
-- ----------------------------

-- ----------------------------
-- Table structure for feed
-- ----------------------------
DROP TABLE IF EXISTS `feed`;
CREATE TABLE `feed` (
  `f_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '饲料名称',
  `f_freight` decimal(10,2) DEFAULT NULL COMMENT '饲料运费',
  `f_total` int(10) DEFAULT NULL COMMENT '饲料总数',
  `f_use_total` int(10) DEFAULT '0' COMMENT '已使用数量',
  `f_surplus_number` int(10) DEFAULT NULL COMMENT '剩余数量',
  `f_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '添加时间',
  `u_number` char(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作人',
  `f_return` int(1) DEFAULT '0' COMMENT '是否退回（0未使用完，1使用完了，2已退回）',
  `p_id` int(6) DEFAULT NULL COMMENT '猪场ID',
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='饲料进库';

-- ----------------------------
-- Records of feed
-- ----------------------------
INSERT INTO `feed` VALUES ('1', '越吃越胖', '368.32', '2000', '300', '1700', '2020-05-26 16:34:09', '20200509093153366000', '0', '1');
INSERT INTO `feed` VALUES ('2', '肥胖吃好饲料', '635.32', '6000', '0', '6000', '2020-05-26 11:08:00', '20200509093153366000', '0', '1');
INSERT INTO `feed` VALUES ('3', '越吃越胖', '368.32', '2000', '0', '2000', '2020-05-26 11:08:00', '20200509093153366000', '0', '1');
INSERT INTO `feed` VALUES ('4', '越吃越胖哦', '368.32', '2001', '0', '2001', '2020-05-26 11:08:00', '20200509093153366000', '0', '1');
INSERT INTO `feed` VALUES ('5', '超级饲料', '365.32', '3264', '0', '3264', '2020-05-26 11:08:00', '20200509093420098000', '0', '1');

-- ----------------------------
-- Table structure for feeduse
-- ----------------------------
DROP TABLE IF EXISTS `feeduse`;
CREATE TABLE `feeduse` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `f_use_fid` bigint(20) DEFAULT NULL COMMENT '使用饲料ID',
  `f_user_number` int(3) DEFAULT NULL COMMENT '使用数量',
  `f_use_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '使用时间',
  `f_sid` int(11) DEFAULT NULL COMMENT '使用人',
  `u_number` char(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作人编号',
  `f_pigsty` int(3) DEFAULT NULL COMMENT '用于几号圈',
  `p_id` int(6) DEFAULT NULL COMMENT '猪场ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='饲料使用';

-- ----------------------------
-- Records of feeduse
-- ----------------------------
INSERT INTO `feeduse` VALUES ('1', '1', '300', '2020-05-27 11:53:01', '1', '20200509093153366000', '3', '1');
INSERT INTO `feeduse` VALUES ('2', '1', '200', '2020-05-27 11:42:07', '1', '20200509093153366000', '3', '1');
INSERT INTO `feeduse` VALUES ('3', '1', '200', '2020-05-27 11:42:31', '1', '20200509093153366000', '3', '1');

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
  `m_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `u_number` char(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '添加人唯一编号',
  `m_number` char(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '材料明细唯一编号',
  `m_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '材料名称',
  `m_count` int(5) DEFAULT NULL COMMENT '材料数量',
  `m_price` decimal(5,2) DEFAULT NULL COMMENT '材料单价',
  `m_total` decimal(5,2) DEFAULT NULL COMMENT '材料总价',
  `m_add_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '添加时间',
  `m_usage` int(5) DEFAULT NULL COMMENT '材料使用量',
  `m_surplus` int(5) DEFAULT NULL COMMENT '材料剩余量',
  `m_remarks` text COLLATE utf8_unicode_ci COMMENT '备注',
  `p_id` int(6) DEFAULT NULL COMMENT '猪场ID',
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='材料进库';

-- ----------------------------
-- Records of material
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `m_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '菜单名',
  `superior_id` int(11) DEFAULT NULL COMMENT '菜单上级ID',
  `href` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '菜单路径',
  `icon` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '菜单图标',
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='菜单管理';

-- ----------------------------
-- Records of menu
-- ----------------------------

-- ----------------------------
-- Table structure for piglet
-- ----------------------------
DROP TABLE IF EXISTS `piglet`;
CREATE TABLE `piglet` (
  `p_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `p_number` char(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '进库编号',
  `p_total` int(5) DEFAULT NULL COMMENT '猪仔数量',
  `p_death_total` int(5) DEFAULT NULL COMMENT '死亡数量',
  `p_problem_total` int(5) DEFAULT NULL COMMENT '异常数量',
  `p_instorage_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '进库时间',
  `p_outbound_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '出栏时间',
  `p_state` int(1) DEFAULT NULL COMMENT '是否出库（0未出库，1出库）',
  `p_ids` int(6) DEFAULT NULL COMMENT '猪场ID',
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='猪仔进库';

-- ----------------------------
-- Records of piglet
-- ----------------------------

-- ----------------------------
-- Table structure for pigsite
-- ----------------------------
DROP TABLE IF EXISTS `pigsite`;
CREATE TABLE `pigsite` (
  `p_id` int(6) NOT NULL AUTO_INCREMENT,
  `p_name` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '猪场名字',
  `p_address` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '猪场地址',
  `p_scale` int(10) DEFAULT NULL COMMENT '猪场规模',
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='猪场管理';

-- ----------------------------
-- Records of pigsite
-- ----------------------------
INSERT INTO `pigsite` VALUES ('1', '凤翔猪场', '宝鸡市凤翔县', '2100');
INSERT INTO `pigsite` VALUES ('2', '鄠邑区猪场', '西安市鄠邑区膀胱镇', '3000');
INSERT INTO `pigsite` VALUES ('3', '富平猪场', '渭南市富平县村子', '3000');
INSERT INTO `pigsite` VALUES ('4', 'AAA', '215464', '3632');

-- ----------------------------
-- Table structure for reference
-- ----------------------------
DROP TABLE IF EXISTS `reference`;
CREATE TABLE `reference` (
  `r_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `u_number` char(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '上传人',
  `r_phone` char(11) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `r_source` int(1) DEFAULT NULL COMMENT '来源（0未知,1淘宝，2京东,3实体店）',
  `r_remarks` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `r_state` int(1) DEFAULT NULL COMMENT '审核状态（0未审核，1审核通过）',
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='材料参考';

-- ----------------------------
-- Records of reference
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `r_name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色名',
  `r_time` datetime DEFAULT NULL COMMENT '添加时间',
  `u_number` int(11) DEFAULT NULL COMMENT '添加人唯一编号',
  `m_id` int(2) DEFAULT NULL COMMENT '菜单ID',
  `p_id` int(6) DEFAULT NULL COMMENT '猪场ID',
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色管理';

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `s_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `s_name` char(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '员工姓名',
  `s_age` int(3) DEFAULT NULL COMMENT '员工年龄',
  `s_id_number` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '员工身份证号',
  `s_entry_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '入职时间',
  `s_state` int(1) DEFAULT '1' COMMENT '是否离职（1未离职，0离职）',
  `s_hide_age` int(1) DEFAULT '0' COMMENT '是否隐藏年龄（0隐藏，1不隐藏）',
  `s_hide_id_number` int(1) DEFAULT '0' COMMENT '是否隐藏身份证(1不隐藏，0隐藏)',
  `p_id` int(6) DEFAULT NULL COMMENT '猪场ID',
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='员工管理';

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES ('1', '张三', '58', '612321196906065648', '2020-05-26 15:07:05', '1', '1', '0', '1');
INSERT INTO `staff` VALUES ('2', '李四', '55', '110101199003078574', '2020-05-26 14:56:35', '1', '0', '0', '1');

-- ----------------------------
-- Table structure for troublepig
-- ----------------------------
DROP TABLE IF EXISTS `troublepig`;
CREATE TABLE `troublepig` (
  `t_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `p_number` char(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '猪仔进库编号',
  `t_find_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '发现时间',
  `t_trouble_total` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '异常数量',
  `t_handle` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '如何处理',
  `t_state` int(1) DEFAULT NULL COMMENT '是否死亡（0为死亡，1死亡）',
  `t_reason` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '死亡原因',
  `u_number` char(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作人唯一编号',
  `t_pigsty_no` int(2) DEFAULT NULL COMMENT '发现于那个猪圈',
  `p_id` int(6) DEFAULT NULL COMMENT '猪场ID',
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='生猪异常';

-- ----------------------------
-- Records of troublepig
-- ----------------------------

-- ----------------------------
-- Table structure for usemateriallog
-- ----------------------------
DROP TABLE IF EXISTS `usemateriallog`;
CREATE TABLE `usemateriallog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `m_number` char(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '材料明细唯一编号',
  `m_use_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '使用时间(年月日)',
  `m_purpose` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用途',
  `u_number` char(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作人唯一编号',
  `m_remarks` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `m_usage` int(10) DEFAULT NULL COMMENT '使用数量',
  `m_sid` int(11) DEFAULT NULL COMMENT '使用人',
  `p_id` int(6) DEFAULT NULL COMMENT '猪场ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='材料使用日志';

-- ----------------------------
-- Records of usemateriallog
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `u_id` bigint(8) NOT NULL AUTO_INCREMENT,
  `u_number` char(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '唯一编号',
  `u_superior_leader_number` char(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '上级领导编号',
  `u_name` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '真实姓名',
  `u_account` char(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '账户',
  `u_password` char(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  `u_level` int(1) DEFAULT NULL COMMENT '级别',
  `u_status` int(1) DEFAULT '2' COMMENT '状态 (2未激活，1可用，0禁用) ',
  `u_create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `u_last_login_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上次登录时间',
  `u_login_count` bigint(8) DEFAULT NULL COMMENT '登录次数',
  `p_id` int(6) DEFAULT NULL COMMENT '属于哪个猪场',
  `u_power` int(2) DEFAULT NULL COMMENT '登录权限',
  `u_token` char(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '20200508114219576000', null, '超级管理员', 'admin', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '1', '2020-05-25 14:26:37', '2020-05-25 18:06:06', '91', '0', null, 'A6EEAA936F6D0293D47E325F36D6AA1E');
INSERT INTO `user` VALUES ('2', '20200509093153366000', null, '测试专员', 'admins', 'E10ADC3949BA59ABBE56E057F20F883E', '2', '1', '2020-05-25 16:34:29', '2020-05-26 09:58:15', '6', '1', null, '6A0E36537F633B6D7122DE8EFC7EACA9');
INSERT INTO `user` VALUES ('3', '20200509093420098000', null, '测试专员2', 'adminss', 'E10ADC3949BA59ABBE56E057F20F883E', '3', '1', '2020-05-25 19:38:47', '2020-05-25 19:38:50', '1', '1', null, '4967BC01B3487DE18F27EA7C272FEB3A');
INSERT INTO `user` VALUES ('4', '20200509095827397000', null, null, '17602988122', 'E10ADC3949BA59ABBE56E057F20F883E', '2', '2', '2020-05-09 09:58:27', null, null, null, null, null);
