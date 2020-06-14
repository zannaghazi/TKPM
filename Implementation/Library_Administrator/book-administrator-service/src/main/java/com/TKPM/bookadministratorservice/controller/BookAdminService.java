package com.TKPM.bookadministratorservice.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.TKPM.bookadministratorservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/book")
public class BookAdminService {

        @Autowired
	private RestTemplate restTemplate;
        
	@RequestMapping("/ping")
	public String Hello() {
		return "Hello";
	}
	
	@RequestMapping("/getListBook") 
	public List<BookInfo> GetListBookInformation() {
		// TODO: change to real data
		List<BookInfo> temp = new ArrayList<BookInfo>();
		temp.add(new BookInfo("001", "OP001", "One Piece e1", "001", new Date(1996, 3, 01), 37000));
		temp.add(new BookInfo("002", "OP002", "One Piece e2", "001", new Date(1996, 3, 01), 37000));
		temp.add(new BookInfo("003", "CN001", "Conan e1", "002", new Date(1999, 9, 11), 26000));
		return temp;
	}
	
	@RequestMapping("/info/{bookId}")
	public BookInfo GetBookInformation(@PathVariable("bookId")String BookID) {
		return new BookInfo(BookID, "NA002", "Naruto e2", "003", new Date(2001, 11, 3), 30000);
	}
}
