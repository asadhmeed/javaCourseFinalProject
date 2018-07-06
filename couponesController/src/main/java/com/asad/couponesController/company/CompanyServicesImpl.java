package com.asad.couponesController.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.asad.couponesController.LogIn;
import com.asad.couponesController.LogInResponse;
import com.asad.couponesController.LoginIdGenerator;
import com.asad.couponesController.NullCheck;
import com.asad.couponesController.RequestData;
import com.asad.couponesController.coupons.CouponRepository;
import com.asad.couponesController.coupons.CouponServices;
import com.asad.couponesController.entitys.Company;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.enums.LogInEnum;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.LogInDataIsNullException;
import com.asad.couponesController.exceptions.NameIsUsedException;
import com.asad.couponesController.exceptions.RequestDataIsNullException;
import com.asad.couponesController.exceptions.notLogedInException;
import com.mysql.jdbc.log.Log;

@Service
public class CompanyServicesImpl implements CompanyServices {

	private Map<Long, Company> logInedCompanys = new HashMap<>();

	@Autowired
	private CouponRepository couponDao;
	@Autowired
	private CompanyRepository companyDao;

	@Override
	public LogInResponse logIn(LogIn logIn) throws LogInDataIsNullException, RequestDataIsNullException {
		NullCheck.checkIfItIsNull(logIn, "log in data is null please send the right data");
		if (logIn.getUserId() != null && logInedCompanys.get(logIn.getUserId()) != null) {
			return new LogInResponse(LogInEnum.ALREADYLOGINEDIN);
		} else {

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

	public Coupon creatCoupon(RequestData companyData) throws NameIsUsedException, RequestDataIsNullException, notLogedInException {
		NullCheck.checkIfItIsNull(companyData, "your request is empty ");
		NullCheck.checkIfItIsNull(companyData.getClientId(), "please log in to do this task");
		logInCheck(companyData, "plaese log in to do this task");
		NullCheck.checkIfItIsNull(companyData.getCoupon(), "the coupon you want to creat is not valid");
		
		try {
			Company company = logInedCompanys.get(companyData.getClientId());
			Set<Coupon> coupons =company.getCoupons();
			coupons.add(companyData.getCoupon());
			company.setCoupons(coupons);
			companyDao.save(company);
			return couponDao.save(companyData.getCoupon());
		} catch (Exception e) {

			System.out.println(e.getStackTrace());
			throw new NameIsUsedException("The Name Of The Coupon Is Already Used", e);

		}

	}

	public List<Coupon> listAllCoupons(RequestData idData) throws RequestDataIsNullException, notLogedInException {
		NullCheck.checkIfItIsNull(idData, "your request is empty");
		NullCheck.checkIfItIsNull(idData.getClientId(), "your id is empty please log in to do this task");
		logInCheck(idData, "please log in to do this task");
		
				return (List<Coupon>) couponDao.findAll();

			
		
	}

	@Override
	public Coupon deleteCoupon(RequestData couponData) {

		return null;
	}

	private void logInCheck(RequestData requestData, String textMessage) throws notLogedInException {
		try {
			if(!this.logInedCompanys.containsKey(requestData.getClientId())) {
				throw new notLogedInException(textMessage);
			}
		}catch(Exception e){
			throw new notLogedInException("id cannot be null", e);
		}

	}

	

}
