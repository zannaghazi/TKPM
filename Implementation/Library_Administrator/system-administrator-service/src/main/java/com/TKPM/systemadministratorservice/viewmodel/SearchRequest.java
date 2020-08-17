package com.TKPM.systemadministratorservice.viewmodel;

public class SearchRequest {
	public String type;
	public String key;
	
	public SearchRequest(String type, String key) {
		this.type = type;
		this.key = key;
	}
}
