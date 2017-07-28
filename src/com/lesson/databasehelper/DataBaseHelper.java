package com.lesson.databasehelper;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

/**
 * ��ݿ����ӣ�����ģʽ
 * 
 * @author ���
 * 
 */
public class DataBaseHelper {
	private static Connection conn = null;
	private static DataBaseHelper db = null;

	private DataBaseHelper() {
		// TODO Auto-generated constructor stub
	}

	private static DataBaseHelper getInstance() {
		if (db == null) {
			db = new DataBaseHelper();
			db.setConnection();
		}
		return db;
	}

	public static Connection getConnection() {
		return getInstance().conn;
	}

	private static void setConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/lesson";
			String user = "root";
			String password = "123456";
			conn = (Connection) DriverManager.getConnection(url, user,
					password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
