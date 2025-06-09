package com.springboot.hospmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.hospmgmt.model.PatientDoctor;

@Repository
public interface PatientDoctorRepository extends JpaRepository<PatientDoctor, Integer> {
    List<PatientDoctor> findByDoctorId(int doctorId);
    List<PatientDoctor> findByPatientId(int patientId);
}
