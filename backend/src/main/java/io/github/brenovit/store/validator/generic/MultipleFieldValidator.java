package io.github.brenovit.store.validator.generic;

import java.util.ArrayList;
import java.util.List;

import io.github.brenovit.store.exception.RequestValidationException;
import io.github.brenovit.store.exception.ValidationException;
import io.github.brenovit.store.payload.error.Error;
import io.github.brenovit.store.util.ErrorCode;
import lombok.Getter;
import lombok.SneakyThrows;

public abstract class MultipleFieldValidator<T> extends GenericValidator<T> {

	private List<ValidationMethodRunnable> listOfValidations = new ArrayList<ValidationMethodRunnable>();
	@Getter
	private List<Error> listErro = new ArrayList<Error>();

	protected abstract void addValidationFor(T request);

	protected void add(ValidationMethodRunnable toValidate) {
		listOfValidations.add(toValidate);
	}

	@Override
	@SneakyThrows
	public void validate(T request) {
		try {
			addValidationFor(request);
			run();
			if (!this.listErro.isEmpty()) {
				throw new RequestValidationException(ErrorCode.DATA_INCONSISTENCY, this.listErro);
			}
		} finally {
			this.listErro.clear();
			this.listOfValidations.clear();
		}
	}

	@SneakyThrows
	private void run() {
		for (ValidationMethodRunnable method : listOfValidations) {
			try {
				method.execute();
			} catch (ValidationException exception) {
				this.listErro.add(new Error(exception.getCode(), exception.getMessage()));
			}
		}
	}

}
