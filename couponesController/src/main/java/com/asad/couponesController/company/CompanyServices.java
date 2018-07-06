package com.asad.couponesController.company;

import java.util.List;

import com.asad.couponesController.RequestData;
import com.asad.couponesController.administrator.LogInService;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.exceptions.NameIsUsedException;
import com.asad.couponesController.exceptions.RequestDataIsNullException;
import com.asad.couponesController.exceptions.notLogedInException;

public interface CompanyServices extends LogInService {

	 Coupon creatCoupon(RequestData couponData) throws NameIsUsedException, RequestDataIsNullException, notLogedInException;
	 Coupon deleteCoupon(RequestData couponData);
	
	List<Coupon> listAllCoupons(RequestData idData) throws RequestDataIsNullException, notLogedInException;
}
