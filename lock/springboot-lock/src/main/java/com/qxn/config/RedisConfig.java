package com.qxn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

//标识配置类
@Configuration
//引入配置文件
@PropertySource("classpath:/properties/redis.properties")
public class RedisConfig {
//    @Value("${redis.host}")
//    private String host;
//    @Value("${redis.port}")
//    private Integer port;
//    //单个redis
//    @Bean//<bean id="jedis" class="com.qxn.config.Jedis" />
//    public Jedis jedis() {
//        return new Jedis(host, port);
//    }

//    @Value("${redis.nodes}")
//    private String nodes;
//    //redis分片
//    @Bean
//    public ShardedJedis shardedJedis() {
//        List<JedisShardInfo> shards = getList();
//        return new ShardedJedis(shards);
//    }
//    private List<JedisShardInfo> getList() {
//        List<JedisShardInfo> list = new ArrayList<>();
//        String[] arrayNodes = nodes.split(","); //node=ip:port
//        for (String node:arrayNodes){
//            String host = node.split(":")[0];
//            int port = Integer.parseInt(node.split(":")[1]);
//            list.add(new JedisShardInfo(host,port));
//        }
//        return list;
//    }

    /**
     * 配置哨兵
     */
//    @Value("${redis.sentinel}")
//    private String sentinel;
//    @Bean//单例池
//    public JedisSentinelPool jedisSentinelPool() {
//        Set<String> sentinels = new HashSet<>();
//        sentinels.add(sentinel);
//        JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", sentinels);
//        return sentinelPool;
//    }
//    @Bean
//    @Scope("prototype")//单例池导入，多例jedis，效率最高
//    public Jedis jedis(JedisSentinelPool sentinelPool) {
//        return sentinelPool.getResource();
//    }

    @Value("${redis.cluster}")
    private String cluster; //node,node,node
    /**
     * 配置redis集群
     */
    @Bean
    public JedisCluster jedisCluster() {
        Set<HostAndPort> nodeSet = new HashSet<HostAndPort>();
        String[] arrayNodes = cluster.split(",");
        for (String node : arrayNodes) { //node=host:port
            String host = node.split(":")[0];
            int port = Integer.parseInt(node.split(":")[1]);
            nodeSet.add(new HostAndPort(host, port));
        }
        return new JedisCluster(nodeSet);
    }
}
