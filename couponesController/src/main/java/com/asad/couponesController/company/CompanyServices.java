package com.asad.couponesController.company;

import java.util.List;
import java.util.Set;

import com.asad.couponesController.RequestData;

import com.asad.couponesController.administrator.LogInService;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.entitys.Income;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.ComponentNotFoundException;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.NameIsUsedException;
import com.asad.couponesController.exceptions.RequestDataIsNullException;
import com.asad.couponesController.exceptions.NotLogedInException;

public interface CompanyServices extends LogInService {

	ResponseMassageEnum creatCoupon(RequestData couponData)
			throws NameIsUsedException, RequestDataIsNullException, NotLogedInException;

	ResponseMassageEnum deleteCoupon(RequestData couponData) throws RequestDataIsNullException, NotLogedInException;

	Set<Coupon> getSpecificCouponsForCumpany(RequestData SpecificCouponData)
			throws IdIsNullException, ComponentNotFoundException, NotLogedInException, RequestDataIsNullException;

	Set<Coupon> listAllCouponsForSpecificCompany(RequestData idData)
			throws RequestDataIsNullException, NotLogedInException;

	ResponseMassageEnum updateCoupon(RequestData couponData)
			throws RequestDataIsNullException, NotLogedInException, ComponentNotFoundException;

	List<Income> viewCompanyIncome(RequestData companyData) throws NotLogedInException, RequestDataIsNullException;

}
