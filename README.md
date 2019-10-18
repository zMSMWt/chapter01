
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
  `role` varchar(50) DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户信息';

**************************************************************************
1、如何使用 springboot，springSecurity，jwt 实现基于 token 的权限管理；
2、统一处理无权限请求的结果。
3、具体步骤：
    1）搭建 springboot 工程
    2）导入 springSecurity 跟 jwt 的依赖
    3）用户的实体类
    4）dao 层
    5）service 层（真正开发时再写，这里就直接调用 dao 层操作数据库）
    6）实现 UserDetailsService 接口
    7）实现 UserDetails 接口
    8）验证用户登录信息的拦截器
    9）验证用户权限的拦截器
    10）springSecurity 配置
    11）认证的 Controller 以及测试的 controller
    12）测试

【参照链接：https://blog.csdn.net/ech13an/article/details/80779973】