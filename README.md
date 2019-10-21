
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';
##################################################################################################

1、如何使用 springboot，springSecurity，jwt 实现基于 token 的权限管理；
2、统一处理无权限请求的结果；
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
    11）认证的 Controller 以及测试的 Controller
    12）测试
##################################################################################################
  User、JwtTokenUtils、UserRepository、UserDetailsServiceImpl、JwtUser、                         
  JWTAuthenticationFilter、JWTAuthorizationFilter、SecurityConfig、AuthController、TaskController
 【参照：https://blog.csdn.net/ech13an/article/details/80779973  
   Github: springboot-jwt-demo】
##################################################################################################

基于角色的权限管理的思路：
1、用户验证成功，根据用户名以及过期时间生成 token；
2、权限验证，假如能从 token 中获取用户名则该 token 验证成功；
3、创建一个 UsernamePasswordAuthenticationToken 该 token 包含用户的角色信息，
   而不是一个空的 ArrayList，查看一下源代码是有以下一个构造方法的。
##################################################################################################
   public UsernamePasswordAuthenticationToken(Object principal, Object credentials,
    			Collection<? extends GrantedAuthority> authorities) {
    		super(authorities);
    		this.principal = principal;
    		this.credentials = credentials;
    		super.setAuthenticated(true); // must use super, as we override
    }
##################################################################################################
   
jwt 是由三部分组成的：
第一部分我们称它为头部（header)；
第二部分我们称其为载荷（payload)；
第三部分是签证（signature)。
【此处使用 payload 去存储我们的用户角色信息，由于第一第二部分都是公开的，任何人都能知道里面的信息，不建议存储一些比较敏感的数据，但是存放角色信息还是没有问题的。】
