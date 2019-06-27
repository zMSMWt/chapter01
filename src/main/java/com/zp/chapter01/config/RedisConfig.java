package com.zp.chapter01.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@Configuration //applicationContext.xml
public class RedisConfig {

    //注入集群节点信息
    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;

    @Bean //<bean> 标签
    public JedisCluster getJedisCluster(){

        //分割集群节点
        String[] cNodes = clusterNodes.split(",");

        //创建 Set 集合对象
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();

        //循环集群节点对象
        for(String node : cNodes){
            //192.168.66.66:7001
            String[] hp = node.split(":");
            nodes.add(new HostAndPort(hp[0], Integer.parseInt(hp[1])));
        }

        //创建 Redis 集群对象
        JedisCluster jedisCluster = new JedisCluster(nodes);

        return jedisCluster;
    }
}
