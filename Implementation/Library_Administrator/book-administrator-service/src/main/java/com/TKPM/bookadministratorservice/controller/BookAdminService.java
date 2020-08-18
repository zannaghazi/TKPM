package com.TKPM.bookadministratorservice.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import com.TKPM.bookadministratorservice.model.*;
import com.TKPM.bookadministratorservice.repository.BookInfoRepository;
import com.TKPM.bookadministratorservice.viewmodel.AddAuthorRequest;
import com.TKPM.bookadministratorservice.viewmodel.AddBookInfo;
import com.TKPM.bookadministratorservice.viewmodel.AddBookRequest;
import com.TKPM.bookadministratorservice.viewmodel.AddPublisherRequest;
import com.TKPM.bookadministratorservice.viewmodel.AuthorDetailInfo;
import com.TKPM.bookadministratorservice.viewmodel.BookDetail;
import com.TKPM.bookadministratorservice.viewmodel.BookInfoDetail;
import com.TKPM.bookadministratorservice.viewmodel.BookInfoSearchResult;
import com.TKPM.bookadministratorservice.viewmodel.BookTypeDetail;
import com.TKPM.bookadministratorservice.viewmodel.BookWithBookInfo;
import com.TKPM.bookadministratorservice.viewmodel.Message;
import com.TKPM.bookadministratorservice.viewmodel.MessageData;
import com.TKPM.bookadministratorservice.viewmodel.PublisherDetailInfo;
import com.TKPM.bookadministratorservice.viewmodel.RentingBookInfo;
import com.TKPM.bookadministratorservice.viewmodel.RentingRequest;
import com.TKPM.bookadministratorservice.viewmodel.ReportRequest;
import com.TKPM.bookadministratorservice.viewmodel.ReportResponse;
import com.TKPM.bookadministratorservice.viewmodel.ReturnBookRequest;
import com.TKPM.bookadministratorservice.viewmodel.UpdateAuthorRequest;
import com.TKPM.bookadministratorservice.viewmodel.VNDateTime;
import com.TKPM.bookadministratorservice.viewmodel.UpdateBookInfo;


@RestController
@RequestMapping("/book")
public class BookAdminService {	
	private BookInfoRepository repo = new BookInfoRepository();
        
	@RequestMapping("/ping")
	public String Hello(@RequestHeader("x-access-token") String token) {
		System.out.println(token);
		//throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Login token incorrect!");
		return "Hello";
	}
	
	/*BOOK INFO CONTROL*/
	@CrossOrigin
	@RequestMapping("/get_all_book") 
	public List<BookInfo> GetListBookInformation() {
		List<BookInfo> temp = repo.getAllBook();
		return temp;
	}
	
	@CrossOrigin
	@RequestMapping("/get_book_info_by_isbn/{bookId}")
	public BookInfoDetail GetBookInformation(@PathVariable("bookId")String ISBN) {
		BookInfo temp = repo.getBookInfoByISBN(ISBN);
		BookType bookType = new BookType();
		
		AuthorInfo author = repo.getAuthorByID(temp.getAuthorID());
		Publisher publisher = repo.getPublisherByID(temp.getPublisherID());
		String typeName = bookType.getTypeNameByID(temp.getType());
		
		return new BookInfoDetail(
				temp,
				author.getName(), 
				publisher.getName(),
				typeName);
	}
	
	@CrossOrigin
	@RequestMapping("/get_book_by_isbn/{ISBN}")
	public List<BookDetail> GetBook(@PathVariable("ISBN")String ISBN) {
		return repo.getListBookByISBN(ISBN);
	}
	
	@CrossOrigin
	@RequestMapping("/search")
	public List<BookInfoSearchResult> GetSearchedBookInfo(@RequestBody SearchRequest request) {
		List<BookInfoSearchResult> result = new ArrayList<BookInfoSearchResult>();
		List<BookInfo> data = repo.getSearchedBook(request);
		BookType bookType = new BookType();
		
		for (int i = 0; i < data.size(); i++) {
			AuthorInfo author = repo.getAuthorByID(data.get(i).getAuthorID());
			Publisher publisher = repo.getPublisherByID(data.get(i).getPublisherID());
			
			BookInfoSearchResult temp =  new BookInfoSearchResult(
					data.get(i).getISBN(),
					data.get(i).getName(),
					author.getName(),
					publisher.getName(),
					new VNDateTime(data.get(i).getReleaseDate()).getVNTime(),
					bookType.getTypeNameByID(data.get(i).getType()),
					data.get(i).getLocation(),
					data.get(i).getPath());
			result.add(temp);
		}
		return result;
	}
	
