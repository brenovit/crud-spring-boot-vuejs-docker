package io.github.brenovit.store.service;

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
		String userName = jwtUtils.getUserNameFromJwtToken(headerHelper.getAuthorization());
		return userService.findByUsername(userName).get(); 
	}
}
