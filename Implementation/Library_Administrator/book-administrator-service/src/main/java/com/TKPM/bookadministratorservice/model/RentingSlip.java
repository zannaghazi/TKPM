package com.TKPM.bookadministratorservice.model;

import java.util.Date;

public class RentingSlip {
	private int ID;
	private int accountID;
	private Date createdDate;
	private boolean isDeleted;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public RentingSlip(int iD, int accountID, Date createdDate, boolean isDeleted) {
		super();
		ID = iD;
		this.accountID = accountID;
		this.createdDate = createdDate;
		this.isDeleted = isDeleted;
	}
	
	
}
