package com.asad.couponesController.administrator;

import java.util.List;

import com.asad.couponesController.LogIn;
import com.asad.couponesController.LogInResponse;
import com.asad.couponesController.entitys.Company;
import com.asad.couponesController.entitys.Customer;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.ComponentNotFoundException;
import com.asad.couponesController.exceptions.NameIsUsedException;

public interface AdministratorServices {

	public LogInResponse logInCheck(LogIn logIn);
	public Company creatCompany( Company company) throws NameIsUsedException ;
	public ResponseMassageEnum deleteCompany( Company company) throws ComponentNotFoundException;
	public ResponseMassageEnum updateCompany( Company company);
	public List<Company> listAllCompany();
	public Company getCompanyById( Long id);
	
	public Customer creatCustomer( Customer customer) throws NameIsUsedException;
	public ResponseMassageEnum deleteCustomer(  Customer customer)  throws ComponentNotFoundException;
	
}
