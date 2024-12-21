package com.generator.invoice.ExceptionHandling;

public interface ExceptionCodes {
	
	public static final int SYSTEM_EXCEPTION_CODE = 10000;
	
	public static final int ISSUE_IN_DELETION = 10001;
	
	public static final int USER_INVALID_TOKEN = 601;
	
	public static final int USER_ALREADY_EXISTED = 602;
	
	public static final int USER_UNAUTHORIZED = 603;
	
	public static final int USER_INVALID = 604;
	
	public static final int EMAIL_NOT_SEND_EXCEPTION = 701;
	
	public static final int FILE_UPLOAD_ISSUE = 801;
	
	public static final int PROJECT_CONTENT_ISSUE = 901;
	
	public static final int PROJECT_UPLOAD_ISSUE = 902;
	
	public static final int PROJECT_DUPLICATE = 903;

	public static final int PROJECT_CONTENT_DUPLICATE = 904;
	
	public static final int NO_PROJECT_EXISTS = 905;
	
	public static final int NO_PROJECT_CONTENT_EXISTS = 906;
}

