package com.asad.couponesController;

import com.asad.couponesController.entitys.Company;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.entitys.Customer;
import com.asad.couponesController.enums.CheckClientRequestEnum;
import com.asad.couponesController.exceptions.RequestDataIsNullException;

public class CheckClientRequest {

	public void checkCompany(RequestData companyData) throws RequestDataIsNullException {
		
		Company company = companyData.getCompany();
		NullCheck.checkIfItIsNull(company,CheckClientRequestEnum.COMPANYISEMPTY.toString());
		NullCheck.checkIfItIsNull(company.getCompanyName(), CheckClientRequestEnum.COMPANYNAMENOTFOUND.toString());
		NullCheck.checkIfItIsNull(company.getEmail(), CheckClientRequestEnum.COMPANYEMAILNOTFOUND.toString());
		NullCheck.checkIfItIsNull(company.getCompanyName(), CheckClientRequestEnum.COMPANYPASSWORDNOTFOUND.toString());

	}

	public void checkCustomer(RequestData customerData) throws RequestDataIsNullException {
		Customer customer = customerData.getCustomer();
		NullCheck.checkIfItIsNull(customer, CheckClientRequestEnum.CUSTOMERISEMPTY.toString());
		NullCheck.checkIfItIsNull(customer.getName(), CheckClientRequestEnum.CUSTOMERNAMENOTFOUND.toString());
		NullCheck.checkIfItIsNull(customer.getPassword(), CheckClientRequestEnum.CUSTOMERPASSWORDNOTFOUND.toString());

	}

	public void checkCoupon(RequestData couponData) throws RequestDataIsNullException {
		
		Coupon coupon = couponData.getCoupon();
		NullCheck.checkIfItIsNull(coupon, CheckClientRequestEnum.COUPONISEMPTY.toString());
		NullCheck.checkIfItIsNull(coupon.getTitle(), CheckClientRequestEnum.COUPONTITLENOTFOUND.toString());
		NullCheck.checkIfItIsNull(coupon.getStartDate(), CheckClientRequestEnum.COUPONSTARTDATENOTFOUND.toString());
		NullCheck.checkIfItIsNull(coupon.getEndDate(), CheckClientRequestEnum.COUPONENDDATENOTFOUND.toString());
		NullCheck.checkIfItIsNull(coupon.getAmount(), CheckClientRequestEnum.COUPONAMOUNTNOTFOUND.toString());
		NullCheck.checkIfItIsNull(coupon.getCouponType(), CheckClientRequestEnum.COUPONTYPENOTFOUND.toString());
		NullCheck.checkIfItIsNull(coupon.getMassage(), CheckClientRequestEnum.COUPONMASSAGENOTFOUND.toString());
		NullCheck.checkIfItIsNull(coupon.getPrice(), CheckClientRequestEnum.COUPONPRICENOTFOUND.toString());
		NullCheck.checkIfItIsNull(coupon.getImage(), CheckClientRequestEnum.COUPONIMAGENOTFOUND.toString());

	}
}
