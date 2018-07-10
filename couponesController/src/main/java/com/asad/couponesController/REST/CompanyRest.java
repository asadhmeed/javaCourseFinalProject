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
import com.asad.couponesController.SpecificCouponData;
import com.asad.couponesController.IncomeServices.IncomeServices;
import com.asad.couponesController.company.CompanyServices;
import com.asad.couponesController.coupons.CouponServices;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.enums.ActionType;
import com.asad.couponesController.enums.ClientType;
import com.asad.couponesController.enums.LogInEnum;
import com.asad.couponesController.exceptions.ComponentNotFoundException;
import com.asad.couponesController.exceptions.CouponIsAlreadyPurchasedException;
import com.asad.couponesController.exceptions.CustomerPurchaseDataException;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.IncomeIsNullException;
import com.asad.couponesController.exceptions.LogInDataIsNullException;
import com.asad.couponesController.exceptions.NameIsUsedException;
import com.asad.couponesController.exceptions.RequestDataIsNullException;
import com.asad.couponesController.exceptions.NotLogedInException;

@RequestMapping("/companyRest")
@RestController
public class CompanyRest implements CouponClaintREST {
	
	@Autowired
	private CompanyServices companyServices;
	@Autowired
	private IncomeServices incomeServices;

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
	public Response creatCoupon(@RequestBody RequestData couponData) throws NameIsUsedException //couponCreated
, RequestDataIsNullException, NotLogedInException, CouponIsAlreadyPurchasedException, IncomeIsNullException, IdIsNullException, CustomerPurchaseDataException
	{
		AppLogger.getLogger().log(Level.CONFIG, couponData.toString());
//		return new Response(companyServices.creatCoupon(coupon));
	return incomeServices.storeIncome(couponData, ClientType.COMPANY, ActionType.CREAT);
	}
	
	
	@DeleteMapping("/deleteCoupon")
	public Response deleteCoupon(@RequestBody RequestData couponData) throws RequestDataIsNullException, NotLogedInException //couponDeleted
	{
		
		return new Response(companyServices.deleteCoupon(couponData));
	}
	@PostMapping("/updateCoupon")
	public Response updateCoupon(RequestData couponData) throws RequestDataIsNullException, NotLogedInException, CouponIsAlreadyPurchasedException, IncomeIsNullException, NameIsUsedException, IdIsNullException, CustomerPurchaseDataException 
	{
		return incomeServices.storeIncome(couponData, ClientType.COMPANY, ActionType.UPDATE);
		//		return new Response(companyServices.updateCoupon(couponData));
	}
	
	@GetMapping("/listAllCoupons")
	public Response listAllCoupons(@RequestBody RequestData couponData) throws RequestDataIsNullException, NotLogedInException 
	{
		
		return new Response(companyServices.listAllCoupons(couponData));
	}
	@PostMapping("/getSpecificCoupons")
	public Response getSpecificCoupons(@RequestBody RequestData specificCouponData) throws IdIsNullException, ComponentNotFoundException, NotLogedInException, RequestDataIsNullException 
	{
		return new Response(companyServices.getSpecificCouponsForCumpany(specificCouponData));
		
	}
	
	
	
}
