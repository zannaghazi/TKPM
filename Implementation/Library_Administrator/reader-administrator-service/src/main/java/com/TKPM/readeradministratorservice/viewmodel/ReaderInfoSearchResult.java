package com.TKPM.readeradministratorservice.viewmodel;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

public class ReaderInfoSearchResult {
	private int accountID;
	private String fullName;
	private int libraryCardID;
	private Date createdDate;
	private int duration;
	private String expiredDate;

	public ReaderInfoSearchResult(int accountID, String fullName, int libraryCardID, Date createdDate, int duration) {
		this.accountID = accountID;
		this.fullName = fullName;
		this.libraryCardID = libraryCardID;
		this.createdDate = createdDate;
		this.duration = duration;
		Date date = DateUtils.addMonths(this.createdDate, this.duration);
		this.expiredDate = new VNDateTime(date).getVNTime();
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getLibraryCardID() {
		return libraryCardID;
	}

	public void setLibraryCardID(int libraryCardID) {
		this.libraryCardID = libraryCardID;
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

	public String getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}

	@Override
	public String toString() {
		return "ReaderInfoSearchResult [accountID=" + accountID + ", fullName=" + fullName + ", libraryCardID="
				+ libraryCardID + ", createdDate=" + createdDate + ", duration=" + duration + ", expiredDate="
				+ expiredDate + "]";
	}

}
