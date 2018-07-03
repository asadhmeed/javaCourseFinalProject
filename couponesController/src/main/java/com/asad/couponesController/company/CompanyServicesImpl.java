package com.asad.couponesController.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asad.couponesController.LogIn;
import com.asad.couponesController.LogInResponse;
import com.asad.couponesController.LoginIdGenerator;
import com.asad.couponesController.RequestData;
import com.asad.couponesController.coupons.CouponRepository;
import com.asad.couponesController.entitys.Company;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.enums.LogInEnum;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.LogInDataIsNullException;
import com.asad.couponesController.exceptions.NameIsUsedException;
import com.asad.couponesController.exceptions.notLogedInException;
import com.mysql.jdbc.log.Log;

@Service
public class CompanyServicesImpl implements CompanyServices {

	
	private Map<Long,Company> logInedCompanys = new HashMap<>();

	@Autowired
	private CouponRepository couponDao;
	@Autowired
	private CompanyRepository companyDao;

	@Override
	public LogInResponse logIn(LogIn logIn) throws LogInDataIsNullException {
		if (logIn != null) {
			if (logIn.getUserId() != null && logInedCompanys.get(logIn.getUserId())!=null) {
				return new LogInResponse(LogInEnum.ALREADYLOGINEDIN);
			} else {
				
				Company company =companyDao.findCompanyByCompanyNameAndPassword(logIn.getUserName(), logIn.getPassword());
				if ( company!= null) {
						Long id = LoginIdGenerator.generateId();
						logInedCompanys.put(id, company);
						return new LogInResponse(LogInEnum.LOGINSUCCESS, id);
				} else {
					return new LogInResponse(LogInEnum.LOGINFAILED);
				}
			}
		} else {
				throw new LogInDataIsNullException("companys logIn is null");
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

	public Coupon creatCoupon(RequestData coupon) throws NameIsUsedException {
		try {
			
			return couponDao.save(coupon.getCoupon());
		} catch (Exception e) {

			System.out.println(e.getStackTrace());
			throw new NameIsUsedException("The Name Of The Coupon Is Already Used", e);

		}

	}

	public List<Coupon> listAllCoupons() {

		return (List<Coupon>) couponDao.findAll();
	}

	@Override
	public Coupon deleteCoupon(Coupon coupon) {

		return null;
	}

		private void logInCheck(RequestData requestData, String textMessage) throws notLogedInException {
		// TODO Auto-generated method stub
		
	}

}
