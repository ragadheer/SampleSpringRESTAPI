package com.jdpin.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomServiceException extends RuntimeException {

	private static final long serialVersionUID = -6919976724076657650L;

	public CustomServiceException(String message) {
		super(message);
	}

	public CustomServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}