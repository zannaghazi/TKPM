package com.TKPM.readeradministratorservice.model;

public class SearchRequest {
	public String type;
	public String key;
	public int currentUserID;
	
	public SearchRequest(String type, String key, int currentUserID) {
		super();
		this.type = type;
		this.key = key;
		this.currentUserID = currentUserID;
	}

	@Override
	public String toString() {
		return "SearchRequest [type=" + type + ", key=" + key + ", currentUserID=" + currentUserID + "]";
	}
	
}
