package com.ctbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ctbc.vo.UrlMappingVO;

public class UrlDAO {
	private static final String DRIVER_NAME = "org.sqlite.JDBC";
//	private static final String PROJECT_PATH = System.getProperty("user.dir");
//	private static final String CONNECTION_URL = "jdbc:sqlite:" + PROJECT_PATH + "/mySqliteDB.db";
	private static final String CONNECTION_URL = "jdbc:sqlite:" + "D:/myWorkspace3/ShortURL_By_Redis" + "/mySqliteDB.db";

	private static final String GET_ALL_STMT = " SELECT * FROM URL_SURL_MAPPING ";
	private static final String INSERT_INTO_STMT = " INSERT INTO URL_SURL_MAPPING( short_URL , real_URL ) VALUES ( ? , ? ) ";
	private static final String SELECT_COUNT_LONG_URL = " SELECT COUNT(1) AS cc FROM URL_SURL_MAPPING WHERE real_url = ? ";
	
	static {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	public int getNumOfLongUrl(String longUrl) {
		int pen = 0;
		try(Connection conn = DriverManager.getConnection(CONNECTION_URL);
			PreparedStatement pstmt = conn.prepareStatement(SELECT_COUNT_LONG_URL);
			) 
		{
			pstmt.setString(1, longUrl);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pen = rs.getInt("cc");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pen;
	}
	
	public int addUrlPair(UrlMappingVO vo){
		int pen = 0;
		try(Connection conn = DriverManager.getConnection(CONNECTION_URL);
			PreparedStatement pstmt = conn.prepareStatement(INSERT_INTO_STMT);
			) 
		{
			pstmt.setString(1, vo.getShortUrl());
			pstmt.setString(2, vo.getRealUrl());
			pen = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pen;
	}
	
	public List<UrlMappingVO> getAll() {
		//---------------------------------------------
		List<UrlMappingVO> urlList = new ArrayList<>();
		//---------------------------------------------
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(CONNECTION_URL);
			pstmt = conn.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString(1) + "  ,  " + rs.getString(2) +  "  ,  " + rs.getString(3));
				UrlMappingVO vo = new UrlMappingVO();
				vo.setId(rs.getInt("id"));
				vo.setShortUrl(rs.getString("short_URL"));
				vo.setRealUrl(rs.getString("real_URL"));
				urlList.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return urlList;
	}
}
