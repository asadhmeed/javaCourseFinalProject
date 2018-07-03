package com.asad.couponesController.administrator;

import java.util.List;

import com.asad.couponesController.RequestData;
import com.asad.couponesController.entitys.Company;
import com.asad.couponesController.entitys.Customer;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.ComponentNotFoundException;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.NameIsUsedException;
import com.asad.couponesController.exceptions.notLogedInException;

public interface AdministratorServices extends LogInService {

	
	public Company creatCompany( RequestData requestData) throws NameIsUsedException, notLogedInException ;
	public ResponseMassageEnum deleteCompany( RequestData requestData) throws ComponentNotFoundException, notLogedInException;
	public ResponseMassageEnum updateCompany(RequestData requestData) throws IdIsNullException, notLogedInException;
	public List<Company> listAllCompany(RequestData companyData) throws notLogedInException;
	public Company getCompanyById( RequestData companyRequestData) throws IdIsNullException, notLogedInException;
	
	public Customer creatCustomer( RequestData requestData) throws NameIsUsedException, notLogedInException;
	public ResponseMassageEnum deleteCustomer(  RequestData requestData)  throws ComponentNotFoundException, notLogedInException;
	List<Customer> listAllCustomers(RequestData adminData) throws notLogedInException;
	Customer getCustomerById(RequestData customerRequestData) throws IdIsNullException, notLogedInException;
	
}
