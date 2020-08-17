package com.TKPM.readeradministratorservice.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.TKPM.readeradministratorservice.model.AccountInfo;
import com.TKPM.readeradministratorservice.model.AuthorInfo;
import com.TKPM.readeradministratorservice.model.BookType;
import com.TKPM.readeradministratorservice.model.ChangeInfoRequest;
import com.TKPM.readeradministratorservice.model.CreateRequest;
import com.TKPM.readeradministratorservice.model.ExtendLibCardRequest;
import com.TKPM.readeradministratorservice.model.LibraryCardInfo;
import com.TKPM.readeradministratorservice.model.Publisher;
import com.TKPM.readeradministratorservice.model.RsPwdDelAccRequest;
import com.TKPM.readeradministratorservice.model.SearchRequest;
import com.TKPM.readeradministratorservice.repository.ReaderInfoRepository;
import com.TKPM.readeradministratorservice.viewmodel.AccountDetailInfo;
import com.TKPM.readeradministratorservice.viewmodel.Message;
import com.TKPM.readeradministratorservice.viewmodel.MessageData;
import com.TKPM.readeradministratorservice.viewmodel.ReaderInfoSearchResult;

@RestController
@RequestMapping("/reader")
public class ReaderAdminService {

	private ReaderInfoRepository repo = new ReaderInfoRepository();

	@CrossOrigin
	@RequestMapping("/ping")
	public String Hello() {
		return "Hello";
	}

	@CrossOrigin
	@RequestMapping("/get_list_reader")
	public List<AccountInfo> GetAllReader(@RequestHeader("x-access-token") String token) {
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		List<AccountInfo> result = repo.GetAllReader();
		return result;
	}

	@CrossOrigin
	@GetMapping("/info/{readerId}")
	public AccountInfo GetReaderByID(@PathVariable("readerId") String readerID) {
		return null;
	}

