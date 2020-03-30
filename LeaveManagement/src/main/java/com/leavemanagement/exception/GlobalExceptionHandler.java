package com.leavemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.leavemanagement.dto.SuccessResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(LeaveTypeNotFoundException.class)
	ResponseEntity<SuccessResponseDTO> categoryNotFoundException(LeaveTypeNotFoundException exception) {
		SuccessResponseDTO sucessResponseDTO = new SuccessResponseDTO();
		sucessResponseDTO.setMessage(exception.getMessage());
		sucessResponseDTO.setStatusCode(LeaveTypeNotFoundException.getStatuscode());
		return new ResponseEntity<>(sucessResponseDTO, HttpStatus.OK);

	}

	@ExceptionHandler(UserNotFoundException.class)
	ResponseEntity<SuccessResponseDTO> userNotFoundException(UserNotFoundException exception) {
		SuccessResponseDTO sucessResponseDTO = new SuccessResponseDTO();
		sucessResponseDTO.setMessage(exception.getMessage());
		sucessResponseDTO.setStatusCode(LeaveTypeNotFoundException.getStatuscode());
		return new ResponseEntity<>(sucessResponseDTO, HttpStatus.OK);

	}

	@ExceptionHandler(LeaveNotSufficientException.class)
	ResponseEntity<SuccessResponseDTO> leaveNotSufficientException(LeaveNotSufficientException exception) {
		SuccessResponseDTO sucessResponseDTO = new SuccessResponseDTO();
		sucessResponseDTO.setMessage(exception.getMessage());
		sucessResponseDTO.setStatusCode(LeaveTypeNotFoundException.getStatuscode());
		return new ResponseEntity<>(sucessResponseDTO, HttpStatus.OK);

	}

}
