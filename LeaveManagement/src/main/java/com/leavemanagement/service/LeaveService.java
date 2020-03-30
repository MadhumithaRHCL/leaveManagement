package com.leavemanagement.service;

import com.leavemanagement.dto.ApplyLeaveRequestDTO;
import com.leavemanagement.dto.AvailableLeaveResponseDTO;
import com.leavemanagement.dto.SuccessResponseDTO;
import com.leavemanagement.exception.LeaveTypeNotFoundException;
import com.leavemanagement.exception.LeaveNotSufficientException;
import com.leavemanagement.exception.UserNotFoundException;

public interface LeaveService {
	public AvailableLeaveResponseDTO getAvailableLeave(Long userId)throws UserNotFoundException;
	public SuccessResponseDTO applyLeave(ApplyLeaveRequestDTO applyLeaveRequestDTO) throws UserNotFoundException, LeaveTypeNotFoundException, LeaveNotSufficientException;
	
}
