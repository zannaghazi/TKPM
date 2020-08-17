package com.TKPM.bookadministratorservice.model;

public class Book {
	private int ID;
	private String ISBN;
	private Boolean isDeleted;
	private int status;
	
	public Book(int iD, String iSBN, Boolean isDeleted) {
		super();
		ID = iD;
		ISBN = iSBN;
		this.isDeleted = isDeleted;
		this.status = -1;
	}
	
	public Book(int iD, String iSBN, Boolean isDeleted, int status) {
		super();
		ID = iD;
		ISBN = iSBN;
		this.isDeleted = isDeleted;
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
