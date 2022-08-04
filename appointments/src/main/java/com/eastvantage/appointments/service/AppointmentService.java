package com.eastvantage.appointments.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eastvantage.appointments.entity.Appointment;
import com.eastvantage.appointments.exception.AppointmentCreationException;
import com.eastvantage.appointments.exception.InvalidDateException;
import com.eastvantage.appointments.exception.NoAppointmentFoundException;
import com.eastvantage.appointments.repository.AppointmentRepository;

@Service
public class AppointmentService {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	public Appointment save(Appointment appointment) throws 
	AppointmentCreationException,InvalidDateException{
		
		
		//null checks for date values
		if (appointment.getDate() == null || appointment.getStartTime() == null ||
				appointment.getEndTime() == null) 
			throw new InvalidDateException();

		//basic date validation start date <= end date
		if (appointment.getStartTime().isAfter(appointment.getEndTime()) ||
				appointment.getStartTime().equals(appointment.getEndTime())) 
			throw new AppointmentCreationException();

//		check for an existing appointment in time window requested .
//		if another appointment exists , throw an exception
		List<Appointment> backwardAppointments = appointmentRepository.findAllBeforeStartTimeOrderByDesc(
		appointment.getStartTime(),appointment.getDate());

//		List<Appointment> existingAppointments = appointmentRepository.
//				findAllAppointmentsBeforeStartTimeandAfterEndTimeOrderByStartTimeDesc(
//		appointment.getStartTime(),appointment.getEndTime(),appointment.getDate());
		
		if(backwardAppointments != null && backwardAppointments.size() != 0) {
			
			//get(0) works to retrieve the most recent appointment, because 
			// the repository method returns the appts in descending order of starttime.
			Appointment mostRecentAppointment = backwardAppointments.get(0);
			
			//if the end time of the most recent appointment is >= (i.e later than) the requested
			//appointment end time,
			if(mostRecentAppointment.getEndTime().isAfter(appointment.getEndTime()) ||
					mostRecentAppointment.getEndTime().equals(appointment.getEndTime()))
				throw new AppointmentCreationException();
			
		}
		else {


//			check for an existing appointment in time window requested .
//			if another appointment exists , throw an exception
			List<Appointment> forwardAppointments = appointmentRepository.findAllBeforeStartTimeOrderByDesc(
			appointment.getEndTime(),appointment.getDate());	
			
			if(forwardAppointments != null && forwardAppointments.size()!= 0) {
				
				// if the start time of the most recent appointment is <= (i.e earlier than) the requested
				// appointment end time this is an overlap and an exception has to be thrown	
				Appointment mostSubsequentAppointment = forwardAppointments.get(0);
				if(mostSubsequentAppointment.getStartTime().isBefore(appointment.getEndTime()))
					throw new AppointmentCreationException();

			}
		}
		

		appointment = appointmentRepository.save(appointment);
		return appointment;
	
	}
	
	public Appointment update(Appointment appointment) throws NoAppointmentFoundException {
	
		//in an update scenario 
		//if trying to update an non-existent record throw an error
		Optional<Appointment> appointmentForUpdate = appointmentRepository.findById(Integer.valueOf(appointment.getId()));
		if (!appointmentForUpdate.isPresent()) 
			throw new NoAppointmentFoundException();
		appointment = appointmentRepository.save(appointment);
		return appointment;	
		
	}
	
	public String delete(String appointmentId) throws NoAppointmentFoundException {

		//in a delete scenario 
		//if trying to delete an non-existent record throw an error
		Optional<Appointment> appointmentForDeletion = appointmentRepository.findById(Integer.valueOf(appointmentId));
		if (!appointmentForDeletion.isPresent()) 
			throw new NoAppointmentFoundException();
		Appointment appointment = appointmentForDeletion.get();
		appointmentRepository.deleteById(appointment.getId());
		return "Appointment with Id "+ appointment.getId()+" deleted";
		
	}
	
	public List<Appointment> findAllAppointments(){
		return appointmentRepository.findAll();
	}

	public List<Appointment> findAllAppointmentsByDateRange(LocalDate startDate,LocalDate endDate) throws InvalidDateException{
		if(startDate.isAfter(endDate)) throw new InvalidDateException();
		return appointmentRepository.findAllByDateRange(startDate,endDate);
	}	
}
