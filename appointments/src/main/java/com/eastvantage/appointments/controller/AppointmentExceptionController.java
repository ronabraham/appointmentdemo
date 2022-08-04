package com.eastvantage.appointments.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.eastvantage.appointments.exception.AppointmentCreationException;
import com.eastvantage.appointments.exception.InvalidDateException;
import com.eastvantage.appointments.exception.NoAppointmentFoundException;

@ControllerAdvice
public class AppointmentExceptionController {

	@ExceptionHandler(AppointmentCreationException.class)
	public ResponseEntity<Object> exception(AppointmentCreationException ae) {
		return new ResponseEntity<Object>("Unable to create An appointment in this time period.\nPlease reschedule.", HttpStatus.NOT_FOUND);
		   
	}

	@ExceptionHandler(InvalidDateException.class)
	public ResponseEntity<Object> exception(InvalidDateException ie) {
		return new ResponseEntity<Object>("Invalid Date / Time Period.\nPlease reschedule.", HttpStatus.NOT_FOUND);
		   
	}
	
	@ExceptionHandler(NoAppointmentFoundException.class)
	public ResponseEntity<Object> exception(NoAppointmentFoundException ne) {
		return new ResponseEntity<Object>("No Appointment Found.", HttpStatus.NOT_FOUND);
		   
	}
	

}
