package test;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class CommonUse {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost");
		setStringKey(jedis);
		setListKey(jedis);
		getKeyByRegex(jedis);
		jedis.close();
	}

	private static void setStringKey(Jedis jedis) {
		System.out.println(jedis.ping());
		System.out.println();
		jedis.set("name", "zhangsan");
		System.out.println(jedis.get("name"));
		System.out.println();
	}

	private static void setListKey(Jedis jedis) {
		jedis.lpush("students", "lisi");
		jedis.lpush("students", "wanger");
		jedis.lpush("students", "mazi");
		// 取一个key的list值
		List<String> students = jedis.lrange("students", 0, 2);
		for (String str : students) {
			System.out.println(str);
		}
		System.out.println();
	}

	private static void getKeyByRegex(Jedis jedis) {
		// 正则
		Set<String> keys = jedis.keys("*");
		for (String key : keys) {
			System.out.println(key);
		}
		System.out.println();
	}

//	private static void setHashKey(Jedis jedis) {
//		jedis.h
//	}

}