	/*READER COMTROL*/
	@CrossOrigin
	@RequestMapping(path = "/search", produces = "application/json")
	public MessageData<List<ReaderInfoSearchResult>> GetSearchReader(
			@RequestHeader("x-access-token") String token,
			@RequestBody SearchRequest request) {
		// Verify access token
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		
		try {
			// Check user
			AccountInfo currentUser = repo.GetAccountByID(request.currentUserID);
			if (currentUser == null) {
				return new MessageData<List<ReaderInfoSearchResult>>(
						false, 
						"Vui lòng đăng nhập với tài khoản người quản lý",
						new ArrayList<ReaderInfoSearchResult>()
						);
			}
			if (currentUser.getRole() != 2) {
				return new MessageData<List<ReaderInfoSearchResult>>(
						false, 
						"Vui lòng đăng nhập với tài khoản người quản lý",
						new ArrayList<ReaderInfoSearchResult>()
						);
			}
			
			// Search Reader by condition
			List<ReaderInfoSearchResult> data = new ArrayList<ReaderInfoSearchResult>();
			List<ReaderInfoSearchResult> lstSearchResult = repo.GetSearchedReader(request);
			BookType bookType = new BookType();
			
			for (int i = 0; i < lstSearchResult.size(); i++) {
				AuthorInfo author = repo.GetAuthorByID(Integer.valueOf(lstSearchResult.get(i).getAuthor()));
				Publisher publisher = repo.GetPublisherByID(Integer.valueOf(lstSearchResult.get(i).getPublisher()));
				
				ReaderInfoSearchResult temp =  new ReaderInfoSearchResult(
						lstSearchResult.get(i).getFullName(),
						Integer.valueOf(lstSearchResult.get(i).getGender()) == 0 ? "Nữ" : "Nam",
						lstSearchResult.get(i).getISBN(),
						lstSearchResult.get(i).getBookName(),
						bookType.getTypeNameByID(Integer.valueOf(lstSearchResult.get(i).getType())),
						author.getName(),
						publisher.getName(),
						lstSearchResult.get(i).getID());
				data.add(temp);
			}
			
			MessageData<List<ReaderInfoSearchResult>> result = new MessageData<List<ReaderInfoSearchResult>>(true, null, data);
			
			return result;
		} catch (NumberFormatException e) {
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, 
					"A problem has occur. Please contact the administrator!"
					);
		}
	}

	/*ACCOUNT COMTROL*/
	@CrossOrigin
	@RequestMapping("/create")
	public MessageData<AccountDetailInfo> CreateAccount(
			@RequestHeader(name = "x-access-token", required = true) String token,
			@RequestBody CreateRequest request) {
		// Verify access token
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		
		try {
			// Check user
			AccountInfo currentUser = repo.GetAccountByID(request.currentUserID);
			if (currentUser == null) {
				return new MessageData<AccountDetailInfo>(
						false, 
						"Vui lòng đăng nhập với tài khoản nhân viên thư viện",
						null);
			}
			
			int accRole = 0; // role of account
			Boolean isCrtCard = false; // can create library card or not
			if (currentUser.getRole() == 2) {
				accRole = 3;
				isCrtCard = false;
			} else if (currentUser.getRole() == 3) {
				accRole = 4;
				isCrtCard = true;
			} else {
				return new MessageData<AccountDetailInfo>(
						false, 
						"Vui lòng đăng nhập với tài khoản nhân viên thư viện",
						null);
			}

			// Create account
			AccountInfo acc = repo.CreateAccount(request, accRole);
			// if failed to create new account, return message error
			if (acc == null) {
				return new MessageData<AccountDetailInfo>(
						false, 
						"Tạo tài khoản thất bại", 
						null);
			}
			System.out.println(acc.toString());
			// Create Library card for reader
			LibraryCardInfo libCard = new LibraryCardInfo();
			if (isCrtCard) {
				libCard = repo.CreateLibraryCard(acc);
				// if failed to create Library card, return message error
				if (libCard == null) {
					return new MessageData<AccountDetailInfo>(
							false, 
							"Tạo thẻ thư viện thất bại", 
							null);
				}
			}

			// Create return message
			MessageData<AccountDetailInfo> result = new MessageData<AccountDetailInfo>(
					true, 
					"Tạo thành công",
					new AccountDetailInfo(acc, libCard, repo)
					);

			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, 
					"A problem has occur. Please contact the administrator!"
					);
		}
	}
	
	@CrossOrigin
	@RequestMapping("/reset_password")
	public MessageData<String> ResetPassword(
			@RequestHeader(name = "x-access-token", required = true) String token,
			@RequestBody RsPwdDelAccRequest request){

		// Verify access token
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		
		try {
			// Check user
			AccountInfo currentUser = repo.GetAccountByID(request.loginUserID);
			if (currentUser == null) {
				return new MessageData<String>(
						false, 
						"Vui lòng đăng nhập với tài khoản nhân viên thư viện",
						"");
			}
			if (currentUser.getRole() != 2 && currentUser.getRole() != 3) {
				return new MessageData<String>(
						false, 
						"Vui lòng đăng nhập với tài khoản nhân viên thư viện",
						"");
			}
			
			Boolean result = repo.ResetPassword(request);
			if (!result) {
				return new MessageData<String>(
						false, 
						"Không thể thiết lập lại mật khẩu. Vui lòng thử lại.",
						"");
			}
			
			return new MessageData<String>(
					true, 
					"Thiết lập lại mật khẩu thành công",
					"123456");

		} catch (Exception e) {
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, 
					"A problem has occur. Please contact the administrator!"
					);
		}
	}

	@CrossOrigin
	@RequestMapping("/delete_account")
	public Message DeleteAccount(
			@RequestHeader(name = "x-access-token", required = true) String token,
			@RequestBody RsPwdDelAccRequest request){

		// Verify access token
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		
		try {
			// Check user
			AccountInfo currentUser = repo.GetAccountByID(request.loginUserID);
			if (currentUser == null) {
				return new Message(
						false, 
						"Vui lòng đăng nhập với tài khoản nhân viên thư viện");
			}
			if (currentUser.getRole() != 2 && currentUser.getRole() != 3) {
				return new Message(
						false, 
						"Vui lòng đăng nhập với tài khoản nhân viên thư viện");
			}
			
			Boolean result = repo.DeleteAccount(request);
			if (!result) {
				return new Message(
						false, 
						"Không thể xóa tài khoản. Vui lòng thử lại.");
			}
			
			return new Message(
					true, 
					"Xóa tài khoản thành công");

		} catch (Exception e) {
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, 
					"A problem has occur. Please contact the administrator!"
					);
		}
	}

	@CrossOrigin
	@RequestMapping("/extend_libcard")
	public Message ExtendLibraryCard(
			@RequestHeader(name = "x-access-token", required = true) String token,
			@RequestBody ExtendLibCardRequest request){

		// Verify access token
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}
		
		try {
			// Check user
			AccountInfo currentUser = repo.GetAccountByID(request.loginUserID);
			if (currentUser == null) {
				return new Message(
						false, 
						"Vui lòng đăng nhập với tài khoản nhân viên thư viện");
			}
			if (currentUser.getRole() != 2 && currentUser.getRole() != 3) {
				return new Message(
						false, 
						"Vui lòng đăng nhập với tài khoản nhân viên thư viện");
			}
			
			Boolean result = repo.ExtendLibCard(request);
			if (!result) {
				return new Message(
						false, 
						"Không thể gia hạn thẻ thư viện. Vui lòng thử lại.");
			}
			
			return new Message(
					true, 
					"Gia hạn thẻ thư viện thành công");

		} catch (Exception e) {
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, 
					"A problem has occur. Please contact the administrator!"
					);
		}
	}

	@CrossOrigin
	@RequestMapping("/change_account_info")
	public MessageData<AccountInfo> ChangeAccountInfo(
			@RequestHeader(name = "x-access-token", required = true) String token,
			@RequestBody ChangeInfoRequest request) {

		// Verify access token
		if (!repo.VerifyToken(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login token incorrect!");
		}

		try {
			// Check user
			AccountInfo currentUser = repo.GetAccountByID(request.logintUserID);
			if (currentUser == null) {
				return new MessageData<AccountInfo>(false, "Vui lòng đăng nhập với tài khoản nhân viên thư viện", null);
			}
			if (currentUser.getRole() != 2 && currentUser.getRole() != 3) {
				return new MessageData<AccountInfo>(false, "Vui lòng đăng nhập với tài khoản nhân viên thư viện", null);
			}

			// Validate data
			// fullName
			if (request.fullName.length() > 255) {
				return new MessageData<AccountInfo>(false, "Tên vượt quá 255 ký tự", null);
			}
			// address
			if (request.address.length() > 100) {
				return new MessageData<AccountInfo>(false, "Địa chỉ vượt quá 100 ký tự", null);
			}
			// gender
			if (request.gender != 0 && request.gender != 1) {
				return new MessageData<AccountInfo>(false, "Giới tính không đúng", null);
			}
			// birthday
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				format.parse(request.birthday.toString());
			} catch (Exception e) {
				return new MessageData<AccountInfo>(false, "Không đúng định dạng ngày", null);
			}
			// isReader
			if (request.isReader != true && request.isReader != false) {
				return new MessageData<AccountInfo>(false, "Cờ độc giả không đúng", null);
			}
			// role
			if (request.role < 1 || request.role > 5) {
				return new MessageData<AccountInfo>(false, "Role không tồn tại", null);
			}

			// update account info
			AccountInfo data = repo.ChangeAccountInfo(request);
			if (data == null) {
				return new MessageData<AccountInfo>(false, "Cập nhật không thành công", null);
			}

			return new MessageData<AccountInfo>(true, "Cập nhật thông tin tài khoản thành công", data);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"A problem has occur. Please contact the administrator!");
		}
	}

}
