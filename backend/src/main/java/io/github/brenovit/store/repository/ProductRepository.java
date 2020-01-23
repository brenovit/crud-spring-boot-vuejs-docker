package io.github.brenovit.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.brenovit.store.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByUserId(Long id);

}
