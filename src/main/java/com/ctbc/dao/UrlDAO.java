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
	private static final String CONNECTION_URL = "jdbc:sqlite:mySqliteDB.db";

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static final String GET_ALL_STMT = "SELECT * FROM URL_SURL_MAPPING";

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
				vo.setShortUrl(rs.getString("rand_URL"));
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
