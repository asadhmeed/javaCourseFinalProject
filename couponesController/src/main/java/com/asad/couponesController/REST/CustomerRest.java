package com.asad.couponesController.REST;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asad.couponesController.LogIn;
import com.asad.couponesController.LogInResponse;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.enums.LogInEnum;
@RequestMapping("/customerRest")
@RestController
public class CustomerRest implements CouponClaintREST {

	
	@PostMapping("/logIn")
	@Override
	public LogInResponse logIn(@RequestBody LogIn logIn) { 
		
		return new LogInResponse(LogInEnum.LOGINFAILED);
	}

	@PostMapping("/beyCoupon")
	public void beyCoupon(@RequestBody Coupon coupon){
		
	}
	
	
	@PostMapping("/listAllCustomerCoupon")
	public List<Coupon> listAllCustomerCoupon(){
		
		return null;
	}
}
