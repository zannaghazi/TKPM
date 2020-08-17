package com.TKPM.systemadministratorservice.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class AccountRole {
	public AccountRole() {
		
	}
	
	public int getRoleIDByName(String name) {
		switch(name) {
		case "Quản trị hệ thống":
			return 1;
		case "Quản trị viên":
			return 2;
		case "Thủ thư":
			return 3;
		case "Độc giả":
			return 4;
		default:
			return 0;
		}
	}
	public String getRoleNameByID(int ID) {
		switch(ID) {
		case 1:
			return "Quản trị hệ thống";
		case 2:
			return "Quản trị viên";
		case 3:
			return "Thủ thư";
		case 4:
			return "Độc giả";
		default:
			return "Không có";
		}
	}
	
	public List<RoleDetail> getListRole() {
		List<RoleDetail> result = new ArrayList<RoleDetail>();
		result.add(new RoleDetail(2, "Quản trị viên"));
		result.add(new RoleDetail(3, "Thủ thư"));
		result.add(new RoleDetail(4, "Độc giả"));
		
		return result;
	}
}
