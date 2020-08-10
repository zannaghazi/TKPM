package com.TKPM.bookadministratorservice.viewmodel;

public class AddPublisherRequest {
	public String name;
	public int currentUserID;
	public AddPublisherRequest(String name, int currentUserID) {
		super();
		this.name = name;
		this.currentUserID = currentUserID;
	}
}
