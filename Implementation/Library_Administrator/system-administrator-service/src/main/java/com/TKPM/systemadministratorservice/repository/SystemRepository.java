package com.TKPM.systemadministratorservice.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import com.TKPM.systemadministratorservice.model.Account;
import com.TKPM.systemadministratorservice.viewmodel.AccountBasicInfo;

public class SystemRepository {
	private Connection conn = null;
	private Statement stmt = null;
	private static String DB_URL = "jdbc:mysql://localhost:3306/tkpm_quanlythuvien";
    private static String USER_NAME = "root";
    private static String PASSWORD = "123456";
    
	public SystemRepository() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			this.stmt = conn.createStatement();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String generateToken() {
		String result = "";
		Random ran = new Random();
		for (int i = 0; i < 15; i++) {
			char c = (char)(ran.nextInt(26) + 'a');
			result += c;
		}
		return result;
	}
	
	public String RenewToken(int accountID, String token) {
		try {
			// Delete old token if exist
			ResultSet rs = this.stmt.executeQuery("select * from sessiontoken where isDeleted = false and accountid = "+ accountID);
			if (rs.isBeforeFirst()) {
				String sql1 = "update SESSIONTOKEN set ISDELETED = true " + 
						"where accountid =" + accountID;
				this.stmt.execute(sql1);
			}
			String sql2 = "INSERT INTO SESSIONTOKEN(ACCOUNTID, TOKEN, ISDELETED) " + 
					"VALUES (" + accountID +", '" + token +"', FALSE);" ;
			this.stmt.execute(sql2);
			return token;
		// Create new token
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return token;
	}
	
	public AccountBasicInfo Login(String username, String password) {
		AccountBasicInfo result = new AccountBasicInfo();
		
		try {
			ResultSet rs = this.stmt.executeQuery("select * from account where isDeleted=false and username = '"
					+ username + "' and password = '"
					+ password + "';");
			if (!rs.isBeforeFirst()) {
				return result;
			}
			
			while (rs.next()) {
				String token = this.generateToken();
				Account data = new Account(
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
				result = new AccountBasicInfo(data, this.RenewToken(data.getID(), token));
				return result;
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
}
