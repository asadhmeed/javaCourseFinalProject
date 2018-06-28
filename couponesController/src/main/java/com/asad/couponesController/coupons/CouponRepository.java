package com.asad.couponesController.coupons;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.enums.CouponType;

public interface CouponRepository extends CrudRepository<Coupon, Long> {

	Coupon findCouponById(Long id);
	Coupon findCouponByTitle(String title);
	List<Coupon> findAllByCouponType(CouponType couponType);
}
