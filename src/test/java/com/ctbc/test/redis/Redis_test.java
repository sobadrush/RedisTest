package com.ctbc.test.redis;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import redis.clients.jedis.Jedis;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Redis_test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_001() {
		// 連接本機的 Redis 服務
//		Jedis jedis = new Jedis("127.0.0.1", 6379);
		Jedis jedis = new Jedis("localhost", 6379);
		System.out.println("連接成功");
//		// 查看服務是否運行
		System.out.println("服務正在運行: " + jedis.ping());
		jedis.close();
	}
	
	@Test
	public void test_002() {
		// 連接本機的 Redis 服務
//		Jedis jedis = new Jedis("127.0.0.1", 6379);
		Jedis jedis = new Jedis("localhost", 6379);
		System.out.println("連接成功");
//		// 查看服務是否運行
		System.out.println("服務正在運行: " + jedis.ping());
		
		// 設置 redis 字符串数据
        jedis.set("testRedisKey", "www.blackRabbiter.com");
        // 獲取存儲的字串
        System.out.println("redis 存的value : "+ jedis.get("testRedisKey"));
        
        // 刪除存儲的字串 , 參考： http://redisdoc.com/key/del.html
//        jedis.del("testRedisKey");
        
		jedis.close();
	}

	
	
}
