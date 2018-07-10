package com.asad.couponesController;

import java.util.logging.Level;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.asad.couponesController.CouponDateCheck.CouponEndDateCheck;

@SpringBootApplication
public class CouponesControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponesControllerApplication.class, args);
		CouponEndDateCheck checkCoupons = new  CouponEndDateCheck();

		//TODO: fix the thread bug
		
//		while(true) {
//		Thread thread = new Thread(checkCoupons);
//		thread.start();
//		try {
//			Thread.sleep(86400000);
//		} catch (InterruptedException e) {
//			
//			e.printStackTrace();
//			AppLogger.getLogger().log(Level.WARNING, "thread error ");
//
//		}
//		}
//	}
	
}
