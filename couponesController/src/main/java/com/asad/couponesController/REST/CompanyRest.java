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
import com.asad.couponesController.RequestData;
import com.asad.couponesController.Response;
import com.asad.couponesController.SpecificCouponDataCheck;
import com.asad.couponesController.company.CompanyServices;
import com.asad.couponesController.coupons.CouponServices;
import com.asad.couponesController.entitys.Coupon;

import com.asad.couponesController.enums.LogInEnum;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.LogInDataIsNullException;
import com.asad.couponesController.exceptions.NameIsUsedException;
import com.asad.couponesController.exceptions.RequestDataIsNullException;
import com.asad.couponesController.exceptions.notLogedInException;

@RequestMapping("/companyRest")
@RestController
public class CompanyRest implements CouponClaintREST {
	
	@Autowired
	private CompanyServices companyServices;

	@Override
	@GetMapping("/logIn")
	public synchronized Response logIn(@RequestBody LogIn logIn)throws LogInDataIsNullException, RequestDataIsNullException  {
							           
		
		return new Response(companyServices.logIn(logIn));
	}

	@PostMapping("/companyLogOut")
	@Override
	public synchronized Response logout(Long Id) throws IdIsNullException, RequestDataIsNullException
	{
		return new Response(companyServices.logout(Id));
	}
	@PostMapping("/creatCoupon")
	public Response creatCoupon(@RequestBody RequestData coupon) throws NameIsUsedException //couponCreated
, RequestDataIsNullException, notLogedInException
	{
		AppLogger.getLogger().log(Level.CONFIG, coupon.toString());
		return new Response(companyServices.creatCoupon(coupon));
	}
	
	
	@DeleteMapping("/deleteCoupon")
	public Response deleteCoupon(@RequestBody RequestData couponData) //couponDeleted
	{
		
		return new Response(companyServices.deleteCoupon(couponData));
	}
	@PostMapping("/updateCoupon")
	public Response updateCoupon(RequestData couponData) throws RequestDataIsNullException, notLogedInException //couponUpdated
	{
		
		
		return new Response(companyServices.updateCoupon(couponData));
	}
	
	@GetMapping("/listAllCoupons")
	public Response listAllCoupons(@RequestBody RequestData couponData) throws RequestDataIsNullException, notLogedInException 
	{
		
		return new Response(companyServices.listAllCoupons(couponData));
	}
	@PostMapping("/getSpecificCoupons")
	public Response getSpecificCoupons(@RequestBody SpecificCouponDataCheck specificCouponCheck) 
	{
		return null;
		
	}
	
	
	
}
