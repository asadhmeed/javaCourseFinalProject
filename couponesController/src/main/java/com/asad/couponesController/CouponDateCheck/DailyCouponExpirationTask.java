package com.asad.couponesController.CouponDateCheck;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.asad.couponesController.AppLogger;
import com.asad.couponesController.coupons.CouponRepository;
import com.asad.couponesController.entitys.Coupon;


public class DailyCouponExpirationTask implements Runnable {

	
	CouponRepository couponDao;

	/**
	 * iterates on all the coupons in the coupon table and check the end data 
	 * and deletes the out of data coupons
	 */
	public DailyCouponExpirationTask(CouponRepository couponDao) {
		
		this.couponDao = couponDao;
	}

	
	@Override
	public void run() {
		AppLogger.getLogger().log(Level.INFO, "start Coupon end date check");
		Set<Coupon> coupons = new HashSet<>((ArrayList<Coupon>)couponDao.findAll()) ;
		if (coupons != null) {
			if (coupons.size() > 0) {
				for (Coupon coupon : coupons) {
					if (coupon.getEndDate().isBefore(LocalDate.now())) {
						couponDao.delete(coupon);
					}
				}
			}
		}

		AppLogger.getLogger().log(Level.INFO, "finshed Coupon end date check");

	}
	
	
	

}
