package com.TKPM.systemadministratorservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.TKPM.systemadministratorservice.repository.SystemRepository;
import com.TKPM.systemadministratorservice.viewmodel.AccountBasicInfo;
import com.TKPM.systemadministratorservice.viewmodel.AccountInfo;
import com.TKPM.systemadministratorservice.viewmodel.LoginInfo;
import com.TKPM.systemadministratorservice.viewmodel.Message;

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
	
	@CrossOrigin
	@RequestMapping("/logout")
	public Message Logout(@RequestHeader("x-access-token") String token) {
		if (!repo.VerifyToken(token)) {
			System.out.print(repo.VerifyToken(token));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		return repo.Logout(token);
	}
	
	@CrossOrigin
	@RequestMapping("/add_new_account")
	public Message addNewAccount(@RequestBody AccountInfo account) {
		
		return new Message(true, "Thêm tài khoản thành công");
	}
	
	@CrossOrigin
	@RequestMapping("/get_current_user")
	public AccountBasicInfo GetCurrentUser(@RequestHeader("x-access-token") String token) {
		return repo.GetCurrentUser(token);
	}
}
