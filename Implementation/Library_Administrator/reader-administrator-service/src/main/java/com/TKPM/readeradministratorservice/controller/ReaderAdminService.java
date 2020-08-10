package com.TKPM.readeradministratorservice.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.TKPM.readeradministratorservice.model.ReaderInfo;
import com.TKPM.readeradministratorservice.repository.ReaderInfoRepository;

@RestController
@RequestMapping("/reader")
public class ReaderAdminService {
	
	private ReaderInfoRepository repository = new ReaderInfoRepository();
	
	@RequestMapping("/ping")
	public String Hello() {
		return "Hello";
	}
	
	@GetMapping("/get_list_reader")
	public List<ReaderInfo> getAllReader() {
		List<ReaderInfo> result = repository.getAllReader();
		return result;
	}
	
	@GetMapping("/info/{readerId}")
	public ReaderInfo getReaderByID(@PathVariable("readerId")String readerID) {
		return null;
	}
}
