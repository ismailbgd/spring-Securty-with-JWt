package org.sid.sec;

import java.util.ArrayList;
import java.util.Collection;

import org.sid.model.AppUser;
import org.sid.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	
	@Autowired
	private AccountService accountService ; 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appuser = accountService.loadUserByUsername(username);
		if(appuser==null) throw new UsernameNotFoundException("Invalid user");
		Collection<GrantedAuthority> autority  = new ArrayList<GrantedAuthority>();
		// changer les role comme autority 
		appuser.getRole().forEach(r->{
			autority.add(new SimpleGrantedAuthority(r.getRoleName()));
		});
		// return user detqils
		return  new User(appuser.getUsername() , appuser.getPassword() , autority);
	}

}