	@CrossOrigin
	@RequestMapping("/get_top_new")
	public List<BookInfoSearchResult> GetListTopNewBook() {
		List<BookInfoSearchResult> result = new ArrayList<BookInfoSearchResult>();
		List<BookInfo> data = repo.getTopNewBook();
		BookType bookType = new BookType();
		
		for (int i = 0; i < data.size(); i++) {
			AuthorInfo author = repo.getAuthorByID(data.get(i).getAuthorID());
			Publisher publisher = repo.getPublisherByID(data.get(i).getPublisherID());
			
			BookInfoSearchResult temp =  new BookInfoSearchResult(
					data.get(i).getISBN(),
					data.get(i).getName(),
					author.getName(),
					publisher.getName(),
					new VNDateTime(data.get(i).getReleaseDate()).getVNTime(),
					bookType.getTypeNameByID(data.get(i).getType()),
					data.get(i).getLocation(),
					data.get(i).getPath());
			result.add(temp);
		}
		return result;
	}
	
	@CrossOrigin
	@RequestMapping("/get_top_renting")
	public List<BookInfoSearchResult> GetListTopRentingBook() {
		return repo.getTopRentinhBook();
	}
	
	@CrossOrigin
	@RequestMapping("/get_image/{fileName}") 
	public void getImage(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName){
		try {
			File file = new File("data/" + fileName);
			if (file.exists()) {
				String mimeType = URLConnection.guessContentTypeFromName(file.getName());
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				
				response.setHeader("Content-Disposition", String.format("inline; filename=\"" +file.getName() +"\""));
				response.setContentLength((int)file.length());
				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
				FileCopyUtils.copy(inputStream, response.getOutputStream());
			}
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	@CrossOrigin
	@RequestMapping(value="/upload_book_image", method=RequestMethod.POST, consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public MessageData<String> UploadFile(@RequestParam("file") MultipartFile file) throws IOException{
		
		String fileName = file.getOriginalFilename();
		String nameTail = fileName.substring(fileName.lastIndexOf("."));
		try {
			File myFile = new File("data/config.txt");
			Scanner reader = new Scanner(myFile);
			fileName = reader.nextLine();
			reader.close();
			int fileNameInt = Integer.parseInt(fileName);
			fileName += nameTail;
			
			FileWriter writer = new FileWriter("data/config.txt");
			int nextFileName = fileNameInt + 1;
			System.out.println(nextFileName);
			writer.write(String.valueOf(nextFileName));
			writer.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
				
		File newFile = new File("data/" + fileName);
		newFile.createNewFile();
		FileOutputStream out = new FileOutputStream(newFile);
		out.write(file.getBytes());
		out.close();
		return new MessageData<String>(true, "Thành công", "data/" + fileName);
	}
	
	@CrossOrigin
	@RequestMapping("update_book_info")
	public Message UpdateBookInfo(@RequestHeader("x-access-token") String token, @RequestBody UpdateBookInfo data) {
		if (!repo.VerifyToken(token)) {
			System.out.print(repo.VerifyToken(token));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		return repo.updateBookInfo(data);
	}
	
	@CrossOrigin
	@RequestMapping("/add_new_book_info")
	public MessageData<AddBookInfo> addNewBook(@RequestHeader("x-access-token") String token, @RequestBody AddBookInfo data) {
		if (!repo.VerifyToken(token)) {
			System.out.print(repo.VerifyToken(token));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		return repo.addNewBookInfo(data);
	}
	
	/*BOOK CONTROL*/
	@CrossOrigin
	@RequestMapping("search_book")
	public List<BookDetail> SearchBook(@RequestHeader("x-access-token") String token, @RequestBody SearchRequest request) {
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		return repo.searchBook(request.type, request.key);
	}
	
	@CrossOrigin
	@RequestMapping("add_book")
	public Message AddBook(@RequestHeader("x-access-token") String token, @RequestBody AddBookRequest request) {
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		if (repo.AddNewBook(request.ISBN, request.amount)) {
			return new Message(true, "Thêm sách thành công!");
		}else {
			return new Message(false, "Thêm sách thất bại!");
		}
	}
	
	@CrossOrigin
	@RequestMapping("delete_book/{ID}")
	public Message DeleteBook(@RequestHeader("x-access-token") String token, @PathVariable("ID") String ID) {
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		return repo.DeleteBook(Integer.parseInt(ID));
	}
	
	
	/*TYPE CONTROL*/
	@CrossOrigin
	@RequestMapping("/get_all_type")
	public List<BookTypeDetail> getAllType() {
		BookType booktype = new BookType();
		return booktype.getListType();
	}
	
	/*AUTHOR CONTROL*/
	@CrossOrigin
	@RequestMapping("get_all_author")
	public List<AuthorDetailInfo> getAllAuthor(@RequestHeader("x-access-token") String token) {
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		List<AuthorInfo> data = repo.getListAuthor();
		List<AuthorDetailInfo> result = new ArrayList<AuthorDetailInfo>();
		for (int i = 0; i < data.size(); i++) {
			result.add(new AuthorDetailInfo(data.get(i), repo));
		}
		return result;
	}
	
	@CrossOrigin
	@RequestMapping("add_author")
	public MessageData<AuthorDetailInfo> AddNewAuthor(@RequestHeader("x-access-token") String token, @RequestBody AddAuthorRequest request) {
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		if (request.currentUserID == 0) {
			request.currentUserID = repo.getAccountIDByToken(token);
		}
		return repo.createNewAuthor(request.name, request.currentUserID);
	}
	
	@CrossOrigin
	@RequestMapping("update_author")
	public MessageData<AuthorDetailInfo> UpdateAuthor(@RequestHeader("x-access-token") String token, @RequestBody UpdateAuthorRequest request) {
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		if (request.currentUserID == 0) {
			request.currentUserID = repo.getAccountIDByToken(token);
		}
		return repo.updateAuthor(request.id, request.name, request.currentUserID);
	}
	
	@CrossOrigin
	@RequestMapping("delete_author/{ID}")
	public Message DeleteAuthor(@RequestHeader("x-access-token") String token, @PathVariable("ID") String ID)
	{
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		return repo.deleteAuthor(Integer.parseInt(ID));
	}
	
	/*PUBLISHER CONTROL*/
	@CrossOrigin
	@RequestMapping("get_all_publisher")
	public List<PublisherDetailInfo> getAllPubliser(@RequestHeader("x-access-token") String token) {
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		List<Publisher> data = repo.getAllPublisher();
		List<PublisherDetailInfo> result = new ArrayList<PublisherDetailInfo>();
		
		for (int i = 0; i < data.size(); i++) {
			PublisherDetailInfo temp = new PublisherDetailInfo(data.get(i), repo);
			result.add(temp);
		}
		
		return result;
	}
	
	@CrossOrigin
	@RequestMapping("add_publisher")
	public MessageData<PublisherDetailInfo> AddNewPublisher(@RequestHeader("x-access-token") String token, @RequestBody AddPublisherRequest request) {
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		if (request.currentUserID == 0) {
			request.currentUserID = repo.getAccountIDByToken(token);
		}
		return repo.createNewPublisher(request.name, request.currentUserID);
	}
	
	@CrossOrigin
	@RequestMapping("delete_publisher/{ID}")
	public Message DeletePublisher(@RequestHeader("x-access-token") String token, @PathVariable("ID") String ID)
	{
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		return repo.deletePublisher(Integer.parseInt(ID));
	}
	
	@CrossOrigin
	@RequestMapping("update_publisher")
	public MessageData<PublisherDetailInfo> UpdatePublisher(@RequestHeader("x-access-token") String token, @RequestBody UpdateAuthorRequest request) {
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		if (request.currentUserID == 0) {
			request.currentUserID = repo.getAccountIDByToken(token);
		}
		return repo.updatePublisher(request.id, request.name, request.currentUserID);
	}
	
	/*REPORT CONTROL*/
	@CrossOrigin
	@RequestMapping("/report")
	public ReportResponse<BookWithBookInfo> GetBookReport(@RequestHeader("x-access-token") String token, @RequestBody ReportRequest request) {
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		return repo.getBookReport(request.type, request.time);
	}
	
	/*SLIP CONTROL*/
	@CrossOrigin
	@RequestMapping("/create_renting_slip")
	public MessageData<RentingSlip> CreateRentingSlip(@RequestHeader("x-access-token") String token, @RequestBody RentingRequest request) {
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		return repo.CreateRentingSlip(request.accountID, request.bookID);
	}
	
	@CrossOrigin
	@RequestMapping("/get_renting_by_reader_id/{ID}")
	public List<RentingBookInfo> GetRentingBook(@RequestHeader("x-access-token") String token, @PathVariable("ID") String accountID) {
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		return repo.getRentingBook(Integer.parseInt(accountID));
	}
	
	@CrossOrigin
	@RequestMapping("/return_book")
	public Message ReturnBook(@RequestHeader("x-access-token") String token, @RequestBody ReturnBookRequest request) {
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		return repo.returnBook(request.rentingID, request.ID);
	}
	
	@CrossOrigin
	@RequestMapping("/repay_book")
	public Message RepayBook(@RequestHeader("x-access-token") String token, @RequestBody ReturnBookRequest request) {
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		return repo.repayBook(request.rentingID, request.ID);
	}
}
