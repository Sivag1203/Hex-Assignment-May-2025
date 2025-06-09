package com.springboot.hospmgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.hospmgmt.model.Doctor;
import com.springboot.hospmgmt.model.Patient;
import com.springboot.hospmgmt.model.PatientDoctor;
import com.springboot.hospmgmt.repository.PatientDoctorRepository;

@Service
public class PatientDoctorService {

    private final PatientDoctorRepository patientDoctorRepository;

    public PatientDoctorService(PatientDoctorRepository patientDoctorRepository) {
        this.patientDoctorRepository = patientDoctorRepository;
    }

    public PatientDoctor assignDoctorToPatient(Patient patient, Doctor doctor) {
        PatientDoctor pd = new PatientDoctor();
        pd.setPatient(patient);
        pd.setDoctor(doctor);
        return patientDoctorRepository.save(pd);
    }

    public List<PatientDoctor> getPatientsByDoctorId(int doctorId) {
        return patientDoctorRepository.findByDoctorId(doctorId);
    }

    public List<PatientDoctor> getDoctorsByPatientId(int patientId) {
        return patientDoctorRepository.findByPatientId(patientId);
    }
}
