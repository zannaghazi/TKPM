package com.TKPM.bookadministratorservice.viewmodel;

import com.TKPM.bookadministratorservice.model.AuthorInfo;
import com.TKPM.bookadministratorservice.repository.BookInfoRepository;

public class AuthorDetailInfo {
	public int id;
	public String name;
	public String updatedDate;
	public String updatedAccount;
	public int updatedAccountID;
	
	public AuthorDetailInfo(AuthorInfo data, BookInfoRepository repo) {
		this.id = data.getId();
		this.name = data.getName();
		this.updatedDate = new VNDateTime(data.getUpdatedDate()).getVNTime();
		this.updatedAccountID = data.getUpdatedAccount();
		this.updatedAccount = repo.getAccountNameByID(this.updatedAccountID);
	}
}
