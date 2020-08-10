package com.TKPM.bookadministratorservice.viewmodel;

public class AddAuthorRequest {
	public String name;
	public int currentUserID;
	public AddAuthorRequest(String name, int currentUserID) {
		super();
		this.name = name;
		this.currentUserID = currentUserID;
	}
}
