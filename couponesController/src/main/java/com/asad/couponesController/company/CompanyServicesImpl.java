package com.asad.couponesController.company;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asad.couponesController.CheckClientRequest;
import com.asad.couponesController.LogIn;
import com.asad.couponesController.LogInResponse;
import com.asad.couponesController.LoginIdGenerator;
import com.asad.couponesController.NullCheck;
import com.asad.couponesController.RequestData;
import com.asad.couponesController.coupons.CouponRepository;
import com.asad.couponesController.entitys.Company;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.enums.LogInEnum;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.LogInDataIsNullException;
import com.asad.couponesController.exceptions.NameIsUsedException;
import com.asad.couponesController.exceptions.RequestDataIsNullException;
import com.asad.couponesController.exceptions.notLogedInException;

@Service
public class CompanyServicesImpl implements CompanyServices {

	private Map<Long, Company> logInedCompanys = new HashMap<>();

	@Autowired
	private CouponRepository couponDao;
	@Autowired
	private CompanyRepository companyDao;

	@Override
	public LogInResponse logIn(LogIn logIn) throws LogInDataIsNullException, RequestDataIsNullException {
			new CheckClientRequest().checkLogIn(logIn);
		if (logIn.getUserId() != null && logInedCompanys.get(logIn.getUserId()) != null) {
			return new LogInResponse(LogInEnum.ALREADYLOGINEDIN);
		} else {

			if (logIn.getUserId() != null) {
				return new LogInResponse(LogInEnum.USERIDISNOTVALID);
			}
			Company company = companyDao.findCompanyByCompanyNameAndPassword(logIn.getUserName(), logIn.getPassword());
			if (company != null) {
				Long id = LoginIdGenerator.generateId();
				logInedCompanys.put(id, company);
				return new LogInResponse(LogInEnum.LOGINSUCCESS, id);
			} else {
				return new LogInResponse(LogInEnum.LOGINFAILED);
			}
		}

	}

	@Override
	public ResponseMassageEnum logout(Long id) throws IdIsNullException {
		if (id != null) {
			for (Long id1 : logInedCompanys.keySet()) {
				if (id == id1) {
					logInedCompanys.remove(id);
					return ResponseMassageEnum.LOGOUTSUCCESS;
				}
			}
		} else {
			throw new IdIsNullException("company id is null ");
		}
		return ResponseMassageEnum.LOGOUTFAILED;

	}

	public Coupon creatCoupon(RequestData couponData)
			throws NameIsUsedException, RequestDataIsNullException, notLogedInException {
		//TODO: create a class to check the data of the company ,customer and coupon that the user entered 
		//and return an enum to the Client depend on the empty variables in the request 
		
		logInCheck(couponData, LogInEnum.NOTLOGEDIN);
		new CheckClientRequest().checkCoupon(couponData);

		try {
			Company company = logInedCompanys.get(couponData.getClientId());
			Set<Coupon> coupons = company.getCoupons();
			coupons.add(couponData.getCoupon());
			company.setCoupons(coupons);
			companyDao.save(company);
			return couponDao.save(couponData.getCoupon());
		} catch (Exception e) {

			System.out.println(e.getStackTrace());
			throw new NameIsUsedException("The Name Of The Coupon Is Already Used", e);

		}

	}
	@Override
	public ResponseMassageEnum updateCoupon(RequestData couponData) throws RequestDataIsNullException, notLogedInException {
		logInCheck(couponData, LogInEnum.NOTLOGEDIN);
		new CheckClientRequest().checkCoupon(couponData);

		Coupon couponfromDataBase = couponDao.findCouponById(couponData.getCoupon().getId());
		boolean customerFound = couponfromDataBase != null;
		if (!customerFound) {
			return ResponseMassageEnum.COUPONNOTFOUND;
		} else {
			couponfromDataBase.setEndDate(couponData.getCoupon().getEndDate());
			couponfromDataBase.setPrice(couponData.getCoupon().getPrice());
			couponDao.save(couponfromDataBase);
			return ResponseMassageEnum.THECOUPONUPDATED;
		}
		
	
	}

	public List<Coupon> listAllCoupons(RequestData idData) throws RequestDataIsNullException, notLogedInException {
		logInCheck(idData, LogInEnum.NOTLOGEDIN);

		
		return (List<Coupon>) couponDao.findAll();

	}

	@Override
	public Coupon deleteCoupon(RequestData couponData) throws RequestDataIsNullException, notLogedInException {
		logInCheck(couponData, LogInEnum.NOTLOGEDIN);
		Coupon couponfromDataBase = couponDao.findCouponById(couponData.getCoupon().getId());
		NullCheck.checkIfItIsNull(couponfromDataBase, ResponseMassageEnum.COUPONNOTFOUND.toString());
		if (!couponfromDataBase.getTitle().trim().equals(couponData.getCoupon().getTitle().trim())) {
			throw new RequestDataIsNullException("COUPONSTITLEISNOTMATCH");
		}
		couponDao.delete(couponfromDataBase);
		return couponfromDataBase;
	}

	private void logInCheck(RequestData requestData, LogInEnum notlogedin) throws notLogedInException, RequestDataIsNullException {
		NullCheck.checkIfItIsNull(requestData, "your request is empty ");
		try {
			if (!this.logInedCompanys.containsKey(requestData.getClientId())) {
				throw new notLogedInException(notlogedin.toString());
			}
		} catch (Exception e) {
			throw new notLogedInException("please log in to do this task id is null", e);
		}

	}

	

}
