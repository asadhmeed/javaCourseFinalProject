package com.asad.couponesController.customer;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asad.couponesController.CustomerPurchaseData;
import com.asad.couponesController.LogIn;
import com.asad.couponesController.LogInResponse;
import com.asad.couponesController.LoginIdGenerator;
import com.asad.couponesController.coupons.CouponRepository;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.entitys.Customer;
import com.asad.couponesController.enums.LogInEnum;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.CouponIsAlreadyPurchasedException;
@Service
public class CustomerServicesImpl implements CustomerServices{
   private static Map<Long, Customer> customers = new HashMap<>();
   
   
   @Autowired
   private CustomerRepository customerDao; 
   @Autowired
   private CouponRepository couponDao; 
   
   //TODO:finsh the Purchase coupon method
	@Override
	public synchronized Coupon beyACoupon(CustomerPurchaseData customerData) throws CouponIsAlreadyPurchasedException {
		if (customerData.getCustomerId()!= null) {
			
			Coupon dbCouponCheck =this.couponDao.findCouponByTitle(customerData.getCoupon().getTitle());
			Customer customer  = customers.get(customerData.getCustomerId());
		if ( dbCouponCheck != null && customer != null) {
			
			for (Coupon customersCouponCheck : customer.getCoupons()) {
				if (customersCouponCheck.getTitle().equals(dbCouponCheck.getTitle())) {
					throw new CouponIsAlreadyPurchasedException("can not purchase coupon twice");
				}
			}
		if (customerData.getCoupon().getEndDate().isAfter(LocalDate.now())) {
			Customer customerFromDb = customerDao.findCustomerByName(customer.getName());
			customerFromDb.getCoupons().add(dbCouponCheck);
			customerDao.save(customerFromDb);
			Long customerId= customerData.getCustomerId();
			this.customers.remove(customerId);
			this.customers.put(customerId, customerFromDb);
							
		}
		}
		}
		return null;
	}


	@Override
	public synchronized LogInResponse logInCheck(LogIn logIn) {
		if (logIn.getUserId()!= null) {
		 Customer customer = customerDao.findCustomerByNameAndPassword(logIn.getUserName(), logIn.getPassword());
	     if (customer != null) {
	    	 Long logInPassCode = LoginIdGenerator.generateId();
			customers.put(logInPassCode, customer);
			return new LogInResponse(LogInEnum.LOGINSUCCESS,logInPassCode);
		}else {
	     return new LogInResponse(LogInEnum.LOGINFAILED);
		}
		}
		 return new LogInResponse(LogInEnum.ALREADYLOGINEDIN);
	}


	@Override
	public synchronized ResponseMassageEnum logout(Long CustomerId) {
		// TODO add log out Customer
		return null;
	}

}
