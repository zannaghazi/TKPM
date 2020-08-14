package com.TKPM.systemadministratorservice.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TKPM.systemadministratorservice.repository.SystemRepository;
import com.TKPM.systemadministratorservice.viewmodel.AccountBasicInfo;
import com.TKPM.systemadministratorservice.viewmodel.LoginInfo;

@RestController
@RequestMapping("/system")
public class SystemAdminService {
	private SystemRepository repo = new SystemRepository();
	
	@RequestMapping("/ping")
	public String Hello() {
		return "Hello";
	}
	
	@CrossOrigin
	@RequestMapping("/login")
	public AccountBasicInfo Login(@RequestBody LoginInfo loginInfo) {
		AccountBasicInfo result = repo.Login(loginInfo.username, loginInfo.password);
		return result;
	}
}
