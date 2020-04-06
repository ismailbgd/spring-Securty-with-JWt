package org.sid.web;

import java.util.List;

import org.sid.dao.AppUserRepesotory;
import org.sid.model.AppUser;
import org.sid.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
public class UserController {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AppUserRepesotory appuserRe;
	
	
	
	@PostMapping("/register")
	public AppUser registerNewUser(@RequestBody UserForm userForm)
	{
	return	accountService.saveUser(
			userForm.getUsername(), userForm.getPassword(), userForm.getConfirmpass());
	}
	
	
	@GetMapping("/getall")
	public List<AppUser> getall()
	{
	return	appuserRe.findAll();
	}

}

@Data
class UserForm{
	private String username ; 
	private String password ; 
	private String confirmpass;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmpass() {
		return confirmpass;
	}
	public void setConfirmpass(String confirmpass) {
		this.confirmpass = confirmpass;
	}
	
	
	
	
	
}
