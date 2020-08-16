package com.TKPM.systemadministratorservice.viewmodel;

import com.TKPM.systemadministratorservice.model.Account;

public class AccountBasicInfo {
	public int id;
	public String fullname;
	public int roleID;
	public String role;
	public String token;
	
	public AccountBasicInfo() {
		this.id = -1;
		this.fullname = null;
		this.role = null;
		this.token = null;
		this.roleID = -1;
	}
	
	public AccountBasicInfo(int id, String fullname, int roleID, String role, String token) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.roleID = roleID;
		this.role = role;
		this.token = token;
	}
	
	public AccountBasicInfo(Account data, String token) {
		this.id = data.getID();
		this.fullname = data.getFullName();
		this.roleID = data.getRole();
		switch (this.roleID) {
		case 1:
			this.role = "Quản trị hệ thống";
			break;
		case 2:
			this.role = "Quản trị viên";
			break;
		case 3:
			this.role = "Thủ thư";
			break;
		case 4:
			this.role = "Độc giả";
			break;
		default:
			this.role = "Độc giả";
			break;
		}
		this.token = token;
	}
}
