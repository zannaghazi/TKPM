package com.TKPM.bookadministratorservice.viewmodel;

import java.util.Date;

import com.TKPM.bookadministratorservice.model.BookInfo;

public class BookInfoDetail {
	public String ISBN;
	public String name;
	public int authorID;
	public String author;
	public int publisherID;
	public String publisher;
	public Date releaseDate;
	public int type;
	public String typeName;
	public String location;
	public String path;
	public Boolean isDeleted;

	public BookInfoDetail(BookInfo data, String author, String publisher, String typeName) {
		super();
		this.ISBN = data.getISBN();
		this.name = data.getName();
		this.authorID = data.getAuthorID();
		this.publisherID = data.getPublisherID();
		this.releaseDate = data.getReleaseDate();
		this.type = data.getType();
		this.location = data.getLocation();
		this.path = data.getPath();
		this.isDeleted = data.getIsDeleted();
		
		this.author = author;
		this.publisher = publisher;
		this.typeName = typeName;
	}
}
