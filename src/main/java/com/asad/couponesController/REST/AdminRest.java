package com.asad.couponesController.REST;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asad.couponesController.LogIn;
import com.asad.couponesController.entitys.Company;
import com.asad.couponesController.entitys.Customer;
@RequestMapping("/adminRest")
@RestController
public class AdminRest implements CouponClaintREST 
{

	
	@PostMapping("/logIn")
	@Override
	public boolean logIn(@RequestBody LogIn logIn) //logInSuccess
							//logInFailed
	{
		
		return false;
	}
	
//Companies--------------------------------------------------
	@PostMapping("/creatCompany")
	public boolean creatCompany(@RequestBody Company company) //companyCreated
	{
		return false;
		
	}
	
	
	@PostMapping("/deleteCompany")
	public boolean deleteCompany(@RequestBody Company company) //companyDeleted
	{
		return false;
		
	}
	@PostMapping("/updateCompany")
	public boolean updateCompany(@RequestBody Company company) //companyUpdated
	{
		return false;
		
	}
	
	@GetMapping("/listAllCompany")
	public List<Company> listAllCompany() 
	{
		
		return null;
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
