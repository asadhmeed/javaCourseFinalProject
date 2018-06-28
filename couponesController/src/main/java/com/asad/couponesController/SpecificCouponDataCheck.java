package com.asad.couponesController;

import java.time.LocalDate;

import com.asad.couponesController.enums.CouponType;

public class SpecificCouponDataCheck {
	private CouponType CouponType;
	private Integer price;
	private LocalDate endDate;
	
	
	
	public CouponType getCouponType() {
		return CouponType;
	}
	public void setCouponType(CouponType couponType) {
		CouponType = couponType;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
	
}
