package com.TKPM.readeradministratorservice.repository;
import java.sql.*;
import java.util.ArrayList;

import com.TKPM.readeradministratorservice.model.ReaderInfo;


public class ReaderInfoRepository {
	private Connection conn = null;
	private Statement stmt = null;
	private static String DB_URL = "jdbc:mysql://localhost:3306/tkpm_quanlythuvien";
    private static String USER_NAME = "root";
    private static String PASSWORD = "123456";
    
	public ReaderInfoRepository() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			this.stmt = conn.createStatement();
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	public ArrayList<ReaderInfo> getAllReader() {
		ArrayList<ReaderInfo> result = new ArrayList<ReaderInfo>();
		try {
			ResultSet rs = this.stmt.executeQuery("select * from account where isReader=true");
			while (rs.next()) {
				ReaderInfo temp = new ReaderInfo(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getBoolean(4),
						rs.getDate(5),
						rs.getBoolean(6),
						rs.getBoolean(7),
						rs.getInt(8),
						rs.getString(9),
						rs.getString(10));
				result.add(temp);
			}
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return result;
	}
}
