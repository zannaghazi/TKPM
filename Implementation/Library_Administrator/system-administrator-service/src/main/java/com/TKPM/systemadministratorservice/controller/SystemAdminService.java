package com.TKPM.systemadministratorservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.TKPM.systemadministratorservice.model.Account;
import com.TKPM.systemadministratorservice.repository.SystemRepository;
import com.TKPM.systemadministratorservice.viewmodel.AccountBasicInfo;
import com.TKPM.systemadministratorservice.viewmodel.AccountDetailInfo;
import com.TKPM.systemadministratorservice.viewmodel.AccountInfo;
import com.TKPM.systemadministratorservice.viewmodel.AccountRole;
import com.TKPM.systemadministratorservice.viewmodel.LoginInfo;
import com.TKPM.systemadministratorservice.viewmodel.Message;
import com.TKPM.systemadministratorservice.viewmodel.RoleDetail;
import com.TKPM.systemadministratorservice.viewmodel.SearchAccountInfo;
import com.TKPM.systemadministratorservice.viewmodel.SearchRequest;
import com.TKPM.systemadministratorservice.viewmodel.SystemConstBasic;

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
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		return repo.Logout(token);
	}
	
	@CrossOrigin
	@RequestMapping("/add_new_account")
	public Message addNewAccount(@RequestHeader("x-access-token") String token, @RequestBody AccountInfo account) {
		if (!repo.VerifyToken(token)) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		if (account.currentUserID == 0) {
			account.currentUserID = repo.getAccountIDByToken(token);
		}
		return repo.AddNewAccount(account);
	}
	
	@CrossOrigin
	@RequestMapping("/get_account_by_id/{ID}")
	public AccountDetailInfo GetAccountByID(@RequestHeader("x-access-token") String token, @PathVariable("ID") String ID) {
		Account temp = repo.getAccountByID(Integer.parseInt(ID));
		AccountDetailInfo result = new AccountDetailInfo();
		result.address = temp.getAddress();
		result.birthDay = temp.getBirthDay();
		result.fullName = temp.getFullName();
		result.username = temp.getUsername();
		result.gender = temp.isGender();
		result.ID = temp.getID();
		result.role = temp.getRole();
		return result;
	}
	
	@CrossOrigin
	@RequestMapping("/get_current_user")
	public AccountBasicInfo GetCurrentUser(@RequestHeader("x-access-token") String token) {
		if (!repo.VerifyToken(token)) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		return repo.GetCurrentUser(token);
	}
	
	@CrossOrigin
	@RequestMapping("/search_account")
	public List<SearchAccountInfo> GetSearchedAccount(@RequestHeader("x-access-token") String token, @RequestBody SearchRequest request) {
		if (!repo.VerifyToken(token)) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		return this.repo.SearchAccount(request.type, request.key);
	}
	
	@CrossOrigin
	@RequestMapping("/delete_account/{ID}")
	public Message DeleteAccount(@RequestHeader("x-access-token") String token, @PathVariable("ID") String ID) {
		if (!repo.VerifyToken(token)) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		return this.repo.DeleteAccount(ID);
	}
	
	@CrossOrigin
	@RequestMapping("/get_all_role")
	public List<RoleDetail> GetAllRole(@RequestHeader("x-access-token") String token) {
		if (!repo.VerifyToken(token)) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		AccountRole role = new AccountRole();
		return role.getListRole();
	}
	
	@CrossOrigin
	@RequestMapping("/update_account")
	public Message UpdateAccount(@RequestHeader("x-access-token") String token, @RequestBody AccountInfo data) {
		if (!repo.VerifyToken(token)) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		if (data.currentUserID == 0) {
			data.currentUserID = repo.getAccountIDByToken(token);
		}
		return repo.UpdateAccountInfo(data);
	}
	
	@CrossOrigin
	@RequestMapping("/get_all_system_const")
	public SystemConstBasic GetAllSystemConst(@RequestHeader("x-access-token") String token) {
		if (!repo.VerifyToken(token)) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		return repo.getAllSystemConst();
	}
	
	@CrossOrigin
	@RequestMapping("/update_system_const")
	public Message UpdateSystemConst(@RequestHeader("x-access-token") String token, @RequestBody SystemConstBasic data) {
		if (!repo.VerifyToken(token)) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		return repo.UpdateSystemConst(data);
	}
}
