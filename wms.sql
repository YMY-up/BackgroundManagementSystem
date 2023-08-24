
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int NOT NULL COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '名称',
  `role` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `department` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `pid` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of department
-- ----------------------------
BEGIN;
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (1, '权威', '超级管理员', '数据科学与大数据技术', 26);
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (2, '小王', '管理员', '计算机科学技术', 26);
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (3, '小雨', '用户', NULL, 2);
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (4, '马哈哈', '管理员', '软件工程', 26);
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (5, '小闫', '用户', NULL, 2);
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (6, '吕哈哈', '管理员', '光电信息科学与工程', 27);
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (7, '小杨', '用户', NULL, 4);
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (8, '小李', '用户', NULL, 6);
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (9, '小何', '用户', NULL, 6);
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (14, '小哈哈', '用户', NULL, 6);
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (15, '李杨', '管理员', '测控技术与仪器', 28);
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (16, '小明', '用户', NULL, 15);
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (17, '肖砚', '用户', NULL, 15);
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (18, '笑哈哈', '管理员', '电子信息工程', 28);
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (19, '李纯', '用户', NULL, 18);
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (20, '胖胖', '用户', NULL, 18);
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (21, '哈哈', '用户', NULL, 4);
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (22, '小h', '用户', NULL, 4);
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (23, '小丽', '管理员', '通信工程', 27);
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (24, '小蛤', '用户', NULL, 23);
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (25, '消炎', '用户', NULL, 23);
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (26, '计算机科学技术学院', NULL, NULL, 0);
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (27, '光电工程学院', NULL, NULL, 0);
INSERT INTO `department` (`id`, `name`, `role`, `department`, `pid`) VALUES (28, '电子信息学院', NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '货名',
  `storage` int NOT NULL COMMENT '仓库',
  `goodsType` int NOT NULL COMMENT '分类',
  `count` int DEFAULT NULL COMMENT '数量',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of goods
-- ----------------------------
BEGIN;
INSERT INTO `goods` (`id`, `name`, `storage`, `goodsType`, `count`, `remark`) VALUES (1, '钢笔', 2, 3, 1852, '个');
INSERT INTO `goods` (`id`, `name`, `storage`, `goodsType`, `count`, `remark`) VALUES (3, '面包', 1, 2, 724, '包');
INSERT INTO `goods` (`id`, `name`, `storage`, `goodsType`, `count`, `remark`) VALUES (4, '电脑', 3, 1, 1013, '台');
INSERT INTO `goods` (`id`, `name`, `storage`, `goodsType`, `count`, `remark`) VALUES (6, '榴莲', 4, 2, 877, '个');
COMMIT;

-- ----------------------------
-- Table structure for goodstype
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '分类名',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of goodstype
-- ----------------------------
BEGIN;
INSERT INTO `goodstype` (`id`, `name`, `remark`) VALUES (1, '电子产品', NULL);
INSERT INTO `goodstype` (`id`, `name`, `remark`) VALUES (2, '食品', NULL);
INSERT INTO `goodstype` (`id`, `name`, `remark`) VALUES (3, '文具', NULL);
INSERT INTO `goodstype` (`id`, `name`, `remark`) VALUES (4, '生鲜', '');
INSERT INTO `goodstype` (`id`, `name`, `remark`) VALUES (5, '笔记本', '');
COMMIT;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int NOT NULL,
  `menu_code` varchar(8) DEFAULT NULL COMMENT '菜单编码',
  `menu_name` varchar(16) DEFAULT NULL COMMENT '菜单名字',
  `menu_level` varchar(2) DEFAULT NULL COMMENT '菜单级别',
  `menu_click` varchar(16) DEFAULT NULL COMMENT '点击触发的函数',
  `menu_right` varchar(8) DEFAULT NULL COMMENT '权限 0超级管理员，1表示管理员，2表示普通用户，可以用逗号组合使用',
  `menu_component` varchar(200) DEFAULT NULL,
  `menu_icon` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of menu
-- ----------------------------
BEGIN;
INSERT INTO `menu` (`id`, `menu_code`, `menu_name`, `menu_level`, `menu_click`, `menu_right`, `menu_component`, `menu_icon`) VALUES (1, '001', '管理员管理', '1', 'Admin', '0', 'admin/AdminManage.vue', 'el-icon-s-custom');
INSERT INTO `menu` (`id`, `menu_code`, `menu_name`, `menu_level`, `menu_click`, `menu_right`, `menu_component`, `menu_icon`) VALUES (2, '002', '用户管理', '1', 'User', '0,1', 'user/UserManage.vue', 'el-icon-user-solid');
INSERT INTO `menu` (`id`, `menu_code`, `menu_name`, `menu_level`, `menu_click`, `menu_right`, `menu_component`, `menu_icon`) VALUES (3, '003', '仓库管理', '1', 'Storage', '0,1', 'storage/StorageManage.vue', 'el-icon-office-building');
INSERT INTO `menu` (`id`, `menu_code`, `menu_name`, `menu_level`, `menu_click`, `menu_right`, `menu_component`, `menu_icon`) VALUES (4, '004', '物品类型管理', '1', 'Goodstype', '0,1', 'goodstype/GoodstypeManage.vue', 'el-icon-menu');
INSERT INTO `menu` (`id`, `menu_code`, `menu_name`, `menu_level`, `menu_click`, `menu_right`, `menu_component`, `menu_icon`) VALUES (5, '005', '物品管理', '1', 'Goods', '0,1,2', 'goods/GoodsManage', 'el-icon-box');
INSERT INTO `menu` (`id`, `menu_code`, `menu_name`, `menu_level`, `menu_click`, `menu_right`, `menu_component`, `menu_icon`) VALUES (6, '006', '记录管理', '1', 'Record', '0,1,2', 'record/RecordManage', 'el-icon-document');
INSERT INTO `menu` (`id`, `menu_code`, `menu_name`, `menu_level`, `menu_click`, `menu_right`, `menu_component`, `menu_icon`) VALUES (7, '007', '部门管理', '1', 'Department', '0,1', 'department/Department', 'el-icon-document');
COMMIT;

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods` int NOT NULL COMMENT '货品id',
  `userId` int DEFAULT NULL COMMENT '取货人/补货人',
  `admin_id` int DEFAULT NULL COMMENT '操作人id',
  `count` int DEFAULT NULL COMMENT '数量',
  `createtime` datetime DEFAULT NULL COMMENT '操作时间',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of record
-- ----------------------------
BEGIN;
INSERT INTO `record` (`id`, `goods`, `userId`, `admin_id`, `count`, `createtime`, `remark`) VALUES (1, 2, 3, 1, 10, '2023-07-14 20:31:22', NULL);
INSERT INTO `record` (`id`, `goods`, `userId`, `admin_id`, `count`, `createtime`, `remark`) VALUES (2, 2, 3, 1, 10, NULL, '');
INSERT INTO `record` (`id`, `goods`, `userId`, `admin_id`, `count`, `createtime`, `remark`) VALUES (3, 1, 3, 1, 10, NULL, '个');
INSERT INTO `record` (`id`, `goods`, `userId`, `admin_id`, `count`, `createtime`, `remark`) VALUES (4, 1, 3, 1, 100, '2023-07-05 20:31:36', '个');
INSERT INTO `record` (`id`, `goods`, `userId`, `admin_id`, `count`, `createtime`, `remark`) VALUES (7, 1, 10, 1, 20, NULL, '');
INSERT INTO `record` (`id`, `goods`, `userId`, `admin_id`, `count`, `createtime`, `remark`) VALUES (8, 4, 12, 1, 12, '2023-07-20 20:31:40', '');
INSERT INTO `record` (`id`, `goods`, `userId`, `admin_id`, `count`, `createtime`, `remark`) VALUES (10, 1, 7, 1, 12, NULL, '');
INSERT INTO `record` (`id`, `goods`, `userId`, `admin_id`, `count`, `createtime`, `remark`) VALUES (11, 2, 8, 1, -12, NULL, '');
INSERT INTO `record` (`id`, `goods`, `userId`, `admin_id`, `count`, `createtime`, `remark`) VALUES (12, 3, 3, 1, 200, NULL, '');
INSERT INTO `record` (`id`, `goods`, `userId`, `admin_id`, `count`, `createtime`, `remark`) VALUES (13, 4, 5, 1, 999, NULL, '');
INSERT INTO `record` (`id`, `goods`, `userId`, `admin_id`, `count`, `createtime`, `remark`) VALUES (14, 3, 18, 1, -19, NULL, '');
INSERT INTO `record` (`id`, `goods`, `userId`, `admin_id`, `count`, `createtime`, `remark`) VALUES (15, 3, 3, 2, -12, NULL, '');
INSERT INTO `record` (`id`, `goods`, `userId`, `admin_id`, `count`, `createtime`, `remark`) VALUES (16, 2, 5, 2, -16, NULL, '');
INSERT INTO `record` (`id`, `goods`, `userId`, `admin_id`, `count`, `createtime`, `remark`) VALUES (17, 1, 3, 1, 20, NULL, '');
INSERT INTO `record` (`id`, `goods`, `userId`, `admin_id`, `count`, `createtime`, `remark`) VALUES (18, 3, 3, 1, 555, NULL, '');
INSERT INTO `record` (`id`, `goods`, `userId`, `admin_id`, `count`, `createtime`, `remark`) VALUES (19, 3, 11, 1, 10, NULL, '');
INSERT INTO `record` (`id`, `goods`, `userId`, `admin_id`, `count`, `createtime`, `remark`) VALUES (20, 3, 14, 1, -20, NULL, '');
INSERT INTO `record` (`id`, `goods`, `userId`, `admin_id`, `count`, `createtime`, `remark`) VALUES (21, 1, 12, 1, 123, NULL, '斤');
INSERT INTO `record` (`id`, `goods`, `userId`, `admin_id`, `count`, `createtime`, `remark`) VALUES (22, 6, 16, 1, -123, NULL, '个');
COMMIT;

-- ----------------------------
-- Table structure for storage
-- ----------------------------
DROP TABLE IF EXISTS `storage`;
CREATE TABLE `storage` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '仓库名',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of storage
-- ----------------------------
BEGIN;
INSERT INTO `storage` (`id`, `name`, `remark`) VALUES (1, '食品库', '217');
INSERT INTO `storage` (`id`, `name`, `remark`) VALUES (2, '文具库', '');
INSERT INTO `storage` (`id`, `name`, `remark`) VALUES (3, '电子产品库', '121');
INSERT INTO `storage` (`id`, `name`, `remark`) VALUES (4, '工具库', NULL);
INSERT INTO `storage` (`id`, `name`, `remark`) VALUES (6, '仓库3', NULL);
INSERT INTO `storage` (`id`, `name`, `remark`) VALUES (8, '物理仓库', '32');
INSERT INTO `storage` (`id`, `name`, `remark`) VALUES (9, '高数仓库', '');
INSERT INTO `storage` (`id`, `name`, `remark`) VALUES (10, '线代仓库', '离线饿');
INSERT INTO `storage` (`id`, `name`, `remark`) VALUES (11, '离散仓库', '这个啥也没有');
INSERT INTO `storage` (`id`, `name`, `remark`) VALUES (12, '传统仓库', NULL);
INSERT INTO `storage` (`id`, `name`, `remark`) VALUES (13, '高端仓库32', '黄金门票');
INSERT INTO `storage` (`id`, `name`, `remark`) VALUES (14, '123', '123');
INSERT INTO `storage` (`id`, `name`, `remark`) VALUES (15, '日立库', '');
INSERT INTO `storage` (`id`, `name`, `remark`) VALUES (16, '日立酷', '123');
INSERT INTO `storage` (`id`, `name`, `remark`) VALUES (17, '调料库', '');
INSERT INTO `storage` (`id`, `name`, `remark`) VALUES (18, 'asd123', '');
INSERT INTO `storage` (`id`, `name`, `remark`) VALUES (19, 'asd', '');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `no` varchar(20) DEFAULT NULL COMMENT '账号',
  `name` varchar(100) NOT NULL COMMENT '名字',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `age` int DEFAULT NULL,
  `sex` int DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `role_id` int DEFAULT NULL COMMENT '⻆⾊ 0超级管理员，1管理员，2普通账号',
  `isValid` varchar(4) DEFAULT 'Y' COMMENT '是否有效，Y有效，其他⽆效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `no`, `name`, `password`, `age`, `sex`, `phone`, `role_id`, `isValid`) VALUES (1, 'cust', '权威', 'y53e0bd92ebf37dcbbf9b43594446e53d15ym', 18, 1, '14545657678', 0, 'Y');
INSERT INTO `user` (`id`, `no`, `name`, `password`, `age`, `sex`, `phone`, `role_id`, `isValid`) VALUES (2, 'aaa', '小王', 'y53e0bd92ebf37dcbbf9b43594446e53d15ym', 19, 1, '19876765657', 1, 'Y');
INSERT INTO `user` (`id`, `no`, `name`, `password`, `age`, `sex`, `phone`, `role_id`, `isValid`) VALUES (3, 'bbb', '小雨', 'y53e0bd92ebf37dcbbf9b43594446e53d15ym', 20, 0, '13434546767', 2, 'Y');
INSERT INTO `user` (`id`, `no`, `name`, `password`, `age`, `sex`, `phone`, `role_id`, `isValid`) VALUES (4, '123000', '小鹏', 'y53e0bd92ebf37dcbbf9b43594446e53d15ym', 45, 1, '12345675643', 1, 'Y');
INSERT INTO `user` (`id`, `no`, `name`, `password`, `age`, `sex`, `phone`, `role_id`, `isValid`) VALUES (5, 'ccc', '小闫', 'y53e0bd92ebf37dcbbf9b43594446e53d15ym', 20, 1, '19869765611', 2, 'Y');
INSERT INTO `user` (`id`, `no`, `name`, `password`, `age`, `sex`, `phone`, `role_id`, `isValid`) VALUES (9, '112123', '鹏子遛狗', 'y53e0bd92ebf37dcbbf9b43594446e53d15ym', 21, 1, '12312125623', 1, 'Y');
INSERT INTO `user` (`id`, `no`, `name`, `password`, `age`, `sex`, `phone`, `role_id`, `isValid`) VALUES (11, '2123', '小杨', 'y53e0bd92ebf37dcbbf9b43594446e53d15ym', 19, 0, '15222150017', 2, 'Y');
INSERT INTO `user` (`id`, `no`, `name`, `password`, `age`, `sex`, `phone`, `role_id`, `isValid`) VALUES (12, '777654', '小李', 'y53e0bd92ebf37dcbbf9b43594446e53d15ym', 19, 0, '15222152218', 2, 'Y');
INSERT INTO `user` (`id`, `no`, `name`, `password`, `age`, `sex`, `phone`, `role_id`, `isValid`) VALUES (13, '66677', '小何', 'y53e0bd92ebf37dcbbf9b43594446e53d15ym', 19, 0, '13232323232', 1, 'Y');
INSERT INTO `user` (`id`, `no`, `name`, `password`, `age`, `sex`, `phone`, `role_id`, `isValid`) VALUES (14, '657575', '小哈哈', 'y53e0bd92ebf37dcbbf9b43594446e53d15ym', 19, 1, '13245253529', 2, 'Y');
INSERT INTO `user` (`id`, `no`, `name`, `password`, `age`, `sex`, `phone`, `role_id`, `isValid`) VALUES (16, '1236547', '小明', 'y53e0bd92ebf37dcbbf9b43594446e53d15ym', 12, 1, '17686578687', 2, 'Y');
INSERT INTO `user` (`id`, `no`, `name`, `password`, `age`, `sex`, `phone`, `role_id`, `isValid`) VALUES (18, 'ddd', '笑哈哈', 'y53e0bd92ebf37dcbbf9b43594446e53d15ym', 19, 0, '17287866051', 2, 'Y');
INSERT INTO `user` (`id`, `no`, `name`, `password`, `age`, `sex`, `phone`, `role_id`, `isValid`) VALUES (19, 'LLC', '李纯', 'y53e0bd92ebf37dcbbf9b43594446e53d15ym', 24, 0, '12345646789', 2, 'Y');
INSERT INTO `user` (`id`, `no`, `name`, `password`, `age`, `sex`, `phone`, `role_id`, `isValid`) VALUES (20, 'PPP', '胖胖', 'y53e0bd92ebf37dcbbf9b43594446e53d15ym', 12, 1, '12300044430', 2, 'Y');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
