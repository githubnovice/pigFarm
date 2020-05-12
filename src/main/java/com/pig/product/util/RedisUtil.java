package com.pig.product.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 */

public class RedisUtil {
	private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

	// Redis服务器IP
	private static String ADDR = "127.0.0.1";

	// Redis的端口号
	private static int PORT = 6379;

	// 访问密码
	private static String AUTH = "123456";

	// 可用连接实例的最大数目，默认值为8；
	// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private static int MAX_ACTIVE = 1024;

	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 200;

	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = 10000;

	private static int TIMEOUT = 10000;

	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;

	private static JedisPool jedisPool = null;

	/**
	 * 初始化Redis连接池
	 */
	static {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(MAX_WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);
			jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取Jedis实例
	 * 
	 * @return
	 */
	public synchronized static Jedis getJedis() {
		try {
			if (jedisPool != null) {
				Jedis resource = jedisPool.getResource();
				return resource;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取redis键值-object
	 * 
	 * @param key
	 * @return
	 */
	public static Object getObject(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			byte[] bytes = jedis.get(key.getBytes());
			if (!StringUtils.isEmpty(bytes)) {
				return SerializableUtil.unserializable(bytes);
			}
		} catch (Exception e) {
			logger.error("getObject获取redis键值异常:key=" + key + " cause:" + e.getMessage());
		} finally {
			jedis.close();
		}
		return null;
	}

	/**
	 * 设置redis键值-object
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static String setObject(String key, Object value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.set(key.getBytes(), SerializableUtil.serializable(value));
		} catch (Exception e) {
			logger.error("setObject设置redis键值异常:key=" + key + " value=" + value + " cause:" + e.getMessage());
			return null;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public static String setObject(String key, Object value, int expiretime) {
		String result = "";
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.set(key.getBytes(), SerializableUtil.serializable(value));
			if (result.equals("OK")) {
				jedis.expire(key.getBytes(), expiretime);
			}
			return result;
		} catch (Exception e) {
			logger.error("setObject设置redis键值异常:key=" + key + " value=" + value + " cause:" + e.getMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return result;
	}

	/**
	 * 删除key
	 */
	public static Long delkeyObject(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.del(key.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public static Boolean existsObject(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.exists(key.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 设置一个key的过期时间（单位：秒）
	 *
	 * @param key
	 *            key值
	 * @param seconds
	 *            多少秒后过期
	 * @return 1：设置了过期时间 0：没有设置过期时间/不能设置过期时间
	 */
	public static long expire(String key, int seconds) {
		if (key == null || key.equals("")) {
			return 0;
		}
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.expire(key, seconds);
		} catch (Exception e) {
			logger.error("expire 设置过期时间异常：[key=" + key + " seconds=" + seconds + "]" + e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return 0;
	}
}