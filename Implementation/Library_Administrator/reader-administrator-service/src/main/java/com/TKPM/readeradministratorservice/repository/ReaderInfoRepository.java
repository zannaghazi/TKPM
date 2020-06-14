package com.TKPM.readeradministratorservice.repository;
import java.sql.*;

public class ReaderInfoRepository {
	public static Connection getDBConnection() throws SQLException, ClassNotFoundException {
		
		String connectionString = "jdbc:mysql://localhost:3306/";
		Connection conn = DriverManager.getConnection(connectionString, "root", "123456");
		return conn;
	}
}
