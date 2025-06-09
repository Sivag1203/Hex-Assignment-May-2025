package com.springboot.hospmgmt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.springboot.hospmgmt.enums.Speciality;
import com.springboot.hospmgmt.model.Auth;
import com.springboot.hospmgmt.model.Doctor;
import com.springboot.hospmgmt.model.Patient;
import com.springboot.hospmgmt.model.PatientDoctor;
import com.springboot.hospmgmt.repository.PatientDoctorRepository;
import com.springboot.hospmgmt.service.PatientDoctorService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PatientDoctorServiceTest {

    @Mock
    private PatientDoctorRepository repository;

    @InjectMocks
    private PatientDoctorService service;

    private PatientDoctor pd1;
    private PatientDoctor pd2;

    @BeforeEach
    public void setUp() {
        Auth doctorAuth = new Auth();
        doctorAuth.setId(100);
        doctorAuth.setName("dr.ram");
        doctorAuth.setPassword("password");

        Auth patient1Auth = new Auth();
        patient1Auth.setId(200);
        patient1Auth.setName("john123");
        patient1Auth.setPassword("password");

        Auth patient2Auth = new Auth();
        patient2Auth.setId(300);
        patient2Auth.setName("alice456");
        patient2Auth.setPassword("password");

        Doctor doctor = new Doctor();
        doctor.setId(100);
        doctor.setName("Dr. Ram");
        doctor.setSpeciality(Speciality.PHYSICIAN); 
        doctor.setAuth(doctorAuth);

        Patient patient1 = new Patient();
        patient1.setId(101);
        patient1.setName("John");
        patient1.setAge(30);
        patient1.setAuth(patient1Auth);

        Patient patient2 = new Patient();
        patient2.setId(102);
        patient2.setName("Alice");
        patient2.setAge(28);
        patient2.setAuth(patient2Auth);

        pd1 = new PatientDoctor();
        pd1.setDoctor(doctor);
        pd1.setPatient(patient1);

        pd2 = new PatientDoctor();
        pd2.setDoctor(doctor);
        pd2.setPatient(patient2);

        when(repository.findByDoctorId(100)).thenReturn(Arrays.asList(pd1, pd2));
    }

    @Test
    public void testGetPatientsByDoctorId_Success() {
        List<PatientDoctor> result = service.getPatientsByDoctorId(100);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getPatient().getName());
        assertEquals("Alice", result.get(1).getPatient().getName());
    }
    
    @AfterEach
    public void cleanUp() {
        pd1 = null;
        pd2 = null;
    }
}
