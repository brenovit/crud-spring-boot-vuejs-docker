package io.github.brenovit.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.brenovit.store.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
