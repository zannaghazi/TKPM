package com.TKPM.readeradministratorservice.viewmodel;

public class ReaderInfoSearchResult {
	private int ID;
	private String fullName;
	private String gender;
	private String ISBN;
	private String bookName;
	private String type;
	private String author;
	private String publisher;

	public ReaderInfoSearchResult(String fullName, String gender, String iSBN, String bookName, String type, String author,
			String publisher, int ID) {
		super();
		this.fullName = fullName;
		this.gender = gender;
		ISBN = iSBN;
		this.bookName = bookName;
		this.type = type;
		this.author = author;
		this.publisher = publisher;
		this.ID = ID;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getFullName() {
		return fullName;
	}

	public String getGender() {
		return gender;
	}

	public String getISBN() {
		return ISBN;
	}

	public String getBookName() {
		return bookName;
	}

	public String getType() {
		return type;
	}

	public String getAuthor() {
		return author;
	}

	public String getPublisher() {
		return publisher;
	}

	@Override
	public String toString() {
		return "ReaderInfoSearchResult [fullName=" + fullName + ", gender=" + gender + ", ISBN=" + ISBN + ", bookName="
				+ bookName + ", type=" + type + ", author=" + author + ", publisher=" + publisher + "]";
	}
}
