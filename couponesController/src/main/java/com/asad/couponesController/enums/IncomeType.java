package com.asad.couponesController.enums;

public enum IncomeType {

	CUSTOMER_PURCHASE("customer purchased a coupon"),
	COMPANY_NEW_COUPON("company created a new coupon"),
	COMPANY_UPDATE_COUPON("company updated coupon");
	
	private  String descrption;

	private IncomeType(String descrption) {
		this.descrption = descrption;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}
	
	
	
	
	
}
