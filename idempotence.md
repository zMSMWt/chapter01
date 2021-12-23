参考：【https://iflow.uc.cn/webview/news?app=uc-iflow&aid=13902715435526683284&cid=100&zzd_from=uc-iflow&recoid=&rd_type=share&sp_gz=0&pagetype=share&btifl=100&uc_share_depth=1&uc_param_str=dndsfrvesvntnwpfgipc】

何为幂等？
任意多次执行所产生的影响均与一次执行的影响相同。按照这个含义，最终的含义就是对数据库的影响只能是一次性的，不能重复处理。
如何保证其幂等性，通常有以下手段：
1、数据库建立唯一性索引，可以保证最终插入数据库的只有一条数据。
2、token 机制，每次接口请求前先获取一个 token，然后再下次请求的时候在请求的 header 体中加上这个 token，后台进行验证，如果验证通过删除 token，下次请求再次判断 token。
3、悲观锁或者乐观锁，悲观锁可以保证每次 for update 的时候其他 sql 无法 update 数据(在数据库引擎是 innodb 的时候，select 的条件必须是唯一索引,防止锁全表)。
4、先查询后判断，首先通过查询数据库是否存在数据，如果存在证明已经请求过了，直接拒绝该请求；如果不存在，就证明是第一次进来，直接放行。

搭建 Redis 服务 API：
1、首先是搭建 redis 服务器。
2、引入 springboot 中到的 redis 的 stater，或者 Spring 封装的 jedis 也可以，
   后面主要用到的 api 就是它的 set 方法和 exists 方法，这里使用的是 springboot 的封装好的 redisTemplate。

步骤：
1）创建 RedisUtil 工具类；
2）自定义注解 AutoIdempotent；
3）token 的创建和校验；
4）自定义拦截器及其配置类；
5）模拟业务请求进行测试。