package com.danielalvc.public_holiday.model.exception;

public class ExceptionResponse {

	private int status;
	private String message;
	
	public ExceptionResponse() {
		super();
	}

	public ExceptionResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ExceptionResponse [status=" + status + ", message=" + message + "]";
	}
	
}
