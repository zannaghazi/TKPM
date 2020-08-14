package com.TKPM.systemadministratorservice.model;

import java.util.Date;

public class Account {
	private int ID;
	private String fullName;
	private String address;
	private boolean gender;
	private Date birthDay;
	private boolean isDeleted;
	private boolean isReader;
	private int role;
	private String username;
	private String password;
	
	public Account(int iD, String fullName, String address, boolean gender, Date birthDay, boolean isDeleted,
			boolean isReader, int role, String username, String password) {
		super();
		ID = iD;
		this.fullName = fullName;
		this.address = address;
		this.gender = gender;
		this.birthDay = birthDay;
		this.isDeleted = isDeleted;
		this.isReader = isReader;
		this.role = role;
		this.username = username;
		this.password = password;
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
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public boolean isReader() {
		return isReader;
	}
	public void setReader(boolean isReader) {
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
	
	
}
