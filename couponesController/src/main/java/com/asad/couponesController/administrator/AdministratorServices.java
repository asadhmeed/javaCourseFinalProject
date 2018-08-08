package com.asad.couponesController.administrator;

import java.util.List;

import com.asad.couponesController.RequestData;
import com.asad.couponesController.entitys.Company;
import com.asad.couponesController.entitys.Customer;
import com.asad.couponesController.entitys.Income;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.ComponentNotFoundException;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.NameIsUsedException;
import com.asad.couponesController.exceptions.RequestDataIsNullException;
import com.asad.couponesController.exceptions.NotLogedInException;

public interface AdministratorServices extends LogInService {

	ResponseMassageEnum creatCompany(RequestData requestData)
			throws NameIsUsedException, NotLogedInException, RequestDataIsNullException;

	ResponseMassageEnum deleteCompany(RequestData requestData)
			throws ComponentNotFoundException, NotLogedInException, RequestDataIsNullException;

	ResponseMassageEnum updateCompany(RequestData requestData)
			throws IdIsNullException, NotLogedInException, RequestDataIsNullException;

	List<Company> listAllCompany(RequestData companyData) throws NotLogedInException, RequestDataIsNullException;

	Company getCompanyById(RequestData companyRequestData)
			throws IdIsNullException, NotLogedInException, RequestDataIsNullException, ComponentNotFoundException;

	ResponseMassageEnum creatCustomer(RequestData requestData)
			throws NameIsUsedException, NotLogedInException, RequestDataIsNullException;

	ResponseMassageEnum deleteCustomer(RequestData requestData)
			throws ComponentNotFoundException, NotLogedInException, RequestDataIsNullException;

	ResponseMassageEnum updateCustomer(RequestData customerData) throws NotLogedInException, RequestDataIsNullException;

	List<Customer> listAllCustomers(RequestData adminData) throws NotLogedInException, RequestDataIsNullException;

	Customer getCustomerById(RequestData customerRequestData)
			throws IdIsNullException, NotLogedInException, RequestDataIsNullException, ComponentNotFoundException;

	List<Income> viewSpesificCompanyIncome(RequestData companyData)
			throws NotLogedInException, RequestDataIsNullException;

	List<Income> viewSpesificCustomerIncome(RequestData customerData)
			throws NotLogedInException, RequestDataIsNullException;

	List<Income> viewAllIncome(RequestData requestData) throws NotLogedInException, RequestDataIsNullException;
}
