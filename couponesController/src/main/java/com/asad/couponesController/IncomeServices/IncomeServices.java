package com.asad.couponesController.IncomeServices;

import java.util.List;


import com.asad.couponesController.RequestData;
import com.asad.couponesController.Response;
import com.asad.couponesController.entitys.Income;
import com.asad.couponesController.enums.ActionType;
import com.asad.couponesController.enums.ClientType;
import com.asad.couponesController.exceptions.ComponentNotFoundException;
import com.asad.couponesController.exceptions.CouponIsAlreadyPurchasedException;
import com.asad.couponesController.exceptions.CustomerPurchaseDataException;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.IncomeIsNullException;
import com.asad.couponesController.exceptions.NameIsUsedException;
import com.asad.couponesController.exceptions.RequestDataIsNullException;
import com.asad.couponesController.exceptions.NotLogedInException;

public interface IncomeServices {
	Response storeIncome(RequestData requestData, ClientType clientType, ActionType actionType)
			throws IncomeIsNullException, NameIsUsedException, RequestDataIsNullException, NotLogedInException,
			CouponIsAlreadyPurchasedException, IdIsNullException, CustomerPurchaseDataException, ComponentNotFoundException;
	List<Income> viewAllIncome();
	List<Income> viewIncomeByCusomer(String name);
	List<Income> viewIncomeByCompany(String name);
	
}
