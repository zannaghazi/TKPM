package com.TKPM.bookadministratorservice.model;

public class Book {
	private int ID;
	private int ISBN;
	private Boolean isDeleted;
	public Book(int iD, int iSBN, Boolean isDeleted) {
		super();
		ID = iD;
		ISBN = iSBN;
		this.isDeleted = isDeleted;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
