package com.fh;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {


    private static JedisPool pool;

    private static void  initPool(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(1000);
        poolConfig.setMaxIdle(100);
        poolConfig.setMinIdle(100);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        pool = new JedisPool(poolConfig,"192.168.1.178",6379);
    }

    static {
        initPool();
    }
    public static Jedis getInstance() {
        return pool.getResource();
    }
    private RedisPool(){

    }

    public static Jedis getResource(){
        return pool.getResource();
    }


}
