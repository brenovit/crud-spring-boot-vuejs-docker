package io.github.brenovit.store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.brenovit.store.repository.Product;
import io.github.brenovit.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository repository;
	
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
