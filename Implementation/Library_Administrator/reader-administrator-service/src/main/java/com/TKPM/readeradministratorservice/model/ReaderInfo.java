package com.TKPM.readeradministratorservice.model;

import java.util.Date;

import com.TKPM.readeradministratorservice.repository.ReaderInfoRepository;

public class ReaderInfo {
	private String ID;
	private String name;
	private Date birth;
	private String certifiedNumber;
	private String email;
	
	private ReaderInfoRepository repository;
	
	public ReaderInfo(String iD, String name, Date birth, String certifiedNumber, String email) {
		super();
		ID = iD;
		this.name = name;
		this.birth = birth;
		this.certifiedNumber = certifiedNumber;
		this.email = email;
	}

	@Override
	public String toString() {
		return "ReaderInfo [ID=" + ID + ", name=" + name + ", birth=" + birth + ", certifiedNumber=" + certifiedNumber
				+ ", email=" + email + "]";
	}


	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getCertifiedNumber() {
		return certifiedNumber;
	}

	public void setCertifiedNumber(String certifiedNumber) {
		this.certifiedNumber = certifiedNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
