package com.backend.assetmanagement.repository;

import com.backend.assetmanagement.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Integer> {
	Auth findByEmail(String email);
}
