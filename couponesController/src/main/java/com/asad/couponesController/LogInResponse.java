package com.asad.couponesController;

import com.asad.couponesController.enums.LogInEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogInResponse {
private LogInEnum logInResponseMassege;
private Long id;


public LogInResponse(LogInEnum loginsuccess, Long generatedId) {
	this.logInResponseMassege =loginsuccess;
	this.id =generatedId;
}
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
