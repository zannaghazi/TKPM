package com.TKPM.systemadministratorservice.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.TKPM.systemadministratorservice.model.Account;
import com.TKPM.systemadministratorservice.model.SystemConstrants;
import com.TKPM.systemadministratorservice.viewmodel.AccountBasicInfo;
import com.TKPM.systemadministratorservice.viewmodel.AccountInfo;
import com.TKPM.systemadministratorservice.viewmodel.Message;
import com.TKPM.systemadministratorservice.viewmodel.SearchAccountInfo;
import com.TKPM.systemadministratorservice.viewmodel.SystemConstBasic;

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
	
	public int getAccountIDByToken(String token) {
		try {
			ResultSet rs = this.stmt.executeQuery("select * from SESSIONTOKEN where token like binary'"
					+ token +"'");
			int accountId = -1;
			if (rs.next()) {
				accountId = rs.getInt(2);
			}
			return accountId;
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
			return -1;
		}
	}
	
	public boolean VerifyToken(String token) {
		try {
			ResultSet rs = this.stmt.executeQuery("select * from SESSIONTOKEN where isDeleted=false and token like binary '"
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
	
	public Account getNewestAccount() {
		try {
			ResultSet rs = this.stmt.executeQuery("Select * from account order by id desc limit 1");
			Account data = null;
			if (rs.next()) {
				data = new Account(
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
			}
			return data;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void CreateLibraryCard(int accountID, int currentUserID) {
		try {
			String sql = "select * from SYSTEMCONSTANTS where name like binary'Thời hạn chung của thẻ'";
			ResultSet rs = this.stmt.executeQuery(sql);
			int duration = 3;
			if (rs.next()) {
				duration = Integer.parseInt(rs.getString(3));
			}
			sql = "insert into LIBRARYCARD(duration, accountid, UPDATEDACCOUNT) "
					+ "values ("
					+ duration + ", "
					+ accountID +", "
					+ currentUserID +")";
			this.stmt.execute(sql);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Message AddNewAccount(AccountInfo data) {
		try {
			String gender = data.gender? "true" : "false";
			String reader = (data.role == 4) ? "true" : "false";
			String sql = "INSERT INTO ACCOUNT(FULLNAME, ADDRESS, GENDER, BIRTHDAY, ISDELETED, ISREADER, ROLE, USERNAME, PASSWORD, UPDATEDACCOUNT) " +
					"VALUES('" + data.fullName + "', '"
					+ data.address +"', "
					+ gender + ", '"
					+ data.birthDay + "', "
					+ "false, "
					+ reader + ", "
					+ data.role + ", '"
					+ data.username + "', '"
					+ data.password + "', "
					+ data.currentUserID + ")";
			this.stmt.execute(sql);
			
			Account newest = this.getNewestAccount();
			
			if (data.role == 4) {
				this.CreateLibraryCard(newest.getID(), data.currentUserID);
			}
			return new Message(true, "Thêm tài khoản thành công!");
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new Message(false, "Thêm tài khoản thất bại!");
		}
	}
	
	public Message UpdateAccountInfo(AccountInfo data) {
		try {
			String gender = data.gender? "true" : "false";
			String reader = (data.role == 4)? "true" : "false";
			String sql = "update account set "
					+ "fullname = '" + data.fullName + "', "
					+ "address = '" + data.address + "', "
					+ "gender = " + gender + ", "
					+ "birthday = '" + data.birthDay + "', "
					+ "isReader = " + reader + ", "
					+ "role = " + data.role + ", "
					+ "username = '" + data.username + "', "
					+ "password = '" + data.password + "', "
					+ "updatedaccount = " + data.currentUserID
					+ " where id = " + data.ID;
			this.stmt.execute(sql);
			return new Message(true, "Cập nhật tài khoản thành công!");
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new Message(false, "Cập nhật tài khoản thất bại!");
		}
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
			String sql = "update sessiontoken set isDeleted = true where token like binary '"
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
			ResultSet rs = this.stmt.executeQuery("select * from sessiontoken where isDeleted = false and token like binary '"
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
	
	public List<SearchAccountInfo> SearchAccount(String type, String key) {
		List<SearchAccountInfo> result = new ArrayList<SearchAccountInfo>();
		
		String sql = "select * from account where isDeleted = false and ";
		
		switch (type) {
		case "username":
			sql += "username like binary '%" + key + "%'";
			break;
		case "fullname":
			sql += "fullname like binary '%" + key + "%'";
			break;
		default:
			return result;
		}
		
		try {
			ResultSet rs = this.stmt.executeQuery(sql);
			while (rs.next()) {
				Account temp = new Account(
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
				SearchAccountInfo data = new SearchAccountInfo(temp);
				result.add(data);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public Message DeleteAccount(String ID) {
		try {
			Account checkAccount = this.getAccountByID(Integer.parseInt(ID));
			if (checkAccount.getRole() == 1) {
				return new Message(true, "Không thể xóa quản lý của hệ thống!");
			}
			String sql = "update account set isDeleted = true where id = " + ID;
			this.stmt.execute(sql);
			return new Message(true, "Xoá tài khoản thành công!");
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new Message(false, "Xoá tài khoản thất bại!");
		}
	}
	
	public SystemConstBasic getAllSystemConst() {
		SystemConstBasic result = new SystemConstBasic();
		
		try {
			ResultSet rs = this.stmt.executeQuery("select * from SYSTEMCONSTANTS where isDeleted = false");
			while (rs.next()) {
				SystemConstrants temp = new SystemConstrants(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getBoolean(4),
						rs.getDate(5),
						rs.getInt(6));
				switch (temp.getID()) {
				case 1:
					result.durationTime = Integer.parseInt(temp.getValue());
					break;
				case 2:
					result.maxBook = Integer.parseInt(temp.getValue());
					break;
				case 3:
					result.maxTime = Integer.parseInt(temp.getValue());
					break;
				}
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return result;
		}
		
		return result;
	}
	
	public void updateConstants(int ID, int value) throws Exception{
		String sql = "update SYSTEMCONSTANTS set value = '" + value + "' "
				+ "where id ='" + ID + "'";
		this.stmt.execute(sql);
	}
	
	public Message UpdateSystemConst(SystemConstBasic data) {
		try {
			this.updateConstants(1, data.durationTime);
			this.updateConstants(2, data.maxBook);
			this.updateConstants(3, data.maxTime);
			return new Message(true, "Cập nhật thành công!");
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new Message(false, "Cập nhật thất bại!");
		}
	}
}
