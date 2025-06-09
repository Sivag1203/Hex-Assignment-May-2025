package com.springboot.hospmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.hospmgmt.model.MedicalHistory;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Integer> {

    @Query("SELECT mh FROM MedicalHistory mh WHERE mh.patient.id =?1")
    List<MedicalHistory> findByPatientId(@Param("patientId") int patientId);
}
