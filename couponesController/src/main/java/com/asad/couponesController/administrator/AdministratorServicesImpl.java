package com.asad.couponesController.administrator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.autoconfigure.DevToolsProperties.Livereload;
import org.springframework.stereotype.Service;

import com.asad.couponesController.AppLogger;
import com.asad.couponesController.LogIn;
import com.asad.couponesController.LogInResponse;
import com.asad.couponesController.LoginIdGenerator;
import com.asad.couponesController.Response;
import com.asad.couponesController.company.CompanyRepository;
import com.asad.couponesController.coupons.CouponRepository;
import com.asad.couponesController.coupons.CouponServices;
import com.asad.couponesController.customer.CustomerRepository;
import com.asad.couponesController.entitys.Company;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.entitys.Customer;
import com.asad.couponesController.enums.LogInEnum;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.ComponentNotFoundException;
import com.asad.couponesController.exceptions.NameIsUsedException;

/**
 * @author asad
 *
 */
@Service
public class AdministratorServicesImpl implements AdministratorServices {

	private List<Long> logInIds =new ArrayList<>();
	
//connection to the customers table in the data base
	@Autowired
	private CompanyRepository companyDao;

//connection to the companies table in the data base
	@Autowired
	private CustomerRepository customerDao;
	
//connection to the companies table in the data base
	@Autowired
	private CouponRepository couponDao;

	
	

	/* 
	 * 
	 * @see com.asad.couponesController.administrator.AdministratorServices#logInCheck(com.asad.couponesController.LogIn)
	 */
	public LogInResponse logInCheck(LogIn logIn) {
    //TODO:remove logger  (admin service)
		AppLogger.getLogger().log(Level.INFO, logIn.toString());
		
		if (logIn.getUserId()!= null) {
			return new LogInResponse(LogInEnum.ALREADYLOGINEDIN);
		}else {
		if (logIn.getUserName().trim().equals("admin") && logIn.getPassword().trim().equals("1234")) {
			Long id = LoginIdGenerator.generateId();
			this.logInIds.add(id);
			return new LogInResponse(LogInEnum.LOGINSUCCESS,id) ;
		}
		return new LogInResponse(LogInEnum.LOGINFAILED)  ;
	}
		}

	/* (non-Javadoc)
	 * @see com.asad.couponesController.administrator.AdministratorServices#creatCompany(com.asad.couponesController.entitys.Company)
	 */
	public Company creatCompany(Company company) throws NameIsUsedException {
		try {
			Set<Coupon> coupons= company.getCoupons();	
			for (Coupon coupon : coupons) {
				couponDao.save(coupon);	
			}
			
			return companyDao.save(company);
		} catch (Exception e) {

			throw new NameIsUsedException("The Name Of The Company Or Name  One Of The Coupons Is Already Used", e);

		}

	}

	/* (non-Javadoc)
	 * @see com.asad.couponesController.administrator.AdministratorServices#deleteCompany(com.asad.couponesController.entitys.Company)
	 */
	public ResponseMassageEnum deleteCompany(Company company) throws ComponentNotFoundException {
		try {
			companyDao.delete(company);

			return ResponseMassageEnum.COMPANYDELETED;
		} catch (Exception e) {
			throw new ComponentNotFoundException("the company you want to delete does not exist", e);

		}
	}

	/* (non-Javadoc)
	 * @see com.asad.couponesController.administrator.AdministratorServices#updateCompany(com.asad.couponesController.entitys.Company)
	 */
	public ResponseMassageEnum updateCompany(Company company) {
		AppLogger.getLogger().log(Level.INFO , company.getCoupons().toString());
		
		// companyDao.updateCompany(company);
		Company companyfromDataBase = companyDao.findCompanyByCompanyName(company.getCompanyName());
		if (!(companyfromDataBase != null)) {
			return ResponseMassageEnum.COMPANYNOTFOUND;
		} else {
			Set<Coupon> coupons = company.getCoupons();
			Set<Coupon> existingCoupons = companyfromDataBase.getCoupons();
			if (coupons != null) {
			for (int i = 0; i < coupons.size(); i++) {
				couponDao.save((Coupon)coupons.toArray()[i]);
				existingCoupons.add((Coupon)coupons.toArray()[i]);
			}
			}
			companyDao.save(companyfromDataBase);
			return ResponseMassageEnum.THECOMPANYUPDATED;
		}

	}

	/* (non-Javadoc)
	 * @see com.asad.couponesController.administrator.AdministratorServices#listAllCompany()
	 */
	public List<Company> listAllCompany() {


		List<Company> companies =(List<Company>) companyDao.findAll();
		//TODO:remove Logger
		AppLogger.getLogger().log(Level.INFO, companies.get(0).getCoupons().toString());
		
		return companies;
	}

	/* (non-Javadoc)
	 * @see com.asad.couponesController.administrator.AdministratorServices#getCompanyByName(java.lang.String)
	 */
	public Company getCompanyById(Long id) {

		return companyDao.findCompanyById(id);
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////

	// Customers------------------------------------------
	/* (non-Javadoc)
	 * @see com.asad.couponesController.administrator.AdministratorServices#creatCustomer(com.asad.couponesController.entitys.Customer)
	 */
	public Customer creatCustomer(Customer customer) throws NameIsUsedException {

		try {
			return this.customerDao.save(customer);
			
		}catch(Exception e) {
			throw new NameIsUsedException("The Name Of The Customer Is Allready Used", e);
		}
	}

	/**
	 * 
	 */
	public ResponseMassageEnum deleteCustomer(Customer customer) throws ComponentNotFoundException // CustomerDeleted
	{
		 
		try {
			customerDao.delete(customer);

			return ResponseMassageEnum.CUSTUMBERDELETED;
		} catch (Exception e) {
			throw new ComponentNotFoundException("the customer you want to delete does not exist", e);

		}
	}

	/**
	 * @param customer
	 * @return
	 */
	public ResponseMassageEnum updateCustomer(Customer customer) // CustomerUpdated
	{
		Customer customerfromDataBase = customerDao.findCustomerByName(customer.getName());
		boolean customerFound = customerfromDataBase != null;
		if (!customerFound) {
			return ResponseMassageEnum.CUSTOMERNOTFOUND;
		} else {
			customerDao.save(customerfromDataBase);
			return ResponseMassageEnum.THECUSTOMERUPDATED;
		}
	}

	/**
	 * @return
	 */
	public List<Customer> listAllCustomers() {

		return (List<Customer>) customerDao.findAll();
	}

	/**
	 * @param name
	 * @return
	 */
	public Customer getCustomerById(Long id) {

		return customerDao.findCustomerById(id);
	}

}
