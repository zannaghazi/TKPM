package com.TKPM.readeradministratorservice.viewmodel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VNDateTime {
	public Date date;
	
	public VNDateTime(Date date) {
		this.date = date;
	}
	
	public VNDateTime(String VNDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date newDate = new Date();
		try {
			newDate = formatter.parse(VNDate);
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		this.date = newDate;
	}
	
	public String getVNTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(this.date);
	}
}
