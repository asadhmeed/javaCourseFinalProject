package com.asad.couponesController.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.asad.couponesController.AppLogger;
import com.asad.couponesController.CheckClientRequest;
import com.asad.couponesController.LogIn;
import com.asad.couponesController.LogInResponse;
import com.asad.couponesController.LoginIdGenerator;
import com.asad.couponesController.NullCheck;
import com.asad.couponesController.RequestData;
import com.asad.couponesController.SpecificCouponData;
import com.asad.couponesController.CouponDateCheck.DailyCouponExpirationTask;
import com.asad.couponesController.IncomeServices.IncomeServices;
import com.asad.couponesController.coupons.CouponRepository;
import com.asad.couponesController.entitys.Company;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.entitys.Income;
import com.asad.couponesController.enums.CheckClientRequestEnum;
import com.asad.couponesController.enums.IncomeType;
import com.asad.couponesController.enums.LogInEnum;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.ComponentNotFoundException;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.LogInDataIsNullException;
import com.asad.couponesController.exceptions.NameIsUsedException;
import com.asad.couponesController.exceptions.RequestDataIsNullException;
import com.asad.couponesController.exceptions.NotLogedInException;

@Service
public class CompanyServicesImpl implements CompanyServices {

	// save the log in authorization number and data of the logged in Companies
	private static Map<Long, Company> logedInCompanies = new HashMap<>();
	// save the data of the logged in Companies
	private static List<Company> logedInCompaniesList = new ArrayList<>();
	// connection to the coupons table in the data base
	@Autowired
	private CouponRepository couponDao;

	// connection to the companies table in the data base
	@Autowired
	private CompanyRepository companyDao;
	// using the income service ( add the companies and the customers paid
	// activities )
	@Autowired
	private IncomeServices incomeServices;

	/**
	 * company log in
	 * 
	 * @param logIn
	 *            : takes user name and password
	 * @return logInResponse (if the userName and password valid return LOGINSUCCESS
	 *         and number for further Authorization if the user already log in
	 *         returns ALREADYLOGINEDIN and the current Authorization number for the
	 *         logged in company if the data does not exist in the data base returns
	 *         LOGINFAILED
	 * @throws LogInDataIsNullException
	 * @throws RequestDataIsNullException
	 */
	@Override
	public LogInResponse logIn(LogIn logIn) throws LogInDataIsNullException, RequestDataIsNullException {
		new CheckClientRequest().checkLogIn(logIn);

		if (logIn.getUserId() != null && logedInCompanies.get(logIn.getUserId()) != null) {

			return new LogInResponse(LogInEnum.ALREADYLOGGEDIN);
		} else {

			if (logIn.getUserId() != null) {
				return new LogInResponse(LogInEnum.USERIDISNOTVALID);
			}
			Company company = companyDao.findCompanyByCompanyNameAndPassword(logIn.getUserName(), logIn.getPassword());

			if (company != null) {
				for (Company companyInList : logedInCompaniesList) {

					if (company.getId().equals(companyInList.getId())) {
						Set<Long> keys = logedInCompanies.keySet();
						for (Long key : keys) {
							if (logedInCompanies.get(key).getId() == company.getId()) {

								logedInCompanies.remove(key);
								logedInCompaniesList.remove(company);
								logedInCompanies.put(key, company);
								logedInCompaniesList.add(company);
								return new LogInResponse(LogInEnum.ALREADYLOGGEDIN, key);
							}
						}
					}
				}
				Long id = LoginIdGenerator.generateId();
				logedInCompanies.put(id, company);
				logedInCompaniesList.add(company);

				return new LogInResponse(LogInEnum.LOGINSUCCESS, id);
			} else {
				return new LogInResponse(LogInEnum.LOGINFAILED);
			}
		}

	}

