package com.example.api.dto;

public class CustomResponse {

	private String description;
	private String HttpStatus;
	
	public CustomResponse() {
		
	}

	public CustomResponse(String description, String httpStatus) {
		this.description = description;
		HttpStatus = httpStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHttpStatus() {
		return HttpStatus;
	}

	public void setHttpStatus(String httpStatus) {
		HttpStatus = httpStatus;
	}
	
	
	
}
