package com.TKPM.systemadministratorservice.model;

import java.util.Date;

public class SystemConstrants {
	private int ID;
	private String name;
	private String value;
	private boolean isDeleted;
	private Date updatedData;
	private int updatedAccount;
	public SystemConstrants(int iD, String name, String value, boolean isDeleted, Date updatedData,
			int updatedAccount) {
		super();
		ID = iD;
		this.name = name;
		this.value = value;
		this.isDeleted = isDeleted;
		this.updatedData = updatedData;
		this.updatedAccount = updatedAccount;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Date getUpdatedData() {
		return updatedData;
	}
	public void setUpdatedData(Date updatedData) {
		this.updatedData = updatedData;
	}
	public int getUpdatedAccount() {
		return updatedAccount;
	}
	public void setUpdatedAccount(int updatedAccount) {
		this.updatedAccount = updatedAccount;
	}
	
}
