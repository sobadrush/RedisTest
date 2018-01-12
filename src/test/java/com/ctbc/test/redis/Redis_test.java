package com.ctbc.test.redis;

import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import _01_GlobalParam.GlobalParam;
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
		Jedis jedis = new Jedis("localhost", GlobalParam.HOST_PORT); // 也可以
		System.out.println("test_001 >>> 連接成功");
		// 查看服務是否運行
		System.out.println("服務正在運行: " + jedis.ping());
		jedis.close();
	}

	@Test
	public void test_002() {
		// 連接本機的 Redis 服務
//		Jedis jedis = new Jedis(GlobalParam.HOST_IP, GlobalParam.HOST_PORT);
		Jedis jedis = new Jedis("localhost", 6379);
		System.out.println("test_002 >>> 連接成功");
		System.out.println("服務正在運行: " + jedis.ping());

		// 設置 redis 字符串数据
		jedis.set("testRedisKey", "www.blackRabbiter.com");
		// 獲取存儲的字串
		System.out.println("redis 存的value : " + jedis.get("testRedisKey"));

		Assert.assertEquals("test002錯誤！","www.blackRabbiter.com", jedis.get("testRedisKey"));
		
		// 刪除存儲的字串 , 參考： http://redisdoc.com/key/del.html
        jedis.del("testRedisKey");
		jedis.close();
	}

	@Test
	public void test_003() {
		Jedis jedis = new Jedis(GlobalParam.HOST_IP, GlobalParam.HOST_PORT);
		System.out.println("test_003 >>> 連接成功");
		System.out.println("服務正在運行: " + jedis.ping());

		jedis.setex("tenSecKey", 10, "測試10秒key"); // 設定有expire時間的key(單位:秒)
		System.out.println(jedis.get("tenSecKey"));
		
		jedis.close();
	}

	
	@Test
	public void test_999() {
		Jedis jedis = new Jedis(GlobalParam.HOST_IP, GlobalParam.HOST_PORT);
		System.out.println("test_999 >>> 連接成功");
		System.out.println("服務正在運行: " + jedis.ping());
		
		jedis.set("test", "tttt");
		Set<String> kSet = jedis.keys("*");// 列出所有key
		int length = kSet.size();
		
		for (String kk : kSet) {
			System.out.println(kk + " ::: " + jedis.get(kk));			
		}
		
		jedis.flushAll();		
		
		System.out.println("========================================");
		
		for (String kk : kSet) {
			System.out.println(kk + " ::: " + jedis.get(kk));			
		}
		System.out.println(kSet);
		Assert.assertEquals("清空緩存失敗！",length, kSet.size());
		jedis.close();
	}
}
