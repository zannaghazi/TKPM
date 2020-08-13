package com.TKPM.bookadministratorservice.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.TKPM.bookadministratorservice.model.AuthorInfo;
import com.TKPM.bookadministratorservice.model.Book;
import com.TKPM.bookadministratorservice.model.BookInfo;
import com.TKPM.bookadministratorservice.model.BookType;
import com.TKPM.bookadministratorservice.model.Publisher;
import com.TKPM.bookadministratorservice.model.SearchRequest;
import com.TKPM.bookadministratorservice.viewmodel.Message;

public class BookInfoRepository {
	private Connection conn = null;
	private Statement stmt = null;
	private static String DB_URL = "jdbc:mysql://localhost:3306/tkpm_quanlythuvien";
    private static String USER_NAME = "root";
    private static String PASSWORD = "123456";
    
	public BookInfoRepository() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			this.stmt = conn.createStatement();
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	public ArrayList<BookInfo> getAllBook() {
		ArrayList<BookInfo> result = new ArrayList<BookInfo>();
		try {
			ResultSet rs = this.stmt.executeQuery("select * from bookinformation where isDeleted=false;");
			while (rs.next()) {
				BookInfo temp = new BookInfo(
						rs.getString(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getDate(5),
						rs.getInt(6),
						rs.getString(7),
						rs.getString(8),
						rs.getBoolean(9));
				result.add(temp);
			}
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return result;
	}
	
	public List<BookInfo> getTopNewBook() {
		List<BookInfo> result = new ArrayList<BookInfo>();
		
		try {
			String sql = "select* from bookinformation " + 
					"where isDeleted=false " + 
					"order by releasedate desc " + 
					"limit 6";
			ResultSet rs = this.stmt.executeQuery(sql);
			while (rs.next()) {
				BookInfo temp = new BookInfo(
						rs.getString(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getDate(5),
						rs.getInt(6),
						rs.getString(7),
						rs.getString(8),
						rs.getBoolean(9));
				result.add(temp);
			}
		}catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
		return result;
	}
	
	/*AUTHOR CONTROL*/
	public AuthorInfo getAuthorByName(String name) {
		try {
			ResultSet rs = this.stmt.executeQuery("select * from author where name like '%" + name + "%'");
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
	
	public AuthorInfo getAuthorByID(int id) {
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
	
	public List<AuthorInfo> getListAuthor() {
		List<AuthorInfo> result = new ArrayList<AuthorInfo>();
		try {
			ResultSet rs = this.stmt.executeQuery("select * from author where isDeleted=false");
			while (rs.next()) {
				AuthorInfo temp = new AuthorInfo(
						rs.getInt(1),
						rs.getString(2),
						rs.getBoolean(3));
				result.add(temp);
			}
			return result;
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}
	}
	
	public Message createNewAuthor(String newAuthor, int AccountID) {
		try {
			String createCommand = "INSERT INTO AUTHOR(NAME, ISDELETED, UPDATEDACCOUNT) "
					+ "VALUES ('" + newAuthor + "', false, "
					+ AccountID +")";
			this.stmt.execute(createCommand);
			return new Message(true, "Tạo thành công");
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
			return new Message(false, "Tạo thất bại");
		}
	}
	
	/*PUBLISHER CONTROL*/
	public Publisher getPublisherByName(String name) {
		try {
			ResultSet rs = this.stmt.executeQuery("select * from publisher where name like '%" + name + "%'");
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
	
	public Publisher getPublisherByID(int i) {
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
	
	public List<Publisher> getAllPublisher() {
		List<Publisher> result = new ArrayList<Publisher>();
		try {
			ResultSet rs = this.stmt.executeQuery("select * from publisher where isDeleted=false");
			while (rs.next()) {
				Publisher temp = new Publisher(
						rs.getInt(1),
						rs.getString(2),
						rs.getBoolean(3));
				result.add(temp);
			}
			return result;
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}
	}
	
	public Message createNewPublisher(String newPublisher, int AccountID) {
		try {
			String createCommand = "INSERT INTO PUBLISHER(NAME, ISDELETED, UPDATEDACCOUNT) "
					+ "VALUES ('" + newPublisher + "', false, "
					+ AccountID +")";
			this.stmt.execute(createCommand);
			return new Message(true, "Tạo thành công");
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
			return new Message(false, "Tạo thất bại");
		}
	}
	
	/*BOOK CONTROL*/
	public Book getBookByID(int id) {
		try {
			ResultSet rs = this.stmt.executeQuery("select * from book where id = " + id );
			while (rs.next()) {
				Book temp = new Book(
						rs.getInt(1),
						rs.getInt(2),
						rs.getBoolean(3));
				return temp;
			}
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}
		return null;
	}
	
	public List<BookInfo> getSearchedBook(SearchRequest request) {
		List<BookInfo> result = new ArrayList<BookInfo>();
		
		String queryString = "select* from bookinformation where isDeleted=false";
		switch(request.type) {
		case "name":
			queryString += " and name like '%" + request.key +"%'";
			break;
		case "author":
			AuthorInfo author = getAuthorByName(request.key);
			if (author == null) {
				return result;
			}
			queryString += " and author = " + author.getId();
			break;
		case "ISBN":
			queryString += " and isbn like '%" + request.key +"%'";
			break;
		case "ID":
			Book book = getBookByID(Integer.parseInt(request.key));
			if (book == null) {
				return result;
			}
			queryString += " and isbn like '%" + book.getISBN() + "%'";
			break;
		case "publisher":
			Publisher publisher = getPublisherByName(request.key);
			if (publisher == null) {
				return result;
			}
			queryString += " and publisher = " + publisher.getID();
			break;
		case "release":
			queryString += " and releasedate = \"" + request.key +"\"";
			break;
		case "type":
			BookType bookType = new BookType();
			int type = bookType.getTypeIDByName(request.key);
			queryString += " and type = " + type;
			break;
		default:
			result = getAllBook();
			return result;
		}
		
		try {
			System.out.println(queryString);
			ResultSet rs = this.stmt.executeQuery(queryString);
			while (rs.next()) {
				BookInfo temp = new BookInfo(
						rs.getString(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getDate(5),
						rs.getInt(6),
						rs.getString(7),
						rs.getString(8),
						rs.getBoolean(9));
				result.add(temp);
			}
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		return result;
	}
}
