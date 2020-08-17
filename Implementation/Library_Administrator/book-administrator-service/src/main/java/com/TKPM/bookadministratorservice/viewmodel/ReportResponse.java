package com.TKPM.bookadministratorservice.viewmodel;

import java.util.List;

public class ReportResponse<T> {
	public int numberOfRecord;
	public String message;
	public List<T> data;
	
	public ReportResponse(int number, String message, List<T> data) {
		this.numberOfRecord = number;
		this.message = message;
		this.data = data;
	}
}
