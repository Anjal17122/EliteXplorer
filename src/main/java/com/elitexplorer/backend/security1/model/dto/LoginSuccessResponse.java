package com.elitexplorer.backend.security1.model.dto;


public class LoginSuccessResponse {
    
	private boolean success;
    private String username;
    private String name;
    private String token;
    private String roles;

	private String wardId;
  
  
    public LoginSuccessResponse() {
    }


	public LoginSuccessResponse(boolean success, String username, String token, String roles, String wardId
			) {
		super();
		this.success = success;
		this.username = username;
		this.token = token;
		this.roles = roles;
		this.wardId = wardId;
	}


	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getWardId() {
		return wardId;
	}

	public void setWardId(String wardId) {
		this.wardId = wardId;
	}
}
