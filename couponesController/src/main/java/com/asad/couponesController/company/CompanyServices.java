package com.asad.couponesController.company;

import java.util.List;

import com.asad.couponesController.administrator.LogInService;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.exceptions.NameIsUsedException;

public interface CompanyServices extends LogInService {

	 Coupon creatCoupon(Coupon coupon) throws NameIsUsedException;
	 Coupon deleteCoupon(Coupon coupon);
	List<Coupon> listAllCoupons();
}
