package com.elitexplorer.backend.security1.model.dto;

public class LoginFailedResponse {
	
	private boolean success;
	private String message;

	public LoginFailedResponse(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
}
