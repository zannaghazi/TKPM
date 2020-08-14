package com.TKPM.bookadministratorservice.viewmodel;

public class MessageData<T> {
	public Boolean result;
	public String message;
	public T data;
	
	public MessageData(Boolean result, String message, T data) {
		super();
		this.result = result;
		this.message = message;
		this.data = data;
	}
}

