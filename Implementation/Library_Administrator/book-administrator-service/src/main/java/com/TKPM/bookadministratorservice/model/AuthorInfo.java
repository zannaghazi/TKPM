package com.TKPM.bookadministratorservice.model;

import java.util.Date;

public class AuthorInfo {
	private int id;
	private String name;
	private Boolean isDeleted;
	private Date updatedDate;
	private int updatedAccount;
	
	public AuthorInfo() {
		
	}
	
	public AuthorInfo(int id, String name, Boolean isDeleted) {
		super();
		this.id = id;
		this.name = name;
		this.isDeleted = isDeleted;
	}
	
	public AuthorInfo(int id, String name, Boolean isDeleted, Date updatedDate, int updatedAccount) {
		super();
		this.id = id;
		this.name = name;
		this.isDeleted = isDeleted;
		this.updatedDate = updatedDate;
		this.updatedAccount = updatedAccount;
	}



	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getUpdatedAccount() {
		return updatedAccount;
	}

	public void setUpdatedAccount(int updatedAccount) {
		this.updatedAccount = updatedAccount;
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
