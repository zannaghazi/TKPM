package com.TKPM.bookadministratorservice.viewmodel;

public class BookInfoSearchResult {
	public String ISBN;
	public String name;
	public String author;
	public String publisher;
	public String releaseDate;
	public String type;
	public String location;
	public String path;
	public int rentingCount;
	
	public BookInfoSearchResult(String iSBN, String name, String author, String publisherID, String releaseDate,
			String type, String location, String path) {
		super();
		ISBN = iSBN;
		this.name = name;
		this.author = author;
		this.publisher = publisherID;
		this.releaseDate = releaseDate;
		this.type = type;
		this.location = location;
		this.path = path;
		this.rentingCount = 0;
	}
	
	public BookInfoSearchResult(String iSBN, String name, String author, String publisherID, String releaseDate,
			String type, String location, String path, int rentingCount) {
		super();
		ISBN = iSBN;
		this.name = name;
		this.author = author;
		this.publisher = publisherID;
		this.releaseDate = releaseDate;
		this.type = type;
		this.location = location;
		this.path = path;
		this.rentingCount = rentingCount;
	}

	@Override
	public String toString() {
		return "BookInfoSearchResult [ISBN=" + ISBN + ", name=" + name + ", author=" + author + ", publisher="
				+ publisher + ", releaseDate=" + releaseDate + ", type=" + type + ", location=" + location + ", path="
				+ path + "]";
	}
	
}
