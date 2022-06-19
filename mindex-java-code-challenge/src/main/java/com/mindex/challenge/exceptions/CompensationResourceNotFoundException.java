package com.mindex.challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CompensationResourceNotFoundException  extends RuntimeException {

	/**
	 * Constructor for RecordNotFoundRuntimeException.
	 * @param msg the detail message
	 */
	public CompensationResourceNotFoundException(String msg) {
		super(msg);
	}

	/**
	 * Constructor for RecordNotFoundRuntimeException.
	 * @param msg the detail message
	 * @param cause the root cause
	 */
	public CompensationResourceNotFoundException(@Nullable String msg, @Nullable Throwable cause) {
		super(msg, cause);
	}

}