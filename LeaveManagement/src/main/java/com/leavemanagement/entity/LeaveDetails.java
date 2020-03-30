package com.leavemanagement.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class LeaveDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long detailsId;
	@OneToOne
	private User user;

	private String appliedLeaveType;
	private LocalDate fromDate;
	private LocalDate toDate;
	private String reason;

	private int noOfDayApplied;
	@OneToOne
	private LeaveType leaveType;
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
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getNoOfDayApplied() {
		return noOfDayApplied;
	}
	public void setNoOfDayApplied(int noOfDayApplied) {
		this.noOfDayApplied = noOfDayApplied;
	}
	public LeaveType getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}
	public LeaveDetails() {
		super();
	}
	
	
	
}
