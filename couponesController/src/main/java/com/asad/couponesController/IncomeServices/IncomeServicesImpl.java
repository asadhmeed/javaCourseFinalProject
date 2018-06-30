package com.asad.couponesController.IncomeServices;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asad.couponesController.IncomeData;

import com.asad.couponesController.entitys.Income;
import com.asad.couponesController.enums.IncomeType;
import com.asad.couponesController.exceptions.IncomeIsNullException;

@Service
public class IncomeServicesImpl implements IncomeServices {

	@Autowired
	private IncomeRepository incomeDao;

	@Override
	public void storeIncome(IncomeData customerAndCoupon) throws IncomeIsNullException {
		if (customerAndCoupon != null || customerAndCoupon.getCoupon() != null
				|| customerAndCoupon.getCustomer() != null) {
			incomeDao.save(new Income(customerAndCoupon.getCustomer().getName(), LocalDate.now(),
					IncomeType.CUSTOMER_PURCHASE, customerAndCoupon.getCoupon().getPrice()));
		} else {
			throw new IncomeIsNullException("coupon or customer income is null");
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
