package com.TKPM.readeradministratorservice.model;

public class Publisher {
	private int ID;
	private String name;
	private Boolean isDeleted;
	
	public Publisher(int iD, String name, Boolean isDeleted) {
		super();
		ID = iD;
		this.name = name;
		this.isDeleted = isDeleted;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
