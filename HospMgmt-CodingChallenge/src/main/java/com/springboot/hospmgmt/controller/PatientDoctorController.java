package com.springboot.hospmgmt.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.hospmgmt.dto.AssignPatientToDoctorDTO;
import com.springboot.hospmgmt.model.Doctor;
import com.springboot.hospmgmt.model.Patient;
import com.springboot.hospmgmt.model.PatientDoctor;
import com.springboot.hospmgmt.repository.DoctorRepository;
import com.springboot.hospmgmt.repository.PatientRepository;
import com.springboot.hospmgmt.service.PatientDoctorService;

@RestController
@RequestMapping("/api/appointments")
public class PatientDoctorController {

    private final PatientDoctorService service;
    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;

    public PatientDoctorController(PatientDoctorService service, PatientRepository patientRepo, DoctorRepository doctorRepo) {
        this.service = service;
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
    }

    @PostMapping("/assign")
    public ResponseEntity<?> assignDoctorToPatient(@RequestBody AssignPatientToDoctorDTO request) {
        Patient patient = patientRepo.findById(request.getPatientId()).orElse(null);
        Doctor doctor = doctorRepo.findById(request.getDoctorId()).orElse(null);

        if (patient == null || doctor == null) {
            return ResponseEntity.badRequest().body("Invalid patient or doctor ID");
        }

        PatientDoctor assigned = service.assignDoctorToPatient(patient, doctor);
        return ResponseEntity.ok(assigned);
    }
    
    @GetMapping("/by-doctor/{doctorId}")
    public ResponseEntity<List<PatientDoctor>> getPatientsByDoctor(@PathVariable int doctorId) {
        List<PatientDoctor> list = service.getPatientsByDoctorId(doctorId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/by-patient/{patientId}")
    public ResponseEntity<List<PatientDoctor>> getDoctorsByPatient(@PathVariable int patientId) {
        List<PatientDoctor> list = service.getDoctorsByPatientId(patientId);
        return ResponseEntity.ok(list);
    }
}
