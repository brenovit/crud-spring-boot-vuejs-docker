package io.github.brenovit.store.models;

import java.util.stream.Stream;

public enum EPermission {
	ROLE_USER(1L),
	ROLE_MODERATOR(2L),
	ROLE_ADMIN(3L);
	
	private Long value;
	
	EPermission (Long value) {
		this.value = value;
	}
	
	public Long getValue() {
		return this.value;
	}
	
	public static EPermission of(Long permission) {
		return Stream.of(EPermission.values())
				.filter(p -> p.getValue() == permission)
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}