package com.springboot.hospmgmt.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.hospmgmt.dto.MedicalHistoryDTO;
import com.springboot.hospmgmt.service.MedicalHistoryService;

@RestController
@RequestMapping("/api/medical-history")
public class MedicalHistoryController {

    private final MedicalHistoryService service;

    public MedicalHistoryController(MedicalHistoryService service) {
        this.service = service;
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalHistoryDTO>> getMedicalHistoryByPatientId(@PathVariable int patientId) {
        return ResponseEntity.ok(service.getHistoryByPatientId(patientId));
    }

    @PostMapping("/patient/add/{patientId}")
    public ResponseEntity<String> addMedicalHistory(
            @PathVariable int patientId,
            @RequestBody MedicalHistoryDTO dto) {

        String message = service.addMedicalHistory(patientId, dto);
        if (message.equals("Patient not found")) {
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.ok(message);
    }
}
