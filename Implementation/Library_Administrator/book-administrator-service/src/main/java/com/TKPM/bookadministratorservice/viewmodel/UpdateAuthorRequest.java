package com.TKPM.bookadministratorservice.viewmodel;

public class UpdateAuthorRequest {
	public int id;
	public String name;
	public int currentUserID;
	
	public UpdateAuthorRequest(int id, String name, int currentUserID) {
		this.id = id;
		this.name = name;
		this.currentUserID = currentUserID;
	}
}
