package com.TKPM.bookadministratorservice.model;

import java.util.ArrayList;
import java.util.List;

public class BookType {
	public BookType() {
		
	}
	
	public int getTypeIDByName(String name) {
		switch(name) {
		case "Chính trị – pháp luật":
			return 1;
		case "Khoa học công nghệ – Kinh tế":
			return 2;
		case "Văn hóa xã hội – Lịch sử":
			return 3;
		case "Văn học nghệ thuật":
			return 4;
		case "Giáo trình":
			return 5;
		case "Truyện, tiểu thuyết":
			return 6;
		case "Tâm lý, tâm linh, tôn giáo":
			return 7;
		case "Sách thiếu nhi":
			return 8;
		default:
			return 0;
		}
	}
	public String getTypeNameByID(int ID) {
		switch(ID) {
		case 1:
			return "Chính trị – pháp luật";
		case 2:
			return "Khoa học công nghệ – Kinh tế";
		case 3:
			return "Văn hóa xã hội – Lịch sử";
		case 4:
			return "Văn học nghệ thuật";
		case 5:
			return "Giáo trình";
		case 6:
			return "Truyện, tiểu thuyết";
		case 7:
			return "Tâm lý, tâm linh, tôn giáo";
		case 8:
			return "Sách thiếu nhi";
		default:
			return "Không có";
		}
	}
	public List<String> getListType() {
		List<String> result = new ArrayList<String>();
		result.add("Chính trị – pháp luật");
		result.add("Khoa học công nghệ – Kinh tế");
		result.add("Văn hóa xã hội – Lịch sử");
		result.add("Văn học nghệ thuật");
		result.add("Giáo trình");
		result.add("Truyện, tiểu thuyết");
		result.add("Tâm lý, tâm linh, tôn giáo");
		result.add("Sách thiếu nhi");
		
		return result;
	}
}
