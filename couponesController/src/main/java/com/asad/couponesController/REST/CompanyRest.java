package com.asad.couponesController.REST;


import java.util.List;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asad.couponesController.AppLogger;
import com.asad.couponesController.LogIn;
import com.asad.couponesController.LogInResponse;
import com.asad.couponesController.Response;
import com.asad.couponesController.SpecificCouponDataCheck;
import com.asad.couponesController.company.CompanyServices;
import com.asad.couponesController.coupons.CouponServices;
import com.asad.couponesController.entitys.Coupon;

import com.asad.couponesController.enums.LogInEnum;
import com.asad.couponesController.exceptions.LogInDataIsNullException;
import com.asad.couponesController.exceptions.NameIsUsedException;

@RequestMapping("/companyRest")
@RestController
public class CompanyRest implements CouponClaintREST {
	
	@Autowired
	private CompanyServices companyServices;

	@Override
	@GetMapping("/logIn")
	public synchronized Response logIn(@RequestBody LogIn logIn)throws LogInDataIsNullException  {
							           
		
		return new Response(companyServices.logInCheck(logIn));
	}

	
	@PostMapping("/creatCoupon")
	public Response creatCoupon(@RequestBody Coupon coupon) throws NameIsUsedException //couponCreated
	{
		AppLogger.getLogger().log(Level.CONFIG, coupon.toString());
		return new Response(companyServices.creatCoupon(coupon));
	}
	
	
	@DeleteMapping("/deleteCoupon")
	public boolean deleteCoupon(@RequestBody Coupon coupon) //couponDeleted
	{
		
		return false;
	}
	@PostMapping("/updateCoupon")
	public boolean updateCoupon() //couponUpdated
	{
		
		return true;
	}
	
	@GetMapping("/listAllCoupons")
	public Response listAllCoupons() 
	{
		
		return new Response(companyServices.listAllCoupons());
	}
	@PostMapping("/getSpecificCoupons")
	public List<Coupon> getSpecificCoupons(@RequestBody SpecificCouponDataCheck specificCouponCheck) 
	{
		return null;
		
	}
	
	
	
}
