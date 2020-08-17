package com.TKPM.bookadministratorservice.viewmodel;

import com.TKPM.bookadministratorservice.model.Book;

public class BookDetail {
	public int ID;
	public String ISBN;
	public Boolean isDeleted;
	public int status;
	public String statusName;
	
	public BookDetail(int iD, String iSBN, Boolean isDeleted, int status, String statusString) {
		super();
		ID = iD;
		ISBN = iSBN;
		this.isDeleted = isDeleted;
		this.status = status;
		this.statusName = statusString;
	}
	
	public BookDetail(Book data, String statusString) {
		this.ID = data.getID();
		this.ISBN = data.getISBN();
		this.isDeleted = data.getIsDeleted();
		this.status = data.getStatus();
		this.statusName = statusString;
	}
}
