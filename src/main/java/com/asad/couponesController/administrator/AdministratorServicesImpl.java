package com.asad.couponesController.administrator;

import org.springframework.stereotype.Service;

import com.asad.couponesController.LogIn;

@Service
public class AdministratorServicesImpl implements AdministratorServices{

	
	public boolean logInCheck(LogIn logIn) {
		
		if(logIn.getUserName().trim().equals("admin") && logIn.getPassword().trim().equals("1234") ) {
			return true;
		}
		return false;
		
	}
	
}
