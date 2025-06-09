package com.springboot.hospmgmt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.hospmgmt.dto.MedicalHistoryDTO;
import com.springboot.hospmgmt.model.MedicalHistory;
import com.springboot.hospmgmt.model.Patient;
import com.springboot.hospmgmt.repository.MedicalHistoryRepository;
import com.springboot.hospmgmt.repository.PatientRepository;

@Service
public class MedicalHistoryService {

    private final MedicalHistoryRepository repo;
    private final PatientRepository patientRepo;

    public MedicalHistoryService(MedicalHistoryRepository repo, PatientRepository patientRepo) {
        this.repo = repo;
        this.patientRepo = patientRepo;
    }

    public List<MedicalHistoryDTO> getHistoryByPatientId(int patientId) {
        List<MedicalHistory> records = repo.findByPatientId(patientId);
        List<MedicalHistoryDTO> result = new ArrayList<>();

        for (MedicalHistory mh : records) {
            MedicalHistoryDTO dto = new MedicalHistoryDTO();
            dto.setIllness(mh.getIllness());
            dto.setNumOfYears(mh.getNumOfYears());
            dto.setCurrentMedication(mh.getCurrentMedication());
            result.add(dto);
        }

        return result;
    }

    public String addMedicalHistory(int patientId, MedicalHistoryDTO dto) {
        Patient patient = patientRepo.findById(patientId).orElse(null);
        if (patient == null) {
            return "Patient not found";
        }

        MedicalHistory history = new MedicalHistory();
        history.setIllness(dto.getIllness());
        history.setNumOfYears(dto.getNumOfYears());
        history.setCurrentMedication(dto.getCurrentMedication());
        history.setPatient(patient);

        repo.save(history);
        return "Medical record added successfully";
    }
}
