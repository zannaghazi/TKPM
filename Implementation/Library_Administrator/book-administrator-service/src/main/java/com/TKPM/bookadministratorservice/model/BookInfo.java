package com.TKPM.bookadministratorservice.model;

import java.util.Date;

import com.TKPM.bookadministratorservice.repository.BookInfoRepository;

public class BookInfo {
	private String ID;
	private String ISBN;
	private String name;
	private String authorID;
	private Date releaseDate;
	private double price;
	
	private BookInfoRepository repository;

	public BookInfo(String iD, String iSBN, String name, String authorID, Date releaseDate, double price) {
		super();
		ID = iD;
		ISBN = iSBN;
		this.name = name;
		this.authorID = authorID;
		this.releaseDate = releaseDate;
		this.price = price;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthorID() {
		return authorID;
	}

	public void setAuthorID(String authorID) {
		this.authorID = authorID;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
