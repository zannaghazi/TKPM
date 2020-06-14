package com.TKPM.readeradministratorservice.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.TKPM.readeradministratorservice.model.ReaderInfo;

@RestController
@RequestMapping("/reader")
public class ReaderAdminService {
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/ping")
	public String Hello() {
		return "Hello";
	}
	
	@GetMapping("/info/{readerId}")
	public ReaderInfo getReaderByID(@PathVariable("readerId")String readerID) {
		return new ReaderInfo(readerID, "Võ Thanh Hiếu", new Date(1996, 7, 3), "123123123", "thanhhieu@yahoo.com");
	}
}
