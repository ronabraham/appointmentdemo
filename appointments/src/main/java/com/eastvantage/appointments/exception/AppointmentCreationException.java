package com.eastvantage.appointments.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class AppointmentCreationException extends Exception {
	
	private static final long serialVersionUID = 1L;
}
