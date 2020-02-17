package io.github.brenovit.store.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public enum ErrorCode {	
	USER_ALREADY_REGISTERED("952"),
	USER_NOT_FOUND("952"),
	EXPIRED_TOKEN("953"),
	INCORRECT_SIZE_FIELD("901"),
	INCORRECT_FORMAT_FIELD("902"),
	INVALID_FIELD("903"),
	REQUIRED_FIELD("904"),
	INVALID_CREDENTIAL("940"),
	DATA_INCONSISTENCY("998"),
	INTERNAL_ERROR("999");
	
	private static final MessageSource bundle;
	
	static {
		bundle = BeanUtil.getBeans(MessageSource.class);
	}
	
	private String code;
	
	ErrorCode(String code){
		this.code = code;		
	}
	
	public String getCode() {
		return code;
	}
	
	public String getMessage() {
		return bundle.getMessage(code, null, LocaleContextHolder.getLocale());
	}
	
	public static ErrorCode valueOfCode(String code) {
		for (ErrorCode e : values()) {
			if (e.code.equals(code)) {
				return e;
			}
		}
		return ErrorCode.INTERNAL_ERROR;
	}
	
	public boolean equals(String code) {
		return this.getCode().equals(code);
	}
}
