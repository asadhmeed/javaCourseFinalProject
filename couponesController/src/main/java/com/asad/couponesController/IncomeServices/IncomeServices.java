package com.asad.couponesController.IncomeServices;

import java.util.List;

import com.asad.couponesController.IncomeData;

import com.asad.couponesController.entitys.Income;
import com.asad.couponesController.exceptions.IncomeIsNullException;

public interface IncomeServices {
	void storeIncome(IncomeData purchaseCoupon) throws IncomeIsNullException;
	List<Income> viewAllIncome();
	List<Income> viewIncomeByCusomer(String name);
	List<Income> viewIncomeByCompany(String name);
	
}
