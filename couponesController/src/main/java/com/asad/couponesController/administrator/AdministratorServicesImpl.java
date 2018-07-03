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

	private static List<Long> logInIds = new ArrayList<>();

	// connection to the customers table in the data base
	@Autowired
	private CompanyRepository companyDao;

	// connection to the companies table in the data base
	@Autowired
	private CustomerRepository customerDao;

	// connection to the companies table in the data base
	@Autowired
	private CouponRepository couponDao;

	/*
	 * 
	 * @see
	 * com.asad.couponesController.administrator.AdministratorServices#logInCheck(
	 * com.asad.couponesController.LogIn)
	 */
	public LogInResponse logIn(LogIn logIn) throws LogInDataIsNullException {
		// TODO:remove logger (admin service)
		AppLogger.getLogger().log(Level.INFO, logIn.toString());
		try {

			
			if (logInIds.contains(logIn.getUserId())) {

				return new LogInResponse(LogInEnum.ALREADYLOGINEDIN);
			} else {
				if (logIn.getUserName().trim().equals("admin") && logIn.getPassword().trim().equals("1234")) {
					Long id = LoginIdGenerator.generateId();
					this.logInIds.add(id);
					return new LogInResponse(LogInEnum.LOGINSUCCESS, id);
				}
				return new LogInResponse(LogInEnum.LOGINFAILED);
			}
		} catch (NullPointerException e) {
			throw new LogInDataIsNullException("admin data is null or not valid");
		}

	}

	@Override
	public ResponseMassageEnum logout(Long id) throws IdIsNullException {

		try {
			for (Long id1 : logInIds) {
				if (id == id1) {
					logInIds.remove(id);
					return ResponseMassageEnum.LOGOUTSUCCESS;
				}
			}
		} catch (NullPointerException e) {
			throw new IdIsNullException("admin id is null");
		}
		return ResponseMassageEnum.LOGOUTFAILED;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asad.couponesController.administrator.AdministratorServices#creatCompany(
	 * com.asad.couponesController.entitys.Company)
	 */
	public Company creatCompany(RequestData companyData) throws NameIsUsedException, notLogedInException {
		logInCheck(companyData, "plase log in to do this task");
		try {
			if (companyData.getCompany().getCoupons() != null) {
				Set<Coupon> coupons = companyData.getCompany().getCoupons();
				for (Coupon coupon : coupons) {

					couponDao.save(coupon);
				}
			}

			return companyDao.save(companyData.getCompany());
		} catch (Exception e) {

			throw new NameIsUsedException("The Name Of The Company Or Name  One Of The Coupons Is Already Used", e);

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asad.couponesController.administrator.AdministratorServices#deleteCompany
	 * (com.asad.couponesController.entitys.Company)
	 */
	public ResponseMassageEnum deleteCompany(RequestData companyData)
			throws ComponentNotFoundException, notLogedInException {

		logInCheck(companyData, "plase log in to do this task");

		try {
			companyDao.delete(companyData.getCompany());

			return ResponseMassageEnum.COMPANYDELETED;
		} catch (Exception e) {
			throw new ComponentNotFoundException("the company you want to delete  does not exist", e);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asad.couponesController.administrator.AdministratorServices#updateCompany
	 * (com.asad.couponesController.entitys.Company)
	 */
	public ResponseMassageEnum updateCompany(RequestData companyData) throws notLogedInException {
		AppLogger.getLogger().log(Level.INFO, companyData.getCompany().getCoupons().toString());

		logInCheck(companyData, "plase log in to do this task");

		Company companyfromDataBase = companyDao.findCompanyByCompanyName(companyData.getCompany().getCompanyName());
		if (companyfromDataBase == null) {
			return ResponseMassageEnum.COMPANYNOTFOUND;
		} else {
			// Set<Coupon> coupons = requestData.getCompany().getCoupons();
			// Set<Coupon> existingCoupons = companyfromDataBase.getCoupons();
			// if (coupons != null) {
			// for (int i = 0; i < coupons.size(); i++) {
			// couponDao.save((Coupon)coupons.toArray()[i]);
			// existingCoupons.add((Coupon)coupons.toArray()[i]);
			// }
			// companyfromDataBase.setCoupons(existingCoupons);
		}
		companyDao.save(companyfromDataBase);
		return ResponseMassageEnum.THECOMPANYUPDATED;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asad.couponesController.administrator.AdministratorServices#
	 * listAllCompany()
	 */
	public List<Company> listAllCompany(RequestData companyData) throws notLogedInException {

		logInCheck(companyData, "plase log in to do this task");
		List<Company> companies = (List<Company>) companyDao.findAll();
		// TODO:remove Logger
		AppLogger.getLogger().log(Level.INFO, companies.get(0).getCoupons().toString());

		return companies;
	}

	public Company getCompanyById(RequestData companyData) throws IdIsNullException, notLogedInException {

		logInCheck(companyData, "plase log in to do this task");
		try {
			return companyDao.findCompanyById(companyData.getCustomer().getId());

		} catch (Exception e) {
			throw new IdIsNullException("some of the data you sent is not valid or null");
		}

	}

	/////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////

	// Customers------------------------------------------
	/**
	 * 
	 */
	public Customer creatCustomer(RequestData customerData) throws NameIsUsedException, notLogedInException {

		logInCheck(customerData, "plase log in to do this task");

		try {
			return this.customerDao.save(customerData.getCustomer());

		} catch (Exception e) {
			throw new NameIsUsedException("The Name Of The Customer Is Allready Used", e);
		}
	}

	/**
	 * @throws notLogedInException
	 * 
	 */
	public ResponseMassageEnum deleteCustomer(RequestData customerData) throws ComponentNotFoundException // CustomerDeleted
			, notLogedInException {

		logInCheck(customerData, "plase log in to do this task");

		try {
			Customer customer = customerDao.findCustomerByName(customerData.getCustomer().getName());
			customerDao.delete(customer);

			return ResponseMassageEnum.CUSTUMBERDELETED;
		} catch (Exception e) {
			throw new ComponentNotFoundException("the customer you want to delete does not exist", e);

		}
	}

	/**
	 * @param customer
	 * @return
	 * @throws notLogedInException
	 */
	public ResponseMassageEnum updateCustomer(RequestData customerData) throws notLogedInException // CustomerUpdated
	{
		logInCheck(customerData, "plase log in to do this task");

		Customer customerfromDataBase = customerDao.findCustomerByName(customerData.getCustomer().getName());
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
	 * @throws notLogedInException
	 */
	@Override
	public List<Customer> listAllCustomers(RequestData adminData) throws notLogedInException {
		logInCheck(adminData, "plase log in to do this task");

		return (List<Customer>) customerDao.findAll();
	}

	/**
	 * @param name
	 * @return
	 * @throws IdIsNullException
	 *             ,notLogedInException
	 */
	@Override
	public Customer getCustomerById(RequestData customerRequestData) throws IdIsNullException, notLogedInException {

		logInCheck(customerRequestData, "plase log in to do this task");
		try {
			return customerDao.findCustomerById(customerRequestData.getCustomer().getId());

		} catch (NullPointerException e) {
			throw new IdIsNullException("some of the data you sent is not valid or null");
		}
	}

	private void logInCheck(RequestData requestData, String textMessage) throws notLogedInException {
		Long id = requestData.getClientId();
		try {
			if (!logInIds.contains(id)) {
				throw new notLogedInException(textMessage);
			}
		} catch (NullPointerException e) {
			throw new notLogedInException("id cannot be null", e);
		}

	}

}
