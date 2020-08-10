package com.TKPM.bookadministratorservice.model;

public class SearchRequest {
	public String type;
	public String key;
	
	public SearchRequest(String type, String key) {
		super();
		this.type = type;
		this.key = key;
	}
	
	
}
