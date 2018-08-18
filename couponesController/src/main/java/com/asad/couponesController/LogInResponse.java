package com.asad.couponesController;

import com.asad.couponesController.enums.LogInEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
//JsonInclude.Include.NON_NULL return the not null variable to the client in the response
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogInResponse {
private LogInEnum logInResponseMassege;
private Long id;

/**
 * 
 * used if the programmer want to return LogInEnum and long number
 * @param loginsuccess
 * @param generatedId
 */
public LogInResponse(LogInEnum loginsuccess, Long generatedId) {
	this.logInResponseMassege =loginsuccess;
	this.id =generatedId;
}
/**
 * used if the programmer want to return only LogInEnum 
 * @param loginfaild
 */
public LogInResponse(LogInEnum loginfaild) {
	this.logInResponseMassege =loginfaild;
}
public LogInEnum getLogInResponseMassege() {
	return logInResponseMassege;
}
public void setLogInResponseMassege(LogInEnum logInResponseMassege) {
	this.logInResponseMassege = logInResponseMassege;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}

}
