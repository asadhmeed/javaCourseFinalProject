package com.asad.couponesController.CouponDateCheck;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;

import com.asad.couponesController.AppLogger;
import com.asad.couponesController.NullCheck;
import com.asad.couponesController.coupons.CouponRepository;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.enums.CouponType;

public class CouponEndDateCheck implements Runnable {

	
	CouponRepository couponDao =new CouponRepository() {
		
		@Override
		public <S extends Coupon> Iterable<S> saveAll(Iterable<S> entities) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <S extends Coupon> S save(S entity) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Optional<Coupon> findById(Long id) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Iterable<Coupon> findAllById(Iterable<Long> ids) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Iterable<Coupon> findAll() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public boolean existsById(Long id) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public void deleteById(Long id) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void deleteAll(Iterable<? extends Coupon> entities) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void deleteAll() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void delete(Coupon entity) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public long count() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public Coupon findCouponByTitle(String title) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Coupon findCouponById(Long id) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public List<Coupon> findAllByCouponType(CouponType couponType) {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	
	@Override
	public void run() {
		//TODO:fix the thread bug thread does not restart after the first time
		AppLogger.getLogger().log(Level.INFO, "start Coupon end date check");
	 Set<Coupon> coupons= (Set<Coupon>) couponDao.findAll();
	 if (coupons != null) {
	 if (coupons.size() >0) {
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
