package com.asad.couponesController.company;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asad.couponesController.LogIn;
import com.asad.couponesController.LogInResponse;
import com.asad.couponesController.LoginIdGenerator;
import com.asad.couponesController.coupons.CouponRepository;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.enums.LogInEnum;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.LogInDataIsNullException;
import com.asad.couponesController.exceptions.NameIsUsedException;
import com.mysql.jdbc.log.Log;

@Service
public class CompanyServicesImpl implements CompanyServices {

	private List<Long> logInIds = new ArrayList<>();

	@Autowired
	private CouponRepository couponDao;
	@Autowired
	private CompanyRepository companyDao;

	@Override
	public LogInResponse logInCheck(LogIn logIn) throws LogInDataIsNullException {
		if (logIn != null) {
			if (logIn.getUserId() != null) {
				return new LogInResponse(LogInEnum.ALREADYLOGINEDIN);
			} else {
				if (companyDao.findCompanyByCompanyNameAndPassword(logIn.getUserName(), logIn.getPassword()) != null) {
						Long id = LoginIdGenerator.generateId();
						logInIds.add(id);
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
			for (Long id1 : logInIds) {
				if (id == id1) {
					logInIds.remove(id);
					return ResponseMassageEnum.LOGOUTSUCCESS;
				}
			}
		} else {
			throw new IdIsNullException("company id is null ");
		}
		return ResponseMassageEnum.LOGOUTFAILED;

	}

	public Coupon creatCoupon(Coupon coupon) throws NameIsUsedException {
		try {

			return couponDao.save(coupon);
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

}
