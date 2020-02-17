package io.github.brenovit.store.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.brenovit.store.payload.auth.SignInRequest;
import io.github.brenovit.store.payload.auth.SignInResponse;
import io.github.brenovit.store.payload.auth.SignUpRequest;
import io.github.brenovit.store.payload.response.MessageResponse;
import io.github.brenovit.store.service.AuthService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService service;

	@PostMapping("/signin")
	public ResponseEntity<?> signin(@Valid @RequestBody SignInRequest request) {
		SignInResponse response = service.signin(request);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest request) {
		service.signup(request);		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
