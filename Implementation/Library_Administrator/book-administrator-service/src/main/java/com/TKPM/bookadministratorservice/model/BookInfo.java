package com.TKPM.bookadministratorservice.model;

import java.util.Date;

public class BookInfo {
	private String ISBN;
	private String name;
	private int authorID;
	private int publisherID;
	private Date releaseDate;
	private int type;
	private String location;
	private String path;
	private Boolean isDeleted;

	public BookInfo(String ISBN, String name, int authorID, int publisherID, Date releaseDate, int type,
			String location, String path, Boolean isDeleted) {
		super();
		this.ISBN = ISBN;
		this.name = name;
		this.authorID = authorID;
		this.publisherID = publisherID;
		this.releaseDate = releaseDate;
		this.type = type;
		this.location = location;
		this.path = path;
		this.isDeleted = isDeleted;
	}

	public int getPublisherID() {
		return publisherID;
	}

	public void setPublisherID(int publisherID) {
		this.publisherID = publisherID;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
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

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		return "BookInfo [ISBN=" + ISBN + ", name=" + name + ", authorID=" + authorID + ", publisherID=" + publisherID
				+ ", releaseDate=" + releaseDate + ", type=" + type + ", location=" + location + ", path=" + path
				+ ", isDeleted=" + isDeleted + "]";
	}

}
