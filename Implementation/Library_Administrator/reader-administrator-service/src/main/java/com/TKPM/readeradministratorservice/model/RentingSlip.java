package com.TKPM.readeradministratorservice.model;

public class RentingSlip {
	private int ID;
	private int slipID;
	private String ISBN;
	private int bookID;
	private int status;

	public RentingSlip(int iD, int slipID, String iSBN, int bookID, int status) {
		super();
		ID = iD;
		this.slipID = slipID;
		ISBN = iSBN;
		this.bookID = bookID;
		this.status = status;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getSlipID() {
		return slipID;
	}

	public void setSlipID(int slipID) {
		this.slipID = slipID;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RentingSlip [ID=" + ID + ", slipID=" + slipID + ", ISBN=" + ISBN + ", bookID=" + bookID + ", status="
				+ status + "]";
	}

}
