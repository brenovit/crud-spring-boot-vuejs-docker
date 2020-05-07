package io.github.brenovit.store.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.brenovit.store.models.User;
import io.github.brenovit.store.security.jwt.JwtUtils;
import io.github.brenovit.store.util.HeaderHelper;

public class InternalService {

	@Autowired
	private HeaderHelper headerHelper;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserService userService;

	public User getLoggedUser() {
		String authorization = headerHelper.getAuthorization();
		if (StringUtils.isEmpty(authorization)) {
			return null;
		}
		String userName = jwtUtils.getUserNameFromJwtToken(authorization);
		Optional<User> findedUser = userService.findByUsername(userName);
		if (findedUser.isPresent()) {
			return findedUser.get();
		}
		return null;
	}
}
