package com.asad.couponesController.IncomeServices;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asad.couponesController.entitys.Income;


@Service
public class IncomeServicesImpl implements IncomeServices{

	@Autowired
	private IncomeRepository incomeDao;
	@Override
	public void storeIncome(Income income) throws IncomeIsNullException {
		if (income != null) {
			incomeDao.save(income);
		}else {
			throw new IncomeIsNullException("income is null");
		}
		
	}

	@Override
	public List<Income> viewAllIncome() {
		
		return (List<Income>) incomeDao.findAll();
	}

	@Override
	public List<Income> viewIncomeByCusomer(String name) {
		
		return incomeDao.findByName(name);
	}

	@Override
	public List<Income> viewIncomeByCompany(String name) {
	
		return incomeDao.findByName(name);
	}
   

}
