package com.TKPM.systemadministratorservice.viewmodel;

import java.util.Date;

import com.TKPM.systemadministratorservice.model.Account;

public class SearchAccountInfo {
	public int ID;
	public String fullname;
	public String username;
	public Date birthDay;
	public String address;
	public String gender;
	public String role;
	
	public SearchAccountInfo(Account data) {
		AccountRole accountRole = new AccountRole();
		this.ID = data.getID();
		this.fullname = data.getFullName();
		this.username = data.getUsername();
		this.birthDay = data.getBirthDay();
		this.address = data.getAddress();
		this.gender = data.isGender() ? "Nam" : "Nữ";
		this.role = accountRole.getRoleNameByID(data.getRole());
	}
}
