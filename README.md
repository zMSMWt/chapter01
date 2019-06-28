
用户表结构：

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(50) NOT NULL COMMENT '用户姓名',
  `password` varchar(30) NOT NULL COMMENT '用户密码',
  `birthday` date DEFAULT NULL COMMENT '用户生日',
  `age` int(11) DEFAULT NULL COMMENT '用户年龄',
  `sex` varchar(2) DEFAULT NULL COMMENT '用户性别(0：男 1：女)',
  `phone` varchar(11) DEFAULT NULL COMMENT '用户电话',
  `address` varchar(50) DEFAULT NULL COMMENT '用户住址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户信息';
