package com.asad.couponesController.entitys;

import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.asad.couponesController.enums.IncomeType;

@Entity
@Table(name= "")
public class Income {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private LocalDate date;
	@Convert(converter = IncomeTypeConverter.class)
	private IncomeType descrption;
	
	private Double amount;
	
	
	

	public Income(String name, LocalDate date, IncomeType descrption, Double amount) {
		super();
		this.name = name;
		this.date = date;
		this.descrption = descrption;
		this.amount = amount;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public IncomeType getDescrption() {
		return descrption;
	}

	public void setDescrption(IncomeType descrption) {
		this.descrption = descrption;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Income [id=" + id + ", name=" + name + ", date=" + date + ", descrption=" + descrption + ", amount="
				+ amount + "]";
	}
	
	
	
}