	/**
	 * company log out
	 * 
	 * @param idData
	 *            : takes authorization number (clientId inside a RequestData)
	 * @return : LOGOUTSUCCESS if the authorization number is in the server memory
	 *         (the logedInCompanies) else return LOGOUTFAILED
	 * @throws RequestDataIsNullException
	 * @throws IdIsNullException
	 * @throws NotLogedInException
	 */
	@Override
	public ResponseMassageEnum logout(RequestData idData)
			throws IdIsNullException, NotLogedInException, RequestDataIsNullException {
		AppLogger.getLogger().log(Level.INFO, idData.toString());

		logInCheck(idData);
		AppLogger.getLogger().log(Level.INFO, idData.getClientId() + "");
		for (Long id1 : logedInCompanies.keySet()) {
			if ((long) idData.getClientId() == id1) {
				logedInCompaniesList.remove(logedInCompanies.get((long) idData.getClientId()));
				logedInCompanies.remove((long) idData.getClientId());

				return ResponseMassageEnum.LOGOUTSUCCESS;
			}
		}

		return ResponseMassageEnum.LOGOUTFAILED;

	}

	/**
	 * create new coupon
	 * 
	 * @param couponData
	 *            : coupon data and client id (for authorization) in RequestData
	 * @return COUPONISCREATED if the coupon is successfully created if the name of
	 *         the coupon is already used and return COUPONAMEISALREADYUSED
	 * @throws NameIsUsedException
	 *             : if the name of the coupon is already used and return
	 *             COUPONAMEISALREADYUSED
	 * @throws RequestDataIsNullException
	 * @throws NotLogedInException
	 */
	public synchronized ResponseMassageEnum creatCoupon(RequestData couponData)
			throws NameIsUsedException, RequestDataIsNullException, NotLogedInException {
		logInCheck(couponData);

		new CheckClientRequest().checkCoupon(couponData);

		try {
			Company company = logedInCompanies.get((long) couponData.getClientId());
			Set<Coupon> coupons = company.getCoupons();
			couponDao.save(couponData.getCoupon());

			coupons.add(couponDao.findCouponByTitle(couponData.getCoupon().getTitle()));
			companyDao.save(company);
			this.logedInCompanies.remove((long) couponData.getClientId());
			this.logedInCompanies.put((long) couponData.getClientId(), companyDao.findCompanyById(company.getId()));
			return ResponseMassageEnum.COUPONISCREATED;
		} catch (Exception e) {

			System.out.println(e.getStackTrace());
			throw new NameIsUsedException(ResponseMassageEnum.COUPONAMEISALREADYUSED.toString(), e);

		}

	}

	/**
	 * update coupon in the data base
	 * 
	 * @param couponData
	 *            : takes coupon data and client id (for authorization) in
	 *            RequestData
	 * @return THECOUPONUPDATED if the coupon is successfully updated ,
	 *         COUPONNOTFOUND if the coupon not found in the data base,
	 *         COUPONTITLEMOSTNOTCHANGE if the coupon title is changed
	 * @throws RequestDataIsNullException
	 * @throws NotLogedInException
	 * @throws ComponentNotFoundException
	 */
	@Override
	public ResponseMassageEnum updateCoupon(RequestData couponData)
			throws RequestDataIsNullException, NotLogedInException, ComponentNotFoundException {
		AppLogger.getLogger().log(Level.INFO, couponData.toString());
		logInCheck(couponData);
		new CheckClientRequest().checkCoupon(couponData);
		Set<Coupon> companyCoupons = this.logedInCompanies.get((long) couponData.getClientId()).getCoupons();

		Coupon couponfromDataBase = couponDao.findCouponById(couponData.getCoupon().getId());

		NullCheck.checkIfItIsNull(couponfromDataBase, ResponseMassageEnum.COUPONNOTFOUND.toString());
		checkIfCompanyCouponIsValid(couponData, companyCoupons);

		if (!couponfromDataBase.getTitle().trim().equals(couponData.getCoupon().getTitle().trim())) {
			throw new RequestDataIsNullException(ResponseMassageEnum.COUPONTITLEMOSTNOTCHANGE.toString());
		}

		couponDao.save(couponData.getCoupon());

		return ResponseMassageEnum.THECOUPONUPDATED;

	}

