package io.github.brenovit.store.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.brenovit.store.models.User;
import io.github.brenovit.store.repository.UserRepository;

@Service
public class UserService extends InternalService {
		
	@Autowired
	private UserRepository userRepository;

	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public Optional<User> findByUsernameOrEmail(String username) {		
		return userRepository.findByUsernameOrEmail(username);
	}

}
