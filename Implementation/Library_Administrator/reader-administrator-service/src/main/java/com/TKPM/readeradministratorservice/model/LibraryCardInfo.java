package com.TKPM.readeradministratorservice.model;

import java.util.Date;

public class LibraryCardInfo {
	private int ID;
	private Date createdDate;
	private int duration;
	private int accountID;
	private Boolean isDeleted;
	private Date updatedDate;
	private int updatedAccountID;

	public LibraryCardInfo() {
	}

	public LibraryCardInfo(int iD, Date createdDate, int duration, int accountID, Boolean isDeleted, Date updatedDate,
			int updatedAccountID) {
		ID = iD;
		this.createdDate = createdDate;
		this.duration = duration;
		this.accountID = accountID;
		this.isDeleted = isDeleted;
		this.updatedDate = updatedDate;
		this.updatedAccountID = updatedAccountID;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getUpdatedAccountID() {
		return updatedAccountID;
	}

	public void setUpdatedAccountID(int updatedAccountID) {
		this.updatedAccountID = updatedAccountID;
	}

}