	// check if the coupon in the company coupon list for validation if the coupon
	// owned by the specific company
	private void checkIfCompanyCouponIsValid(RequestData couponData, Set<Coupon> companyCoupons)
			throws RequestDataIsNullException {
		boolean couponIsInTheCompanyCouponList = false;
		for (Coupon coupon : companyCoupons) {
			if (coupon.getId().equals(couponData.getCoupon().getId())) {
				couponIsInTheCompanyCouponList = true;
			}

		}
		if (!couponIsInTheCompanyCouponList) {
			throw new RequestDataIsNullException(ResponseMassageEnum.COUPONISNOTYOURS.toString());
		}
	}

	/**
	 * list all coupons for specific company
	 * 
	 * @param idData
	 *            : takes client id(for authorization) in RequestData
	 * @return list of the coupons if the company exist else return NOTLOGEDIN
	 * @throws RequestDataIsNullException
	 * @throws NotLogedInException
	 */
	public Set<Coupon> listAllCouponsForSpecificCompany(RequestData idData)
			throws RequestDataIsNullException, NotLogedInException {
		logInCheck(idData);
		Company logedInCompany = logedInCompanies.get((long) idData.getClientId());
		Company company = companyDao.findCompanyById(logedInCompany.getId());

		return company.getCoupons();

	}

	/**
	 * delete coupon from the data base
	 * 
	 * @param couponData
	 *            :takes coupon data and client id (authorization id) in RequestData
	 * @return COUPONDELETED if the coupon deleted from the data base
	 *         COUPONSTITLEISNOTMATCH if the title in the coupon data from the
	 *         request is changed (not the original)
	 * @throws RequestDataIsNullException
	 * @throws NotLogedInException
	 */

	@Override
	public ResponseMassageEnum deleteCoupon(RequestData couponData)
			throws RequestDataIsNullException, NotLogedInException {
		logInCheck(couponData);
		Company company = this.logedInCompanies.get((long) couponData.getClientId());

		Coupon couponfromDataBase = couponDao.findCouponById(couponData.getCoupon().getId());
		NullCheck.checkIfItIsNull(couponfromDataBase, ResponseMassageEnum.COUPONNOTFOUND.toString());
		checkIfCompanyCouponIsValid(couponData, company.getCoupons());
		if (!couponfromDataBase.getTitle().trim().equals(couponData.getCoupon().getTitle().trim())) {
			throw new RequestDataIsNullException("COUPONSTITLEISNOTMATCH");
		}
		Set<Coupon> coupons = company.getCoupons();
		coupons.remove(couponData.getCoupon());
		AppLogger.getLogger().log(Level.INFO, coupons.toString());
		company.setCoupons(coupons);
		couponDao.delete(couponfromDataBase);
		// companyDao.save(company);

		this.logedInCompaniesList.remove(this.logedInCompanies.get((long) couponData.getClientId()));
		this.logedInCompanies.remove((long) couponData.getClientId());
		this.logedInCompanies.put((long) couponData.getClientId(), companyDao.findCompanyById(company.getId()));
		this.logedInCompaniesList.add(this.logedInCompanies.get((long) couponData.getClientId()));

		return ResponseMassageEnum.COUPONDELETED;
	}

	/**
	 * view all company income (create update coupons with cost)
	 * 
	 * @param companyData
	 *            : takes client id (for authorization) in RequestData
	 * @return the company list of income
	 * @throws NotLogedInException
	 * @throws RequestDataIsNullException
	 */
	@Override
	public List<Income> viewCompanyIncome(RequestData companyData)
			throws NotLogedInException, RequestDataIsNullException {
		logInCheck(companyData);

		Company company = logedInCompanies.get((long) companyData.getClientId());
		NullCheck.checkIfItIsNull(company, ResponseMassageEnum.COMPANYNOTFOUND.toString());

		List<Income> companyIncomes = incomeServices.viewIncomeByClientNameAndIncomeType(company.getCompanyName(),
				IncomeType.COMPANY_NEW_COUPON);
		companyIncomes.addAll(incomeServices.viewIncomeByClientNameAndIncomeType(company.getCompanyName(),
				IncomeType.COMPANY_UPDATE_COUPON));

		return companyIncomes;
	}

