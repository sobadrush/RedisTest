package com.ctbc.test.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.ctbc.dao.UrlDAO;
import com.ctbc.vo.UrlMappingVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Url_DAO_Test {

	private UrlDAO urlDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.urlDAO = new UrlDAO();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_001() {
		for (UrlMappingVO vo : urlDAO.getAll()) {
			System.out.println(vo.toString());
		}
	}

	@Test
	public void test_002() {
		UrlMappingVO vo = new UrlMappingVO("https://GGGGGGG.ptt.cc", "https://redis.io/topics/quickstart");
		int pen = urlDAO.addUrlPair(vo);
		Assert.assertEquals("insert失敗！", 1, pen);
	}
	
	@Test
	public void test_003() {		
		int count = urlDAO.getNumOfLongUrl("http://www.runoob.com/redis/redis-java.html");
		System.out.println(" count >>> " + count);
	}

}
