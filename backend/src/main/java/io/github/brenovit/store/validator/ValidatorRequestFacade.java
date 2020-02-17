package io.github.brenovit.store.validator;

import io.github.brenovit.store.payload.auth.SignInRequest;
import io.github.brenovit.store.validator.auth.sigin.SignInValidator;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidatorRequestFacade {
	private static final SignInValidator signInValidator = new SignInValidator();
	
	@SneakyThrows	
	public void validate(SignInRequest request) {
		signInValidator.validate(request);
	}	
}
