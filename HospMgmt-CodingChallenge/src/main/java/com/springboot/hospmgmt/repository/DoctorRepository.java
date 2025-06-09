package com.springboot.hospmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.hospmgmt.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
