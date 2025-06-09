package com.springboot.hospmgmt.service;

import org.springframework.stereotype.Service;

import com.springboot.hospmgmt.model.Auth;
import com.springboot.hospmgmt.model.Patient;
import com.springboot.hospmgmt.repository.AuthRepository;
import com.springboot.hospmgmt.repository.PatientRepository;

@Service
public class PatientService {
	private final PatientRepository patientRepository;
    private final AuthRepository authRepository;

    public PatientService(PatientRepository patientRepository, AuthRepository authRepository) {
        this.patientRepository = patientRepository;
        this.authRepository = authRepository;
    }

    public Patient addPatient(Patient patient) {
        Auth savedAuth = authRepository.save(patient.getAuth());

        Patient p = new Patient();
        p.setName(patient.getName());
        p.setAge(patient.getAge());
        p.setAuth(savedAuth);
        Patient saved = patientRepository.save(p);
        patient.setId(saved.getId());
        return patient;
    }
}
