package io.github.brenovit.store.validator.generic;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import io.github.brenovit.store.exception.GenericException;
import io.github.brenovit.store.exception.ValidationException;
import io.github.brenovit.store.util.ErrorCode;
import lombok.SneakyThrows;

public abstract class GenericValidator<T> {
	
	public abstract void validate(T request) throws GenericException;
	
	@SneakyThrows
	protected void requiredField(Object field, String fieldName) {
		if(field == null) {
			throw new ValidationException(ErrorCode.REQUIRED_FIELD, fieldName);
		}
	}
	
	@SneakyThrows
	protected void requiredField(String campo, String fieldName) {
		if(StringUtils.isBlank(campo)) {
			throw new ValidationException(ErrorCode.REQUIRED_FIELD, fieldName);
		}
	}	
	
	@SneakyThrows
	protected void requiredField(BigDecimal campo, String fieldName) {
		if(campo == null) {
			throw new ValidationException(ErrorCode.REQUIRED_FIELD, fieldName);
		}
	}

	@SneakyThrows
	protected void requiredField(int campo, String fieldName) {
		if(campo <= 0) {
			throw new ValidationException(ErrorCode.REQUIRED_FIELD, fieldName);
		}
	}

	@SneakyThrows
	protected void requiredField(double campo, String fieldName) {
		if(campo <= 0) {
			throw new ValidationException(ErrorCode.REQUIRED_FIELD, fieldName);
		}
	}

	@SneakyThrows
	protected void requiredField(Long campo, String fieldName) {
		if(campo == null) {
			throw new ValidationException(ErrorCode.REQUIRED_FIELD, fieldName);
		}
	}

	@SneakyThrows
	protected void requiredField(Integer campo, String fieldName) {
		if(campo == null) {
			throw new ValidationException(ErrorCode.REQUIRED_FIELD, fieldName);
		}
	}

	@SneakyThrows
	protected void requiredField(List<?> campo, String fieldName) {
		if(campo == null || (campo != null && campo.isEmpty())) {
			throw new ValidationException(ErrorCode.REQUIRED_FIELD, fieldName);
		}
	}
}