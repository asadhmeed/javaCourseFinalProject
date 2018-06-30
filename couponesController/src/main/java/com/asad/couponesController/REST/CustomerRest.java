package com.asad.couponesController.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.asad.couponesController.CustomerPurchaseData;
import com.asad.couponesController.LogIn;
import com.asad.couponesController.Response;
import com.asad.couponesController.IncomeServices.IncomeServices;
import com.asad.couponesController.customer.CustomerServices;
import com.asad.couponesController.entitys.Customer;
import com.asad.couponesController.exceptions.ComponentNotFoundException;
import com.asad.couponesController.exceptions.CouponIsAlreadyPurchasedException;
import com.asad.couponesController.exceptions.CustomerPurchaseDataException;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.IncomeIsNullException;
import com.asad.couponesController.exceptions.LogInDataIsNullException;

@RequestMapping("/customerRest")
@RestController
public class CustomerRest implements CouponClaintREST {

	@Autowired
	private CustomerServices customerServices;
	@Autowired
	private IncomeServices incomeServices;
	

	@PostMapping("/logIn")
	@Override
	public Response logIn(@RequestBody LogIn logIn) throws LogInDataIsNullException {

		return new Response( customerServices.logInCheck(logIn));
	}

	@PostMapping("/purchaseCoupon")
	public Response purchaseCoupon(@RequestBody CustomerPurchaseData customerData)
			throws CouponIsAlreadyPurchasedException, IdIsNullException, CustomerPurchaseDataException, IncomeIsNullException {
		
		
		return new Response(customerServices.beyACoupon(customerData));
	}

	@PostMapping("/listAllCustomerCoupons")
	public Response listAllCustomerCoupons(Long customerId) throws IdIsNullException, ComponentNotFoundException {
		
		return new Response(customerServices.getAllCoupon(customerId));
	}
}
