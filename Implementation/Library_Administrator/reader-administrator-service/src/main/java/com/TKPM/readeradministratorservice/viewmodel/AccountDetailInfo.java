package com.TKPM.readeradministratorservice.viewmodel;

import com.TKPM.readeradministratorservice.model.AccountInfo;
import com.TKPM.readeradministratorservice.model.LibraryCardInfo;
import com.TKPM.readeradministratorservice.repository.ReaderInfoRepository;

public class AccountDetailInfo {
	public int ID;
	public String fullName;
	public String username;
	public String updatedDate;
	public String updatedAccount;
	public int updatedAccountID;
	public int cardID;
	public String createdDate;
	public int duration;

	public AccountDetailInfo() {
	}

	public AccountDetailInfo(AccountInfo accInfo, LibraryCardInfo libCard, ReaderInfoRepository repo) {
		ID = accInfo.getID();
		this.fullName = accInfo.getFullName();
		this.username = accInfo.getUsername();
		this.updatedDate = new VNDateTime(accInfo.getUpdatedDate()).getVNTime();
		this.updatedAccountID = accInfo.getUpdatedAccountID();
		this.updatedAccount = repo.GetAccountNameByID(this.updatedAccountID);
		this.cardID = libCard.getID();
		this.createdDate = new VNDateTime(libCard.getCreatedDate()).getVNTime();
		this.duration = libCard.getDuration();
	}

}
