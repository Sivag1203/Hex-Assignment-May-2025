package com.springboot.hospmgmt.service;

import org.springframework.stereotype.Service;

import com.springboot.hospmgmt.model.Auth;
import com.springboot.hospmgmt.model.Doctor;
import com.springboot.hospmgmt.repository.AuthRepository;
import com.springboot.hospmgmt.repository.DoctorRepository;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final AuthRepository authRepository;

    public DoctorService(DoctorRepository doctorRepository, AuthRepository authRepository) {
        this.doctorRepository = doctorRepository;
        this.authRepository = authRepository;
    }

    public Doctor addDoctor(Doctor doctor) {
        Auth savedAuth = authRepository.save(doctor.getAuth());

        Doctor d = new Doctor();
        d.setName(doctor.getName());
        d.setSpeciality(doctor.getSpeciality());
        d.setAuth(savedAuth);

        Doctor saved = doctorRepository.save(d);
        doctor.setId(saved.getId());
        return doctor;
    }
}
