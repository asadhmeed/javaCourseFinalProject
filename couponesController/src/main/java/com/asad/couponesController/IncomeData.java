package com.asad.couponesController;

import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.entitys.Customer;

public class IncomeData {

	
	private Coupon coupon;
	private Customer customer;
	
	
	
	
	public IncomeData(Coupon coupon, Customer customer) {
		super();
		this.coupon = coupon;
		this.customer = customer;
	}
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
