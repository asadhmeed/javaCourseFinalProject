package com.asad.couponesController.administrator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.autoconfigure.DevToolsProperties.Livereload;
import org.springframework.stereotype.Service;

import com.asad.couponesController.AppLogger;
import com.asad.couponesController.LogIn;
import com.asad.couponesController.LogInResponse;
import com.asad.couponesController.LoginIdGenerator;
import com.asad.couponesController.RequestData;
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
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.LogInDataIsNullException;
import com.asad.couponesController.exceptions.NameIsUsedException;
import com.asad.couponesController.exceptions.notLogedInException;

/**
 * @author asad
 *
 */
@Service
public class AdministratorServicesImpl implements AdministratorServices {

	private static List<Long> logInIds =new ArrayList<>();
	
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
	public LogInResponse logInCheck(LogIn logIn) throws LogInDataIsNullException {
    //TODO:remove logger  (admin service)
		AppLogger.getLogger().log(Level.INFO, logIn.toString());
		if (logIn != null) {
			
		
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
			throw new LogInDataIsNullException("admin data is null");
		
		}

	@Override
	public ResponseMassageEnum logout(Long id) throws IdIsNullException {
		// TODO add log out admin
		
		if(id != null) {
			for (Long id1 : logInIds) {
				if (id == id1) {
					logInIds.remove(id);
					return ResponseMassageEnum.LOGOUTSUCCESS;
				}
			}
		}else {
			throw new IdIsNullException("admin id is null ");
		}
		return ResponseMassageEnum.LOGOUTFAILED;
		
	}
	
	/* (non-Javadoc)
	 * @see com.asad.couponesController.administrator.AdministratorServices#creatCompany(com.asad.couponesController.entitys.Company)
	 */
	public Company creatCompany(RequestData requestData) throws NameIsUsedException {
		try {
			Set<Coupon> coupons= requestData.getCompany().getCoupons();	
			for (Coupon coupon : coupons) {
				couponDao.save(coupon);	
			}
			
			return companyDao.save(requestData.getCompany());
		} catch (Exception e) {

			throw new NameIsUsedException("The Name Of The Company Or Name  One Of The Coupons Is Already Used", e);

		}

	}

	/* (non-Javadoc)
	 * @see com.asad.couponesController.administrator.AdministratorServices#deleteCompany(com.asad.couponesController.entitys.Company)
	 */
	public ResponseMassageEnum deleteCompany(RequestData requestData) throws ComponentNotFoundException {
		try {
			companyDao.delete(requestData.getCompany());

			return ResponseMassageEnum.COMPANYDELETED;
		} catch (Exception e) {
			throw new ComponentNotFoundException("the company you want to delete does not exist", e);

		}
	}

	/* (non-Javadoc)
	 * @see com.asad.couponesController.administrator.AdministratorServices#updateCompany(com.asad.couponesController.entitys.Company)
	 */
	public ResponseMassageEnum updateCompany(RequestData requestData) throws IdIsNullException, notLogedInException {
		AppLogger.getLogger().log(Level.INFO , requestData.getCompany().getCoupons().toString());
		Long adminId= requestData.getClientId();
	if (requestData !=null && adminId !=null) {
		if (logInIds.contains(adminId)) {
			
		Company companyfromDataBase = companyDao.findCompanyByCompanyName(requestData.getCompany().getCompanyName());
		if (companyfromDataBase == null) {
			return ResponseMassageEnum.COMPANYNOTFOUND;
		} else {
//			Set<Coupon> coupons = requestData.getCompany().getCoupons();
//			Set<Coupon> existingCoupons = companyfromDataBase.getCoupons();
//			if (coupons != null) {
//			for (int i = 0; i < coupons.size(); i++) {
//				couponDao.save((Coupon)coupons.toArray()[i]);
//				existingCoupons.add((Coupon)coupons.toArray()[i]);
//			}
//			companyfromDataBase.setCoupons(existingCoupons);
			}
			companyDao.save(companyfromDataBase);
			return ResponseMassageEnum.THECOMPANYUPDATED;
	
		}
		throw new notLogedInException("please log in to do this task");
		
		}
	throw new IdIsNullException("admin id or request data is null");
	}

	/* (non-Javadoc)
	 * @see com.asad.couponesController.administrator.AdministratorServices#listAllCompany()
	 */
	public List<Company> listAllCompany() {

		//TODO:check if the client logged in by id 
		List<Company> companies =(List<Company>) companyDao.findAll();
		//TODO:remove Logger
		AppLogger.getLogger().log(Level.INFO, companies.get(0).getCoupons().toString());
		
		return companies;
	}

	
	public Company getCompanyById(RequestData companyRequestData) throws IdIsNullException,notLogedInException {
		try {
			if (logInIds.contains(companyRequestData.getClientId())) {
				
			
			return companyDao.findCompanyById(companyRequestData.getCustomer().getId());
			}else {
				throw new notLogedInException("please log in to do this task");
			}
		} catch (Exception e) {
			throw new IdIsNullException("some of the data you sent is not valid or null");
		}
		
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////

	// Customers------------------------------------------
	/* (non-Javadoc)
	 * @see com.asad.couponesController.administrator.AdministratorServices#creatCustomer(com.asad.couponesController.entitys.Customer)
	 */
	public Customer creatCustomer(RequestData customerRequestData) throws NameIsUsedException {
		//TODO:check if the client logged in by id 
		try {
			return this.customerDao.save(customerRequestData.getCustomer());
			
		}catch(Exception e) {
			throw new NameIsUsedException("The Name Of The Customer Is Allready Used", e);
		}
	}

	/**
	 * 
	 */
	public ResponseMassageEnum deleteCustomer(RequestData customerRequestData) throws ComponentNotFoundException // CustomerDeleted
	{
	//TODO:check if the client logged in by id 
		 
		try {
			customerDao.delete(customerRequestData.getCustomer());

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
	@Override
	public List<Customer> listAllCustomers() {

		return (List<Customer>) customerDao.findAll();
	}

	/**
	 * @param name
	 * @return
	 * @throws IdIsNullException 
	 */
	@Override
	public Customer getCustomerById(RequestData customerRequestData) throws IdIsNullException,notLogedInException {
		try {
			if (logInIds.contains(customerRequestData.getClientId())) {
				
			
			return customerDao.findCustomerById(customerRequestData.getCustomer().getId());
			}else {
				throw new notLogedInException("please log in to do this task");
			}
		} catch (Exception e) {
			throw new IdIsNullException("some of the data you sent is not valid or null");
		}
	}

	

	

	



	

}
