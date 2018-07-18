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

	private static Map<Long, Company> logedInCompanies = new HashMap<>();
	private static List<Company> logedInCompaniesList = new ArrayList<>();
	@Autowired
	private CouponRepository couponDao;
	@Autowired
	private CompanyRepository companyDao;
	
	@Autowired
	private IncomeServices incomeServices;
	

	@Override
	public LogInResponse logIn(LogIn logIn) throws LogInDataIsNullException, RequestDataIsNullException {
		new CheckClientRequest().checkLogIn(logIn);
		AppLogger.getLogger().log(Level.INFO, logIn.getUserId()+"");
		

		if (logIn.getUserId() != null && logedInCompanies.get(logIn.getUserId()) != null) {
			AppLogger.getLogger().log(Level.INFO, " in the first if");

			return new LogInResponse(LogInEnum.ALREADYLOGINEDIN);
		} else {

			if (logIn.getUserId() != null) {
				return new LogInResponse(LogInEnum.USERIDISNOTVALID);
			}
			Company company = companyDao.findCompanyByCompanyNameAndPassword(logIn.getUserName(), logIn.getPassword());

			if (company != null) {
				for (Company companyInList : logedInCompaniesList) {

					if (company.getId().equals(companyInList.getId())) {
						return new LogInResponse(LogInEnum.ALREADYLOGINEDIN);
					}
				}
				Long id = LoginIdGenerator.generateId();
				logedInCompanies.put(id, company);
				logedInCompaniesList.add(company);
				// TODO: log
				AppLogger.getLogger().log(Level.INFO, this.logedInCompaniesList.toString());
				// TODO: log
				AppLogger.getLogger().log(Level.INFO, id.toString());
				return new LogInResponse(LogInEnum.LOGINSUCCESS, id);
			} else {
				return new LogInResponse(LogInEnum.LOGINFAILED);
			}
		}

	}

	@Override
	public ResponseMassageEnum logout(RequestData idData)
			throws IdIsNullException, NotLogedInException, RequestDataIsNullException {
		logInCheck(idData);
		AppLogger.getLogger().log(Level.INFO, idData.getClientId()+"");
		for (Long id1 : logedInCompanies.keySet()) {
			if (idData.getClientId() == id1) {
				logedInCompaniesList.remove(logedInCompanies.get(idData.getClientId()));
				logedInCompanies.remove(idData.getClientId());
				
				return ResponseMassageEnum.LOGOUTSUCCESS;
			}
		}

		return ResponseMassageEnum.LOGOUTFAILED;

	}

	public synchronized Coupon creatCoupon(RequestData couponData)
			throws NameIsUsedException, RequestDataIsNullException, NotLogedInException {
		// TODO:log
		AppLogger.getLogger().log(Level.INFO, couponData.getCoupon().toString());
		logInCheck(couponData);
		new CheckClientRequest().checkCoupon(couponData);

		try {
			Company company = logedInCompanies.get(couponData.getClientId());
			Set<Coupon> coupons = company.getCoupons();
			couponDao.save(couponData.getCoupon());

			
			coupons.add(couponDao.findCouponByTitle(couponData.getCoupon().getTitle()));
			AppLogger.getLogger().log(Level.INFO,coupons.toString());
			companyDao.save(company);
			this.logedInCompanies.remove(couponData.getClientId());
			this.logedInCompanies.put(couponData.getClientId(), companyDao.findCompanyById(company.getId()));
			return couponDao.findCouponByTitle(couponData.getCoupon().getTitle());
		} catch (Exception e) {

			System.out.println(e.getStackTrace());
			throw new NameIsUsedException("The Name Of The Coupon Is Already Used", e);

		}

	}

	@Override
	public ResponseMassageEnum updateCoupon(RequestData couponData)
			throws RequestDataIsNullException, NotLogedInException, ComponentNotFoundException {
		AppLogger.getLogger().log(Level.INFO, couponData.toString());
		logInCheck(couponData);
		new CheckClientRequest().checkCoupon(couponData);
		Set<Coupon> companyCoupons = this.logedInCompanies.get(couponData.getClientId()).getCoupons();
		
		Coupon couponfromDataBase = couponDao.findCouponById(couponData.getCoupon().getId());

		NullCheck.checkIfItIsNull(couponfromDataBase, ResponseMassageEnum.COUPONNOTFOUND.toString());
		checkIfCompanyCouponIsValid(couponData, companyCoupons);

			if (!couponfromDataBase.getTitle().trim().equals(couponData.getCoupon().getTitle().trim())) {
				throw new RequestDataIsNullException(ResponseMassageEnum.COMPANYNAMEMOSTNOTCHANGE.toString());
			}
			if (couponData.getCoupon().getEndDate() != null) {
				couponfromDataBase.setEndDate(couponData.getCoupon().getEndDate());
			}
			if (couponData.getCoupon().getPrice() != null) {
				couponfromDataBase.setPrice(couponData.getCoupon().getPrice());
			}
			couponDao.save(couponfromDataBase);

			return ResponseMassageEnum.THECOUPONUPDATED;
		

	}

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

	public Set<Coupon> listAllCouponsForSpecificCompany(RequestData idData)
			throws RequestDataIsNullException, NotLogedInException {
		logInCheck(idData);
		return logedInCompanies.get(idData.getClientId()).getCoupons();

	}

	@Override
	public Coupon deleteCoupon(RequestData couponData) throws RequestDataIsNullException, NotLogedInException {
		logInCheck(couponData);
		Company company = this.logedInCompanies.get(couponData.getClientId());

		Coupon couponfromDataBase = couponDao.findCouponById(couponData.getCoupon().getId());
		NullCheck.checkIfItIsNull(couponfromDataBase, ResponseMassageEnum.COUPONNOTFOUND.toString());
		checkIfCompanyCouponIsValid(couponData, company.getCoupons());
		if (!couponfromDataBase.getTitle().trim().equals(couponData.getCoupon().getTitle().trim())) {
			throw new RequestDataIsNullException("COUPONSTITLEISNOTMATCH");
		}
		Set<Coupon> coupons =company.getCoupons();
		coupons.remove(couponData.getCoupon());
		AppLogger.getLogger().log(Level.INFO, coupons.toString());
		company.setCoupons(coupons);
		couponDao.delete(couponfromDataBase);
//		companyDao.save(company);
		
		
		
		this.logedInCompaniesList.remove(this.logedInCompanies.get(couponData.getClientId()));
		this.logedInCompanies.remove(couponData.getClientId());
		this.logedInCompanies.put(couponData.getClientId(), companyDao.findCompanyById(company.getId()));
		this.logedInCompaniesList.add(this.logedInCompanies.get(couponData.getClientId()));
		
		
		AppLogger.getLogger().log(Level.INFO, company.getCoupons().toString());
		//TODO:fix the bug in the company coupon table and the company in the companyMap 
		return couponfromDataBase;
	}
		
	@Override
	public List<Income> viewCompanyIncome(RequestData companyData) throws NotLogedInException, RequestDataIsNullException {
		logInCheck(companyData);
		
		Company company = logedInCompanies.get(companyData.getClientId());
		NullCheck.checkIfItIsNull(company, ResponseMassageEnum.COMPANYNOTFOUND.toString());
		
		List<Income> companyIncomes = incomeServices.viewIncomeByClientNameAndIncomeType(company.getCompanyName(), IncomeType.COMPANY_NEW_COUPON);
		companyIncomes.addAll(incomeServices.viewIncomeByClientNameAndIncomeType(company.getCompanyName(), IncomeType.COMPANY_UPDATE_COUPON));
		
		return companyIncomes;
	}

	public Set<Coupon> getSpecificCouponsForCumpany(RequestData specificCouponData)
			throws IdIsNullException, ComponentNotFoundException, NotLogedInException, RequestDataIsNullException {
		logInCheck(specificCouponData);
		SpecificCouponData couponData = specificCouponData.getSpecificCouponData();
		Company company = logedInCompanies.get(specificCouponData.getClientId());
		Set<Coupon> coupons = company.getCoupons();
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
					if (coupon.getPrice() >= couponData.getPrice()) {

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

	private void logInCheck(RequestData requestData) throws NotLogedInException, RequestDataIsNullException {
		AppLogger.getLogger().log(Level.INFO, "log in Check");

		NullCheck.checkIfItIsNull(requestData, "your request is empty ");
		try {
			if (!this.logedInCompanies.containsKey(requestData.getClientId())) {
				throw new NotLogedInException(LogInEnum.NOTLOGEDIN.toString());
			}
		} catch (Exception e) {
			throw new NotLogedInException(LogInEnum.NOTLOGEDIN.toString(), e);
		}
		AppLogger.getLogger().log(Level.INFO, "loged in true");

	}

	// TODO:check again if the method delete the coupons with real data base coupons
	/**
	 * check if there is any coupons out of date and deletes it
	 */
	@PostConstruct
	private void DailyCouponCheck() {
		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleWithFixedDelay(new DailyCouponExpirationTask(this.couponDao), 0, 1, TimeUnit.DAYS);
	}

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
