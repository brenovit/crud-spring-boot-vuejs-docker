package io.github.brenovit.store.exception;

import java.text.MessageFormat;

import io.github.brenovit.store.util.ErrorCode;
import lombok.Getter;

public abstract class GenericException extends Exception {

	private static final long serialVersionUID = 1L;
	@Getter
	private final String code;
	
	public GenericException(){
		super();
		this.code = ErrorCode.INTERNAL_ERROR.getCode();

	}
	
	public GenericException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.code = errorCode.getCode();
	}
	
	public GenericException(ErrorCode errorCode, Throwable ex) {
		super(errorCode.getMessage(), ex);
		this.code = errorCode.getCode();
	}
	
	public GenericException(ErrorCode errorCode, Object ... extraMsg) {
		super(MessageFormat.format(errorCode.getMessage(),	extraMsg));
		this.code = errorCode.getCode();
	}
		
	public GenericException(ErrorCode errorCode, Throwable ex, Object ... extraMsg) {
		super(MessageFormat.format(errorCode.getMessage(),	extraMsg), ex);
		this.code = errorCode.getCode();
	}	
		
	public GenericException(ErrorCode errorCode, String extraMsg, Throwable ex) {
		super(MessageFormat.format(errorCode.getMessage(),	extraMsg), ex);
		this.code = errorCode.getCode();
	}
	
	public String toString() {
		return String.format("%s : %s", this.code, super.getMessage());
	}
}
