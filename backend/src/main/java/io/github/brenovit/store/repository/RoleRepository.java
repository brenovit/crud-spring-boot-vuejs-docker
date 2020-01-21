package io.github.brenovit.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.brenovit.store.models.EPermission;
import io.github.brenovit.store.models.Permission;

@Repository
public interface RoleRepository extends JpaRepository<Permission, Long> {
	Optional<Permission> findByName(EPermission name);
}