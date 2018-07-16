package com.asad.couponesController;

import java.time.LocalDate;

import com.asad.couponesController.enums.CouponType;

public class SpecificCouponData {
	
	private CouponType couponType;
	private Double price;
	private LocalDate endDate;
	
	
	
	
	
	public CouponType getCouponType() {
		return couponType;
	}
	public void setCouponType(CouponType couponType) {
		this.couponType = couponType;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
	
}
