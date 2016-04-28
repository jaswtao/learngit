package com.tl.exceptions;

public class BaseException extends Exception {
	private static final long serialVersionUID = 8660747344749677567L;
	private String message;

	public BaseException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
