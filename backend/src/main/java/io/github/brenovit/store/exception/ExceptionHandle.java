package io.github.brenovit.store.exception;

import java.text.ParseException;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.github.brenovit.store.payload.error.ApiError;
import io.github.brenovit.store.util.ErrorCode;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
public class ExceptionHandle {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> processValidationError(Exception ex) {
		log.error(ex.getMessage(), ex);
		ApiError apiErros = new ApiError(ErrorCode.INTERNAL_ERROR);
		return new ResponseEntity<ApiError>(apiErros, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiError> processConstraintViolationException(ConstraintViolationException ex) {
		log.error(ex.getMessage(), ex);
		ApiError apiErros = new ApiError(ErrorCode.INTERNAL_ERROR, ex.getMessage());
		return new ResponseEntity<ApiError>(apiErros, HttpStatus.INTERNAL_SERVER_ERROR);
	}	

	@ExceptionHandler(ParseException.class)
	public ResponseEntity<ApiError> processParseException(ParseException ex) {
		log.error(ex.getMessage(), ex);
		ApiError apiErros = new ApiError(ErrorCode.INTERNAL_ERROR, ex.getMessage());
		return new ResponseEntity<ApiError>(apiErros, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ApiError> throwsUnauthorized(UnauthorizedException ex) {
		log.error(ex.getMessage(), ex);
		ApiError apiErros = new ApiError(ex.getCode(), ex.getMessage());		
		return new ResponseEntity<ApiError>(apiErros, HttpStatus.UNAUTHORIZED);

	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ApiError> processValidationError(ValidationException ex) {
		log.error(ex.getMessage(), ex);
		ApiError apiErros = new ApiError(ex.getCode(), ex.getMessage());
		return new ResponseEntity<ApiError>(apiErros, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiError> processValidationError(HttpMessageNotReadableException ex) {
		log.error(ex.getMessage(), ex);
		ApiError apiErros = new ApiError(ErrorCode.DATA_INCONSISTENCY);
		return new ResponseEntity<ApiError>(apiErros, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ApiError> processValidationError(ApplicationException ex) {
		log.error(ex.getMessage(), ex);		
		ApiError apiErros = new ApiError(ex.getCode(), ex.getMessage());
		return new ResponseEntity<ApiError>(apiErros, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(RequestValidationException.class)
	public ResponseEntity<ApiError> processValidationError(RequestValidationException ex) {		
		log.error(ex.getMessage(), ex);
		log.error(ex.getErrors());
		ApiError apiErros = new ApiError(ex.getCode(), ex.getMessage(), ex.getErrors());
		return new ResponseEntity<ApiError>(apiErros, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ApiError> processValidationError(BadCredentialsException ex) {
		log.error(ex.getMessage(), ex);		
		ApiError apiErros = new ApiError(ErrorCode.INVALID_CREDENTIAL);
		return new ResponseEntity<ApiError>(apiErros, HttpStatus.BAD_REQUEST);
	}
}
