package com.leavemanagement.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leavemanagement.dto.ApplyLeaveRequestDTO;
import com.leavemanagement.dto.AvailableLeaveResponseDTO;
import com.leavemanagement.dto.SuccessResponseDTO;
import com.leavemanagement.exception.LeaveTypeNotFoundException;
import com.leavemanagement.exception.LeaveNotSufficientException;
import com.leavemanagement.exception.UserNotFoundException;
import com.leavemanagement.service.LeaveService;


@RestController
@RequestMapping("/users")
public class LeaveController {
	@Autowired
	private LeaveService leaveService;
	Logger log=LoggerFactory.getLogger(LeaveController.class);

	@GetMapping(value = "/{userId}/leaves")
	public ResponseEntity<AvailableLeaveResponseDTO> getLeave(@RequestParam(name = "userId") Long userId)
			throws UserNotFoundException {
		AvailableLeaveResponseDTO response = leaveService.getAvailableLeave(userId);
		log.info("Displaying the available leaves ");
		return new ResponseEntity<>(response, HttpStatus.OK);
		

	}

	@PostMapping(value = "/leaves")
	public ResponseEntity<SuccessResponseDTO> applyLeave(@RequestBody ApplyLeaveRequestDTO applyLeaveRequestDTO)
			throws UserNotFoundException, LeaveTypeNotFoundException, LeaveNotSufficientException {

		SuccessResponseDTO response = leaveService.applyLeave(applyLeaveRequestDTO);
		log.info("applying for the leave");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
