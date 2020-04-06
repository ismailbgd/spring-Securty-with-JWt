package org.sid.service;

import org.sid.dao.AppRoleRepesotory;
import org.sid.dao.AppUserRepesotory;
import org.sid.model.AppRole;
import org.sid.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	public AppRoleRepesotory appRoleRepesotory;
	
	@Autowired
	public AppUserRepesotory appUserRepesotory;
	@Autowired
	public BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public AppUser saveUser(String username, String password, String confirmPassword) {
		AppUser appUser = appUserRepesotory.findByUsername(username);
		if(appUser != null) throw new RuntimeException("User alredy exists");
		if(!password.equals(confirmPassword)) throw new RuntimeException("Please confirm your password");
		AppUser appuser = new AppUser();
		appuser.setUsername(username);
		appuser.setActived(true);
		appuser.setPassword(bCryptPasswordEncoder.encode(confirmPassword));
		appUserRepesotory.save(appuser);
		addRoleToUser(username,"USER");
		return appuser ;
	}

	@Override
	public AppRole save(AppRole appRole) {
		return appRoleRepesotory.save(appRole);
	}

	@Override
	public AppUser loadUserByUsername(String username) {
		return appUserRepesotory.findByUsername(username);
	}

	@Override
	public void addRoleToUser(String username, String rolename) {
		
		AppUser appUser = appUserRepesotory.findByUsername(username);
		AppRole appRole = appRoleRepesotory.findByRoleName(rolename);
		appUser.getRole().add(appRole);
		
	}

}
