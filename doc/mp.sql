CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


INSERT INTO `tb_user` VALUES ('1', 'zhangsan', '999999', '张三', '21', '123@ww.com');
INSERT INTO `tb_user` VALUES ('2', 'lisi', '123456', '李四', '19', '456@ww.com');
INSERT INTO `tb_user` VALUES ('3', 'wangwu', '123456', '王五', '20', '789@ww.com');
INSERT INTO `tb_user` VALUES ('4', 'zhaoliu', '123456', '赵六', '21', '101@ww.com');
