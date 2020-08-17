package com.TKPM.readeradministratorservice.model;

import java.util.Date;

import com.TKPM.readeradministratorservice.viewmodel.VNDateTime;

public class CreateRequest {
	public String fullName;
	public String address;
	public int gender;
	public VNDateTime birthday;
	public Boolean isReader;
	public String userName;
	public int currentUserID;
}
