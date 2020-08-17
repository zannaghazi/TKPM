package com.TKPM.bookadministratorservice.model;

import java.util.ArrayList;
import java.util.List;

import com.TKPM.bookadministratorservice.viewmodel.BookTypeDetail;

public class BookStatus {
	public BookStatus() {
		
	}
	
	public int getStatusIDByName(String name) {
		switch(name) {
		case "Sẵn sàng":
			return 1;
		case "Đang mượn":
			return 2;
		case "Trễ hạn":
			return 3;
		case "Bị mất":
			return 4;
		case "Thanh lý":
			return 5;
		default:
			return 0;
		}
	}
	public String getStatusNameByID(int ID) {
		switch(ID) {
		case 1:
			return "Sẵn sàng";
		case 2:
			return "Đang mượn";
		case 3:
			return "Trễ hạn";
		case 4:
			return "Bị mất";
		case 5:
			return "Thanh lý";
		default:
			return "Không có";
		}
	}
	
	public List<BookTypeDetail> getListStatus() {
		List<BookTypeDetail> result = new ArrayList<BookTypeDetail>();
		result.add(new BookTypeDetail(1, "Sẵn sàng"));
		result.add(new BookTypeDetail(2, "Đang mượn"));
		result.add(new BookTypeDetail(3, "Trễ hạn"));
		result.add(new BookTypeDetail(4, "Bị mất"));
		result.add(new BookTypeDetail(5, "Không có"));
		
		return result;
	}
}
