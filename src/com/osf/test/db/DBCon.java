package com.osf.test.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.osf.test.io.ReadFile;

public class DBCon {
	private static final String URL; // 선언
	private static final String USER;
	private static final String PASSWORD;
	private static final String DRIVER;
	private static Connection con = null;
	public static final String CLIENTID;
	public static final String CLIENTSECRET;
	public static final String NAVER_URL;
	public static final String METHOD;
	
	static { // 여기서 초기화 여기선느 실행부가 가능해서 여기서 돌린ㄴ느 것도 있음 .
		InputStream is = ReadFile.class.getResourceAsStream("/com/osf/test/config/db.properties");
		Properties prop = new Properties();
		try {
			prop.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		URL = prop.getProperty("url");
		USER = prop.getProperty("user");
		PASSWORD = prop.getProperty("password");
		DRIVER = prop.getProperty("classname");
		CLIENTID = prop.getProperty("clientId");
		CLIENTSECRET=prop.getProperty("clientSecret");
		NAVER_URL = prop.getProperty("naverUrl");
		METHOD = prop.getProperty("method");

	}

	public static Connection getCon() {
		if (con == null) {

			try {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return con;
	}

	public static void close() {
		if (con != null) {
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		con = null;

	}
	public static void main(String[] args) {
		getCon();
		close();
	}

}
