package org.sid.dao;

import org.sid.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface AppUserRepesotory extends JpaRepository<AppUser, Long> {
	
	public AppUser findByUsername(String username);
}
