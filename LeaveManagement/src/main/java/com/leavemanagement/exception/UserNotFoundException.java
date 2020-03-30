package com.leavemanagement.exception;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	
	public UserNotFoundException(Long userId) {
		super("User "+":"+userId+"is not found");
		
	}
	private static final Integer statuscode=4000;


	public static Integer getStatuscode() {
		return statuscode;
	}
	

}
