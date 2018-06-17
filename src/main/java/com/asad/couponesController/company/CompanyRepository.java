package com.asad.couponesController.company;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.asad.couponesController.entitys.Company;

import com.asad.couponesController.enums.CouponType;

public interface CompanyRepository extends CrudRepository<Company, Long> {

	Company findCouponById(Long id);
	List<Company> findAllCoupon();
	List<Company> findAllByCouponType(CouponType couponType);
}
