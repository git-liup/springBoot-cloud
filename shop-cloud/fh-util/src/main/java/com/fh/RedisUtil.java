package com.fh;

import redis.clients.jedis.Jedis;

public class RedisUtil {

    public static void del(String key){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            jedis.del(key);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis!=null){
                jedis.close();
                jedis=null;
            }
        }
    }


    public static void set(String key, String value){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            jedis.set(key, value);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis!=null){
                jedis.close();
                jedis=null;
            }
        }
    }

    public static  void expire (String key, int time){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            jedis.expire(key,time);
        } catch (Exception e){
            e.printStackTrace();

        } finally {
            if (jedis!=null){
                jedis.close();
                jedis=null;
            }
        }
    }

    public static String get(String key){
        Jedis jedis =null;
        String result="";
        try {
            jedis = RedisPool.getResource();
            result=jedis.get(key);
        } catch (Exception e){
            e.printStackTrace();
            return result;
        } finally {
            if (jedis!=null){
                jedis.close();
                jedis=null;
            }
        }
        return result;
    }

    public static Boolean setExpireNX(String key, String value, int seconds) {
        Jedis jedis = null;
        try {
            jedis = RedisPool.getInstance();
            Long flag = jedis.setnx(key, value);
            if(flag == 1) {
                jedis.expire(key,seconds);
                return true;
            }
            return false;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(null!=jedis) {
                jedis.close();
                jedis=null;
            }
        }
        return false;
    }

    public static long setIncrExpire(String key, int seconds) {
        Jedis jedis = null;
        Long value = 0l;
        try {
            jedis = RedisPool.getInstance();
            value = jedis.incr(key);
            if(value==1) {
                jedis.expire(key, seconds);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(null!=jedis) {
                jedis.close();
                jedis=null;
            }
        }
        return value;
    }

    public static void main(String[] args) {
        set("user","===============");

        String user = get("user");
        System.out.println(user);

    }



}
