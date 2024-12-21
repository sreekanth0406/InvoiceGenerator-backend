package com.generator.invoice.ExceptionHandling;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.client.HttpServerErrorException;

import jakarta.transaction.SystemException;

public class ControllerResponse {

	
	private Object payload;
	private String status;
	
	public Object getPayload() {
		return payload;
	}
	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public static ControllerResponse getSuccessResponse(Object payload) {
		ControllerResponse response = new ControllerResponse();
		response.setPayload(payload);
		response.setStatus(ExceptionMessages.RESPONSE_STATUS_SUCCESS);
		return response;
	}
	

	public static ControllerResponse getFailureResponse(Throwable ex) {
		ControllerError error = new ControllerError();
		if (ex instanceof BusinessException ) {
			BusinessException be = (BusinessException) ex;
			error.setCode(be.getCode());
			error.setMessage(be.getMessage());
		} else if (ex instanceof SystemException ) {
			SystemException se = (SystemException) ex;
			error.setCode(ExceptionCodes.SYSTEM_EXCEPTION_CODE);
			error.setMessage(se.getMessage());
		}else if(ex instanceof AccessDeniedException) {
			AccessDeniedException ade = (AccessDeniedException) ex;
			if(ade.getMessage().equals(ExceptionMessages.USER_INVALID_TOKEN)) {
				BusinessException be = (BusinessException) ade.getCause();
				error.setCode(be.getCode());
				error.setMessage(be.getMessage());
			}else {
				error.setCode(ExceptionCodes.USER_UNAUTHORIZED);
				error.setMessage(ade.getMessage());
			}
			
		}else if (ex instanceof RuntimeException ) {
			RuntimeException se = (RuntimeException) ex;
			error.setCode(ExceptionCodes.SYSTEM_EXCEPTION_CODE);
			error.setMessage(se.getMessage());
		}else {
			Exception exception = (Exception) ex;
			error.setCode(ExceptionCodes.SYSTEM_EXCEPTION_CODE);
			error.setMessage(exception.getMessage());
		}
		
		ControllerResponse response = new ControllerResponse();
		response.setStatus(ExceptionMessages.RESPONSE_STATUS_ERROR);
		response.setPayload(error);
		return response;
	}
	
	
}
