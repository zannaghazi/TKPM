package com.TKPM.bookadministratorservice.viewmodel;

import com.TKPM.bookadministratorservice.model.Publisher;
import com.TKPM.bookadministratorservice.repository.BookInfoRepository;

public class PublisherDetailInfo {
	public int id;
	public String name;
	public String updatedDate;
	public String updatedAccount;
	public int updatedAccountID;
		
	public PublisherDetailInfo(Publisher data, BookInfoRepository repo) {
		this.id = data.getID();
		this.name = data.getName();
		this.updatedDate = new VNDateTime(data.getUpdatedDate()).getVNTime();
		this.updatedAccountID = data.getUpdatedAccount();
		this.updatedAccount = repo.getAccountNameByID(this.updatedAccountID);
	}
	public PublisherDetailInfo(Publisher data, String updatedAccount) {
		this.id = data.getID();
		this.name = data.getName();
		this.updatedDate = new VNDateTime(data.getUpdatedDate()).getVNTime();
		this.updatedAccountID = data.getUpdatedAccount();
		this.updatedAccount = updatedAccount;
	}
	
	@Override
	public String toString() {
		return "AuthorDetailInfo [id=" + id + ", name=" + name + ", updatedDate=" + updatedDate + ", updatedAccount="
				+ updatedAccount + ", updatedAccountID=" + updatedAccountID + "]";
	}
}
