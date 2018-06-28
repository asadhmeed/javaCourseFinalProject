package com.asad.couponesController.IncomeServices;

import java.util.List;

import com.asad.couponesController.entitys.Income;

public interface IncomeServices {
	void storeIncome(Income income ) throws IncomeIsNullException;
	List<Income> viewAllIncome();
	List<Income> viewIncomeByCusomer(String name);
	List<Income> viewIncomeByCompany(String name);
	
}
