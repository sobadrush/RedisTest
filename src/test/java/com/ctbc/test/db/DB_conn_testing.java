package com.ctbc.test.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DB_conn_testing {

	
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

	private static final  String DRIVER_NAME= "org.sqlite.JDBC";
	private static final String CONNECTION_URL = "jdbc:sqlite:mySqliteDB.db";
	
	@Test
	public void test() {
		
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(CONNECTION_URL);
			
			System.err.println(" DB NAME >>> " + conn.getMetaData().getDatabaseProductName());
			
		 	PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM URL_SURL_MAPPING");
		 	ResultSet rs = pstmt.executeQuery();
		 	while (rs.next()) {
				System.out.println(rs.getString(1) + "  ,  " + rs.getString(2) +  "  ,  " + rs.getString(3));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
