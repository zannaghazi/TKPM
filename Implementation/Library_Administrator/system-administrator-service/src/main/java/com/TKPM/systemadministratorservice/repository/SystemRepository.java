package com.TKPM.systemadministratorservice.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import com.TKPM.systemadministratorservice.model.Account;
import com.TKPM.systemadministratorservice.viewmodel.AccountBasicInfo;
import com.TKPM.systemadministratorservice.viewmodel.Message;

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
	
	public boolean VerifyToken(String token) {
		try {
			ResultSet rs = this.stmt.executeQuery("select * from SESSIONTOKEN where isDeleted=false and token like '"
					+ token + "';");
			if (!rs.isBeforeFirst()) {
				return false;
			}else {
				return true;
			}
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
			return false;
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
	
	public Message Logout(String token) {
		try {
			String sql = "update sessiontoken set isDeleted = true where token like '"
					+ token + "'";
			this.stmt.execute(sql);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new Message(false, "Đăng xuất thất bại");
		}
		
		return new Message(true, "Đăng xuất thành công");
	}
	
	public Account getAccountByID(int id) {
		Account temp = null;
		
		try {
			ResultSet rs = this.stmt.executeQuery("select * from account where isDeleted = false and id =" + id);
			if (!rs.isBeforeFirst()) {
				return temp;
			}
			if (rs.next()) {
				temp = new Account(
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
				return temp;
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return temp;
	}
	
	public AccountBasicInfo GetCurrentUser(String token) {
		AccountBasicInfo result = new AccountBasicInfo();
		
		try {
			ResultSet rs = this.stmt.executeQuery("select * from sessiontoken where isDeleted = false and token like '"
					+ token + "'");
			int accountID = -1;
			if (rs.next()) {
				accountID = rs.getInt(2);
			}
			rs.close();
			System.out.println(accountID);
			Account temp = this.getAccountByID(accountID);
			if (temp == null) {
				return null;
			}
			return new AccountBasicInfo(temp, token);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
}
