package com.asad.couponesController.customer;

import org.springframework.data.repository.CrudRepository;

import com.asad.couponesController.entitys.Customer;

public interface CustomerRepository  extends CrudRepository<Customer, Long>{

	Customer findCustomerByName(String name);
	Customer findCustomerById(Long id);
	Customer findCustomerByNameAndPassword(String name,String password);
}
