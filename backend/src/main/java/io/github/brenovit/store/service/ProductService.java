package io.github.brenovit.store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.brenovit.store.repository.Product;
import io.github.brenovit.store.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	public Optional<Product> findById(Long id){
		return repository.findById(id);
	}
	
	public Product save (Product stock) {
		return repository.save(stock);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
