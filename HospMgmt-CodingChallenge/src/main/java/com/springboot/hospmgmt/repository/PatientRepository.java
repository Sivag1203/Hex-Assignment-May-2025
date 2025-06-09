package com.springboot.hospmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.hospmgmt.model.Patient;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
