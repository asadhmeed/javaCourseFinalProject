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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name = "customers_list",uniqueConstraints = {@UniqueConstraint(columnNames="cust_name")})
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="cust_name")
	@NotNull
	
	private String name;
	
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	
	@JoinTable(name="CUSTOMER_COUPON",
	joinColumns = @JoinColumn(name= "CUSTOMER_ID"),//this class
	inverseJoinColumns=@JoinColumn(name= "COUPON_ID"))//the other class
	
	private Set<Coupon> coupons = new HashSet<>() ;
	

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




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
