package com.tinygame.herostory.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis使用工具类
 */
public final class RedisUtil {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtil.class);

    /**
     * Redis连接池
     */
    private static JedisPool _jedisPool = null;

    /**
     * 私有化类默认构造器
     */
    private RedisUtil() {
    }

    /**
     * 初始化
     */
    public static void init() {
        try {
            _jedisPool = new JedisPool("192.168.74.111", 6379);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * 获取Redis实例
     *
     * @return Redis实例
     */
    public static Jedis getRedis() {
        if (null == _jedisPool) {
            throw new RuntimeException("_jedisPool尚未初始化");
        }

        Jedis redis = _jedisPool.getResource();

        return redis;
    }
}
