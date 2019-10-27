package io.github.brenovit.store.controller.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.tomcat.jni.Proc;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.brenovit.store.repository.Product;
import io.github.brenovit.store.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductRestController {

	private final ProductService service;

	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		log.info("call - findAll");
		return ResponseEntity.ok(service.findAll());
	}

	@PostMapping
	public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
		log.info("call - create");
		return ResponseEntity.ok(service.save(product));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		log.info("call - findById");
		Optional<Product> stock = service.findById(id);
		if (!stock.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(stock.get());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody Product product) {
		log.info("call - update");
		log.info("received: {} : {}",id, product);
		if (!service.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(service.save(product));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Product> delete(@PathVariable Long id) {
		log.info("call - delete");
		if (!service.findById(id).isPresent()) {
			return ResponseEntity.badRequest().build();
		}

		service.delete(id);

		return ResponseEntity.ok().build();
	}
}
