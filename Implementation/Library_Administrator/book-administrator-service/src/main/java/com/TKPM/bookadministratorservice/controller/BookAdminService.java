package com.TKPM.bookadministratorservice.controller;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.TKPM.bookadministratorservice.model.*;
import com.TKPM.bookadministratorservice.repository.BookInfoRepository;
import com.TKPM.bookadministratorservice.viewmodel.AddAuthorRequest;
import com.TKPM.bookadministratorservice.viewmodel.AddPublisherRequest;
import com.TKPM.bookadministratorservice.viewmodel.BookInfoSearchResult;
import com.TKPM.bookadministratorservice.viewmodel.Message;
import com.TKPM.bookadministratorservice.viewmodel.VNDateTime;


@RestController
@RequestMapping("/book")
public class BookAdminService {

	private BookInfoRepository repo = new BookInfoRepository();
        
	@RequestMapping("/ping")
	public String Hello() {
		return "Hello";
	}
	
	/*BOOK CONTROL*/
	@CrossOrigin
	@RequestMapping("/get_all_book") 
	public List<BookInfo> GetListBookInformation() {
		// TODO: change to real data
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
	public Message uploadImage(@RequestParam MultipartFile file) {
		if(file.isEmpty()) {
			return new Message(true, "Hình ảnh không hợp lệ");
		}
		
		try {
			File myFile = new File("data/config.txt");
			Scanner reader = new Scanner(myFile);
			String fileName = reader.nextLine();
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
		
		return new Message(true, "Upload hình ảnh thành công");
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
	public List<AuthorInfo> getAllAuthor() {
		List<AuthorInfo> result = repo.getListAuthor();
		return result;
	}
	
	@CrossOrigin
	@RequestMapping("add_author")
	public Message AddNewAuthor(@RequestBody AddAuthorRequest request) {
		return repo.createNewAuthor(request.name, request.currentUserID);
	}
	
	@CrossOrigin
	@RequestMapping("get_all_publisher")
	public List<Publisher> getAllPubliser() {
		List<Publisher> result = repo.getAllPublisher();
		return result;
	}
	
	/*PUBLISHER CONTROL*/
	@CrossOrigin
	@RequestMapping("add_publisher")
	public Message AddNewPublisher(@RequestBody AddPublisherRequest request) {
		return repo.createNewPublisher(request.name, request.currentUserID);
	}
	
}
