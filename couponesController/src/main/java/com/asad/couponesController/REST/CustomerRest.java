package com.asad.couponesController.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.asad.couponesController.RequestData;
import com.asad.couponesController.LogIn;
import com.asad.couponesController.Response;
import com.asad.couponesController.IncomeServices.IncomeServices;
import com.asad.couponesController.customer.CustomerServices;
import com.asad.couponesController.entitys.Customer;
import com.asad.couponesController.enums.ActionType;
import com.asad.couponesController.enums.ClientType;
import com.asad.couponesController.exceptions.ComponentNotFoundException;
import com.asad.couponesController.exceptions.CouponIsAlreadyPurchasedException;
import com.asad.couponesController.exceptions.CustomerPurchaseDataException;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.IncomeIsNullException;
import com.asad.couponesController.exceptions.LogInDataIsNullException;
import com.asad.couponesController.exceptions.NameIsUsedException;
import com.asad.couponesController.exceptions.RequestDataIsNullException;
import com.asad.couponesController.exceptions.NotLogedInException;

@RequestMapping("/customerRest")
@RestController
public class CustomerRest implements CouponClaintREST {

	@Autowired
	private CustomerServices customerServices;
	@Autowired
	private IncomeServices incomeServices;

	@PostMapping("/customerlogIn")
	@Override
	public Response logIn(@RequestBody LogIn logIn) throws LogInDataIsNullException, RequestDataIsNullException {

		return new Response(customerServices.logIn(logIn));
	}

	@PostMapping("/customerLogOut")
	@Override
	public synchronized Response logout(@RequestBody RequestData IdData) throws IdIsNullException, RequestDataIsNullException, NotLogedInException {
		return new Response(customerServices.logout(IdData));
	}

	@PostMapping("/purchaseCoupon")
	public Response purchaseCoupon(@RequestBody RequestData customerData)
			throws CouponIsAlreadyPurchasedException, IdIsNullException, CustomerPurchaseDataException,
			IncomeIsNullException, NameIsUsedException, RequestDataIsNullException, NotLogedInException, ComponentNotFoundException {

		return incomeServices.storeIncome(customerData, ClientType.CUSTOMER, ActionType.PURCHASE);
	}

	@PostMapping("/listAllCustomerCoupons")
	public Response listAllCustomerCoupons(RequestData customerId) throws IdIsNullException, ComponentNotFoundException, NotLogedInException, RequestDataIsNullException {

		return new Response(customerServices.getAllCouponsForCustomer(customerId));
	}
	@PostMapping("/getCouponsByCouponTypeOrPrice")
	public Response listCouponsByCouponTypeOrPrice(RequestData spesificCouponData) throws IdIsNullException, ComponentNotFoundException, NotLogedInException, RequestDataIsNullException {
		
		return new Response(customerServices.getSpecificCouponsForCustomer(spesificCouponData));
	}
	@PostMapping("/getSpecificCoupons")
	public Response getSpecificCoupons(@RequestBody RequestData specificCouponData) throws IdIsNullException, ComponentNotFoundException, NotLogedInException, RequestDataIsNullException 
	{
		return new Response(customerServices.getSpecificCouponsForCustomer(specificCouponData));
		
	}
}
