package com.TKPM.bookadministratorservice.viewmodel;

public class ReturnBookRequest {
	public int rentingID;
	public int ID;
	
	public ReturnBookRequest(int rentingID, int ID) {
		super();
		this.rentingID = rentingID;
		this.ID = ID;
	}
}
