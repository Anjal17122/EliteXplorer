package com.elitexplorer.backend.security1.model;

import com.elitexplorer.backend.security1.securityutils.UserStatus;
import com.elitexplorer.backend.userdetail.model.dto.UserDetailDto;
import com.elitexplorer.backend.userdetail.model.entity.UserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class MyUserDetails implements UserDetails{

	
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private boolean active;
	private List<?extends GrantedAuthority> authorities;
	private String roles;
	private UserDetailDto user;
	
	
	public MyUserDetails(UserDetailDto user) {
		this.userName = user.getUsername();
		this.password = user.getPassword();
//		if (user.getUserStatus()== UserStatus.approved)
//			this.active = true;
//		else
//			this.active = false;
		this.active=true;
		this.authorities = Arrays.stream(user.getPersonRole().split(","))
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());
		this.roles = user.getPersonRole();
		this.user = user;
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("--------------------- User Role = :"+authorities);
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	public MyUserDetails() {
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return active;
	}

	public String getRoles() {
		return roles;
	}
	
	public UserDetailDto getSecUser() {
		return user;
	}
}
