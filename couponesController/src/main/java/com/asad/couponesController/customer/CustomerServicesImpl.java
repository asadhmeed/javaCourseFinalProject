package com.asad.couponesController.customer;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.asad.couponesController.RequestData;
import com.asad.couponesController.SpecificCouponData;
import com.asad.couponesController.LogIn;
import com.asad.couponesController.LogInResponse;
import com.asad.couponesController.LoginIdGenerator;
import com.asad.couponesController.NullCheck;
import com.asad.couponesController.AppLogger;
import com.asad.couponesController.CheckClientRequest;
import com.asad.couponesController.coupons.CouponRepository;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.entitys.Customer;
import com.asad.couponesController.enums.LogInEnum;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.ComponentNotFoundException;
import com.asad.couponesController.exceptions.CouponIsAlreadyPurchasedException;
import com.asad.couponesController.exceptions.CustomerPurchaseDataException;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.IncomeIsNullException;
import com.asad.couponesController.exceptions.LogInDataIsNullException;
import com.asad.couponesController.exceptions.RequestDataIsNullException;
import com.asad.couponesController.exceptions.NotLogedInException;

@Service
public class CustomerServicesImpl implements CustomerServices {
	// save the log in authorization number and data of the logged in Customers
	private static Map<Long, Customer> logedInCustomers = new HashMap<>();

	// connection to the customers table in the data base
	@Autowired
	private CustomerRepository customerDao;
	// connection to the coupons table in the data base
	@Autowired
	private CouponRepository couponDao;
	
	/**
	 * customer log in 
	 * @param logIn : takes user name and password
	 * @return LOGINSUCCESS if customer successfully logged in ,
	 * ALREADYLOGGEDIN and the client id if the customer already logged in, 
	 *  LOGINFAILED if the log in data does not exist in the data base
	 *  , ALREADYLOGGEDINIDCOMFIRMED if the customer already logged in and has the client id(if the web refreshed the web data return to default and the client id equals to null)
	 * @throws LogInDataIsNullException
	 * @throws RequestDataIsNullException
	 */
	@Override
	public synchronized LogInResponse logIn(LogIn logIn) throws RequestDataIsNullException {
		NullCheck.checkIfItIsNull(logIn, "your request is empty ");
		if (logIn.getUserId() == null) {
			Customer customer = customerDao.findCustomerByNameAndPassword(logIn.getUserName(), logIn.getPassword());
			// TODO:log
			AppLogger.getLogger().log(Level.INFO, customer.toString());
			if (customer != null) {
				for (Long customerInMapId : logedInCustomers.keySet()) {
					if (customer.getId().equals(logedInCustomers.get(customerInMapId).getId())) {
						
						return new LogInResponse(LogInEnum.ALREADYLOGGEDIN,customerInMapId);
					}
				}
				Long logInPassCode = LoginIdGenerator.generateId();
				logedInCustomers.put(logInPassCode, customer);
				
				return new LogInResponse(LogInEnum.LOGINSUCCESS, logInPassCode);
			} else {
				return new LogInResponse(LogInEnum.LOGINFAILED);
			}
		}
		return new LogInResponse(LogInEnum.ALREADYLOGGEDINIDCOMFIRMED);
	}

