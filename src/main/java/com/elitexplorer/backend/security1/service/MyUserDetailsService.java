package com.elitexplorer.backend.security1.service;


import com.elitexplorer.backend.security1.model.MyUserDetails;
import com.elitexplorer.backend.userdetail.model.dto.UserDetailDto;
import com.elitexplorer.backend.userdetail.model.entity.UserDetail;
import com.elitexplorer.backend.userdetail.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserDetailRepository secUserCredentialRepository;

	@Override
	public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetail user = secUserCredentialRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Not Found : " + username);
		}
		
		/*
		 * System.out.println("User status : "+user.getStatus());
		 * if(user.getStatus()==UserStatus.disabled) { throw new
		 * UsernameNotFoundException("User is disabled value : "+user.getStatus()
		 * +" and username: " + username); }
		 */
		
		UserDetailDto secUser = new UserDetailDto();
		secUser.setUsername(user.getUsername());
		secUser.setPassword(user.getPassword());
		secUser.setUserStatus(user.getUserStatus());
		String roles = "";
		String rolePrefix = "ROLE_";
		boolean active = true;
		
		roles = rolePrefix+user.getPersonRole().getName();
		if ()
		secUser.setActive(active);
		secUser.setPersonRole(roles);

		MyUserDetails myUserDetails = new MyUserDetails(secUser);
		
		return myUserDetails;
	}

}
