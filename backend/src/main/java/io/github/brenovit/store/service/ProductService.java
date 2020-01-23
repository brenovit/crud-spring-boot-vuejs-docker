package io.github.brenovit.store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.brenovit.store.models.Product;
import io.github.brenovit.store.models.User;
import io.github.brenovit.store.repository.ProductRepository;
import io.github.brenovit.store.repository.UserRepository;
import io.github.brenovit.store.security.jwt.JwtUtils;
import io.github.brenovit.store.util.HeaderHelper;

@Service
public class ProductService {
	@Autowired
	private HeaderHelper headerHelper;
	
	@Autowired	
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll(){
		return repository.findByUserId(getUser().getId());
	}
	
	public Optional<Product> findById(Long id){
		Optional<Product> product = repository.findById(id);
		if(product.isPresent() && product.get().getUser().getId() != getUser().getId()) {
			return Optional.empty();
		}
		return product;
	}
	
	public Product save (Product product) {
		product.setUser(getUser());		
		return repository.save(product);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}	
	
	private User getUser() {
		String userName = jwtUtils.getUserNameFromJwtToken(headerHelper.getAuthorization());
		return userRepository.findByUsername(userName).get(); 
	}
}