	/**
	 * company log out
	 * @param IdData : takes client id(for authorization) in RequestData
	 * @return USERIDISNOTVALID if id is null,
	 * LOGOUTSUCCESS if id is valid,
	 * USERIDISNOTVALID if id is not valid
	 * @throws RequestDataIsNullException
	 * @throws IdIsNullException
	 * @throws NotLogedInException
	 */
	@Override
	public synchronized ResponseMassageEnum logout(RequestData idData)
			throws RequestDataIsNullException, IdIsNullException, NotLogedInException {
		logInCheck(idData);
		NullCheck.checkIfItIsNull(idData.getClientId(), LogInEnum.USERIDISNOTVALID.toString());

		if (logedInCustomers.containsKey((long)idData.getClientId())) {
			logedInCustomers.remove((long)idData.getClientId());
			return ResponseMassageEnum.LOGOUTSUCCESS;
		} else {
			throw new IdIsNullException(LogInEnum.USERIDISNOTVALID.toString());

		}

	}
	/**
	 * by coupon from the site 
	 * @param couponData : takes coupon data and client id (for authorization) in RequestData
	 * @return 
	 * @throws CouponIsAlreadyPurchasedException
	 * @throws IdIsNullException
	 * @throws CustomerPurchaseDataException
	 * @throws IncomeIsNullException
	 * @throws RequestDataIsNullException
	 * @throws NotLogedInException
	 */
	@Override
	public synchronized ResponseMassageEnum beyACoupon(RequestData couponData) throws NotLogedInException,
			RequestDataIsNullException, IncomeIsNullException, CustomerPurchaseDataException {
		logInCheck(couponData);
		new CheckClientRequest().checkCoupon(couponData);
		NullCheck.checkIfItIsNull(couponData.getClientId(), "your id is empty");

		Coupon dbCouponCheck = couponDao.findCouponById(couponData.getCoupon().getId());
		Customer customer = logedInCustomers.get((long)couponData.getClientId());
		if (dbCouponCheck != null && customer != null) {

			for (Coupon customersCouponCheck : customer.getCoupons()) {
				if (customersCouponCheck.getTitle().equals(dbCouponCheck.getTitle())) {
					throw new CouponIsAlreadyPurchasedException(ResponseMassageEnum.CANNOTPURCHASECOUPONTWICE.toString());
				}
			}
			if (couponData.getCoupon().getEndDate().isAfter(LocalDate.now())) {
				
				Set<Coupon> coupons = customer.getCoupons();
				// TODO:decrease the coupon amount in purchase coupon
				coupons.add(dbCouponCheck);

				customerDao.save(customer);
				Long customerId = (long)couponData.getClientId();
				this.logedInCustomers.remove(customerId);
				this.logedInCustomers.put(customerId, customerDao.findCustomerById(customer.getId()));

				return ResponseMassageEnum.COUPONPURCHASED;
			} else {
				throw new CustomerPurchaseDataException(ResponseMassageEnum.THECOUPONISNOLONGERAVAILABLE.toString());
			}
		} else {
			throw new CustomerPurchaseDataException(ResponseMassageEnum.CUSTOMRORCOUPONDOSNOTEXIST.toString());
		}

	}
	/**
	 * get coupons for specific customer 
	 * @param customerData : customer data and client id (for authorization)
	 * @return set of the customer coupons if found else throw exception with THECUSTOMERDOSENOTHAVECOUPONS massage
	 * @throws IdIsNullException
	 * @throws ComponentNotFoundException
	 * @throws NotLogedInException
	 * @throws RequestDataIsNullException
	 */
	@Override
	public Set<Coupon> getAllCouponsForCustomer(RequestData customerData)
			throws IdIsNullException, ComponentNotFoundException, NotLogedInException, RequestDataIsNullException {
		logInCheck(customerData);

		Customer customer = customerDao.findCustomerById(logedInCustomers.get((long)customerData.getClientId()).getId());
		if (customer != null) {
			return customer.getCoupons();
		} else {
			throw new ComponentNotFoundException(ResponseMassageEnum.THECUSTOMERDOSENOTHAVECOUPONS.toString());
		}

	}
	// an eternal method that checks if the request sender is authorized
	private void logInCheck(RequestData requestData) throws NotLogedInException, RequestDataIsNullException {
		NullCheck.checkIfItIsNull(requestData, "your request is empty ");
		NullCheck.checkIfItIsNull(requestData.getClientId(), "log in error you need to log in");

		try {
			if (!this.logedInCustomers.containsKey((long)requestData.getClientId())) {
				throw new NotLogedInException(LogInEnum.NOTLOGEDIN.toString());
			}
		} catch (Exception e) {
			throw new NotLogedInException(LogInEnum.NOTLOGEDIN.toString(), e);
		}

	}

	/**
	    * get list of coupon depend on the request data from the client (coupon price or type)
	    * @param SpecificCouponData : coupon type or price in the specificCouponData (check the RequestData class) and client id (for authorization)
	    * @return set of coupons depend on the coupon type or price
	    * @throws IdIsNullException
	    * @throws ComponentNotFoundException
	    * @throws NotLogedInException
	    * @throws RequestDataIsNullException
	    */
	@Override
	public Set<Coupon> getSpecificCouponsForCustomer(RequestData SpecificCouponData)
			throws IdIsNullException, ComponentNotFoundException, NotLogedInException, RequestDataIsNullException {
		logInCheck(SpecificCouponData);
		SpecificCouponData couponData = SpecificCouponData.getSpecificCouponData();
		Customer  customerLogedIn= logedInCustomers.get((long)SpecificCouponData.getClientId());
		Customer customer = customerDao.findCustomerById(customerLogedIn.getId());
		Set<Coupon> coupons = customer.getCoupons();
		NullCheck.checkIfItIsNull(coupons, ResponseMassageEnum.COUPONSAREZEROFORTHISCOMPANY.toString());
		if (coupons.size() > 0) {
			if (couponData.getCouponType() != null) {

				Set<Coupon> specificCoupons = new HashSet<>();
				for (Coupon coupon : coupons) {
					if (coupon.getCouponType().equals(couponData.getCouponType())) {
						specificCoupons.add(coupon);
					}
				}
				return specificCoupons;
			}
			if (couponData.getPrice() != null) {
				Set<Coupon> specificCoupons = new HashSet<>();
				for (Coupon coupon : coupons) {
					// TODO:log
					AppLogger.getLogger().log(Level.INFO, coupon.getPrice() + " <= " + couponData.getPrice());
					if (coupon.getPrice() <= couponData.getPrice()) {
						specificCoupons.add(coupon);
					}
					return specificCoupons;
				}
			}
		} else {
			throw new ComponentNotFoundException(ResponseMassageEnum.COUPONLISTISEMPTY.toString());

		}
		throw new RequestDataIsNullException(ResponseMassageEnum.SPESIFICCOUPONDATAISNOTVALID.toString());
	}
	
	/**
	 * get all the coupons from the data base 
	 * @return list of all coupons from the data base
	 */
	@Override
	public List<Coupon> getAllCoupon(){
		return (List<Coupon>) this.couponDao.findAll();
	}
	/**
	 * get customer name
	 * 
	 * @param clientId
	 *            : client id (authorization number)
	 * @return customer name if the customer logged in else NOTLOGEDIN
	 * @throws NotLogedInException
	 */
	@Override
	public String getClientName(Long clientId) throws NotLogedInException {
		try {
			if (!this.logedInCustomers.containsKey(clientId)) {
				throw new NotLogedInException(LogInEnum.NOTLOGEDIN.toString());
			}
		} catch (Exception e) {
			throw new NotLogedInException(LogInEnum.NOTLOGEDIN.toString(), e);
		}
		return this.logedInCustomers.get(clientId).getName();
	}

}
