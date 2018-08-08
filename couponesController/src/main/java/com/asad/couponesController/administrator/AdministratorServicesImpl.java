package com.asad.couponesController.administrator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperationsExtensionsKt;

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

	// save the log in authorization number of the logged in Administrator
	private static List<Long> logInIds = new ArrayList<>();

	// connection to the customers table in the data base
	@Autowired
	private CompanyRepository companyDao;

	// connection to the companies table in the data base
	@Autowired
	private CustomerRepository customerDao;

	// connection to the coupons table in the data base
	@Autowired
	private CouponRepository couponDao;

	// using the income service (get add the companies and the customers paid
	// activities )
	@Autowired
	private IncomeServices incomeServices;

	/**
	 * administrator log in method
	 * 
	 * @param logIn
	 *            :takes user name and password
	 * @return logInResponse (if the userName and password valid return LOGINSUCCESS
	 *         and number for further validation if the user already log in returns
	 *         ALREADYLOGINEDIN
	 * @throws LogInDataIsNullException
	 * @throws RequestDataIsNullException
	 */
	public synchronized LogInResponse logIn(LogIn logIn) throws LogInDataIsNullException, RequestDataIsNullException {
		
		new CheckClientRequest().checkLogIn(logIn);
		AppLogger.getLogger().log(Level.INFO, "client request Checked");

		if (logIn.getUserId() != null && logInIds.contains(logIn.getUserId())) {

			return new LogInResponse(LogInEnum.ALREADYLOGGEDIN);
		} else {
			

			if (logIn.getUserId() != null) {
				return new LogInResponse(LogInEnum.USERIDISNOTVALID);
			}

			if (logIn.getUserName().trim().equals("admin") && logIn.getPassword().trim().equals("1234")) {

				Long id = LoginIdGenerator.generateId();
				this.logInIds.add(id);
				AppLogger.getLogger().log(Level.INFO, "client id is " + id);

				return new LogInResponse(LogInEnum.LOGINSUCCESS, id);

			}

			return new LogInResponse(LogInEnum.LOGINFAILED);
		}

	}

	/**
	 * administrator log out
	 * 
	 * @param IdData
	 *            :takes authorization number (clientId inside a RequestData)
	 * @return LOGOUTSUCCESS if the authorization number is in the server memory
	 *         (the logInIds) else return LOGOUTFAILED
	 * @throws RequestDataIsNullException
	 * @throws IdIsNullException
	 * @throws NotLogedInException
	 */
	@Override
	public synchronized ResponseMassageEnum logout(RequestData idData)
			throws RequestDataIsNullException, NotLogedInException {
		logInCheck(idData);
		NullCheck.checkIfItIsNull(idData, "admin id is null");

		for (Long id1 : logInIds) {
			if ((long) idData.getClientId() == id1) {
				logInIds.remove((long) idData.getClientId());
				AppLogger.getLogger().log(Level.INFO, idData.getClientId() + "");

				AppLogger.getLogger().log(Level.INFO, this.logInIds.toString());

				return ResponseMassageEnum.LOGOUTSUCCESS;
			}
		}

		return ResponseMassageEnum.LOGOUTFAILED;

	}

	/**
	 * Create a new company
	 * 
	 * @param requestData:
	 *            takes Company data and client id in RequestData
	 * @return COMPANYCREATED if the company successfully created
	 * @throws NameIsUsedException
	 * @throws NotLogedInException
	 * @throws RequestDataIsNullException
	 */
	public ResponseMassageEnum creatCompany(RequestData companyData)
			throws NameIsUsedException, NotLogedInException, RequestDataIsNullException {
		new CheckClientRequest().checkCompany(companyData);
		logInCheck(companyData);
		try {
			companyDao.save(companyData.getCompany());

			return ResponseMassageEnum.COMPANYCREATED;
		} catch (Exception e) {

			throw new NameIsUsedException(ResponseMassageEnum.COMPANYNAMEISUSED.toString(), e);

		}

	}

	/**
	 * delete a company from the data base
	 * 
	 * @param requestData
	 *            : takes a company data and client id(for authorization) in
	 *            RequestData
	 * @return returns COMPANYDELETED if the company fund in the data base and
	 *         deleted else throws an exception and return COMPANYNOTFOUND
	 * @throws ComponentNotFoundException
	 * @throws NotLogedInException
	 * @throws RequestDataIsNullException
	 */
	public ResponseMassageEnum deleteCompany(RequestData companyData)
			throws ComponentNotFoundException, NotLogedInException, RequestDataIsNullException {
		NullCheck.checkIfItIsNull(companyData, "you cannot send empty request");
		NullCheck.checkIfItIsNull(companyData.getCompany(), "there is no data for the company you want to delete");

		logInCheck(companyData);

		try {
			companyDao.delete(companyData.getCompany());

			return ResponseMassageEnum.COMPANYDELETED;
		} catch (Exception e) {
			throw new ComponentNotFoundException(ResponseMassageEnum.COMPANYNOTFOUND.toString(), e);

		}
	}

	/**
	 * update the a company in the data base which has the same id
	 * 
	 * @param requestData
	 *            : takes a company data and client id(for authorization) in
	 *            RequestData
	 * @return THECOMPANYUPDATED if the company fund in the data base and updated
	 *         else if the company not fund in the data base return COMPANYNOTFOUND
	 *         else if the company name is not the same as the company in the data
	 *         base with the same id
	 * @throws IdIsNullException
	 * @throws NotLogedInException
	 * @throws RequestDataIsNullException
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

		}
		companyDao.save(companyData.getCompany());
		return ResponseMassageEnum.THECOMPANYUPDATED;

	}

	/**
	 * lists all companies from the data base
	 * 
	 * @param companyData
	 *            : takes client id in RequestData
	 * @return list of all companies if the client id correct
	 * @throws NotLogedInException
	 * @throws RequestDataIsNullException
	 */
	public List<Company> listAllCompany(RequestData companyData)
			throws NotLogedInException, RequestDataIsNullException {
		logInCheck(companyData);
		List<Company> companies = (List<Company>) companyDao.findAll();

		return companies;
	}

	/**
	 * get company by id from the data base
	 * 
	 * @param companyRequestData
	 *            : takes a company data (minimum id data) and client id(for
	 *            authorization) in RequestData
	 * @return COMPANYNOTFOUND if the company id in the request data dose not match
	 *         any of the companies in the data base
	 * @throws IdIsNullException
	 * @throws NotLogedInException
	 * @throws RequestDataIsNullException
	 * @throws ComponentNotFoundException
	 */

	public Company getCompanyById(RequestData companyData)
			throws IdIsNullException, NotLogedInException, ComponentNotFoundException, RequestDataIsNullException {
		logInCheck(companyData);
		NullCheck.checkIfItIsNull(companyData.getCompany(), "you cannot send empty request");
		NullCheck.checkIfItIsNull(companyData.getCompany().getId(), "the Company id you want to delete is empty");

		Company company = companyDao.findCompanyById(companyData.getCustomer().getId());
		if (company == null) {
			throw new ComponentNotFoundException(ResponseMassageEnum.COMPANYNOTFOUND.toString());
		}
		return company;

	}

	/**
	 * Create a new customer
	 * 
	 * @param requestData
	 *            : takes customer data and client id (for authorization) in
	 *            RequestData
	 * @return CUSTOMERCREATED if the customer successfully created
	 * @throws NameIsUsedException
	 * @throws NotLogedInException
	 * @throws RequestDataIsNullException
	 */
	public ResponseMassageEnum creatCustomer(RequestData customerData)
			throws NameIsUsedException, NotLogedInException, RequestDataIsNullException {

		logInCheck(customerData);
		new CheckClientRequest().checkCustomer(customerData);

		try {

			this.customerDao.save(customerData.getCustomer());
			return ResponseMassageEnum.CUSTOMERCREATED;

		} catch (Exception e) {
			throw new NameIsUsedException(ResponseMassageEnum.CUSTOMERNAMEISUSED.toString(), e);
		}
	}

	/**
	 * delete a customer from the data base
	 * 
	 * @param requestData
	 *            : takes a customer data and client id(for authorization) in
	 *            RequestData
	 * @return CUSTUMERDELETED if the customer is fund in the data base and deleted
	 * @throws ComponentNotFoundException
	 *             and return as a massage CUSTOMERNOTFOUND
	 * @throws NotLogedInException
	 * @throws RequestDataIsNullException
	 */
	public ResponseMassageEnum deleteCustomer(RequestData customerData) throws ComponentNotFoundException // CustomerDeleted
			, NotLogedInException, RequestDataIsNullException {
		logInCheck(customerData);
		NullCheck.checkIfItIsNull(customerData.getCustomer(), "the customer you want to delete is empty");
		NullCheck.checkIfItIsNull(customerData.getCustomer().getId(), "the customer id you want to delete is empty");

		try {
			Customer customer = customerDao.findCustomerById(customerData.getCustomer().getId());
			customerDao.delete(customer);

			return ResponseMassageEnum.CUSTUMERDELETED;
		} catch (Exception e) {
			throw new ComponentNotFoundException(ResponseMassageEnum.CUSTOMERNOTFOUND.toString(), e);

		}
	}

	/**
	 * update customer data in the data base
	 * 
	 * @param customerData
	 *            : takes customer data and client id( for authorization) in
	 *            RequestData
	 * @return THECUSTOMERUPDATED if the customer is fund in the data base and
	 *         updated else return CUSTOMERNOTFOUND
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
	 * lists all the customers in the data base
	 * 
	 * @param adminData
	 *            : takes client id(for authorization)
	 * @return list of all customers
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
	 * get customer by id
	 * 
	 * @param customerRequestData
	 *            : takes customer data minimum id and client id in RequestData
	 * 
	 * @return customer data from the data base if its found else return
	 *         CUSTOMERNOTFOUND massage
	 * @throws IdIsNullException
	 *             ,notLogedInException
	 * @throws RequestDataIsNullException
	 * @throws ComponentNotFoundException
	 *             : with massage CUSTOMERNOTFOUND
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

	// an eternal method that checks if the request sender is authorized
	private void logInCheck(RequestData requestData) throws NotLogedInException, RequestDataIsNullException {
		NullCheck.checkIfItIsNull(requestData, "you cannot send empty request");
		NullCheck.checkIfItIsNull(requestData.getClientId(), "log in error you need to log in");

		Long id = (long) requestData.getClientId();
		try {
			if (!logInIds.contains(id)) {
				throw new NotLogedInException(LogInEnum.NOTLOGEDIN.toString());
			}
		} catch (NullPointerException e) {
			throw new NotLogedInException("id cannot be null", e);
		}

	}

	/**
	 * get the client name
	 * 
	 * @param clientId
	 *            : takes client id
	 * @return client name
	 * 
	 */
	@Override
	public String getClientName(Long clientId) {

		return "admin";
	}

	/**
	 * view specific company income
	 * 
	 * @param companyData
	 *            : takes company data and client id( for authorization) in
	 *            RequestData
	 * @return list of the company income history
	 * @throws NotLogedInException
	 *             : if the client id is not valid
	 * @throws RequestDataIsNullException
	 *             : if the data of the company is not valid
	 */

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

	/**
	 * view specific customer income
	 * 
	 * @param companyData
	 *            : takes customer data and client id( for authorization) in
	 *            RequestData
	 * @return list of the customer income history
	 * @throws NotLogedInException
	 *             : if the client id is not valid
	 * @throws RequestDataIsNullException
	 *             : if the data of the customer is not valid
	 */
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

	/**
	 * 
	 * @param requestData
	 *            :takes the client id in RequestData
	 * @return list of all the income in the data base
	 * @throws NotLogedInException
	 *             : if the client id not valid
	 * @throws RequestDataIsNullException
	 *             : if the request data or id is empty
	 */
	@Override
	public List<Income> viewAllIncome(RequestData requestData) throws NotLogedInException, RequestDataIsNullException {
		logInCheck(requestData);
		return incomeServices.viewAllIncome();

	}

}
