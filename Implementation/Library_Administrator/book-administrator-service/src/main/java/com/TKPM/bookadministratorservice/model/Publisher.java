package com.TKPM.bookadministratorservice.model;

import java.util.Date;

public class Publisher {
	@Override
	public String toString() {
		return "Publisher [ID=" + ID + ", name=" + name + ", isDeleted=" + isDeleted + ", updatedDate=" + updatedDate
				+ ", updatedAccount=" + updatedAccount + "]";
	}
	private int ID;
	private String name;
	private Boolean isDeleted;
	private Date updatedDate;
	private int updatedAccount;
	
	public Publisher(int iD, String name, Boolean isDeleted) {
		super();
		ID = iD;
		this.name = name;
		this.isDeleted = isDeleted;
	}
	
	public Publisher(int iD, String name, Boolean isDeleted, Date updatedDate, int updatedAccount) {
		super();
		ID = iD;
		this.name = name;
		this.isDeleted = isDeleted;
		this.updatedDate = updatedDate;
		this.updatedAccount = updatedAccount;
	}



	public int getUpdatedAccount() {
		return updatedAccount;
	}
	public void setUpdatedAccount(int updatedAccount) {
		this.updatedAccount = updatedAccount;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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
