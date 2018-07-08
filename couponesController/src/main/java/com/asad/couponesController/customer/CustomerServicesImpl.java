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
import com.asad.couponesController.NullCheck;
import com.asad.couponesController.IncomeServices.IncomeServices;
import com.asad.couponesController.CheckClientRequest;
import com.asad.couponesController.coupons.CouponRepository;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.entitys.Customer;
import com.asad.couponesController.enums.ActionType;
import com.asad.couponesController.enums.ClientType;
import com.asad.couponesController.enums.LogInEnum;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.ComponentNotFoundException;
import com.asad.couponesController.exceptions.CouponIsAlreadyPurchasedException;
import com.asad.couponesController.exceptions.CustomerPurchaseDataException;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.IncomeIsNullException;
import com.asad.couponesController.exceptions.RequestDataIsNullException;
import com.asad.couponesController.exceptions.notLogedInException;

@Service
public class CustomerServicesImpl implements CustomerServices {
	private static Map<Long, Customer> customers = new HashMap<>();

	@Autowired
	private CustomerRepository customerDao;
	@Autowired
	private CouponRepository couponDao;
	@Autowired
	private IncomeServices incomeServices;

	@Override
	public synchronized LogInResponse logIn(LogIn logIn) {
		if (logIn.getUserId() != null) {
			Customer customer = customerDao.findCustomerByNameAndPassword(logIn.getUserName(), logIn.getPassword());
			if (customer != null) {
				Long logInPassCode = LoginIdGenerator.generateId();
				customers.put(logInPassCode, customer);
				return new LogInResponse(LogInEnum.LOGINSUCCESS, logInPassCode);
			} else {
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

	// TODO:finsh the Purchase coupon method
	@Override
	public synchronized Coupon beyACoupon(RequestData couponData) throws notLogedInException,
			RequestDataIsNullException, IncomeIsNullException, CustomerPurchaseDataException {
		logInCheck(couponData);
		new CheckClientRequest().checkCoupon(couponData);
		NullCheck.checkIfItIsNull(couponData.getClientId(), "your id is empty");

		Coupon dbCouponCheck = this.couponDao.findCouponById(couponData.getCoupon().getId());
		Customer customer = customers.get(couponData.getClientId());
		if (dbCouponCheck != null && customer != null) {

			for (Coupon customersCouponCheck : customer.getCoupons()) {
				if (customersCouponCheck.getTitle().equals(dbCouponCheck.getTitle())) {
					throw new CouponIsAlreadyPurchasedException("can not purchase the same coupon twice");
				}
			}
			if (couponData.getCoupon().getEndDate().isAfter(LocalDate.now())) {
//				Customer customerFromDb = customerDao.findCustomerByName(customer.getName());
				
				Set<Coupon> coupons = customer.getCoupons();
				coupons.add(dbCouponCheck);
				customer.setCoupons(coupons);
				customerDao.save(customer);
				Long customerId = couponData.getClientId();
				this.customers.remove(customerId);
				this.customers.put(customerId, customer);
				
				return couponData.getCoupon();
			} else {
				throw new CustomerPurchaseDataException(" the coupon is no longer available");
			}
		} else {
			throw new CustomerPurchaseDataException("the customer or the coupon does not exist");
		}

	}

	@Override
	public Set<Coupon> getAllCouponForCustomer(RequestData customerData) throws IdIsNullException, ComponentNotFoundException, notLogedInException, RequestDataIsNullException {
		logInCheck(customerData);
		NullCheck.checkIfItIsNull(customerData.getCustomer(), "customer data is empty");
		Long customerId = customerData.getClientId();
		NullCheck.checkIfItIsNull(customerId, LogInEnum.NOTLOGEDIN.toString());
			Customer customer = customers.get(customerId);
			if (customer != null) {
				return customer.getCoupons();
			} else {
				throw new ComponentNotFoundException("the customer data is not found check the passId");
			}
		
	}
	
	
	

	private void logInCheck(RequestData requestData) throws notLogedInException, RequestDataIsNullException {
		NullCheck.checkIfItIsNull(requestData, "your request is empty ");
		try {
			if (!this.customers.containsKey(requestData.getClientId())) {
				throw new notLogedInException(LogInEnum.NOTLOGEDIN.toString());
			}
		} catch (Exception e) {
			throw new notLogedInException("please log in to do this task id is null", e);
		}

	}

}
