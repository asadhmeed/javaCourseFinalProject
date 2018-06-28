package com.asad.couponesController;

import com.asad.couponesController.entitys.Coupon;

public class CustomerPurchaseData {

	private Coupon coupon;
	private Long CustomerId;
	
	
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	public Long getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(Long customerId) {
		CustomerId = customerId;
	}
	@Override
	public String toString() {
		return "CustomerPurchasData [coupon=" + coupon + ", CustomerId=" + CustomerId + "]";
	}
	
	
}
