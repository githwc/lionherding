/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : lh_basis

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-11-02 10:33:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `sys_dept_id` varchar(32) NOT NULL,
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `depart_name` varchar(100) NOT NULL COMMENT '部门名称',
  `short_name` varchar(50) DEFAULT NULL COMMENT '简称',
  `manager_id` varchar(32) DEFAULT NULL COMMENT '负责人',
  `telephone` varchar(15) DEFAULT NULL COMMENT '固话',
  `fax` varchar(50) DEFAULT NULL COMMENT '传真',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `org_type` varchar(50) NOT NULL COMMENT '机构类型(1.公司 2.部门 3 岗位)',
  `unique_coding` varchar(50) NOT NULL COMMENT '唯一编码',
  `admin_level` int(11) DEFAULT NULL COMMENT '层级',
  `del_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除状态(0:未删除 1:已删除)',
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '状态(0启用 1不启用)',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `sort` int(11) NOT NULL DEFAULT '100' COMMENT '排序',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_id` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`sys_dept_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('3747764ade47307b7af76dbe14b48a5c', '65e64b9b47aafa626bc648b0bf9cc3ee', 'Manager', null, null, '15566778888', '0399-238922323', '环城南路', '3', 'A02A03A01', null, '0', '0', null, '100', '12', 'admin', '2019-11-02 09:30:33', null, '2019-11-02 09:30:33');
INSERT INTO `sys_dept` VALUES ('4475ad9f8cfd500b62bbd5a1a1100162', 'a7d7e77e06c84325a40932163adcdaa6', 'DEV', null, null, '15556778888', '0737-399938849', 'USA', '3', 'A02A01A01', null, '0', '0', null, '100', 'oneself', 'admin', '2019-11-02 09:23:05', null, '2019-11-02 09:23:05');
INSERT INTO `sys_dept` VALUES ('65e64b9b47aafa626bc648b0bf9cc3ee', '6d35e179cd814e3299bd588ea7daed3f', '野生技术协会', null, null, '16677887776', '0388-38829332', '江北路四十三号', '2', 'A02A03', null, '0', '0', null, '100', '', 'admin', '2019-11-02 09:24:22', null, '2019-11-02 09:24:22');
INSERT INTO `sys_dept` VALUES ('6d35e179cd814e3299bd588ea7daed3f', '', '科学搬砖', '卓尔互动公司', '123', '15959559955', null, '英国', '1', 'A02', '4', '0', '1', null, '5', '备注', 'sjdfaoisjojofsd', '2019-09-10 14:40:38', null, '2019-11-02 09:27:50');
INSERT INTO `sys_dept` VALUES ('743ba9dbdc114af8953a11022ef3096a', 'f28c6f53abd841ac87ead43afc483433', '财务部', '财务部', '123', '15959559955', null, '英国', '2', 'A03A01', '4', '0', '1', null, '6', '备注', 'sjdfaoisjojofsd', '2019-09-10 14:40:38', null, '2019-10-27 19:36:58');
INSERT INTO `sys_dept` VALUES ('754dfebea54666dd3c534222221b66da', '996e3cbeda3e1893098c962192851622', 'WEB Designer', null, null, null, null, null, '3', 'A03A02A01', null, '0', '0', null, '100', null, 'admin', '2019-11-02 09:34:21', null, '2019-11-02 09:34:21');
INSERT INTO `sys_dept` VALUES ('861bdd5c102da18edb93f80cef816fb4', 'bd21454dae3b5a5befaa41031852dd41', '中二少女团', null, null, '15556666666', '45', '海蓝路', '2', 'A03A01', null, '0', '0', null, '100', '', 'admin', '2019-10-31 14:32:14', null, '2019-11-02 09:31:27');
INSERT INTO `sys_dept` VALUES ('922a5839b8d24fbbfbc9b041cc018c3e', '861bdd5c102da18edb93f80cef816fb4', 'QA Manager', null, null, '15559555555', '234', '', '3', 'A03A01A01', null, '0', '0', null, '100', '', 'admin', '2019-10-31 14:37:02', null, '2019-11-02 09:31:59');
INSERT INTO `sys_dept` VALUES ('996e3cbeda3e1893098c962192851622', 'bd21454dae3b5a5befaa41031852dd41', '高逼格设计天团', null, null, '15556666666', null, null, '2', 'A03A02', null, '0', '0', null, '2', null, 'admin', '2019-11-02 09:33:38', null, '2019-11-02 09:33:38');
INSERT INTO `sys_dept` VALUES ('a7d7e77e06c84325a40932163adcdaa6', '6d35e179cd814e3299bd588ea7daed3f', '白鹭酱油开发组', '财务部', '123', '15959559955', null, '英国', '2', 'A02A01', '4', '0', '1', null, '7', '备注', 'sjdfaoisjojofsd', '2019-09-10 14:40:38', null, '2019-11-02 09:21:41');
INSERT INTO `sys_dept` VALUES ('bd21454dae3b5a5befaa41031852dd41', '', '设计天团', null, null, '15556666666', '345', '345', '1', 'A03', null, '0', '0', null, '100', '高逼格', 'admin', '2019-10-31 14:26:13', null, '2019-11-02 09:28:32');

-- ----------------------------
-- Table structure for sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary` (
  `sys_dictionary_id` varchar(32) NOT NULL,
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `key` varchar(100) DEFAULT NULL COMMENT '字典项文本',
  `value` varchar(200) DEFAULT NULL COMMENT '字典项值',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `state` int(2) DEFAULT '0' COMMENT '状态(0 启用 1 不启用)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_user_id` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`sys_dictionary_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统字典表';

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `sys_log_id` varchar(32) NOT NULL,
  `op_type` int(2) NOT NULL DEFAULT '4' COMMENT '操作类型(0:增 1：删  2：改 3：查)',
  `log_type` int(2) NOT NULL DEFAULT '0' COMMENT '日志类型(0.操作日志 1.登录日志 2.定时任务）',
  `log_content` varchar(500) NOT NULL COMMENT '日志内容',
  `request_method` varchar(255) DEFAULT NULL COMMENT '请求方法',
  `request_param` varchar(255) DEFAULT NULL COMMENT '请求参数',
  `request_url` varchar(255) DEFAULT NULL COMMENT '请求路径',
  `request_type` varchar(10) DEFAULT NULL COMMENT '请求类型',
  `ip_adress` varchar(15) DEFAULT NULL COMMENT 'IP地址',
  `cost_time` bigint(20) DEFAULT NULL COMMENT '耗时',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_user_id` varchar(32) NOT NULL COMMENT '操作人',
  `create_user_name` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`sys_log_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('0006edeef2f25f83241005ea20b25e4c', '4', '1', '用户名: jeecg,登录成功！', 'sysUser/login', 'loginName:jeecg,password:123456', null, null, '127.0.0.1', null, null, 'admin', 'admin', '2019-11-02 10:01:35');
INSERT INTO `sys_log` VALUES ('0afc050f23fcdf196d78c9647436a287', '4', '1', '用户名: jeecg,登录成功！', 'sysUser/login', 'loginName:jeecg,password:123456', null, null, '127.0.0.1', null, null, 'admin', 'admin', '2019-11-02 09:47:06');
INSERT INTO `sys_log` VALUES ('1675bb1d40d6252f19475b14fe15b01f', '4', '1', '用户名: jeecg,登录成功！', 'sysUser/login', 'loginName:jeecg,password:123456', null, null, '127.0.0.1', null, null, 'admin', 'admin', '2019-11-02 10:07:05');
INSERT INTO `sys_log` VALUES ('4204beaa7b466678d876f1baf4447e39', '4', '1', '用户名: jeecg,登录成功！', 'sysUser/login', 'loginName:jeecg,password:123456', null, null, '127.0.0.1', null, null, 'admin', 'admin', '2019-11-02 10:21:32');
INSERT INTO `sys_log` VALUES ('741fed1a0cf49edf342dfed636755414', '4', '1', '用户名: bailu,登录成功！', 'sysUser/login', 'loginName:bailu,password:123456', null, null, '127.0.0.1', null, null, 'admin', 'admin', '2019-11-02 10:23:59');
INSERT INTO `sys_log` VALUES ('9bcbb4b7c58f806d880098517346822b', '4', '1', '用户名: jeecg,登录成功！', 'sysUser/login', 'loginName:jeecg,password:123456', null, null, '127.0.0.1', null, null, 'admin', 'admin', '2019-11-02 10:05:58');
INSERT INTO `sys_log` VALUES ('bb9b66b07cc93ccde9cf0a884ac09049', '4', '1', '用户名: bailu,登录成功！', 'sysUser/login', 'loginName:bailu,password:123456', null, null, '127.0.0.1', null, null, 'admin', 'admin', '2019-11-02 10:31:38');
INSERT INTO `sys_log` VALUES ('d573f0599d32550413490798f55a4baa', '4', '1', '用户名: bailu,登录成功！', 'sysUser/login', 'loginName:bailu,password:123456', null, null, '127.0.0.1', null, null, 'admin', 'admin', '2019-11-02 10:01:19');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `sys_permission_id` varchar(32) NOT NULL COMMENT '主键id',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父id',
  `name` varchar(100) NOT NULL COMMENT '菜单标题',
  `url` varchar(255) DEFAULT NULL COMMENT '路径',
  `component` varchar(255) DEFAULT NULL COMMENT '组件',
  `component_name` varchar(100) DEFAULT NULL COMMENT '组件名字',
  `redirect` varchar(255) DEFAULT NULL COMMENT '一级菜单跳转地址',
  `menu_type` int(11) NOT NULL DEFAULT '0' COMMENT '菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)',
  `perms_code` varchar(255) DEFAULT NULL COMMENT '菜单权限编码',
  `perms_type` varchar(1) NOT NULL DEFAULT '0' COMMENT '权限策略1显示2禁用',
  `keep_alive` tinyint(1) DEFAULT NULL COMMENT '是否缓存该页面:    1:是   0:不是',
  `sort` int(10) NOT NULL DEFAULT '100' COMMENT '菜单排序',
  `always_show` tinyint(1) NOT NULL DEFAULT '0' COMMENT '聚合子路由: 1是0否',
  `icon` varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `is_route` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否路由菜单: 0:不是  1:是（默认值1）',
  `is_leaf` tinyint(1) DEFAULT NULL COMMENT '是否叶子结点:    1:是   0:不是',
  `hidden` int(1) NOT NULL DEFAULT '0' COMMENT '是否隐藏路由: 0否,1是',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_user_id` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除状态 0正常 1已删除',
  `rule_flag` int(3) NOT NULL DEFAULT '0' COMMENT '是否添加数据权限 1是 0否',
  `status` varchar(2) NOT NULL DEFAULT '1' COMMENT '按钮权限状态(0无效1有效)',
  PRIMARY KEY (`sys_permission_id`) USING BTREE,
  KEY `index_prem_pid` (`parent_id`) USING BTREE,
  KEY `index_prem_is_route` (`is_route`) USING BTREE,
  KEY `index_prem_is_leaf` (`is_leaf`) USING BTREE,
  KEY `index_prem_sort_no` (`sort`) USING BTREE,
  KEY `index_prem_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1a0811914300741f4e11838ff37a1d3a', '3f915b2769fc80648e92d04e84ca059d', '手机号禁用', null, null, null, null, '2', 'user:form:phone', '1', null, '100', '0', null, '0', '1', '0', null, 'admin', '2019-05-11 17:19:30', 'admin', '2019-10-31 14:52:15', '0', '0', '1');
INSERT INTO `sys_permission` VALUES ('3f915b2769fc80648e92d04e84ca059d', 'd7d6e2e4e2934f2c9385a623fd98c6f3', '用户管理', '/isystem/user', 'system/UserList', null, null, '1', null, '1', '0', '10', '0', null, '1', '0', '0', null, 'admin', '2018-12-25 20:34:38', 'admin', '2019-11-02 09:16:44', '0', '0', '1');
INSERT INTO `sys_permission` VALUES ('45c966826eeff4c99b8f8ebfe74511fc', 'd7d6e2e4e2934f2c9385a623fd98c6f3', '部门管理', '/isystem/depart', 'system/DepartList', null, null, '1', null, '1', '0', '1', '0', null, '1', '1', '0', null, 'admin', '2019-01-29 18:47:40', 'admin', '2019-11-02 09:16:57', '0', '0', '1');
INSERT INTO `sys_permission` VALUES ('54dd5457a3190740005c1bfec55b1c34', 'd7d6e2e4e2934f2c9385a623fd98c6f3', '菜单管理', '/isystem/permission', 'system/PermissionList', null, null, '1', null, '1', '0', '20', '0', null, '1', '1', '0', null, 'admin', '2018-12-25 20:34:38', null, '2019-11-02 09:18:36', '0', '0', '1');
INSERT INTO `sys_permission` VALUES ('7593c9e3523a17bca83b8d7fe8a34e58', '3f915b2769fc80648e92d04e84ca059d', '添加用户按钮', '', null, null, null, '2', 'user:add', '1', null, '100', '0', null, '1', '1', '0', null, 'admin', '2019-03-16 11:20:33', 'admin', '2019-10-31 14:51:46', '0', '0', '1');
INSERT INTO `sys_permission` VALUES ('9502685863ab87f0ad1134142788a385', '', '首页', '/dashboard/analysis', 'dashboard/Analysis', null, null, '0', null, '1', '0', '1', '0', 'home', '1', '1', '0', null, 'admin', '2018-12-25 20:34:38', 'admin', '2019-11-02 09:16:24', '0', '0', '1');
INSERT INTO `sys_permission` VALUES ('9cb91b8851db0cf7b19d7ecc2a8193dd', '1939e035e803a99ceecb6f5563570fb2', '我的任务表单', '/modules/bpm/task/form/FormModule', 'modules/bpm/task/form/FormModule', null, null, '1', null, '1', null, '100', '0', null, '1', '1', '0', null, 'admin', '2019-03-08 16:49:05', 'admin', '2019-10-31 14:52:15', '0', '0', '1');
INSERT INTO `sys_permission` VALUES ('b6bcee2ccc854052d3cc3e9c96d90197', '71102b3b87fb07e5527bbd2c530dd90a', '加班申请', '/modules/extbpm/joa/JoaOvertimeList', 'modules/extbpm/joa/JoaOvertimeList', null, null, '1', null, '1', null, '100', '0', null, '1', '1', '0', null, 'admin', '2019-04-03 15:33:10', 'admin', '2019-10-31 14:52:15', '0', '0', '1');
INSERT INTO `sys_permission` VALUES ('d7d6e2e4e2934f2c9385a623fd98c6f3', '', '系统管理', '/isystem', 'layouts/RouteView', null, null, '0', null, '1', null, '100', '0', 'setting', '1', '0', '0', null, 'admin', '2018-12-25 20:34:38', 'admin', '2019-10-31 14:52:15', '0', '0', '1');
INSERT INTO `sys_permission` VALUES ('e8af452d8948ea49d37c934f5100ae6a', 'd7d6e2e4e2934f2c9385a623fd98c6f3', '角色管理', '/isystem/role', 'system/RoleList', null, null, '1', null, '1', null, '100', '0', null, '1', '1', '0', null, 'admin', '2018-12-25 20:34:38', null, '2019-10-31 14:52:15', '0', '0', '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `sys_role_id` varchar(32) NOT NULL,
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `role_code` varchar(255) NOT NULL COMMENT '角色代码',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0',
  `sort` int(11) NOT NULL DEFAULT '100' COMMENT '排序',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_id` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`sys_role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('e51758fa916c881624b046d26bd09230', 'admin', 'hr', '0', '100', '管理员', '12', '2019-09-28 14:15:00', 'ds', '2019-11-02 09:50:56');
INSERT INTO `sys_role` VALUES ('ee8626f80f7c2619917b6236f3a7f02b', 'Administrator', 'hr', '0', '1000', '超级管理员', '12', '2019-09-28 14:15:34', '12', '2019-11-02 09:51:57');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `sys_role_permission_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `permission_id` varchar(32) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`sys_role_permission_id`) USING BTREE,
  KEY `index_group_role_per_id` (`role_id`,`permission_id`) USING BTREE,
  KEY `index_group_role_id` (`role_id`) USING BTREE,
  KEY `index_group_per_id` (`permission_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('5dd85bc3da202c52b429f2accf53a5b9', 'e51758fa916c881624b046d26bd09230', '1a0811914300741f4e11838ff37a1d3a');
INSERT INTO `sys_role_permission` VALUES ('450c355d66fdfbc8efd3b00432be7e80', 'e51758fa916c881624b046d26bd09230', '3f915b2769fc80648e92d04e84ca059d');
INSERT INTO `sys_role_permission` VALUES ('86bb2d21de0035bf0ef9ffa9cbd2d952', 'e51758fa916c881624b046d26bd09230', '45c966826eeff4c99b8f8ebfe74511fc');
INSERT INTO `sys_role_permission` VALUES ('f021c78906b519d027f16cf014fb8ff2', 'e51758fa916c881624b046d26bd09230', '54dd5457a3190740005c1bfec55b1c34');
INSERT INTO `sys_role_permission` VALUES ('09ed6aa4b6a63da5345f68398ca2eb75', 'e51758fa916c881624b046d26bd09230', '7593c9e3523a17bca83b8d7fe8a34e58');
INSERT INTO `sys_role_permission` VALUES ('25dd87cadbb67332895f531f8bdc5f5e', 'e51758fa916c881624b046d26bd09230', '9502685863ab87f0ad1134142788a385');
INSERT INTO `sys_role_permission` VALUES ('627c88133c75efbd26bb77cd5dde64be', 'e51758fa916c881624b046d26bd09230', 'd7d6e2e4e2934f2c9385a623fd98c6f3');
INSERT INTO `sys_role_permission` VALUES ('1bbdaf12f793b62fb039787ff667e7ff', 'e51758fa916c881624b046d26bd09230', 'e8af452d8948ea49d37c934f5100ae6a');
INSERT INTO `sys_role_permission` VALUES ('91b5a2a1e77f3c74a4816bb972c96809', 'ee8626f80f7c2619917b6236f3a7f02b', '1a0811914300741f4e11838ff37a1d3a');
INSERT INTO `sys_role_permission` VALUES ('371c5954c1eac1d1c3bd4cdbfe399188', 'ee8626f80f7c2619917b6236f3a7f02b', '3f915b2769fc80648e92d04e84ca059d');
INSERT INTO `sys_role_permission` VALUES ('9e1f573c287c050d466ca2f2bd2fcf15', 'ee8626f80f7c2619917b6236f3a7f02b', '45c966826eeff4c99b8f8ebfe74511fc');
INSERT INTO `sys_role_permission` VALUES ('bf08c0dfb1b86837d680a14e915fedd4', 'ee8626f80f7c2619917b6236f3a7f02b', '54dd5457a3190740005c1bfec55b1c34');
INSERT INTO `sys_role_permission` VALUES ('a05e7fefd40757764c22d05b7e6989bc', 'ee8626f80f7c2619917b6236f3a7f02b', '7593c9e3523a17bca83b8d7fe8a34e58');
INSERT INTO `sys_role_permission` VALUES ('a325b759fc136e50d8114279e664dc81', 'ee8626f80f7c2619917b6236f3a7f02b', '9502685863ab87f0ad1134142788a385');
INSERT INTO `sys_role_permission` VALUES ('6c6d115ec25b0adb026ac566082c4041', 'ee8626f80f7c2619917b6236f3a7f02b', 'd7d6e2e4e2934f2c9385a623fd98c6f3');
INSERT INTO `sys_role_permission` VALUES ('e4ea81e610c98496c16d7d3e352fa4b1', 'ee8626f80f7c2619917b6236f3a7f02b', 'e8af452d8948ea49d37c934f5100ae6a');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `sys_user_id` varchar(32) NOT NULL COMMENT '主键',
  `user_name` varchar(50) NOT NULL COMMENT '真实姓名',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `login_name` varchar(50) NOT NULL COMMENT '登录名称',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `salt` varchar(255) NOT NULL COMMENT 'salt',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `jobs` int(11) DEFAULT NULL COMMENT '职务',
  `sex` int(11) NOT NULL DEFAULT '3' COMMENT '性别(0女 1男 2保密 3未知)',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `picture` varchar(200) DEFAULT NULL COMMENT '头像',
  `card_id` varchar(18) DEFAULT NULL COMMENT '身份证',
  `mobile_phone` varchar(15) DEFAULT NULL COMMENT '移动电话',
  `telephone` varchar(15) DEFAULT NULL COMMENT '固话',
  `short_tel` varchar(50) DEFAULT NULL COMMENT '短号',
  `fax` varchar(15) DEFAULT NULL COMMENT '传真',
  `address` varchar(200) DEFAULT NULL COMMENT '住址',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '状态(0正常 1 冻结)',
  `del_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除状态(0:未删除 1:已删除)',
  `login_count` int(11) NOT NULL DEFAULT '0' COMMENT '登录次数',
  `today_login_count` int(11) NOT NULL DEFAULT '0' COMMENT '今日登录次数',
  `first_login_time` datetime DEFAULT NULL COMMENT '第一次登录时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `create_user_id` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `sort` int(11) NOT NULL DEFAULT '100' COMMENT '序号',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `depart_id` varchar(32) NOT NULL COMMENT '所属部门',
  PRIMARY KEY (`sys_user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('a75d45a015c44384a04449ee80dc3503', 'jeecg', 'jeecg', 'jeecg', '3dd8371f3cf8240e', 'vDDkDzrK', null, '1', '1', '2019-06-10 00:00:00', null, null, null, null, null, null, null, null, '0', '0', '150', '5', '2019-09-27 16:52:19', '2019-11-02 10:21:32', '1', '2019-11-02 09:45:45', null, '2019-11-02 09:45:45', '111', null, '6d35e179cd814e3299bd588ea7daed3f');
INSERT INTO `sys_user` VALUES ('ac948117453c9d520c12d89d15d99577', '蓝莓', '蓝莓', 'lanmei', '1d143401e615ea0d', 'QFIkmKUQ', null, null, '0', '2019-10-29 00:00:00', null, null, '15533333333', null, null, null, null, '1139987837@qq.com', '0', '0', '0', '0', null, null, 'admin', '2019-11-02 10:26:14', null, '2019-11-02 10:26:14', '12', null, '6d35e179cd814e3299bd588ea7daed3f');
INSERT INTO `sys_user` VALUES ('ae49c59005302cd0a5e67723be02cbd3', '威廉姆斯', '威廉姆斯', 'Williams', 'fe89fec5b12aef3916b6c499a55d411e', 'fuv7SX7a', null, null, '3', null, null, null, null, null, null, null, null, null, '0', '0', '0', '0', null, null, 'admin', '2019-11-02 10:26:30', null, '2019-11-02 10:26:30', '100', null, 'bd21454dae3b5a5befaa41031852dd41');
INSERT INTO `sys_user` VALUES ('c7181cf65586508116087bcd7ad06d6e', '莱昂纳德', '莱昂纳德', 'Leonard', '019554d9eaf0ed83', 'qcnQa94s', null, null, '1', '2019-11-02 00:00:00', null, null, null, null, null, null, null, null, '0', '0', '0', '0', null, null, 'admin', '2019-11-02 10:26:33', null, '2019-11-02 10:26:33', '100', null, '3747764ade47307b7af76dbe14b48a5c');
INSERT INTO `sys_user` VALUES ('e10b53110ccb43bdb65fbbf48ca91be8', '乔治', '乔治', 'George', '738839907619C3AB6728771717759561E76DA7D84261946C', '5wcDRvvT', null, null, '1', '2019-11-01 00:00:00', null, null, null, null, null, null, null, '1197798263@qq.com', '0', '0', '0', '0', null, null, '1', '2019-11-02 10:20:25', null, '2019-11-02 10:20:25', '100', null, 'a7d7e77e06c84325a40932163adcdaa6');
INSERT INTO `sys_user` VALUES ('ebd93efe0a0968b2969a93e65499b68d', '拉莫斯', '拉莫斯', 'Ramos', 'ee60c1a8d7a83086', 'Gcu8cmHt', null, null, '1', '2019-11-02 00:00:00', null, null, '15533333333', null, null, null, null, '1139987837@qq.com', '0', '0', '0', '0', null, null, 'admin', '2019-11-02 10:26:37', null, '2019-11-02 10:26:37', '100', null, '65e64b9b47aafa626bc648b0bf9cc3ee');
INSERT INTO `sys_user` VALUES ('fd46cc844856bc33ff4f606fa54b4788', '白鹭', '白鹭', 'bailu', 'dd1594741ff034cb', 'hrkKySyI', null, null, '0', '2019-11-02 00:00:00', null, null, '15533333333', null, null, null, null, '1139987837@qq.com', '0', '0', '3', '3', '2019-11-02 10:01:19', '2019-11-02 10:31:39', 'admin', '2019-11-02 10:26:40', null, '2019-11-02 10:26:40', '100', null, '4475ad9f8cfd500b62bbd5a1a1100162');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `sys_user_role_id` varchar(32) NOT NULL COMMENT '主键id',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`sys_user_role_id`) USING BTREE,
  KEY `index2_groupuu_user_id` (`user_id`) USING BTREE,
  KEY `index2_groupuu_ole_id` (`role_id`) USING BTREE,
  KEY `index2_groupuu_useridandroleid` (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('4d1b7578ef8f7a02e7da148e7e43ec7e', 'a75d45a015c44384a04449ee80dc3503', 'e51758fa916c881624b046d26bd09230');
INSERT INTO `sys_user_role` VALUES ('b3ffd9311a1ca296c44e2409b547384f', 'a75d45a015c44384a04449ee80dc3503', 'ee8626f80f7c2619917b6236f3a7f02b');
INSERT INTO `sys_user_role` VALUES ('1bfacf28717a56a0746ce58e4a101537', 'ac948117453c9d520c12d89d15d99577', 'e51758fa916c881624b046d26bd09230');
INSERT INTO `sys_user_role` VALUES ('61789f63b58b849c7c19b3599e9de83c', 'ac948117453c9d520c12d89d15d99577', 'ee8626f80f7c2619917b6236f3a7f02b');
INSERT INTO `sys_user_role` VALUES ('89da65c7ca88fca6f9128612e30745ba', 'ae49c59005302cd0a5e67723be02cbd3', 'e51758fa916c881624b046d26bd09230');
INSERT INTO `sys_user_role` VALUES ('56480d87acb7023bfb0d1032c497b427', 'c7181cf65586508116087bcd7ad06d6e', 'e51758fa916c881624b046d26bd09230');
INSERT INTO `sys_user_role` VALUES ('09278ef1e033cdbcadbb201e7156df65', 'e10b53110ccb43bdb65fbbf48ca91be8', 'e51758fa916c881624b046d26bd09230');
INSERT INTO `sys_user_role` VALUES ('213b167f14ddecfabbf724542f578fcd', 'ebd93efe0a0968b2969a93e65499b68d', 'e51758fa916c881624b046d26bd09230');
INSERT INTO `sys_user_role` VALUES ('8c02774b5ab1fb25f98e002dfcc7dd40', 'fd46cc844856bc33ff4f606fa54b4788', 'e51758fa916c881624b046d26bd09230');
INSERT INTO `sys_user_role` VALUES ('4fc86303a1eaae0b581a56bd60842466', 'fd46cc844856bc33ff4f606fa54b4788', 'ee8626f80f7c2619917b6236f3a7f02b');
