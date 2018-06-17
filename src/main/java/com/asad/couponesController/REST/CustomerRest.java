package com.asad.couponesController.REST;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.asad.couponesController.LogIn;
import com.asad.couponesController.entitys.Coupon;

public class CustomerRest implements CouponClaintREST {

	
	@PostMapping("/logIn")
	@Override
	public boolean logIn(@RequestBody LogIn logIn) { //return login number for farther verifying
		// TODO Auto-generated method stub
		return false;
	}

	@PostMapping("/beyCoupon")
	public void beyCoupon(@RequestBody Coupon coupon){
		
	}
	
	
	@PostMapping("/listAllCustomerCoupon")
	public List<Coupon> listAllCustomerCoupon(){
		
		return null;
	}
}
