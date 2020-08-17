package com.TKPM.bookadministratorservice.viewmodel;

public class AddBookRequest {
	public String ISBN;
	public int amount;
	public AddBookRequest(String iSBN, int amountl) {
		super();
		ISBN = iSBN;
		this.amount = amountl;
	}
}
