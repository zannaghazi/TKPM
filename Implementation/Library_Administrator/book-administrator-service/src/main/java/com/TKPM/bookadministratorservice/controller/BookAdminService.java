package com.TKPM.bookadministratorservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.TKPM.bookadministratorservice.model.*;
import com.TKPM.bookadministratorservice.repository.BookInfoRepository;
import com.TKPM.bookadministratorservice.viewmodel.AddAuthorRequest;
import com.TKPM.bookadministratorservice.viewmodel.AddPublisherRequest;
import com.TKPM.bookadministratorservice.viewmodel.Message;

@RestController
@RequestMapping("/book")
public class BookAdminService {

	private BookInfoRepository repo = new BookInfoRepository();
        
	@RequestMapping("/ping")
	public String Hello() {
		return "Hello";
	}
	
	@RequestMapping("/get_all_book") 
	public List<BookInfo> GetListBookInformation() {
		// TODO: change to real data
		List<BookInfo> temp = repo.getAllBook();
		return temp;
	}
	
	@RequestMapping("/info/{bookId}")
	public BookInfo GetBookInformation(@PathVariable("bookId")String BookID) {
		return null;
	}
	
	@RequestMapping("/search")
	public List<BookInfo> GetSearchedBook(@RequestBody SearchRequest request) {
		List<BookInfo> result = repo.getSearchedBook(request);
		
		return result;
	}
	
	/*TYPE CONTROL*/
	@RequestMapping("/get_all_type")
	public List<String> getAllType() {
		BookType booktype = new BookType();
		return booktype.getListType();
	}
	
	/*AUTHOR CONTROL*/
	@RequestMapping("get_all_author")
	public List<AuthorInfo> getAllAuthor() {
		List<AuthorInfo> result = repo.getListAuthor();
		return result;
	}
	
	@RequestMapping("add_author")
	public Message AddNewAuthor(@RequestBody AddAuthorRequest request) {
		return repo.createNewAuthor(request.name, request.currentUserID);
	}
	
	@RequestMapping("get_all_publisher")
	public List<Publisher> getAllPubliser() {
		List<Publisher> result = repo.getAllPublisher();
		return result;
	}
	
	/*PUBLISHER CONTROL*/
	@RequestMapping("add_publisher")
	public Message AddNewPublisher(@RequestBody AddPublisherRequest request) {
		return repo.createNewPublisher(request.name, request.currentUserID);
	}
	
}
