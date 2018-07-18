package com.asad.couponesController.IncomeServices;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.asad.couponesController.entitys.Income;
import com.asad.couponesController.enums.IncomeType;

public interface IncomeRepository  extends CrudRepository<Income, Long>{

	
	List<Income> findByName(String name);



	List<Income> findByIncomeTypeAndName(IncomeType incomeType, String name);
	
}
