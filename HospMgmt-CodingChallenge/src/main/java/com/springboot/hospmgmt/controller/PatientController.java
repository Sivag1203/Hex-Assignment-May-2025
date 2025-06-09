package com.springboot.hospmgmt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.hospmgmt.model.Auth;
import com.springboot.hospmgmt.model.Patient;
import com.springboot.hospmgmt.repository.AuthRepository;
import com.springboot.hospmgmt.security.CustomUserDetailsService;
import com.springboot.hospmgmt.security.JwtUtil;
import com.springboot.hospmgmt.service.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
	
	
    @Autowired
    private PatientService patientService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
	@PostMapping("/register")
    public ResponseEntity<Patient> register(@RequestBody Patient patient) {
        String encodedPassword = passwordEncoder.encode(patient.getAuth().getPassword());
        patient.getAuth().setPassword(encodedPassword);
        Patient saved = patientService.addPatient(patient);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Auth loginRequest) {
        try {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getName());
            if (passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
                Auth auth = authRepository.findByName(loginRequest.getName());
                String token = jwtUtil.generateToken(auth.getName(), auth.getRole());
                return ResponseEntity.ok(Map.of("token", token));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + e.getMessage());
        }
    }
}
