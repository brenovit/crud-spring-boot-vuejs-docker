package io.github.brenovit.store.exception;

import io.github.brenovit.store.util.ErrorCode;

public class ApplicationException extends GenericException{

	private static final long serialVersionUID = 1L;

	public ApplicationException(ErrorCode errorCode) {
		super(errorCode);
	}

}
