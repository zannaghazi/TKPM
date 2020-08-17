package com.TKPM.bookadministratorservice.repository;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.TKPM.bookadministratorservice.model.AuthorInfo;
import com.TKPM.bookadministratorservice.model.Book;
import com.TKPM.bookadministratorservice.model.BookInfo;
import com.TKPM.bookadministratorservice.model.BookStatus;
import com.TKPM.bookadministratorservice.model.BookType;
import com.TKPM.bookadministratorservice.model.Publisher;
import com.TKPM.bookadministratorservice.model.RentingSlip;
import com.TKPM.bookadministratorservice.model.SearchRequest;
import com.TKPM.bookadministratorservice.viewmodel.AddBookInfo;
import com.TKPM.bookadministratorservice.viewmodel.AuthorDetailInfo;
import com.TKPM.bookadministratorservice.viewmodel.BookDetail;
import com.TKPM.bookadministratorservice.viewmodel.BookInfoSearchResult;
import com.TKPM.bookadministratorservice.viewmodel.Message;
import com.TKPM.bookadministratorservice.viewmodel.MessageData;
import com.TKPM.bookadministratorservice.viewmodel.PublisherDetailInfo;
import com.TKPM.bookadministratorservice.viewmodel.ReportResponse;
import com.TKPM.bookadministratorservice.viewmodel.UpdateBookInfo;
import com.TKPM.bookadministratorservice.viewmodel.VNDateTime;

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
			System.out.println(e.getMessage());
		}
	}
	
	/*Verify token*/
	public boolean VerifyToken(String token) {
		try {
			ResultSet rs = this.stmt.executeQuery("select * from SESSIONTOKEN where isDeleted=false and token like BINARY '"
					+ token + "';");
			if (!rs.isBeforeFirst()) {
				return false;
			}else {
				return true;
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
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
	
	public String getAccountNameByID(int id) {
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
			System.out.println(e.getMessage());
			return null;
		}
		return result;
	}
	
	public List<Integer> getAccountIDByName(String name) {
		List<Integer> result = new ArrayList<Integer>();
		try {
			ResultSet rs = this.stmt.executeQuery("select * from account where isDeleted = false and fullname like binary'%"
					+ name + "%'");
			while (rs.next()) {
				result.add(rs.getInt(1));
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	/*BOOK CONTROL*/
	public MessageData<AddBookInfo> addNewBookInfo(AddBookInfo data) {
		try {
			Date releaseDate = new VNDateTime(data.releaseDate).date;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String imageURL = "/book/get_image/" + data.image.replace("data/", "");
			
			String sql = "INSERT INTO BOOKINFORMATION(ISBN, NAME, AUTHOR, PUBLISHER, RELEASEDATE, TYPE, LOCATION, PATH, ISDELETED)"
					+ "VALUES ('"
					+ data.ISBN + "', '"
					+ data.name +"', "
					+ data.author +", "
					+ data.publisher + ", '"
					+ dateFormat.format(releaseDate) +"', "
					+ data.type + ", '"
					+ data.location +"', '"
					+ imageURL + "', false)";
			this.stmt.execute(sql);
			
			this.AddNewBook(data.ISBN, data.amount);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new MessageData<AddBookInfo>(false, "Tạo thất bại", null);
		}
		
		return new MessageData<AddBookInfo>(true, "Tạo thành công", data);
	}
	
	public Message updateBookInfo(UpdateBookInfo data) {
		try {
			Date releaseDate = new VNDateTime(data.releaseDate).date;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			String sql = "UPDATE BOOKINFORMATION "
					+ "set name ='" + data.name +"', "
					+ "author =" +data.authorID +", "
					+ "publisher =" + data.publisherID + ", "
					+ "releasedate = '" + dateFormat.format(releaseDate) +"', "
					+ "type = " + data.type + ", "
					+ "location = '" + data.location +"' "
					+ "where isbn = '" + data.ISBN + "'";
			this.stmt.execute(sql);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new Message(false, "Cập nhật thất bại");
		}
		
		return new Message(true, "Cập nhật thành công");
	}
	
	public boolean AddNewBook(String ISBN, int amount) {
		for (int i = 0; i < amount; i++) {
			try {
				String sql = "insert into book(isbn, isdeleted, status)"
						+ "values ('"+ ISBN + "', false, 1)";
				this.stmt.execute(sql);
			}catch (Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
		}
		return true;
	}
	
	public Message DeleteBook(int ID) {
		try {
			String sql = "update book set isDeleted = true where id = " + ID;
			this.stmt.execute(sql);
			return new Message(true, "Xóa sách thành công!");
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new Message(false, "Xóa sách thất bại!");
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
			System.out.println(e.getMessage());
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
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public List<BookInfoSearchResult> getTopRentinhBook() {
		List<BookInfoSearchResult> result = new ArrayList<BookInfoSearchResult>();
		List<BookInfo> data = new ArrayList<BookInfo>();
		
		try {
			String sql = "SELECT  ISBN, COUNT(*) FROM RENTINGBOOK " +
					"GROUP BY BOOKID " +
					"order by COUNT(*) DESC " +
					"LIMIT 4";
			ResultSet rs = this.stmt.executeQuery(sql);
			List<String> listBookID = new ArrayList<String>();
			List<Integer> rentingCount = new ArrayList<Integer>();
			while (rs.next()) {
				listBookID.add(rs.getString(1));
				rentingCount.add(rs.getInt(2));
			}
			for (int i = 0; i < listBookID.size(); i++) {
				BookInfo temp = this.getBookInfoByISBN(listBookID.get(i));
				data.add(temp);
			}
			
			
			// Convert to ViewModel
			BookType bookType = new BookType();
			for (int i = 0; i < data.size(); i++) {
				AuthorInfo author = this.getAuthorByID(data.get(i).getAuthorID());
				Publisher publisher = this.getPublisherByID(data.get(i).getPublisherID());
				
				BookInfoSearchResult temp =  new BookInfoSearchResult(
						data.get(i).getISBN(),
						data.get(i).getName(),
						author.getName(),
						publisher.getName(),
						new VNDateTime(data.get(i).getReleaseDate()).getVNTime(),
						bookType.getTypeNameByID(data.get(i).getType()),
						data.get(i).getLocation(),
						data.get(i).getPath(),
						rentingCount.get(i));
				result.add(temp);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	/*AUTHOR CONTROL*/
	public AuthorInfo getAuthorByName(String name) {
		try {
			ResultSet rs = this.stmt.executeQuery("select * from author where name like BINARY '%" + name + "%'");
			while (rs.next()) {
				AuthorInfo temp = new AuthorInfo(
						rs.getInt(1),
						rs.getString(2),
						rs.getBoolean(3));
				return temp;
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
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
						rs.getBoolean(3),
						rs.getDate(4),
						rs.getInt(5));
				return temp;
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
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
						rs.getBoolean(3),
						rs.getDate(4),
						rs.getInt(5));
				result.add(temp);
			}
			return result;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public MessageData<AuthorDetailInfo> updateAuthor(int authorID, String name, int accountID) {
		if (authorID == -1) {
			return new MessageData<AuthorDetailInfo>(false, "Không thể thay đổi dữ liệu mặc định", null);
		}
		try {
			String sql = "Update author set name = '"
					+ name + "', updatedaccount = " + accountID + " where id = " + authorID;
			this.stmt.execute(sql);
			
			AuthorInfo temp = this.getAuthorByID(authorID);
			System.out.println(temp.toString());
			AuthorDetailInfo result = new AuthorDetailInfo(temp, this.getAccountNameByID(accountID));
			return new MessageData<AuthorDetailInfo>(true, "Cập nhật thành công", result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new MessageData<AuthorDetailInfo>(false, "Cập nhật thất bại", null);
		}
	}
	
	public Message deleteAuthor(int ID) {
		try {
			String sql = "update author set isDeleted = true where id = " + ID;
			this.stmt.execute(sql);
			
			sql = "update bookinformation set author = -1 where author = " + ID;
			this.stmt.execute(sql);
			return new Message(true, "Cập nhật thành công");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new Message(false, "Cập nhật thất bại");
		}
	}
	
	public MessageData<AuthorDetailInfo> createNewAuthor(String newAuthor, int AccountID) {
		try {
			String createCommand = "INSERT INTO AUTHOR(NAME, ISDELETED, UPDATEDACCOUNT) "
					+ "VALUES ('" + newAuthor + "', false, "
					+ AccountID +")";
			this.stmt.execute(createCommand);
			
			ResultSet rs = this.stmt.executeQuery("Select * from author order by id desc limit 1");
			AuthorInfo temp = new AuthorInfo();
			while (rs.next()) {
				temp = new AuthorInfo(
						rs.getInt(1),
						rs.getString(2),
						rs.getBoolean(3),
						rs.getDate(4),
						rs.getInt(5));
			}
			return new MessageData<AuthorDetailInfo>(true, "Tạo thành công", new AuthorDetailInfo(temp, this));
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new MessageData<AuthorDetailInfo>(false, "Tạo thất bại", null);
		}
	}
	
	/*PUBLISHER CONTROL*/
	public Publisher getPublisherByName(String name) {
		try {
			ResultSet rs = this.stmt.executeQuery("select * from publisher where name like BINARY '%" + name + "%'");
			while (rs.next()) {
				Publisher temp = new Publisher(
						rs.getInt(1),
						rs.getString(2),
						rs.getBoolean(3));
				return temp;
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return null;
	}
	
	public MessageData<PublisherDetailInfo> updatePublisher(int publisherID, String name, int accountID) {
		if (publisherID == -1) {
			return new MessageData<PublisherDetailInfo>(false, "Không thể thay đổi dữ liệu mặc định", null);
		}
		try {
			String sql = "Update publisher set name = '"
					+ name + "', updatedaccount = " + accountID + " where id = " + publisherID;
			this.stmt.execute(sql);
			
			Publisher temp = this.getPublisherByID(publisherID);
			System.out.println(temp.toString());
			PublisherDetailInfo result = new PublisherDetailInfo(temp, this.getAccountNameByID(accountID));
			return new MessageData<PublisherDetailInfo>(true, "Cập nhật thành công", result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new MessageData<PublisherDetailInfo>(false, "Cập nhật thất bại", null);
		}
	}
	
	public Message deletePublisher(int ID) {
		try {
			String sql = "update publisher set isDeleted = true where id = " + ID;
			this.stmt.execute(sql);
			
			sql = "update bookinformation set publisher = -1 where publisher = " + ID;
			this.stmt.execute(sql);
			return new Message(true, "Cập nhật thành công");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new Message(false, "Cập nhật thất bại");
		}
	}
	
	public Publisher getPublisherByID(int i) {
		try {
			ResultSet rs = this.stmt.executeQuery("select * from publisher where id =" + i);
			while (rs.next()) {
				Publisher temp = new Publisher(
						rs.getInt(1),
						rs.getString(2),
						rs.getBoolean(3),
						rs.getDate(4),
						rs.getInt(5));
				return temp;
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return null;
	}
	
	public List<Publisher> getAllPublisher() {
		List<Publisher> result = new ArrayList<Publisher>();
		try {
			ResultSet rs = this.stmt.executeQuery("select * from PUBLISHER where isDeleted=false");
			while (rs.next()) {
				Publisher temp = new Publisher(
						rs.getInt(1),
						rs.getString(2),
						rs.getBoolean(3),
						rs.getDate(4),
						rs.getInt(5));
				result.add(temp);
			}
			return result;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public MessageData<PublisherDetailInfo> createNewPublisher(String newPublisher, int AccountID) {
		try {
			String createCommand = "INSERT INTO PUBLISHER(NAME, ISDELETED, UPDATEDACCOUNT) "
					+ "VALUES ('" + newPublisher + "', false, "
					+ AccountID +")";
			this.stmt.execute(createCommand);
			
			MessageData<PublisherDetailInfo> mess = new MessageData<PublisherDetailInfo>(false, "Tạo thất bại", null);
			ResultSet rs = this.stmt.executeQuery("Select * from publisher order by id desc limit 1");
			Publisher temp = null;
			while (rs.next()) {
				temp = new Publisher(
						rs.getInt(1),
						rs.getString(2),
						rs.getBoolean(3),
						rs.getDate(4),
						rs.getInt(5));
				
			}
			PublisherDetailInfo data = new PublisherDetailInfo(temp, this.getAccountNameByID(AccountID));
			mess = new MessageData<PublisherDetailInfo>(true, "Tạo thành công", data);
			return mess;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new MessageData<PublisherDetailInfo>(false, "Tạo thất bại", null);
		}
	}
	
	/*BOOK CONTROL*/
	public Book getBookByID(int id) {
		try {
			ResultSet rs = this.stmt.executeQuery("select * from book where id = " + id );
			while (rs.next()) {
				Book temp = new Book(
						rs.getInt(1),
						rs.getString(2),
						rs.getBoolean(3));
				return temp;
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return null;
	}
	
	public BookInfo getBookInfoByISBN(String id) {
		try {
			ResultSet rs = this.stmt.executeQuery("select * from bookinformation where isbn like BINARY '" + id + "'");
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
				return temp;
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return null;
	}
	
	public List<BookDetail> getListBookByISBN(String ISBN) {
		List<BookDetail> result = new ArrayList<BookDetail>();
		List<Book> data = new ArrayList<Book>();
		try {
			ResultSet rs = this.stmt.executeQuery("select * from book where isbn like '" + ISBN + "'");
			BookStatus bookStatus = new BookStatus();
			while (rs.next()) {
				Book temp = new Book(
						rs.getInt(1),
						rs.getString(2),
						rs.getBoolean(3),
						rs.getInt(4));
				data.add(temp);
				
				result.add(new BookDetail(temp, bookStatus.getStatusNameByID(temp.getStatus())));
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ArrayList<BookDetail>();
		}
		return result;
	}
	
	public List<BookInfo> getSearchedBook(SearchRequest request) {
		List<BookInfo> result = new ArrayList<BookInfo>();
		
		String queryString = "select* from bookinformation where isDeleted=false";
		switch(request.type) {
		case "name":
			queryString += " and name like BINARY '%" + request.key +"%'";
			break;
		case "author":
			AuthorInfo author = getAuthorByName(request.key);
			if (author == null) {
				return result;
			}
			queryString += " and author = " + author.getId();
			break;
		case "ISBN":
			queryString += " and isbn like BINARY '%" + request.key +"%'";
			break;
		case "ID":
			Book book = getBookByID(Integer.parseInt(request.key));
			if (book == null) {
				return result;
			}
			queryString += " and isbn like BINARY '%" + book.getISBN() + "%'";
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
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	/*BOOK CONTROL*/
	public List<BookDetail> searchBook(String type, String key) {
		List<BookDetail> result = new ArrayList<BookDetail>();
		List<Integer> listID = new ArrayList<Integer>();
		
		BookStatus bookStatus = new BookStatus();
		
		String queryString = "select* from book where isDeleted=false";
		switch(type) {
		case "ID":
			queryString += " and id = " + key;
			break;
		case "status":
			queryString += " and status = "+ bookStatus.getStatusIDByName(key);
			break;
		case "rentName":
			listID = this.getBookIDbyCustomerName(key);
			for (int i = 0; i < listID.size(); i++) {
				List<BookDetail> temp = this.searchBook("ID", listID.get(i).toString());
				for (int j = 0; j < temp.size(); j++) {
					if (!result.contains(temp.get(j))) {
						result.add(temp.get(j));
					}
				}
			}
			return result;
		case "rentID":
			listID = this.getBookIDbyCustomerID(Integer.parseInt(key));
			for (int i = 0; i < listID.size(); i++) {
				List<BookDetail> temp = this.searchBook("ID", listID.get(i).toString());
				for (int j = 0; j < temp.size(); j++) {
					if (!result.contains(temp.get(j))) {
						result.add(temp.get(j));
					}
				}
			}
			return result;
		default:
			return result;
		}
		
		try {
			ResultSet rs = this.stmt.executeQuery(queryString);
			while (rs.next()) {
				Book tempBook = new Book(
						rs.getInt(1),
						rs.getString(2),
						rs.getBoolean(3),
						rs.getInt(4));
				result.add(new BookDetail(tempBook, bookStatus.getStatusNameByID(tempBook.getStatus())));
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public boolean isBookExist(int id) {
		try {
			ResultSet rs = this.stmt.executeQuery("");
			return true;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	/*SLIP CONTROL*/
	public List<Integer> getBookIDbyCustomerName(String name) {
		List<Integer> result = new ArrayList<Integer>();
		List<Integer> accountIDs = this.getAccountIDByName(name);
		
		if (accountIDs.size() < 1) {
			return result;
		}
		for (int i = 0; i < accountIDs.size(); i++) {
			List<Integer> temp = this.getBookIDbyCustomerID(accountIDs.get(i));
			
			for (int j = 0; j < temp.size(); j++) {
				System.out.print("BookID: ");
				System.out.println(temp.get(j));
				if (!result.contains(temp.get(j))) {
					result.add(temp.get(j));
				}
			}
		}
		return result;
	}
	
	public List<Integer> getBookIDbyCustomerID(int ID) {
		List<Integer> result = new ArrayList<Integer>();
		
		try {
			ResultSet rs = this.stmt.executeQuery("select * from rentingslip where isDeleted = false and  accountid =" + ID);
			while (rs.next()) {
				result.add(rs.getInt(1));
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	/*REPORT CONTROL*/
	public List<BookDetail> getRentingReport(String time) {
		List<BookDetail> data = new ArrayList<BookDetail>();
		
		String startDate = time + "-01";
		String endDate = time + "-30";
		
		String sql = "SELECT * FROM RENTINGSLIP WHERE (CREATEDDATE BETWEEN '"
				+ startDate + "' AND '"
				+ endDate + "' )";
		return data;
	}
	
	public ReportResponse<BookDetail> getBookReport(String type, String time) {
		List<BookDetail> data = new ArrayList<BookDetail>();
		
		switch(type) {
		case "rent":
			data = this.getRentingReport(time);
			break;
		case "return":
			break;
		case "input":
			break;
		default:
			return new ReportResponse<BookDetail>(data.size(), "Tạo báo cáo thất bại", data);
		}
		
		ReportResponse<BookDetail> result = new ReportResponse<BookDetail>(data.size(), "Tạo báo cáo thành công", data);
		return result;
	}
	
	/*SLIP CONTROL*/
//	public RentingSlip getNewestRentingSlip() {
//		try {
//			ResultSet rs = this.stmt.executeQuery("Select * from RENTINGSLIP order by id desc limit 1");
//			if (rs.next()) {
//				
//			}
//		}catch (Exception e) {
//			System.out.println(e.getMessage());
//			return null;
//		}
//	}
	
	public MessageData<RentingSlip> CreateRentingSlip(int accountID, List<Integer> bookID) {
		try {
			// Validate bookID
			// Create slip
			String sql1 = "insert into RENTINGSLIP(accountID, isDeleted) values (" + accountID + ", false)";
			this.stmt.execute(sql1);
			
			// Create renting book
			// Update book status
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return new MessageData<RentingSlip>(false, "Tạo thất bại!", null);
	}
}
