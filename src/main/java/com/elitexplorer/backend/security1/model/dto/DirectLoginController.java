package com.elitexplorer.backend.security1.model.dto;/*package com.elitexplorer.backend.security1.model.dto;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elitexplorer.backend.security.utils.Constants;


@RestController
@RequestMapping("/user")
public class DirectLoginController {

	@PreAuthorize("hasAnyRole('"+Constants.ROLE_SUPPLIER_OWNER+"','"+Constants.ROLE_VENDOR_OWNER+"','"+Constants.ROLE_VENDOR_AND_SUPPLIER_OWNER+"','"+Constants.ROLE_SUPPLIER_COLLECTOR+"')")
	@GetMapping(value = "/check/loggedin")
	public LoginSuccessResponse reLogin() {
		
		LoginSuccessResponse response = new LoginSuccessResponse();
		int userId = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getSecUser().getId();
		
		SecUser user = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getSecUser();
		
		response.setUsername(user.getUserName());
		response.setRoles(user.getRoles());
		response.setOrganization(user.getSecOrganization());
		
		return response; 
		
	}
}
*/