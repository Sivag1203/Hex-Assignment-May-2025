package com.springboot.hospmgmt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.springboot.hospmgmt.model.Auth;
import com.springboot.hospmgmt.model.Doctor;
import com.springboot.hospmgmt.repository.AuthRepository;
import com.springboot.hospmgmt.security.CustomUserDetailsService;
import com.springboot.hospmgmt.security.JwtUtil;
import com.springboot.hospmgmt.service.DoctorService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/register")
    public ResponseEntity<Doctor> register(@RequestBody Doctor doctor) {
        String encodedPassword = passwordEncoder.encode(doctor.getAuth().getPassword());
        doctor.getAuth().setPassword(encodedPassword);
        Doctor saved = doctorService.addDoctor(doctor);
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
