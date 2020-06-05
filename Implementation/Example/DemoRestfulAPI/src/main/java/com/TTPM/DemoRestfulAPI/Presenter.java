package com.TTPM.DemoRestfulAPI;

import java.io.Console;

import org.apache.commons.logging.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TTPM.DemoRestfulAPI.Model.User;
import com.TTPM.DemoRestfulAPI.Service.LoginService;

@RestController
public class Presenter {
	@RequestMapping("/hello")
	public String Hello(@RequestParam(name="name", defaultValue = "Zannaghazi") String name) {
		return "Xin chao "+name;
	}
	
	@GetMapping("/login")
	public User Login() {
		return new User("zan", "naghazi");
	}
	
	@PostMapping("/login")
	public Boolean LoginResult(@RequestBody User user) {
		if (LoginService.Login(user)) {
			return true;
		}
		return false;
	}
}
