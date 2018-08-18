package com.asad.couponesController;

import com.asad.couponesController.entitys.Company;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.entitys.Customer;
/**
 * 
 * @author asad
 *
 */
public class RequestData {
	private SpecificCouponData specificCouponData;
	private Company company;
	private Customer customer;
	private Coupon coupon;
	private Integer clientId;
	
	
	
	
	public SpecificCouponData getSpecificCouponData() {
		return specificCouponData;
	}
	public void setSpecificCouponData(SpecificCouponData specificCouponData) {
		this.specificCouponData = specificCouponData;
	}
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
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer customerId) {
		clientId = customerId;
	}
	@Override
	public String toString() {
		return "RequestData [company=" + company + ", customer=" + customer + ", coupon=" + coupon + ", clientId="
				+ clientId + "]";
	}
	
	
	
}
