package com.eastvantage.appointments.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eastvantage.appointments.entity.Appointment;
import com.eastvantage.appointments.exception.AppointmentCreationException;
import com.eastvantage.appointments.exception.InvalidDateException;
import com.eastvantage.appointments.exception.NoAppointmentFoundException;
import com.eastvantage.appointments.service.AppointmentService;

@RestController
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	
	@PostMapping("/create")
	public ResponseEntity<Object> create(@RequestBody Appointment appointment) throws 
	AppointmentCreationException,InvalidDateException {
		
		
		Object objectAppointment = appointmentService.save(appointment);
		return new ResponseEntity<Object>(objectAppointment, HttpStatus.OK);
	}

	
	@PostMapping("/update")
	public ResponseEntity<Object> update(@RequestBody Appointment appointment) throws AppointmentCreationException, InvalidDateException, NoAppointmentFoundException{

		Object objectAppointment = appointmentService.update(appointment);
		return new ResponseEntity<Object>(objectAppointment, HttpStatus.OK);
	}
	
	
	@PostMapping("/delete/{apptId}")
	public String update(@PathVariable String apptId) throws NoAppointmentFoundException{

		String message = appointmentService.delete(apptId);
		return message;
		 
	}


	@GetMapping("/list")
	public List<Appointment> list(){
		return appointmentService.findAllAppointments();
		
		
	}
	
	
	@GetMapping("/listByDateRange")
	public List<Appointment> listByDateRange(@RequestParam("startDate")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	@RequestParam("endDate") 
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate	
	) throws InvalidDateException{
		return appointmentService.findAllAppointmentsByDateRange(startDate,endDate);
		
		
	}	
	
}
