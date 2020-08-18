package com.TKPM.bookadministratorservice.viewmodel;

import com.TKPM.bookadministratorservice.model.Book;

public class BookWithBookInfo {
	public int ID;
	public String ISBN;
	public Boolean isDeleted;
	public int status;
	public String statusName;
	public String BookName;
	
	public BookWithBookInfo(int iD, String iSBN, Boolean isDeleted, int status, String statusString) {
		super();
		ID = iD;
		ISBN = iSBN;
		this.isDeleted = isDeleted;
		this.status = status;
		this.statusName = statusString;
	}
	
	public BookWithBookInfo(Book data, String statusString, String bookName) {
		this.ID = data.getID();
		this.ISBN = data.getISBN();
		this.isDeleted = data.getIsDeleted();
		this.status = data.getStatus();
		this.statusName = statusString;
		this.BookName = bookName;
	}
}
