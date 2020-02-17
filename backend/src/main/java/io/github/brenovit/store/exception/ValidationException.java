package io.github.brenovit.store.exception;

import io.github.brenovit.store.util.ErrorCode;

public class ValidationException extends GenericException{

	private static final long serialVersionUID = 1L;

	public ValidationException(ErrorCode errorCode, String extraMsg) {
		super(errorCode, extraMsg);
	}

}
