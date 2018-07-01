package com.asad.couponesController.customer;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asad.couponesController.RequestData;
import com.asad.couponesController.LogIn;
import com.asad.couponesController.LogInResponse;
import com.asad.couponesController.LoginIdGenerator;
import com.asad.couponesController.IncomeServices.IncomeServices;
import com.asad.couponesController.IncomeData;
import com.asad.couponesController.coupons.CouponRepository;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.entitys.Customer;
import com.asad.couponesController.enums.LogInEnum;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.ComponentNotFoundException;
import com.asad.couponesController.exceptions.CouponIsAlreadyPurchasedException;
import com.asad.couponesController.exceptions.CustomerPurchaseDataException;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.IncomeIsNullException;
@Service
public class CustomerServicesImpl implements CustomerServices{
   private static Map<Long, Customer> customers = new HashMap<>();
   
   
   @Autowired
   private CustomerRepository customerDao; 
   @Autowired
   private CouponRepository couponDao;
   @Autowired
   private IncomeServices incomeServices;
   
   
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
   
   
   
   //TODO:finsh the Purchase coupon method
	@Override
	public synchronized Coupon beyACoupon(RequestData customerData) throws CouponIsAlreadyPurchasedException, IdIsNullException, CustomerPurchaseDataException, IncomeIsNullException {
		if (customerData.getClientId()!= null) {
			
			Coupon dbCouponCheck =this.couponDao.findCouponByTitle(customerData.getCoupon().getTitle());
			Customer customer  = customers.get(customerData.getClientId());
		if ( dbCouponCheck != null && customer != null) {
			
			for (Coupon customersCouponCheck : customer.getCoupons()) {
				if (customersCouponCheck.getTitle().equals(dbCouponCheck.getTitle())) {
					throw new CouponIsAlreadyPurchasedException("can not purchase the same coupon twice");
				}
			}
		if (customerData.getCoupon().getEndDate().isAfter(LocalDate.now())) {
			Customer customerFromDb = customerDao.findCustomerByName(customer.getName());
			customerFromDb.getCoupons().add(dbCouponCheck);
			customerDao.save(customerFromDb);
			Long customerId= customerData.getClientId();
			this.customers.remove(customerId);
			this.customers.put(customerId, customerFromDb);
			Coupon coupon =customerData.getCoupon();
			 incomeServices.storeIncome(new IncomeData(coupon, customerFromDb));
			return coupon;			
		}else {
			throw new CustomerPurchaseDataException(" the coupon is no longer available");
		}
		}else {
			throw new CustomerPurchaseDataException("the customer or the coupon does not exist");
		}
		
		}else {
			throw new IdIsNullException("you need to log in");
		}
	
	}


	@Override
	public Set<Coupon> getAllCoupon(Long customerId) throws IdIsNullException, ComponentNotFoundException {
		if(customerId !=null) {
			Customer customer = customers.get(customerId);
			if (customer != null) {
				return customer.getCoupons();
			}else {
				throw new ComponentNotFoundException("the customer data is not found check the passId");
			}
		}else
		throw new IdIsNullException("customer id is null");
		
	}


	

}
