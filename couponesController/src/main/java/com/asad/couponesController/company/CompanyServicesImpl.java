package com.asad.couponesController.company;


import java.util.HashMap;
import java.util.HashSet;
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
import com.asad.couponesController.SpecificCouponData;
import com.asad.couponesController.coupons.CouponRepository;
import com.asad.couponesController.entitys.Company;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.entitys.Customer;
import com.asad.couponesController.enums.LogInEnum;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.ComponentNotFoundException;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.LogInDataIsNullException;
import com.asad.couponesController.exceptions.NameIsUsedException;
import com.asad.couponesController.exceptions.RequestDataIsNullException;
import com.asad.couponesController.exceptions.NotLogedInException;

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
			throws NameIsUsedException, RequestDataIsNullException, NotLogedInException {
		
		logInCheck(couponData);
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
	public ResponseMassageEnum updateCoupon(RequestData couponData) throws RequestDataIsNullException, NotLogedInException {
		logInCheck(couponData);
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

	public List<Coupon> listAllCoupons(RequestData idData) throws RequestDataIsNullException, NotLogedInException {
		logInCheck(idData);

		
		return (List<Coupon>) couponDao.findAll();

	}

	@Override
	public Coupon deleteCoupon(RequestData couponData) throws RequestDataIsNullException, NotLogedInException {
		logInCheck(couponData);
		Coupon couponfromDataBase = couponDao.findCouponById(couponData.getCoupon().getId());
		NullCheck.checkIfItIsNull(couponfromDataBase, ResponseMassageEnum.COUPONNOTFOUND.toString());
		if (!couponfromDataBase.getTitle().trim().equals(couponData.getCoupon().getTitle().trim())) {
			throw new RequestDataIsNullException("COUPONSTITLEISNOTMATCH");
		}
		couponDao.delete(couponfromDataBase);
		return couponfromDataBase;
	}
	
	public Set<Coupon> getSpecificCouponsForCumpany(RequestData SpecificCouponData)
			throws IdIsNullException, ComponentNotFoundException, NotLogedInException, RequestDataIsNullException {
		logInCheck(SpecificCouponData);
		SpecificCouponData couponData = SpecificCouponData.getSpecificCouponData();
		Company company  = logInedCompanys.get(SpecificCouponData.getClientId());
		Set<Coupon> coupons = company.getCoupons();
		NullCheck.checkIfItIsNull(coupons, "there is no coupons for this customer");
		if (coupons.size() >0) {
		if (couponData.getCouponType() !=null) {
			
			Set<Coupon> specificCoupons = new HashSet<>();
			for (Coupon coupon : coupons) {
				if(coupon.getCouponType().equals(couponData.getCouponType()));
				specificCoupons.add(coupon);
			}
			return specificCoupons;
		}
		if (couponData.getPrice() != null) {
			Set<Coupon> specificCoupons = new HashSet<>();
			for (Coupon coupon : coupons) {
				if(coupon.getPrice() >= couponData.getPrice());
				specificCoupons.add(coupon);
			}
			return specificCoupons;
		} 		
		if (couponData.getEndDate() != null) {
			Set<Coupon> specificCoupons = new HashSet<>();
			for (Coupon coupon : coupons) {
				if(coupon.getEndDate().isBefore(couponData.getEndDate()) || 
						coupon.getEndDate().isEqual(couponData.getEndDate()));
				specificCoupons.add(coupon);
			}
			return specificCoupons;
		} 		
		}
		return null;
	}

	private void logInCheck(RequestData requestData) throws NotLogedInException, RequestDataIsNullException {
		NullCheck.checkIfItIsNull(requestData, "your request is empty ");
		try {
			if (!this.logInedCompanys.containsKey(requestData.getClientId())) {
				throw new NotLogedInException(LogInEnum.NOTLOGEDIN.toString());
			}
		} catch (Exception e) {
			throw new NotLogedInException("please log in to do this task id is null", e);
		}

	}

	

}
