package com.leavemanagement.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leavemanagement.entity.User;

public class LeaveDetailsResponseDTO {
	private Long detailsId;
	@JsonIgnore
	private User user;
	private String appliedLeaveType;
	private LocalDate toDate;
	private LocalDate fromDate;
	private int noOfDayApplied;
	private String reason;
	public Long getDetailsId() {
		return detailsId;
	}
	public void setDetailsId(Long detailsId) {
		this.detailsId = detailsId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAppliedLeaveType() {
		return appliedLeaveType;
	}
	public void setAppliedLeaveType(String appliedLeaveType) {
		this.appliedLeaveType = appliedLeaveType;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public int getNoOfDayApplied() {
		return noOfDayApplied;
	}
	public void setNoOfDayApplied(int noOfDayApplied) {
		this.noOfDayApplied = noOfDayApplied;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public LeaveDetailsResponseDTO() {
		super();
	}

	

}
