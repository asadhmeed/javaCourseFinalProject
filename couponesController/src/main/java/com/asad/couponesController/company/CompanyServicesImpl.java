package com.asad.couponesController.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.asad.couponesController.coupons.CouponRepository;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.exceptions.NameIsUsedException;

@Service
public class CompanyServicesImpl implements CompanyServices {

	@Autowired
	private CouponRepository couponDao;
	
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
