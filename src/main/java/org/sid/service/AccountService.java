package org.sid.service;

import org.sid.model.AppRole;
import org.sid.model.AppUser;
import org.springframework.stereotype.Service;


public interface AccountService {
	
	public AppUser saveUser(String username, String password , String confirmPassword) ; 
	public AppRole save(AppRole appRole) ; 
	public AppUser loadUserByUsername(String username) ; 
	public void addRoleToUser(String username , String rolename) ; 



}
