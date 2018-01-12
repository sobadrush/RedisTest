package _01_GlobalParam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import redis.clients.jedis.Jedis;

public class Init_Table {

	private static final String DRIVER_NAME = "org.sqlite.JDBC";
//	private static final String CONNECTION_URL = "jdbc:sqlite:" + "D:/myWorkspace3/ShortURL_By_Redis" + "/mySqliteDB.db";
	private static final String CONNECTION_URL = "jdbc:sqlite:" + System.getProperty("user.dir") + "/mySqliteDB.db";
	private static final String DROP_TABLE_DDL = " DROP TABLE IF EXISTS URL_SURL_MAPPING ";
	private static final String CREATE_TABLE_DDL = 
			" CREATE TABLE `URL_SURL_MAPPING` ( " +
			"    `id`         INTEGER   NOT NULL  PRIMARY KEY  AUTOINCREMENT , " +
			"    `short_URL`  CHAR(30)  NOT NULL , " +
			"    `real_URL`	 CHAR(50)  NOT NULL " +
			" )";
	private static final String INSERT_DATA_STMT = 
			" INSERT INTO URL_SURL_MAPPING(short_URL,real_URL) VALUES " +
			"  ('https://1qaz25.ptt.cc/' , 'https://webcache.googleusercontent.com/search?q=cache:uUhv0CLoqFwJ:https://blog.techbridge.cc/2016/06/18/redis-introduction/+&cd=2&hl=zh-TW&ct=clnk&gl=tw'), " +
			"  ('https://WERte2.ptt.cc/' , 'http://www.runoob.com/redis/redis-java.html'), " +
			"  ('https://Yertfe2.ptt.cc' , 'https://www.udemy.com/courses/search/?q=c&src=ukw') ";
	private static final String GET_ALL_STMT = " SELECT * FROM URL_SURL_MAPPING ";
	
	private static Connection conn = null;
	private static PreparedStatement pstmt = null;

	static {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(CONNECTION_URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void dropTable() throws SQLException {
		pstmt = conn.prepareStatement(DROP_TABLE_DDL);
		pstmt.executeUpdate();
	}

	public static void createTable() throws SQLException {
		pstmt = conn.prepareStatement(CREATE_TABLE_DDL);
		pstmt.executeUpdate();
	}
	public static void insertData() throws SQLException {
		pstmt = conn.prepareStatement(INSERT_DATA_STMT);
		pstmt.executeUpdate();
	}
	
	public static void initRedis() throws SQLException{
		Jedis jedis = new Jedis(GlobalParam.HOST_IP, GlobalParam.HOST_PORT);
		
		pstmt = conn.prepareStatement(GET_ALL_STMT);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			jedis.setex(rs.getString("short_URL"), 60 * 10 /*保存10分鐘*/, rs.getString("real_URL"));
		}
		jedis.close();
	}
	
	public static void main(String[] args) throws SQLException {
		dropTable();
		createTable();
		insertData();
		System.out.println("======= 初始化TABLE完成 ======");
		initRedis();
		System.out.println("======= 初始化Redis完成 ======");
	}

}
