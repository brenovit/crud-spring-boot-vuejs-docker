package io.github.brenovit.store.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.brenovit.store.exception.ApplicationException;
import io.github.brenovit.store.models.EPermission;
import io.github.brenovit.store.models.Permission;
import io.github.brenovit.store.models.User;
import io.github.brenovit.store.payload.auth.SignInRequest;
import io.github.brenovit.store.payload.auth.SignInResponse;
import io.github.brenovit.store.payload.auth.SignUpRequest;
import io.github.brenovit.store.repository.PermissionRepository;
import io.github.brenovit.store.repository.UserRepository;
import io.github.brenovit.store.security.jwt.JwtUtils;
import io.github.brenovit.store.security.services.UserDetailsImpl;
import io.github.brenovit.store.util.ErrorCode;
import io.github.brenovit.store.validator.ValidatorRequestFacade;
import lombok.SneakyThrows;

@Service
public class AuthService {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PermissionRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	public SignInResponse signin(@Valid SignInRequest request) {
		ValidatorRequestFacade.validate(request);
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> permissions = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		SignInResponse response = new SignInResponse(jwt, userDetails.getId(), userDetails.getUsername(),
				userDetails.getEmail(), permissions);
		return response;
	}

	@SneakyThrows
	public void signup(@Valid SignUpRequest request) {
		if (userRepository.existsByUsername(request.getUsername())
				|| userRepository.existsByEmail(request.getEmail())) {
			throw new ApplicationException(ErrorCode.USER_ALREADY_REGISTERED);
		}

		User user = new User().setUsername(request.getUsername()).setEmail(request.getEmail())
				.setPassword(encoder.encode(request.getPassword()));

		Set<String> strRoles = request.getRole();

		Set<Permission> roles = new HashSet<>();

		if (strRoles == null) {
			Permission userRole = roleRepository.findById(EPermission.ROLE_USER.getValue())
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Permission adminRole = roleRepository.findById(EPermission.ROLE_ADMIN.getValue())
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Permission modRole = roleRepository.findById(EPermission.ROLE_MODERATOR.getValue())
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Permission userRole = roleRepository.findById(EPermission.ROLE_USER.getValue())
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setPermissions(roles);
		userRepository.save(user);
	}
}
