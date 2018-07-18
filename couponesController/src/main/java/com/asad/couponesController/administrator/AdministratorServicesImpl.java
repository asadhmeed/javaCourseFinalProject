package com.asad.couponesController.administrator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asad.couponesController.AppLogger;
import com.asad.couponesController.CheckClientRequest;
import com.asad.couponesController.LogIn;
import com.asad.couponesController.LogInResponse;
import com.asad.couponesController.LoginIdGenerator;
import com.asad.couponesController.NullCheck;
import com.asad.couponesController.RequestData;
import com.asad.couponesController.IncomeServices.IncomeServices;
import com.asad.couponesController.company.CompanyRepository;
import com.asad.couponesController.coupons.CouponRepository;
import com.asad.couponesController.customer.CustomerRepository;
import com.asad.couponesController.entitys.Company;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.entitys.Customer;
import com.asad.couponesController.entitys.Income;
import com.asad.couponesController.enums.IncomeType;
import com.asad.couponesController.enums.LogInEnum;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.ComponentNotFoundException;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.LogInDataIsNullException;
import com.asad.couponesController.exceptions.NameIsUsedException;
import com.asad.couponesController.exceptions.NotLogedInException;
import com.asad.couponesController.exceptions.RequestDataIsNullException;

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

	@Autowired
	private IncomeServices incomeServices;

	/*
	 * 
	 * @see
	 * com.asad.couponesController.administrator.AdministratorServices#logInCheck(
	 * com.asad.couponesController.LogIn)
	 */
	public synchronized LogInResponse logIn(LogIn logIn) throws LogInDataIsNullException, RequestDataIsNullException {
		// TODO:remove logger (admin service)
		AppLogger.getLogger().log(Level.INFO, logIn.toString());
		new CheckClientRequest().checkLogIn(logIn);

		if (logIn.getUserId() != null && logInIds.contains(logIn.getUserId())) {

			return new LogInResponse(LogInEnum.ALREADYLOGINEDIN);
		} else {

			if (logIn.getUserId() != null) {
				return new LogInResponse(LogInEnum.USERIDISNOTVALID);
			}
			if (logIn.getUserName().trim().equals("admin") && logIn.getPassword().trim().equals("1234")) {
				if (this.logInIds.size() == 0) {
					Long id = LoginIdGenerator.generateId();
					this.logInIds.add(id);
					return new LogInResponse(LogInEnum.LOGINSUCCESS, id);
				}
			}
			return new LogInResponse(LogInEnum.LOGINFAILED);
		}

	}

	@Override
	public synchronized ResponseMassageEnum logout(RequestData idData)
			throws RequestDataIsNullException, NotLogedInException {
		logInCheck(idData);
		NullCheck.checkIfItIsNull(idData, "admin id is null");

		for (Long id1 : logInIds) {
			if (idData.getClientId() == id1) {
				logInIds.remove(idData.getClientId());
				return ResponseMassageEnum.LOGOUTSUCCESS;
			}
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
	public Company creatCompany(RequestData companyData)
			throws NameIsUsedException, NotLogedInException, RequestDataIsNullException {
		new CheckClientRequest().checkCompany(companyData);
		logInCheck(companyData);
		try {
			if (companyData.getCustomer().getCoupons() != null) {
				Set<Coupon> coupons = companyData.getCustomer().getCoupons();
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
			throws ComponentNotFoundException, NotLogedInException, RequestDataIsNullException {
		NullCheck.checkIfItIsNull(companyData, "you cannot send empty request");
		NullCheck.checkIfItIsNull(companyData.getCustomer(), "there is no data for the company you want to delete");

		logInCheck(companyData);

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

	public ResponseMassageEnum updateCompany(RequestData companyData)
			throws NotLogedInException, RequestDataIsNullException {
		AppLogger.getLogger().log(Level.INFO, companyData.getCompany().toString());
		logInCheck(companyData);
		new CheckClientRequest().checkCompany(companyData);

		Company companyfromDataBase = companyDao.findCompanyById(companyData.getCompany().getId());
		if (companyfromDataBase == null) {
			return ResponseMassageEnum.COMPANYNOTFOUND;
		} else {
			if (!companyData.getCompany().getCompanyName().trim().equals(companyfromDataBase.getCompanyName().trim())) {
				throw new RequestDataIsNullException(ResponseMassageEnum.COMPANYNAMEMOSTNOTCHANGE.toString());
			}
			// Set<Coupon> coupons = requestData.getCompany().getCoupons();
			// Set<Coupon> existingCoupons = companyfromDataBase.getCoupons();
			// if (coupons != null) {
			/// *---for (int i = 0; i < coupons.size(); i++) {
			// couponDao.save((Coupon)coupons.toArray()[i]);
			// existingCoupons.add((Coupon)coupons.toArray()[i]);
			// }
			// companyfromDataBase.setCoupons(existingCoupons);
		}
		companyDao.save(companyData.getCompany());
		return ResponseMassageEnum.THECOMPANYUPDATED;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asad.couponesController.administrator.AdministratorServices#
	 * listAllCompany()
	 */
	public List<Company> listAllCompany(RequestData companyData)
			throws NotLogedInException, RequestDataIsNullException {
		logInCheck(companyData);
		List<Company> companies = (List<Company>) companyDao.findAll();
		// TODO:remove Logger
		AppLogger.getLogger().log(Level.INFO, companies.get(0).getCoupons().toString());

		return companies;
	}

	public Company getCompanyById(RequestData companyData)
			throws IdIsNullException, NotLogedInException, ComponentNotFoundException, RequestDataIsNullException {
		logInCheck(companyData);
		NullCheck.checkIfItIsNull(companyData.getCustomer(), "you cannot send empty request");
		NullCheck.checkIfItIsNull(companyData.getCustomer().getId(), "the Company id you want to delete is empty");

		Company company = companyDao.findCompanyById(companyData.getCustomer().getId());
		if (company == null) {
			throw new ComponentNotFoundException(ResponseMassageEnum.COMPANYNOTFOUND.toString());
		}
		return company;

	}

	/////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////

	// Customers------------------------------------------
	/**
	 * @throws RequestDataIsNullException
	 * 
	 */
	public Customer creatCustomer(RequestData customerData)
			throws NameIsUsedException, NotLogedInException, RequestDataIsNullException {

		logInCheck(customerData);
		new CheckClientRequest().checkCustomer(customerData);

		try {
			return this.customerDao.save(customerData.getCustomer());

		} catch (Exception e) {
			throw new NameIsUsedException("The Name Of The Customer Is Allready Used", e);
		}
	}

	/**
	 * @throws NotLogedInException
	 * @throws RequestDataIsNullException
	 * 
	 */
	public ResponseMassageEnum deleteCustomer(RequestData customerData) throws ComponentNotFoundException // CustomerDeleted
			, NotLogedInException, RequestDataIsNullException {
		logInCheck(customerData);
		NullCheck.checkIfItIsNull(customerData.getCustomer(), "the customer you want to delete is empty");
		NullCheck.checkIfItIsNull(customerData.getCustomer().getId(), "the customer id you want to delete is empty");

		try {
			Customer customer = customerDao.findCustomerById(customerData.getCustomer().getId());
			customerDao.delete(customer);

			return ResponseMassageEnum.CUSTUMBERDELETED;
		} catch (Exception e) {
			throw new ComponentNotFoundException("the customer you want to delete does not exist", e);

		}
	}

	/**
	 * @param customer
	 * @return
	 * @throws NotLogedInException
	 * @throws RequestDataIsNullException
	 */
	public ResponseMassageEnum updateCustomer(RequestData customerData) throws NotLogedInException // CustomerUpdated
			, RequestDataIsNullException {
		logInCheck(customerData);
		new CheckClientRequest().checkCustomer(customerData);

		Customer customerfromDataBase = customerDao.findCustomerById(customerData.getCustomer().getId());
		boolean customerFound = customerfromDataBase != null;
		if (!customerFound) {
			return ResponseMassageEnum.CUSTOMERNOTFOUND;
		} else {
			customerDao.save(customerData.getCustomer());
			return ResponseMassageEnum.THECUSTOMERUPDATED;
		}
	}

	/**
	 * @return
	 * @throws NotLogedInException
	 * @throws RequestDataIsNullException
	 */
	@Override
	public List<Customer> listAllCustomers(RequestData adminData)
			throws NotLogedInException, RequestDataIsNullException {
		logInCheck(adminData);

		return (List<Customer>) customerDao.findAll();
	}

	/**
	 * @param name
	 * @return
	 * @throws IdIsNullException
	 *             ,notLogedInException
	 * @throws RequestDataIsNullException
	 * @throws ComponentNotFoundException
	 */
	@Override
	public Customer getCustomerById(RequestData customerRequestData)
			throws IdIsNullException, NotLogedInException, RequestDataIsNullException, ComponentNotFoundException {
		logInCheck(customerRequestData);
		NullCheck.checkIfItIsNull(customerRequestData.getCustomer(), "customer is empty");
		NullCheck.checkIfItIsNull(customerRequestData.getCustomer().getId(),
				"the Customer id you want to delete is empty");

		Customer customer = customerDao.findCustomerById(customerRequestData.getCustomer().getId());
		if (customer == null) {
			throw new ComponentNotFoundException(ResponseMassageEnum.CUSTOMERNOTFOUND.toString());
		}
		return customer;
	}

	private void logInCheck(RequestData requestData) throws NotLogedInException, RequestDataIsNullException {
		NullCheck.checkIfItIsNull(requestData, "you cannot send empty request");

		Long id = requestData.getClientId();
		try {
			if (!logInIds.contains(id)) {
				throw new NotLogedInException(LogInEnum.NOTLOGEDIN.toString());
			}
		} catch (NullPointerException e) {
			throw new NotLogedInException("id cannot be null", e);
		}

	}

	@Override
	public String getClientName(Long clientId) {

		return "admin";
	}

	@Override
	public List<Income> viewSpesificCompanyIncome(RequestData companyData)
			throws NotLogedInException, RequestDataIsNullException {
		logInCheck(companyData);
		NullCheck.checkIfItIsNull(companyData.getCompany(), "company is empty");
		NullCheck.checkIfItIsNull(companyData.getCompany().getId(), "there is no company id in the request");
		Company company = companyDao.findCompanyById(companyData.getCompany().getId());
		NullCheck.checkIfItIsNull(company, ResponseMassageEnum.COMPANYNOTFOUND.toString());
		if (!company.getCompanyName().trim().equals(companyData.getCompany().getCompanyName())) {
			throw new RequestDataIsNullException(ResponseMassageEnum.COMPANYNAMEMOSTNOTCHANGE.toString());
		}
		List<Income> companyIncomes = incomeServices.viewIncomeByClientNameAndIncomeType(
				companyData.getCompany().getCompanyName(), IncomeType.COMPANY_NEW_COUPON);
		companyIncomes.addAll(incomeServices.viewIncomeByClientNameAndIncomeType(
				companyData.getCompany().getCompanyName(), IncomeType.COMPANY_UPDATE_COUPON));

		return companyIncomes;
	}

	@Override
	public List<Income> viewSpesificCustomerIncome(RequestData customerData)
			throws NotLogedInException, RequestDataIsNullException {
		logInCheck(customerData);
		NullCheck.checkIfItIsNull(customerData.getCustomer(), "customer is empty");
		NullCheck.checkIfItIsNull(customerData.getCustomer().getId(), "there is no customer id in the request");
		Customer customer = customerDao.findCustomerById(customerData.getCustomer().getId());
		NullCheck.checkIfItIsNull(customer, ResponseMassageEnum.CUSTOMERNOTFOUND.toString());
		if (!customer.getName().trim().equals(customerData.getCustomer().getName().trim())) {
			throw new RequestDataIsNullException(ResponseMassageEnum.CUSTOMERNAMEMOSTNOTCHANGE.toString());
		}
		List<Income> companyIncomes = incomeServices.viewIncomeByClientNameAndIncomeType(
				customerData.getCustomer().getName(), IncomeType.CUSTOMER_PURCHASE);
		return companyIncomes;
	}

	@Override
	public List<Income> viewAllIncome(RequestData requestData) throws NotLogedInException, RequestDataIsNullException {
		logInCheck(requestData);
		return incomeServices.viewAllIncome();

	}

}
