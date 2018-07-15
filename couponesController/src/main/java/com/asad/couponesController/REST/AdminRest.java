package com.asad.couponesController.REST;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asad.couponesController.LogIn;
import com.asad.couponesController.LogInResponse;
import com.asad.couponesController.LoginIdGenerator;
import com.asad.couponesController.RequestData;
import com.asad.couponesController.Response;
import com.asad.couponesController.IncomeServices.IncomeServices;
import com.asad.couponesController.administrator.AdministratorServices;
import com.asad.couponesController.company.CompanyServices;
import com.asad.couponesController.customer.CustomerServices;
import com.asad.couponesController.entitys.Company;
import com.asad.couponesController.entitys.Customer;
import com.asad.couponesController.enums.LogInEnum;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.ComponentNotFoundException;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.LogInDataIsNullException;
import com.asad.couponesController.exceptions.NameIsUsedException;
import com.asad.couponesController.exceptions.RequestDataIsNullException;
import com.asad.couponesController.exceptions.NotLogedInException;

@RestController
@RequestMapping("/adminRest")

public class AdminRest implements CouponClaintREST {

	@Autowired
	private AdministratorServices admin;
	

	@PostMapping("/adminlogIn")
	@Override
	public synchronized Response logIn(@RequestBody LogIn logIn) throws LogInDataIsNullException, RequestDataIsNullException {
		return new Response(admin.logIn(logIn));
	}

	@PostMapping("/adminLogout")
	@Override
	public synchronized Response logout(@RequestBody RequestData IdData) throws IdIsNullException, RequestDataIsNullException, NotLogedInException {
		return new Response(admin.logout(IdData));
	}

	// Companies--------------------------------------------------
	@PostMapping("/creatCompany")
	public Response creatCompany(@RequestBody RequestData companyRequestData) throws NameIsUsedException // companyCreated
			, NotLogedInException, RequestDataIsNullException {
	return new Response(admin.creatCompany(companyRequestData));
		
	
	}

	@PostMapping("/deleteCompany")
	public Response deleteCompany(@RequestBody RequestData companyRequestData) throws ComponentNotFoundException // companyDeleted
			, NotLogedInException, RequestDataIsNullException {
		return new Response(admin.deleteCompany(companyRequestData));
	}

	@PostMapping("/updateCompany")
	public Response updateCompany(@RequestBody RequestData companyRequestData) throws IdIsNullException // companyUpdated
			, NotLogedInException, RequestDataIsNullException {
		return new Response(admin.updateCompany(companyRequestData));

	}

	@PostMapping("/listAllCompany")
	public Response listAllCompany(@RequestBody RequestData companyRequestData) throws NotLogedInException, RequestDataIsNullException {
		return new Response(admin.listAllCompany(companyRequestData));
	}

	@PostMapping("/getCompany")
	public Response getCompanyByName(@RequestBody RequestData companyRequestData)
			throws IdIsNullException, NotLogedInException, RequestDataIsNullException, ComponentNotFoundException {

		return new Response(admin.getCompanyById(companyRequestData));
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////

	// Customers------------------------------------------
	@PostMapping("/creatCustomer")
	public Response creatCustomer(@RequestBody RequestData customerRequestData) throws NameIsUsedException // CustomerCreated
			, NotLogedInException, RequestDataIsNullException {

		return new Response(admin.creatCustomer(customerRequestData));
	}

	@PostMapping("/deleteCustomer")
	public Response deleteCustomer(@RequestBody RequestData customerRequestData) throws ComponentNotFoundException // CustomerDeleted
			, NotLogedInException, RequestDataIsNullException {
		return new Response(admin.deleteCustomer(customerRequestData));

	}

	@PostMapping("/updateCustomer")
	public Response updateCustomer(@RequestBody RequestData customerRequestData) throws ComponentNotFoundException // CustomerUpdated
			, NotLogedInException, RequestDataIsNullException {

		return new Response(admin.updateCustomer(customerRequestData));
	}

	@PostMapping("/listAllCustomers")
	public Response listAllCustomers(@RequestBody RequestData adminData) throws NotLogedInException, RequestDataIsNullException {

		return new Response(admin.listAllCustomers(adminData));
	}

	@PostMapping("/getCustomer")
	public Response getCustomerById(@RequestBody RequestData customerRequestData)
			throws IdIsNullException, NotLogedInException, RequestDataIsNullException, ComponentNotFoundException {

		return new Response(admin.getCustomerById(customerRequestData));
	}

}
