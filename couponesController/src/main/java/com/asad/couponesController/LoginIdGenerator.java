package com.asad.couponesController;

public final class LoginIdGenerator {

	private static Long ID =0l;
	
	
	

	public synchronized static Long generateId(){
		LoginIdGenerator.ID++;
		return (Long) LoginIdGenerator.ID;
	}

	public static Long getID() {
		return ID;
	}

//	public static void setID(Long iD) {
//		ID = iD;
//	}
	
	
}
