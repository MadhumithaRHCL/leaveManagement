package com.leavemanagement.exception;

public class LeaveTypeNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final Integer statusCode = 612;

	public LeaveTypeNotFoundException(Long categoryId) {
		super("category is not found for the categoryId"+":" + categoryId);

	}

	public static Integer getStatuscode() {
		return statusCode;
	}

}
