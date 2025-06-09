package com.springboot.hospmgmt.dto;

import org.springframework.stereotype.Component;

@Component
public class AssignPatientToDoctorDTO {
	private int patientId;
    private int doctorId;
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
    
}
