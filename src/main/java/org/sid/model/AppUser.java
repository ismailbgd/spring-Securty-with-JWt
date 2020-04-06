package org.sid.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class AppUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	@Column(unique = true)
	private String username ; 
	@JsonProperty(access = Access.READ_WRITE)
	private String password ; 
	private boolean actived ; 
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<AppRole> role = new ArrayList<AppRole>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public boolean isActived() {
		return actived;
	}
	public void setActived(boolean actived) {
		this.actived = actived;
	}
	public Collection<AppRole> getRole() {
		return role;
	}
	public void setRole(Collection<AppRole> role) {
		this.role = role;
	}
	public AppUser(Long id, String username, String password, boolean actived, Collection<AppRole> role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.actived = actived;
		this.role = role;
	}
	public AppUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
