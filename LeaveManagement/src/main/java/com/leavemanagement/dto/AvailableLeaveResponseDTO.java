package com.leavemanagement.dto;

public class AvailableLeaveResponseDTO {
	private int restrictedAvailable;
	private int annualAvailable;
	private int myLeaveAvailable;

	public int getRestrictedAvailable() {
		return restrictedAvailable;
	}

	public void setRestrictedAvailable(int restrictedAvailable) {
		this.restrictedAvailable = restrictedAvailable;
	}

	public int getAnnualAvailable() {
		return annualAvailable;
	}

	public void setAnnualAvailable(int annualAvailable) {
		this.annualAvailable = annualAvailable;
	}

	public int getMyLeaveAvailable() {
		return myLeaveAvailable;
	}

	public void setMyLeaveAvailable(int myLeaveAvailable) {
		this.myLeaveAvailable = myLeaveAvailable;
	}

}
