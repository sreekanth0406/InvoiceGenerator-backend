package com.generator.invoice.ExceptionHandling;

public interface ExceptionMessages {
	
	public static final String RESPONSE_STATUS_SUCCESS = "SUCCESS";
	public static final String RESPONSE_STATUS_ERROR = "ERROR";
	public static final String USER_INVALID_TOKEN = "Invalid token";
	public static final String USER_ALREADY_EXISTED = "User Already there in System";
	public static final String USER_INVALID = "User Name or Password Are Incorrect";
	public static final String FILE_UPLOAD_ISSUE = "Issue In Uploading File";
	public static final String PROJECT_CONTENT_ISSUE = "Issue In Project Content Upload";
	public static final String PROJECT_UPLOAD_ISSUE = "Issue In Proect Upload";
	public static final String PROJECT_DUPLICATE = "Project With Same Name Already Existed";
	public static final String PROJECT_CONTENT_DUPLICATE = "Project Content for this Project already Existed";
	public static final String NO_PROJECT_EXISTS = "No Project Existed";
	public static final String NO_PROJECT_CONTENT_EXISTS = "No Project Content Existed";
	public static final String ISSUE_IN_DELETION = "Issue in deletion";
}