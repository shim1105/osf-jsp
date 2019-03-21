package com.osf.test.io;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class ReadFile {
	private static final String URL; // 선언
	private static final String USER;
	private static final String PASSWORD;
	private static final String DRIVER;
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

	}


	public static void main(String[] args) {
		System.out.println(URL);
		System.out.println(USER);
		System.out.println(PASSWORD);
		System.out.println(DRIVER);
	}
}
