package com.leavemanagement.exception;

public class LeaveNotSufficientException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final Integer statusCode = 610;

	public static Integer getStatuscode() {
		return statusCode;
	}

	public LeaveNotSufficientException(Long userId) {
		super("user with userId"+":" + userId +" "+ "doesn't have sufficient leave");

	}

}
