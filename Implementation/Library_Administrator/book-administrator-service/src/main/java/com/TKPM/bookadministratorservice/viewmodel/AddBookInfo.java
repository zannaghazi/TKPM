package com.TKPM.bookadministratorservice.viewmodel;

public class AddBookInfo {
	public String ISBN;
	public String name;
	public int author;
	public int publisher;
	public String releaseDate;
	public int type;
	public String location;
	
	public AddBookInfo(String iSBN, String name, int author, int publisher, String releaseDate, int type,
			String location) {
		super();
		ISBN = iSBN;
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.releaseDate = releaseDate;
		this.type = type;
		this.location = location;
	}
}
