package com.asad.couponesController.IncomeServices;

import java.time.LocalDate;

import java.util.List;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asad.couponesController.AppLogger;
import com.asad.couponesController.RequestData;
import com.asad.couponesController.Response;
import com.asad.couponesController.company.CompanyServices;
import com.asad.couponesController.customer.CustomerServices;
import com.asad.couponesController.entitys.Income;
import com.asad.couponesController.enums.ActionType;
import com.asad.couponesController.enums.ClientType;
import com.asad.couponesController.enums.IncomeType;
import com.asad.couponesController.exceptions.ComponentNotFoundException;
import com.asad.couponesController.exceptions.CouponIsAlreadyPurchasedException;
import com.asad.couponesController.exceptions.CustomerPurchaseDataException;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.IncomeIsNullException;
import com.asad.couponesController.exceptions.NameIsUsedException;
import com.asad.couponesController.exceptions.RequestDataIsNullException;
import com.asad.couponesController.exceptions.NotLogedInException;

@Service
public class IncomeServicesImpl implements IncomeServices {

	@Autowired
	private IncomeRepository incomeDao;
	@Autowired
	private CompanyServices companyServices;
	@Autowired
	private CustomerServices customerServices;

	/**
	 * when the client purchase , create , update a coupon this method stores the
	 * data of the action to the data base(name of the client ,type of the action ,price amount )
	 * 
	 * @param requestData : client request data (client data and client id)
	 * @param clientType : customer or company
	 * @param actionType : purchase , create ,update coupon
	 * @return Response depends on the actionType and clientType
	 * @throws IncomeIsNullException
	 * @throws NameIsUsedException
	 * @throws RequestDataIsNullException
	 * @throws NotLogedInException
	 * @throws CouponIsAlreadyPurchasedException
	 * @throws IdIsNullException
	 * @throws CustomerPurchaseDataException
	 * @throws ComponentNotFoundException
	 */
	@Override
	public Response storeIncome(RequestData requestData, ClientType clientType, ActionType actionType)
			throws IncomeIsNullException, NameIsUsedException, RequestDataIsNullException, NotLogedInException,
			CouponIsAlreadyPurchasedException, IdIsNullException, CustomerPurchaseDataException,
			ComponentNotFoundException {
		
		switch (clientType) {
		case COMPANY:
			switch (actionType) {
			case CREAT:
				Response CreatResponse = new Response(companyServices.creatCoupon(requestData));
				if (CreatResponse.getResponse() != null) {
					incomeDao.save(new Income(companyServices.getClientName((long) requestData.getClientId()),
							LocalDate.now(), IncomeType.COMPANY_NEW_COUPON, 100d));
				}
				return CreatResponse;

			case UPDATE:
				Response UpdateResponse = new Response(companyServices.updateCoupon(requestData));
				if (UpdateResponse.getResponse() != null) {
					incomeDao.save(new Income(companyServices.getClientName((long) requestData.getClientId()),
							LocalDate.now(), IncomeType.COMPANY_UPDATE_COUPON, 10d));

				}
				return UpdateResponse;
			default:
				break;

			}
			break;
		case CUSTOMER:
			switch (actionType) {
			case PURCHASE:
				Response PurchaseResponse = new Response(customerServices.beyACoupon(requestData));
				if (PurchaseResponse.getResponse() != null) {
					incomeDao.save(new Income(customerServices.getClientName((long) requestData.getClientId()),
							LocalDate.now(), IncomeType.CUSTOMER_PURCHASE, requestData.getCoupon().getPrice()));
				}
				return PurchaseResponse;

			}
			break;
		}
		return new Response(new IncomeIsNullException("ACTIONNOTALLOWED"));

	}
	/**
	 * view all the income 
	 * @return list of all the income table
	 */
	@Override
	public List<Income> viewAllIncome() {

		return (List<Income>) incomeDao.findAll();
	}
	/**
	 * view income for specific client depends on the income type 
	 * @param name : client name
	 * @param incomeType (CUSTOMER_PURCHASE,COMPANY_NEW_COUPON,COMPANY_UPDATE_COUPON)
	 * @return list of income 
	 */
	@Override
	public List<Income> viewIncomeByClientNameAndIncomeType(String name, IncomeType incomeType) {

		return incomeDao.findByDescrptionAndName(incomeType, name);

	}

}
