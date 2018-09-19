package test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class PoolUse {

	public static void main(String[] args) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(8);
		config.setMaxTotal(18);
		JedisPool pool = new JedisPool(config, "localhost", 6379);
		Jedis jedis = pool.getResource();
		String string = jedis.get("name");
		System.out.println(string);
		jedis.close();
		pool.close();
	}

}
