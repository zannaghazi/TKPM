package com.TKPM.bookadministratorservice.model;

import java.util.ArrayList;
import java.util.List;

import com.TKPM.bookadministratorservice.viewmodel.BookTypeDetail;

public class BookType {
	public BookType() {
		
	}
	
	public int getTypeIDByName(String name) {
		switch(name) {
		case "Khoa học công nghệ – Kinh tế":
			return 1;
		case "Văn hóa xã hội – Lịch sử":
			return 2;
		case "Văn học nghệ thuật":
			return 3;
		case "Giáo trình":
			return 4;
		case "Truyện, tiểu thuyết":
			return 5;
		default:
			return 0;
		}
	}
	public String getTypeNameByID(int ID) {
		switch(ID) {
		case 1:
			return "Khoa học công nghệ – Kinh tế";
		case 2:
			return "Văn hóa xã hội – Lịch sử";
		case 3:
			return "Văn học nghệ thuật";
		case 4:
			return "Giáo trình";
		case 5:
			return "Truyện, tiểu thuyết";
		default:
			return "Không có";
		}
	}
	
	public List<BookTypeDetail> getListType() {
		List<BookTypeDetail> result = new ArrayList<BookTypeDetail>();
		result.add(new BookTypeDetail(1, "Khoa học công nghệ – Kinh tế"));
		result.add(new BookTypeDetail(2, "Văn hóa xã hội – Lịch sử"));
		result.add(new BookTypeDetail(3, "Văn học nghệ thuật"));
		result.add(new BookTypeDetail(4, "Giáo trình"));
		result.add(new BookTypeDetail(5, "Truyện, tiểu thuyết"));
		
		return result;
	}
}
