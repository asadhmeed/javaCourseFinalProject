package com.asad.couponesController;

import com.asad.couponesController.entitys.Company;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.entitys.Customer;
import com.asad.couponesController.enums.CheckClientRequestEnum;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.RequestDataIsNullException;

public class CheckClientRequest {
	
	
	/**
	 * check if the data is null 
	 */
	public CheckClientRequest() {
	
	}
	/**
	 * check if the data in the company not null
	 * @param companyData : RequestData
	 * @throws RequestDataIsNullException
	 */
	public synchronized void  checkCompany(RequestData companyData) throws RequestDataIsNullException {

		Company company = companyData.getCompany();
		NullCheck.checkIfItIsNull(company, CheckClientRequestEnum.COMPANYISEMPTY.toString());
		NullCheck.checkIfItIsNull(company.getCompanyName(), CheckClientRequestEnum.COMPANYNAMENOTFOUND.toString());
		NullCheck.checkIfItIsNull(company.getEmail(), CheckClientRequestEnum.COMPANYEMAILNOTFOUND.toString());
		if(!(new EmailValidator().validateEmail(company.getEmail()))){
			NullCheck.checkIfItIsNull(null, CheckClientRequestEnum.COMPANYEMAILNOTVALID.toString());
		}
		NullCheck.checkIfItIsNull(company.getCompanyName(), CheckClientRequestEnum.COMPANYPASSWORDNOTFOUND.toString());

	}
	/**
	 * check if the data in the customer not null
	 * @param customerData : RequestData
	 * @throws RequestDataIsNullException
	 */
	public synchronized void checkCustomer(RequestData customerData) throws RequestDataIsNullException {
		Customer customer = customerData.getCustomer();
		NullCheck.checkIfItIsNull(customer, CheckClientRequestEnum.CUSTOMERISEMPTY.toString());
		NullCheck.checkIfItIsNull(customer.getName(), CheckClientRequestEnum.CUSTOMERNAMENOTFOUND.toString());
		NullCheck.checkIfItIsNull(customer.getPassword(), CheckClientRequestEnum.CUSTOMERPASSWORDNOTFOUND.toString());

	}
	/**
	 * check if the data in the coupon data not null
	 * @param couponData : RequestData
	 * @throws RequestDataIsNullException
	 */
	public synchronized void checkCoupon(RequestData couponData) throws RequestDataIsNullException {

		Coupon coupon = couponData.getCoupon();
		NullCheck.checkIfItIsNull(coupon, CheckClientRequestEnum.COUPONISEMPTY.toString());
		NullCheck.checkIfItIsNull(coupon.getTitle(), CheckClientRequestEnum.COUPONTITLENOTFOUND.toString());
		NullCheck.checkIfItIsNull(coupon.getStartDate(), CheckClientRequestEnum.COUPONSTARTDATENOTFOUND.toString());
		NullCheck.checkIfItIsNull(coupon.getEndDate(), CheckClientRequestEnum.COUPONENDDATENOTFOUND.toString());
		NullCheck.checkIfItIsNull(coupon.getAmount(), CheckClientRequestEnum.COUPONAMOUNTNOTFOUND.toString());
		NullCheck.checkIfItIsNull(coupon.getCouponType(), CheckClientRequestEnum.COUPONTYPENOTFOUND.toString());
		NullCheck.checkIfItIsNull(coupon.getMassage(), CheckClientRequestEnum.COUPONMASSAGENOTFOUND.toString());
		if(coupon.getPrice()<0) {
			NullCheck.checkIfItIsNull(null, 
					ResponseMassageEnum.COUPONPRICEMUSTNOTBENEGATIVE.toString());
	
		}
		NullCheck.checkIfItIsNull(coupon.getPrice(), CheckClientRequestEnum.COUPONPRICENOTFOUND.toString());
		NullCheck.checkIfItIsNull(coupon.getImage(), CheckClientRequestEnum.COUPONIMAGENOTFOUND.toString());

	}
	/**
	 * check if the data in the log in data not null
	 * @param logIn : LogIn
	 * @throws RequestDataIsNullException
	 */
	public synchronized void checkLogIn(LogIn logIn) throws RequestDataIsNullException {
		NullCheck.checkIfItIsNull(logIn, CheckClientRequestEnum.LOGINREQUSTISEMPTY.toString());
		NullCheck.checkIfItIsNull(logIn.getUserName(), CheckClientRequestEnum.LOGINUSERNAMEISEMPTY.toString());
		NullCheck.checkIfItIsNull(logIn.getPassword(), CheckClientRequestEnum.LOGINPASSWORDISEMPTY.toString());

		
	}

}
