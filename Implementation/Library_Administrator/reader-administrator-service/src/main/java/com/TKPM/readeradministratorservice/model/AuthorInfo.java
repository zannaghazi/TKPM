package com.TKPM.readeradministratorservice.model;

public class AuthorInfo {
	private int id;
	private String name;
	private Boolean isDeleted;
	
	public AuthorInfo(int id, String name, Boolean isDeleted) {
		super();
		this.id = id;
		this.name = name;
		this.isDeleted = isDeleted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
