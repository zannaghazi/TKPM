package com.TKPM.readeradministratorservice.repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.TKPM.readeradministratorservice.model.AccountInfo;
import com.TKPM.readeradministratorservice.model.AuthorInfo;
import com.TKPM.readeradministratorservice.model.ChangeInfoRequest;
import com.TKPM.readeradministratorservice.model.CreateRequest;
import com.TKPM.readeradministratorservice.model.ExtendLibCardRequest;
import com.TKPM.readeradministratorservice.model.LibraryCardInfo;
import com.TKPM.readeradministratorservice.model.Publisher;
import com.TKPM.readeradministratorservice.model.RsPwdDelAccRequest;
import com.TKPM.readeradministratorservice.model.SearchRequest;
import com.TKPM.readeradministratorservice.viewmodel.ReaderInfoSearchResult;


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

	/*Verify token*/
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
			return false;
		}
	}
	
	public String GetAccountNameByID(int id) {
		String result = "";
		try {
			ResultSet rs = this.stmt.executeQuery("select * from account where isDeleted = false and id = '"
					+ id + "'");
			if (!rs.isBeforeFirst()) {
				return null;
			}
			while (rs.next()) {
				result = rs.getString(2);
				return result;
			}
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}
		return result;
	}

	public AccountInfo GetAccountByID(int id) {
		try {
			ResultSet rs = this.stmt.executeQuery("select * from account where isDeleted = false and id = '"
					+ id + "'");
			if (!rs.isBeforeFirst()) {
				return null;
			}
			while (rs.next()) {
				AccountInfo result = new AccountInfo(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getBoolean(4),
						rs.getDate(5),
						rs.getBoolean(6),
						rs.getBoolean(7),
						rs.getInt(8),
						rs.getString(9),
						rs.getString(10),
						rs.getDate(11),
						rs.getInt(12));
				return result;
			}
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}
		return null;
	}

	public Publisher GetPublisherByID(int i) {
		try {
			ResultSet rs = this.stmt.executeQuery("select * from publisher where id =" + i);
			while (rs.next()) {
				Publisher temp = new Publisher(
						rs.getInt(1),
						rs.getString(2),
						rs.getBoolean(3));
				return temp;
			}
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}
		return null;
	}

	public AuthorInfo GetAuthorByID(int id) {
		try {
			ResultSet rs = this.stmt.executeQuery("select * from author where id = " + id);
			while (rs.next()) {
				AuthorInfo temp = new AuthorInfo(
						rs.getInt(1),
						rs.getString(2),
						rs.getBoolean(3));
				return temp;
			}
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}
		return null;
	}

	public ArrayList<AccountInfo> GetAllReader() {
		ArrayList<AccountInfo> result = new ArrayList<AccountInfo>();
		try {
			ResultSet rs = this.stmt.executeQuery("select * from account where isReader=true");
			while (rs.next()) {
				AccountInfo temp = new AccountInfo(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getBoolean(4),
						rs.getDate(5),
						rs.getBoolean(6),
						rs.getBoolean(7),
						rs.getInt(8),
						rs.getString(9),
						rs.getString(10),
						rs.getDate(11),
						rs.getInt(12));
				result.add(temp);
			}
			return result;
		}catch (Exception e) {
			return null;
		}
	}

	public ArrayList<ReaderInfoSearchResult> GetSearchedReader(SearchRequest request) {
		ArrayList<ReaderInfoSearchResult> result = new ArrayList<ReaderInfoSearchResult>();
		try {
			
			String sql = "select distinct ac.FULLNAME, ac.GENDER, bi.ISBN, bi.NAME, bi.TYPE, bi.AUTHOR, bi.PUBLISHER, ac.ID " +
					"from account ac " + 
					"left join rentingslip rs on rs.ACCOUNTID = ac.ID " + 
					"left join rentingbook rb on rb.SLIPID = rs.ID " + 
					"left join bookinformation bi on bi.ISBN = rb.ISBN and rb.STATUS = 1 " + 
					"where ";
			switch(request.type) {
				case "name":
					sql += " bi.NAME like BINARY'%" + request.key + "%'";
					break;
				case "ISBN":
					sql += " bi.ISBN like binary '" + request.key +"'";
					break;
				case "ID":
					sql = "select distinct ac.FULLNAME, ac.GENDER, null, null, null, null, null" + 
							"from account ac " + 
							"left join librarycard lc on ac.ID = lc.ACCOUNTID\r\n" + 
							"where lc.ID = 1;";
					break;
			}
			ResultSet rs = this.stmt.executeQuery(sql);
			while (rs.next()) {
				ReaderInfoSearchResult temp = new ReaderInfoSearchResult(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getInt(8));
				result.add(temp);
			}
			return result;
		}catch (Exception e) {
			return null;
		}
	}

	public AccountInfo CreateAccount(CreateRequest request, int accRole) {
		try {
			String strSql = "";
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			// Query string
			strSql = "INSERT INTO ACCOUNT(FULLNAME, ADDRESS, GENDER, BIRTHDAY, ISDELETED, ISREADER, ROLE, USERNAME, PASSWORD, UPDATEDACCOUNT) ";
			strSql += "VALUES( ";
			strSql += "'"+ request.fullName + "', ";
			strSql += "'"+ request.address + "', ";
			strSql += " "+ request.gender + ", ";
			strSql += "'"+ dateFormat.format(request.birthday.date) + "', ";
			strSql += " "+ "0" + ", ";
			strSql += " "+ (request.isReader ? "1" : "0") + ", ";
			strSql += " "+ accRole + ", ";
			strSql += "'"+ request.userName + "', ";
			strSql += "'"+ Integer.toString(123456) + "', ";
			strSql += " "+ request.currentUserID + " ";
			strSql += ")";
			// Create User
			this.stmt.execute(strSql);
			
			AccountInfo result = null;
			// Get created User
			ResultSet rs = this.stmt.executeQuery("SELECT * FROM ACCOUNT ORDER BY ID DESC LIMIT 1");
			while (rs.next()) {
				result = new AccountInfo(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getBoolean(4),
						rs.getDate(5),
						rs.getBoolean(6),
						rs.getBoolean(7),
						rs.getInt(8),
						rs.getString(9),
						rs.getString(10),
						rs.getDate(11),
						rs.getInt(12));
			}
			
			return result;
		}catch (Exception e) {
			return null;
		}
	}
	
	public LibraryCardInfo CreateLibraryCard(AccountInfo data) {
		try {
			String strSql = "";
			
			// Query string
			strSql = "INSERT INTO LIBRARYCARD(DURATION, ACCOUNTID, ISDELETED, UPDATEDACCOUNT) ";
			strSql += "VALUES( ";
			strSql += " "+ "12" + ", ";
			strSql += " "+ data.getID() + ", ";
			strSql += " "+ "0" + ", ";
			strSql += " "+ data.getUpdatedAccountID() + " ";
			strSql += ")";
			
			System.out.println(strSql);
			// Create LibraryCard
			this.stmt.execute(strSql);
			
			// Get created User
			LibraryCardInfo result = null;
			ResultSet rs = this.stmt.executeQuery("SELECT * FROM LIBRARYCARD ORDER BY ID DESC LIMIT 1");
			while (rs.next()) {
				result = new LibraryCardInfo(
						rs.getInt(1),
						rs.getDate(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getBoolean(5),
						rs.getDate(6),
						rs.getInt(7)
						);
			}
			return result;
		}catch (Exception e) {
			return null;
		}
	}
	
	public Boolean ResetPassword(RsPwdDelAccRequest request) {
		try {
			String strSql = "";
			
			// Query string
			strSql = "UPDATE ACCOUNT ";
			strSql += "SET ";
			strSql += "PASSWORD = '123456', ";
			strSql += "UPDATEDACCOUNT = " + Integer.toString(request.loginUserID) + " ";
			strSql += "WHERE ";
			strSql += "ID = " + Integer.toString(request.resetAccountID);
			
			// Execute query
			this.stmt.execute(strSql);
			
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public Boolean DeleteAccount(RsPwdDelAccRequest request) {
		try {
			String strSql = "";
			
			// Query string
			strSql = "UPDATE ACCOUNT ";
			strSql += "SET ";
			strSql += "ISDELETED = 1, ";
			strSql += "UPDATEDACCOUNT = " + Integer.toString(request.loginUserID) + " ";
			strSql += "WHERE ";
			strSql += "ID = " + Integer.toString(request.resetAccountID);
			
			// Execute query
			this.stmt.execute(strSql);
			
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public Boolean ExtendLibCard(ExtendLibCardRequest request) {
		try {
			String strSql = "";
			
			// Query string
			strSql = "UPDATE LIBRARYCARD ";
			strSql += "SET ";
			strSql += "DURATION = "+ Integer.toString(request.duration) + ", ";
			strSql += "UPDATEDACCOUNT = " + Integer.toString(request.loginUserID) + " ";
			strSql += "WHERE ";
			strSql += "ACCOUNTID = " + Integer.toString(request.resetAccountID);
			
			// Execute query
			this.stmt.execute(strSql);
			
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public AccountInfo ChangeAccountInfo(ChangeInfoRequest request) {
		try {
			String strSql = "";
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			// Query string
			strSql = "UPDATE ACCOUNT ";
			strSql += "SET ";
			strSql += "FULLNAME = '"+ request.fullName + "', ";
			strSql += "ADDRESS = '" + request.address + "', ";
			strSql += "GENDER = "+ request.gender + ", ";
			strSql += "BIRTHDAY = "+ dateFormat.format(request.birthday) + "', ";
			strSql += "ISREADER = "+ (request.isReader ? "1" : "0") + ", ";
			strSql += "ROLE = "+ request.role + ", ";
			strSql += "UPDATEDACCOUNT = "+ request.logintUserID + " ";
			strSql += "WHERE ";
			strSql += "ID = " + request.accountID;
			
			// Execute query
			this.stmt.execute(strSql);
			
			AccountInfo result = null;
			// Get created User
			ResultSet rs = this.stmt.executeQuery("SELECT * FROM ACCOUNT WHERE ID = " + request.accountID);
			while (rs.next()) {
				result = new AccountInfo(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getBoolean(4),
						rs.getDate(5),
						rs.getBoolean(6),
						rs.getBoolean(7),
						rs.getInt(8),
						rs.getString(9),
						rs.getString(10),
						rs.getDate(11),
						rs.getInt(12));
			}
			
			return result;
		}catch (Exception e) {
			return null;
		}
	}
	
}
