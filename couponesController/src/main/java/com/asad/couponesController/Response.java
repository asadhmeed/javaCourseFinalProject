package com.asad.couponesController;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

	private Object response;
	private String responseMessage;
	
	
	
	
	public Response(Object response) {
		super();
		this.response = response;
	}
	
	
	public Response(Exception e) {
		super();
		this.response =null;
		this.responseMessage = e.getMessage();
	}


	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}


	@Override
	public String toString() {
		return "Response [response=" + response + ", responseMessage=" + responseMessage + "]";
	}
	
	
	
	
}
