package com.asad.couponesController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.asad.couponesController.exceptions.ComponentNotFoundException;
import com.asad.couponesController.exceptions.CouponIsAlreadyPurchasedException;
import com.asad.couponesController.exceptions.IncomeIsNullException;
import com.asad.couponesController.exceptions.NameIsUsedException;

@ControllerAdvice
public class ExceptionControllerAdvice {
	@ExceptionHandler(Exception.class)
    public ResponseEntity<Response> exception(Exception e) {
		e.printStackTrace();
		Response body = new Response(e);
		return ResponseEntity.ok(body);
    }
	@ExceptionHandler(NameIsUsedException.class)
    public ResponseEntity<Response> nameIsUsedException(NameIsUsedException e) {
        e.printStackTrace();
		Response body = new Response(e);
		return ResponseEntity.ok(body);
    }
	@ExceptionHandler(ComponentNotFoundException.class)
    public ResponseEntity<Response> ComponentNotFound(ComponentNotFoundException e) {
        e.printStackTrace();
		Response body = new Response(e);
		return ResponseEntity.ok(body);
    }
	@ExceptionHandler(CouponIsAlreadyPurchasedException.class)
	public ResponseEntity<Response> CouponIsAlreadyPurchased(CouponIsAlreadyPurchasedException e) {
		e.printStackTrace();
		Response body = new Response(e);
		return ResponseEntity.ok(body);
	}
	@ExceptionHandler(IncomeIsNullException.class)
	public ResponseEntity<Response> IncomeIsNull(IncomeIsNullException e) {
		e.printStackTrace();
		Response body = new Response(e);
		return ResponseEntity.ok(body);
	}
}
