package com.generator.invoice.ExceptionHandling;

import java.util.List;

public class BusinessException  extends Exception {

	private int code;
	private String message;
	private List<Object> arguments;
	
	public BusinessException() {
		
	}	

	public BusinessException(int code) {
		this.code = code;
	}
	public BusinessException(String message) {
		this.message = message;
	}
	

	public BusinessException(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public BusinessException(int code, String message, List<Object> args) {
		this.code = code;
		this.message = message;
		this.arguments = args;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Object> getArguments() {
		return arguments;
	}
	public void setArguments(List<Object> arguments) {
		this.arguments = arguments;
	}

}

