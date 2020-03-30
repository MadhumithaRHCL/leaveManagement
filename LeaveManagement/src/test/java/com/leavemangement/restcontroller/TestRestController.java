package com.leavemangement.restcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.leavemanagement.dto.ApplyLeaveRequestDTO;
import com.leavemanagement.dto.AvailableLeaveResponseDTO;
import com.leavemanagement.dto.LeaveDetailsResponseDTO;
import com.leavemanagement.dto.SuccessResponseDTO;
import com.leavemanagement.entity.LeaveType;
import com.leavemanagement.entity.User;
import com.leavemanagement.exception.LeaveTypeNotFoundException;
import com.leavemanagement.exception.LeaveNotSufficientException;
import com.leavemanagement.exception.UserNotFoundException;
import com.leavemanagement.restcontroller.LeaveController;
import com.leavemanagement.service.LeaveService;

@RunWith(MockitoJUnitRunner.class)
public class TestRestController {
	@Mock
	private LeaveService leaveService;
	@InjectMocks
	private LeaveController leaveController;
	private User user = null;
	private AvailableLeaveResponseDTO availableLeaveResponseDTO = null;
	private LeaveDetailsResponseDTO leaveDetailsResponseDTO = null;
	private SuccessResponseDTO sucessResponseDTO = null;
	private ApplyLeaveRequestDTO applyLeaveRequestDTO = null;
	private LeaveType leaveType = null;
	private List<LeaveDetailsResponseDTO> listResponse = new ArrayList<LeaveDetailsResponseDTO>();

	@Before
	public void setup() {
		user = new User();
		user.setUserId(1L);
		user.setEmail("madhu@gmail.com");
		
		availableLeaveResponseDTO = new AvailableLeaveResponseDTO();
		availableLeaveResponseDTO.setAnnualAvailable(18);
		availableLeaveResponseDTO.setMyLeaveAvailable(2);
		availableLeaveResponseDTO.setRestrictedAvailable(5);
		
		sucessResponseDTO = new SuccessResponseDTO();
		sucessResponseDTO.setMessage("The leave is Applied Successfully");
		sucessResponseDTO.setStatusCode(2000);
		
		leaveType = new LeaveType();
		leaveType.setLeaveTypeId(1L);
		leaveType.setLeaveTypeName("madhu");
		
		applyLeaveRequestDTO = new ApplyLeaveRequestDTO();
		applyLeaveRequestDTO.setLeaveTypeId(1L);
		applyLeaveRequestDTO.setUserId(1L);
		applyLeaveRequestDTO.setLeaveReason("fever");
		applyLeaveRequestDTO.setFromDate(LocalDate.of(2020, 03, 31));
		applyLeaveRequestDTO.setToDate(LocalDate.of(2020, 04, 01));
		
		leaveDetailsResponseDTO = new LeaveDetailsResponseDTO();
		leaveDetailsResponseDTO.setAppliedLeaveType(leaveType.getLeaveTypeName());
		leaveDetailsResponseDTO.setFromDate(LocalDate.of(2020, 03, 31));
		leaveDetailsResponseDTO.setToDate(LocalDate.of(2020, 04, 01));
		leaveDetailsResponseDTO.setUser(user);
		listResponse.add(leaveDetailsResponseDTO);

	}

	@Test
	public void testGetLeave() throws UserNotFoundException {
		Mockito.when(leaveService.getAvailableLeave(1L)).thenReturn(availableLeaveResponseDTO);
		ResponseEntity<AvailableLeaveResponseDTO> response = leaveController.getLeave(1L);
		assertEquals(response.getBody().getMyLeaveAvailable(), availableLeaveResponseDTO.getMyLeaveAvailable());
	}

	@Test
	public void testApplyLeave() throws UserNotFoundException, LeaveTypeNotFoundException, LeaveNotSufficientException {
		Mockito.when(leaveService.applyLeave(applyLeaveRequestDTO)).thenReturn(sucessResponseDTO);
		ResponseEntity<SuccessResponseDTO> response = leaveController.applyLeave(applyLeaveRequestDTO);
		assertEquals(response.getBody().getMessage(), sucessResponseDTO.getMessage());
	}



	
	

}
