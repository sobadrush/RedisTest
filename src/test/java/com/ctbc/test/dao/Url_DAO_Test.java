package com.ctbc.test.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ctbc.dao.UrlDAO;
import com.ctbc.vo.UrlMappingVO;

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
	public void test() {
		for (UrlMappingVO vo : urlDAO.getAll()) {
			System.out.println(vo.toString());
		}
	}

}
