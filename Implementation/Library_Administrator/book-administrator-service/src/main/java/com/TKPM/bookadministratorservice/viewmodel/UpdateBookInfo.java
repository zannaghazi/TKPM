package com.TKPM.bookadministratorservice.viewmodel;

public class UpdateBookInfo {
	public String ISBN;
	public String name;
	public int authorID;
	public int publisherID;
	public String releaseDate;
	public int type;
	public String location;
	public boolean isDeleted;
	public int amount;
	public String image;
	
	public UpdateBookInfo(String iSBN, String name, int authorID, int publisherID, String releaseDate, int type,
			String location, boolean isDeleted, int amount, String image) {
		super();
		ISBN = iSBN;
		this.name = name;
		this.authorID = authorID;
		this.publisherID = publisherID;
		this.releaseDate = releaseDate;
		this.type = type;
		this.location = location;
		this.isDeleted = isDeleted;
		this.amount = amount;
		this.image = image;
	}
}
