package io.github.brenovit.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.github.brenovit.store.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByUserId(Long user);
	
	@Query("SELECT p FROM Product p WHERE p.id = :id AND p.user.id = :userId")
	Optional<Product> findByIdAndUserId(Long id, Long userId);

}
