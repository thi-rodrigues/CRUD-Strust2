package br.com.soc.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDao {

	public static Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/avaliacao", "admin", "R00t@123");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}