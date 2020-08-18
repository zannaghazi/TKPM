package com.TKPM.bookadministratorservice.viewmodel;

import com.TKPM.bookadministratorservice.model.Book;

public class RentingBookInfo {
	public int rentingID;
	public int ID;
	public String ISBN;
	public String name;

	public RentingBookInfo(Book data, String bookName, int rentingID) {
		super();
		this.ID = data.getID();
		this.ISBN = data.getISBN();
		this.name = bookName;
		this.rentingID = rentingID;
	}
}