	/**
	 * list specific coupons for company by coupon type , price or end date .
	 * 
	 * @param SpecificCouponData
	 *            : takes coupon type , price or endDate and client id(for
	 *            authorization)
	 * @return list of the coupons depends of the specific coupon data
	 * @throws IdIsNullException
	 * @throws ComponentNotFoundException
	 * @throws NotLogedInException
	 * @throws RequestDataIsNullException
	 */
	public Set<Coupon> getSpecificCouponsForCumpany(RequestData specificCouponData)
			throws IdIsNullException, ComponentNotFoundException, NotLogedInException, RequestDataIsNullException {
		logInCheck(specificCouponData);
		SpecificCouponData couponData = specificCouponData.getSpecificCouponData();
		Company company = logedInCompanies.get((long) specificCouponData.getClientId());
		Company companyFromDataBase = companyDao.findCompanyById(company.getId());
		Set<Coupon> coupons = companyFromDataBase.getCoupons();
		NullCheck.checkIfItIsNull(coupons, ResponseMassageEnum.COUPONSAREZEROFORTHISCOMPANY.toString());

		if (coupons.size() > 0) {
			if (couponData.getCouponType() != null) {

				Set<Coupon> specificCoupons = new HashSet<>();
				for (Coupon coupon : coupons) {
					if (coupon.getCouponType().toString().equals(couponData.getCouponType().toString())) {

						specificCoupons.add(coupon);
					}
				}
				return specificCoupons;
			}
			if (couponData.getPrice() != null) {
				Set<Coupon> specificCoupons = new HashSet<>();
				for (Coupon coupon : coupons) {
					if (couponData.getPrice() < 0) {
						throw new RequestDataIsNullException(
								ResponseMassageEnum.COUPONPRICEMUSTNOTBENEGATIVE.toString());
					}
					if (coupon.getPrice() <= couponData.getPrice()) {

						specificCoupons.add(coupon);
					}

				}
				return specificCoupons;
			}
			if (couponData.getEndDate() != null) {
				Set<Coupon> specificCoupons = new HashSet<>();
				for (Coupon coupon : coupons) {
					if (coupon.getEndDate().isBefore(couponData.getEndDate())
							|| coupon.getEndDate().isEqual(couponData.getEndDate())) {

						specificCoupons.add(coupon);
					}
				}
				return specificCoupons;
			}
		}
		throw new RequestDataIsNullException(ResponseMassageEnum.SPESIFICCOUPONDATAISNOTVALID.toString());

	}
	// an eternal method that checks if the request sender is authorized
	private void logInCheck(RequestData requestData) throws NotLogedInException, RequestDataIsNullException {

		NullCheck.checkIfItIsNull(requestData, "your request is empty ");
		NullCheck.checkIfItIsNull((long) requestData.getClientId(), "log in error you need to log in");

		try {
			if (!this.logedInCompanies.containsKey((long) requestData.getClientId())) {
				throw new NotLogedInException(LogInEnum.NOTLOGEDIN.toString());
			}
		} catch (Exception e) {
			throw new NotLogedInException(LogInEnum.NOTLOGEDIN.toString(), e);
		}
		AppLogger.getLogger().log(Level.INFO, "loged in true");

	}

	
	/**
	 * check if there is any coupons out of date and deletes it
	 */
	@PostConstruct
	private void DailyCouponCheck() {
		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleWithFixedDelay(new DailyCouponExpirationTask(this.couponDao), 0, 1, TimeUnit.DAYS);
	}

	/**
	 * get company name
	 * 
	 * @param clientId
	 *            : client id (authorization number)
	 * @return company name if the company logged in else NOTLOGEDIN
	 * @throws NotLogedInException
	 */
	@Override
	public String getClientName(Long clientId) throws NotLogedInException {
		try {
			if (!this.logedInCompanies.containsKey(clientId)) {
				throw new NotLogedInException(LogInEnum.NOTLOGEDIN.toString());
			}
		} catch (Exception e) {
			throw new NotLogedInException(LogInEnum.NOTLOGEDIN.toString(), e);
		}
		return this.logedInCompanies.get(clientId).getCompanyName();
	}

}
