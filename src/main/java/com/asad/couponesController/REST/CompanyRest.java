package com.asad.couponesController.REST;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.asad.couponesController.LogIn;
import com.asad.couponesController.SpecificCouponCheck;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.enums.CouponType;

@RequestMapping("/companyRest")
@RestController
public class CompanyRest implements CouponClaintREST {

	@Override
	@PostMapping("/logIn")
	public boolean logIn(@RequestBody LogIn logIn) {//LogInSuccess
							           //LogInFaild
		// TODO Auto-generated method stub
		return false;
	}

	
	@PostMapping("/creatCoupon")
	public boolean creatCoupon(@RequestBody Coupon Coupon) //couponCreated
	{
		
		return false;
	}
	
	
	@DeleteMapping("/deleteCoupon")
	public boolean deleteCoupon(@RequestBody Coupon coupon) //couponDeleted
	{
		
		return false;
	}
	@PostMapping("/updateCoupon")
	public boolean updateCoupon() //couponUpdated
	{
		
		return false;
	}
	
	@GetMapping("/listAllCoupons")
	public List<Coupon> listAllCoupons() 
	{
		
		return null;
	}
	@PostMapping("/getSpecificCoupons")
	public List<Coupon> getSpecificCoupons(@RequestBody SpecificCouponCheck specificCouponCheck) 
	{
		return null;
		
	}
	
	
}
