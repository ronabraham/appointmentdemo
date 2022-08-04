package com.eastvantage.appointments;


import java.time.LocalDate;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.web.bind.annotation.ControllerAdvice;

//@ControllerAdvice
@SpringBootApplication
public class AppointmentsApplication {

	public static void main(String[] args) {
		Logger LOG = LoggerFactory.getLogger(AppointmentsApplication.class);
		
		LOG.info("LocalTime = " + LocalTime.now().toString());
		LOG.info("LocalDate = " + LocalDate.now().toString());


		SpringApplication.run(AppointmentsApplication.class, args);
		
	}

}
