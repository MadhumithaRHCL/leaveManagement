package com.leavemanagement.service;

import java.time.Period;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leavemanagement.constant.Constant;
import com.leavemanagement.dto.ApplyLeaveRequestDTO;
import com.leavemanagement.dto.AvailableLeaveResponseDTO;
import com.leavemanagement.dto.SuccessResponseDTO;
import com.leavemanagement.entity.LeaveType;
import com.leavemanagement.entity.Leave;
import com.leavemanagement.entity.LeaveDetails;
import com.leavemanagement.entity.User;
import com.leavemanagement.exception.LeaveTypeNotFoundException;
import com.leavemanagement.exception.LeaveNotSufficientException;
import com.leavemanagement.exception.UserNotFoundException;
import com.leavemanagement.repository.LeaveTypeRepository;
import com.leavemanagement.repository.LeaveDetailsRepository;
import com.leavemanagement.repository.LeaveRepository;
import com.leavemanagement.repository.UserRepository;

@Service
@Transactional
public class LeaveServiceImpl implements LeaveService {
	@Autowired
	private LeaveRepository leaveRepository;
	@Autowired
	private UserRepository UserRepository;
	@Autowired
	private LeaveDetailsRepository leaveDetailsRepository;
	@Autowired
	private LeaveTypeRepository leaveTypeRepository;
	Logger log=LoggerFactory.getLogger(LeaveServiceImpl.class);

	@Override
	public AvailableLeaveResponseDTO getAvailableLeave(Long userId) throws UserNotFoundException {
		Optional<User> user = UserRepository.findById(userId);
		if (!user.isPresent()) {
			throw new UserNotFoundException(userId);
		}
		Leave restricedLeave = leaveRepository.findByLeaveTypeLeaveTypeIdAndUserUserId(Constant.Restricted, userId);
		Leave annualLeave = leaveRepository.findByLeaveTypeLeaveTypeIdAndUserUserId(Constant.Annual, userId);
		Leave myLeave = leaveRepository.findByLeaveTypeLeaveTypeIdAndUserUserId(Constant.MyLeave, userId);
		
		AvailableLeaveResponseDTO availableLeaveResponseDTO = new AvailableLeaveResponseDTO();
		availableLeaveResponseDTO.setAnnualAvailable(annualLeave.getAvailableLeave());
		availableLeaveResponseDTO.setRestrictedAvailable(restricedLeave.getAvailableLeave());
		availableLeaveResponseDTO.setMyLeaveAvailable(myLeave.getAvailableLeave());
		log.info("Displaying the Avaialble leaves");
		return availableLeaveResponseDTO;
	}

	@Override
	public SuccessResponseDTO applyLeave(ApplyLeaveRequestDTO applyLeaveRequestDTO)
			throws UserNotFoundException, LeaveTypeNotFoundException, LeaveNotSufficientException {

		Optional<User> user = UserRepository.findById(applyLeaveRequestDTO.getUserId());
		if (!user.isPresent()) {
			throw new UserNotFoundException(applyLeaveRequestDTO.getUserId());
		}
		Leave leave = leaveRepository.findByLeaveTypeLeaveTypeIdAndUserUserId(applyLeaveRequestDTO.getLeaveTypeId(),applyLeaveRequestDTO.getUserId());
		Optional<LeaveType> leaveType = leaveTypeRepository.findById(applyLeaveRequestDTO.getLeaveTypeId());
		if (!leaveType.isPresent()) {
			throw new LeaveTypeNotFoundException(applyLeaveRequestDTO.getLeaveTypeId());
		}
		Period period = Period.between(applyLeaveRequestDTO.getFromDate(), applyLeaveRequestDTO.getToDate());
		int noOfLeaveDays = period.getDays() + 1;
		if (leave.getAvailableLeave() < noOfLeaveDays) {

			throw new LeaveNotSufficientException(applyLeaveRequestDTO.getUserId());
		}
		leave.setAvailableLeave(leave.getAvailableLeave() - noOfLeaveDays);
		leaveRepository.save(leave);
		LeaveDetails details = new LeaveDetails();
		details.setUser(user.get());
		details.setLeaveType(leaveType.get());
		details.setAppliedLeaveType(leaveType.get().getLeaveTypeName());
		details.setFromDate(applyLeaveRequestDTO.getFromDate());
		details.setToDate(applyLeaveRequestDTO.getToDate());
		details.setNoOfDayApplied(noOfLeaveDays);
		details.setReason(applyLeaveRequestDTO.getLeaveReason());
		leaveDetailsRepository.save(details);
		SuccessResponseDTO responseDTO = new SuccessResponseDTO();
		responseDTO.setMessage(Constant.SuccessApply);
		responseDTO.setStatusCode(Constant.SuccessStatusCode);
		log.info("The leave is Applied Successfully");

		return responseDTO;
	}

	

}
