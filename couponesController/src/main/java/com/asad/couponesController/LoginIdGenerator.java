package com.asad.couponesController;

import java.util.Random;

public final class LoginIdGenerator {

	private static Long ID =0l;
	
	
	
/**
 * generate client id for authorization 
 * @return client id (long)
 */
	public synchronized static Long generateId(){

		String strId ="";
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
			strId+= random.nextInt(9);
		}

		return Long.parseLong(strId);
	}

	public static Long getID() {
		return ID;
	}


	
	
}
