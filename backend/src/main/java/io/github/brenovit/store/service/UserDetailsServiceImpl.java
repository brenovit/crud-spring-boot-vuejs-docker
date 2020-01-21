package io.github.brenovit.store.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.brenovit.store.models.User;
import io.github.brenovit.store.repository.UserRepository;
import io.github.brenovit.store.security.services.UserDetailsImpl;
import lombok.SneakyThrows;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	@Transactional
	@SneakyThrows
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: "+username));
		
		return UserDetailsImpl.builder(user);
	}
}
