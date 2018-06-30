package com.asad.couponesController.company;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.asad.couponesController.entitys.Company;

import com.asad.couponesController.enums.CouponType;

public interface CompanyRepository extends CrudRepository<Company, Long> {

	Company findCompanyById(Long id);
	Company findCompanyByCompanyName(String companyName);
	Company findCompanyByCompanyNameAndPassword(String companyName,String password);
	@Query("UPDATE Company c SET c.password =c.password,c.email =c.email")
	Company updateCompany(Company company);
//	List<Company> findAllCoupon();
//	List<Company> findAllByCouponType(CouponType couponType);
}
