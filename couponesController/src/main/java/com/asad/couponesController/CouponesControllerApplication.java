package com.asad.couponesController;

import java.time.LocalDate;
import java.util.logging.Level;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.asad.couponesController.CouponDateCheck.DailyCouponExpirationTask;

@SpringBootApplication
public class CouponesControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponesControllerApplication.class, args);

		
	}
	
}
