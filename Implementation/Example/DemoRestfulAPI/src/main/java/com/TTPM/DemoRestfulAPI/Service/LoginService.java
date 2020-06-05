package com.TTPM.DemoRestfulAPI.Service;

import com.TTPM.DemoRestfulAPI.Model.User;

public class LoginService {
	public static Boolean Login(User user) {
		if (user.getUsername().equals("zan")) {
			return true;
		}
		return false;
	}
}
