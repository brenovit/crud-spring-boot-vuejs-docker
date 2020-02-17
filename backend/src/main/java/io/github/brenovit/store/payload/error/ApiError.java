package io.github.brenovit.store.payload.error;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.github.brenovit.store.util.ErrorCode;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ApiError {
	
	private Date timestamp;
	private String code;
	private String message;
	private String detail;
	private List<Error> errors;
	
	public ApiError(String code, String message) {
		this.timestamp = new Date();
		this.code = code;
		this.message = message;
	}
	
	public ApiError(String code, String message, List<Error> errors) {
		this.timestamp = new Date();
		this.code = code;
		this.message = message;
		this.errors = errors;
	}
	
	public ApiError(ErrorCode errorCode, String detail) {
		this.timestamp = new Date();
		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
		this.detail = detail;
	}
	
	public ApiError(ErrorCode errorCode, List<Error> errors) {
		this.timestamp = new Date();
		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
		this.errors = errors;
	}	
	
	public ApiError(ErrorCode errorCode) {
		this.timestamp = new Date();
		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
	}

	public void add(Error error) {
		if(this.errors == null) {
			this.errors = new ArrayList<Error>();
		}
		
		this.errors.add(error);
	}
}
