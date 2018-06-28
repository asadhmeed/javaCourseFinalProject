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
import com.asad.couponesController.Response;
import com.asad.couponesController.administrator.AdministratorServices;
import com.asad.couponesController.entitys.Company;
import com.asad.couponesController.entitys.Customer;
import com.asad.couponesController.enums.LogInEnum;
import com.asad.couponesController.exceptions.ComponentNotFoundException;
import com.asad.couponesController.exceptions.NameIsUsedException;
@RestController
@RequestMapping("/adminRest")

public class AdminRest implements CouponClaintREST 
{
    
	
	
	@Autowired
	private AdministratorServices admin;
	
	@PostMapping("/logIn")
	@Override
	public synchronized LogInResponse logIn(@RequestBody LogIn logIn) 
	{
			return admin.logInCheck(logIn);
	}
	
//Companies--------------------------------------------------
	@PostMapping("/creatCompany")
	public Response creatCompany(@RequestBody Company company) throws NameIsUsedException //companyCreated
	{

			return new Response(admin.creatCompany(company));

		
	}
	
	
	@PostMapping("/deleteCompany")
	public Response deleteCompany(@RequestBody Company company) throws ComponentNotFoundException //companyDeleted
	{ 
			return new Response(admin.deleteCompany(company));
	}
	@PostMapping("/updateCompany")
	public Response updateCompany(@RequestBody Company company) //companyUpdated
	{
		return new Response(admin.updateCompany(company));
		
	}
	
	@GetMapping("/listAllCompany")
	public Response listAllCompany() 
	{
		return new Response(admin.listAllCompany());
	}
	@GetMapping("/getCompany")
	public Company getCompanyByName(@RequestBody String name) 
	{
		
		return null;
	}
	
/////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////

	
	
//Customers------------------------------------------
	@PostMapping("/creatCustomer")
	public boolean creatCustomer(@RequestBody Customer customer) //CustomerCreated
	{
		
		return false;
	}
	
	
	@PostMapping("/deleteCustomer")
	public boolean deleteCustomer( @RequestBody Customer customer) //CustomerDeleted
	{
		return false;
		
	}
	
	@PostMapping("/updateCustomer")
	public boolean updateCustomer() //CustomerUpdated
	{
		
		return false;
	}
		
	@GetMapping("/listAllCustomers")
	public List<Customer> listAllCustomers() 
	{
		
		return null;
	}
	@GetMapping("/getCustomer")
	public Customer getCustomerByName(@RequestBody String name) 
	{
		
		return null;
	}
		
	
	
	
	
	
	
}
