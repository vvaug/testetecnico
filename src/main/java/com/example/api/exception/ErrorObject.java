package com.example.api.exception;

public class ErrorObject {

	 private  String errorMessage;
	 private  String errorField;
	 
	 public ErrorObject() {
		 /*
		  * default constructor
		  */
	 }
	 
	 public ErrorObject(String errorMessage, String errorField ) {
		 this.errorMessage = errorMessage;
		 this.errorField = errorField;
	 }

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorField() {
		return errorField;
	}

	public void setErrorField(String errorField) {
		this.errorField = errorField;
	}


}
