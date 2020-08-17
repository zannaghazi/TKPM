package com.TKPM.readeradministratorservice.model;

import java.util.Date;

public class AccountInfo {
	private int ID;
	@Override
	public String toString() {
		return "AccountInfo [ID=" + ID + ", fullName=" + fullName + ", address=" + address + ", gender=" + gender
				+ ", birthday=" + birthday + ", isDeleted=" + isDeleted + ", isReader=" + isReader + ", role=" + role
				+ ", username=" + username + ", password=" + password + ", updatedDate=" + updatedDate
				+ ", updatedAccountID=" + updatedAccountID + "]";
	}

	private String fullName;
	private String address;
	private Boolean gender;
	private Date birthday;
	private Boolean isDeleted;
	private Boolean isReader;
	private int role;
	private String username;
	private String password;
	private Date updatedDate;
	private int updatedAccountID;

	public AccountInfo() {
	}

	public AccountInfo(int iD, String fullName, String address, Boolean gender, Date birthday, Boolean isDeleted,
			Boolean isReader, int role, String username, String password, Date updatedDate, int updatedAccountID) {
		ID = iD;
		this.fullName = fullName;
		this.address = address;
		this.gender = gender;
		this.birthday = birthday;
		this.isDeleted = isDeleted;
		this.isReader = isReader;
		this.role = role;
		this.username = username;
		this.password = password;
		this.updatedDate = updatedDate;
		this.updatedAccountID = updatedAccountID;
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

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getIsReader() {
		return isReader;
	}

	public void setIsReader(Boolean isReader) {
		this.isReader = isReader;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
