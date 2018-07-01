package com.asad.couponesController;

import com.asad.couponesController.entitys.Company;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.entitys.Customer;

public class RequestData {
	
	private Company company;
	private Customer customer;
	private Coupon coupon;
	private Long clientId;
	
	
	
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long customerId) {
		clientId = customerId;
	}
	@Override
	public String toString() {
		return "RequestData [company=" + company + ", customer=" + customer + ", coupon=" + coupon + ", clientId="
				+ clientId + "]";
	}
	
	
	
}
