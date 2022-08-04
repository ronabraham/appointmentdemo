package com.eastvantage.appointments.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eastvantage.appointments.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
	
//	public Appointment findByStartTime(LocalTime startTime);
//	
//
//	public Appointment findByDateAndStartTime(LocalDate date , LocalTime startTime);
	
	public Optional<Appointment> findById(Integer id);
	
	@Query(value="SELECT a.* from appointment a where a.start_time <= :st and a.date = :dt order by a.start_time desc" ,nativeQuery=true)
	public List<Appointment> findAllBeforeStartTimeOrderByDesc
	(@Param("st")LocalTime startTime,@Param("dt")LocalDate date);
	

//	@Query(value="SELECT a.* from appointment a where a.date = :dt and (a.start_time <= :st or a.end_time >= :et)  order by a.start_time desc" ,nativeQuery=true)
//	public List<Appointment> findAllAppointmentsBeforeStartTimeandAfterEndTimeOrderByStartTimeDesc
//	(@Param("st")LocalTime startTime,@Param("et")LocalTime endTime,@Param("dt")LocalDate date);
		
	@Query(value="SELECT a.* from appointment a where a.date >= :st and a.date <= :et",nativeQuery=true)
	public List<Appointment> findAllByDateRange(@Param("st") LocalDate startDate,@Param("et") LocalDate endDate);

	//public List<Appointment> findAllByDateRange(LocalDate startDate, LocalDate endDate);
	

}
