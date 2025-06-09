package com.springboot.hospmgmt.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.hospmgmt.model.Auth;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Integer> {
	Auth findByName(String name);
}
