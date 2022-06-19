package com.mindex.challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeResourceNotFoundException  extends RuntimeException {

	/**
	 * Constructor for RecordNotFoundRuntimeException.
	 * @param msg the detail message
	 */
	public EmployeeResourceNotFoundException(String msg) {
		super(msg);
	}

	/**
	 * Constructor for RecordNotFoundRuntimeException.
	 * @param msg the detail message
	 * @param cause the root cause
	 */
	public EmployeeResourceNotFoundException(@Nullable String msg, @Nullable Throwable cause) {
		super(msg, cause);
	}

}