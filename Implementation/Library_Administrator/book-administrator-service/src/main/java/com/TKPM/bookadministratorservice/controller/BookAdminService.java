package com.TKPM.bookadministratorservice.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
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
import com.TKPM.bookadministratorservice.viewmodel.AddPublisherRequest;
import com.TKPM.bookadministratorservice.viewmodel.AuthorDetailInfo;
import com.TKPM.bookadministratorservice.viewmodel.BookInfoSearchResult;
import com.TKPM.bookadministratorservice.viewmodel.Message;
import com.TKPM.bookadministratorservice.viewmodel.MessageData;
import com.TKPM.bookadministratorservice.viewmodel.PublisherDetailInfo;
import com.TKPM.bookadministratorservice.viewmodel.UpdateAuthorRequest;
import com.TKPM.bookadministratorservice.viewmodel.VNDateTime;


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
	
	/*BOOK CONTROL*/
	@CrossOrigin
	@RequestMapping("/get_all_book") 
	public List<BookInfo> GetListBookInformation() {
		List<BookInfo> temp = repo.getAllBook();
		return temp;
	}
	
	@CrossOrigin
	@RequestMapping("/info/{bookId}")
	public BookInfo GetBookInformation(@PathVariable("bookId")String BookID) {
		return null;
	}
	
	@CrossOrigin
	@RequestMapping("/search")
	public List<BookInfoSearchResult> GetSearchedBook(@RequestBody SearchRequest request) {
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
	@RequestMapping(value = "/upload_book_image", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	public MessageData<String> uploadImage(@RequestParam MultipartFile file) {
		String fileName = "";
		if(file.isEmpty()) {
			return new MessageData(true, "Hình ảnh không hợp lệ", "fail");
		}
		
		try {
			File myFile = new File("data/config.txt");
			Scanner reader = new Scanner(myFile);
			fileName = reader.nextLine();
			reader.close();
			int fileNameInt = Integer.parseInt(fileName);
			fileName += ".jpg";
			var is = file.getInputStream();
			Files.copy(is, Paths.get("data/" + fileName), StandardCopyOption.REPLACE_EXISTING);
			
			FileWriter writer = new FileWriter("data/config.txt");
			writer.write(fileNameInt + 1);
			writer.close();
		}catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		return new MessageData(true, "Upload hình ảnh thành công", "/book/get_image/" + fileName);
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
	
	
	/*TYPE CONTROL*/
	@CrossOrigin
	@RequestMapping("/get_all_type")
	public List<String> getAllType() {
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
		return repo.createNewAuthor(request.name, request.currentUserID);
	}
	
	@CrossOrigin
	@RequestMapping("update_author")
	public MessageData<AuthorDetailInfo> UpdateAuthor(@RequestHeader("x-access-token") String token, @RequestBody UpdateAuthorRequest request) {
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		return repo.updateAuthor(request.id, request.name, request.currentUserID);
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
		return repo.createNewPublisher(request.name, request.currentUserID);
	}
	
	@CrossOrigin
	@RequestMapping("update_publisher")
	public MessageData<PublisherDetailInfo> UpdatePublisher(@RequestHeader("x-access-token") String token, @RequestBody UpdateAuthorRequest request) {
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		return repo.updatePublisher(request.id, request.name, request.currentUserID);
	}
}
