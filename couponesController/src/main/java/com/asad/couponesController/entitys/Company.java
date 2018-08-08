package com.asad.couponesController.entitys;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "companies_list",uniqueConstraints = {@UniqueConstraint(columnNames="Comp_name")})
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "Comp_name")
	@NotNull
	private String companyName;
	private String password;
	private String email;
	@OneToMany(fetch = FetchType.EAGER , cascade =CascadeType.ALL)
	@JoinTable(name = "COMPANY_COUPON", joinColumns = @JoinColumn(name = "COMPANY_ID", referencedColumnName = "id"), // this
																														// class
			inverseJoinColumns = @JoinColumn(name = "COUPON_ID", referencedColumnName = "id")) // the other class
	private Set<Coupon> coupons = new HashSet<>();

	public Company() {
		super();

	}

	public Set<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(Set<Coupon> coupons) {
		this.coupons = coupons;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName + ", password=" + password + ", email=" + email
				+ "]";
	}

	

}
