package com.leavemangement.service;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.leavemanagement.dto.ApplyLeaveRequestDTO;
import com.leavemanagement.dto.AvailableLeaveResponseDTO;
import com.leavemanagement.dto.LeaveDetailsResponseDTO;
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
import com.leavemanagement.service.LeaveServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TestLeaveService {
	@Mock
	private LeaveRepository leaveRepository;
	@Mock
	private LeaveDetailsRepository leaveDetailsRepository;
	@Mock
	private UserRepository userRepository;
	@Mock
	private LeaveTypeRepository leaveTypeRepository;

	@InjectMocks
	private LeaveServiceImpl leaveServiceImpl;
	
	private User user = null;
	private AvailableLeaveResponseDTO availableLeaveResponseDTO = null;
	private LeaveDetailsResponseDTO leaveDetailsResponseDTO = null;
	private SuccessResponseDTO sucessResponseDTO = null;
	private ApplyLeaveRequestDTO applyLeaveRequestDTO = null;
	private ApplyLeaveRequestDTO applyLeaveRequestDTO1 = null;
	private LeaveType retricted = null;
	private LeaveType annual = null;
	private LeaveType myleave = null;
	private List<LeaveDetailsResponseDTO> listResponse = new ArrayList<LeaveDetailsResponseDTO>();
	private Leave restrictedLeave = null;
	private Leave annualLeave = null;
	private Leave myleaveLeave = null;
	private LeaveDetails leaveDetails = null;
	private List<LeaveDetails> listLeave = null;

	@Before
	public void setup() {
		user = new User();
		user.setUserId(1L);
		user.setEmail("guru@gmail.com");
		availableLeaveResponseDTO = new AvailableLeaveResponseDTO();
		availableLeaveResponseDTO.setAnnualAvailable(16);
		availableLeaveResponseDTO.setMyLeaveAvailable(2);
		availableLeaveResponseDTO.setRestrictedAvailable(5);
		
		sucessResponseDTO = new SuccessResponseDTO();
		sucessResponseDTO.setMessage("The leave is Applied Successfully");
		sucessResponseDTO.setStatusCode(2000);
		
		retricted = new LeaveType();
		retricted.setLeaveTypeId(1L);
		retricted.setLeaveTypeName("retricted");
		
		annual = new LeaveType();
		annual.setLeaveTypeId(2L);
		annual.setLeaveTypeName("annual");
		
		myleave = new LeaveType();
		myleave.setLeaveTypeId(3L);
		myleave.setLeaveTypeName("myleave");
		
		applyLeaveRequestDTO = new ApplyLeaveRequestDTO();
		applyLeaveRequestDTO.setLeaveTypeId(1L);
		applyLeaveRequestDTO.setUserId(1L);
		applyLeaveRequestDTO.setLeaveReason("fuction");
		applyLeaveRequestDTO.setFromDate(LocalDate.of(2020, 03, 31));
		applyLeaveRequestDTO.setToDate(LocalDate.of(2020, 04, 01));
		
		applyLeaveRequestDTO1 = new ApplyLeaveRequestDTO();
		applyLeaveRequestDTO1.setLeaveTypeId(1L);
		applyLeaveRequestDTO1.setUserId(1L);
		applyLeaveRequestDTO1.setLeaveReason("fuction");
		applyLeaveRequestDTO1.setFromDate(LocalDate.of(2020, 03, 11));
		applyLeaveRequestDTO1.setToDate(LocalDate.of(2020, 03, 20));
		
		leaveDetailsResponseDTO = new LeaveDetailsResponseDTO();
		leaveDetailsResponseDTO.setAppliedLeaveType(retricted.getLeaveTypeName());
		leaveDetailsResponseDTO.setFromDate(LocalDate.of(2020, 03, 31));
		leaveDetailsResponseDTO.setToDate(LocalDate.of(2020, 04, 01));
		leaveDetailsResponseDTO.setUser(user);
		listResponse.add(leaveDetailsResponseDTO);
		
		restrictedLeave = new Leave();
		restrictedLeave.setLeaveType(retricted);
		
		restrictedLeave.setAvailableLeave(10);
		annualLeave = new Leave();
		annualLeave.setLeaveType(annual);
		
		myleaveLeave = new Leave();
		myleaveLeave.setLeaveType(myleave);
	}

	@Test
	public void testGetLeave() throws UserNotFoundException {
		Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		Mockito.when(leaveRepository.findByLeaveTypeLeaveTypeIdAndUserUserId(1L, 1L)).thenReturn(restrictedLeave);
		Mockito.when(leaveRepository.findByLeaveTypeLeaveTypeIdAndUserUserId(2L, 1L)).thenReturn(annualLeave);
		Mockito.when(leaveRepository.findByLeaveTypeLeaveTypeIdAndUserUserId(3L, 1L)).thenReturn(myleaveLeave);
		
		AvailableLeaveResponseDTO responseDTO = leaveServiceImpl.getAvailableLeave(1L);
		assertEquals(responseDTO.getAnnualAvailable(), annualLeave.getAvailableLeave());
	}

	@Test
	public void testApplyLeave() throws UserNotFoundException, LeaveTypeNotFoundException, LeaveNotSufficientException {
		Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		Mockito.when(leaveRepository.findByLeaveTypeLeaveTypeIdAndUserUserId(1L, 1L)).thenReturn(restrictedLeave);
		
		Mockito.when(leaveTypeRepository.findById(1L)).thenReturn(Optional.of(retricted));
		Mockito.when(leaveRepository.save(restrictedLeave)).thenReturn(restrictedLeave);
		// Mockito.when(leaveHistoryRepository.save(leaveHistory)).thenReturn(leaveHistory);
		SuccessResponseDTO responseDTO = leaveServiceImpl.applyLeave(applyLeaveRequestDTO);
		assertEquals(responseDTO.getMessage(), "The leave is Applied Successfully");
	}

	
	@Test(expected = UserNotFoundException.class)
	public void testUserNotFoud() throws UserNotFoundException {
		Mockito.when(userRepository.findById(2L)).thenReturn(Optional.of(user));
		leaveServiceImpl.getAvailableLeave(1L);

	}

	@Test(expected = LeaveTypeNotFoundException.class)
	public void testLeaveTypeNotFoundException()
			throws UserNotFoundException, LeaveTypeNotFoundException, LeaveNotSufficientException {
		Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		Mockito.when(leaveRepository.findByLeaveTypeLeaveTypeIdAndUserUserId(1L, 1L)).thenReturn(restrictedLeave);
		Mockito.when(leaveTypeRepository.findById(4L)).thenReturn(Optional.of(retricted));
		Mockito.when(leaveRepository.save(restrictedLeave)).thenReturn(restrictedLeave);

		leaveServiceImpl.applyLeave(applyLeaveRequestDTO);

	}


}
