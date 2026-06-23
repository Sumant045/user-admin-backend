package com.ideagensys.useradmin.service;

import com.ideagensys.useradmin.config.JwtUtil;
import com.ideagensys.useradmin.dto.*;
import com.ideagensys.useradmin.entity.User;
import com.ideagensys.useradmin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutoService {   // ← FIXED typo: was "AutoService"

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return new RegisterResponse(false, "Email already registered", null, null);
        }

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return new RegisterResponse(false, "Username already taken", null, null);
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);
        String token = jwtUtil.generateToken(savedUser);

        UserData userData = new UserData(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getUsername(),
                savedUser.getEmail()
        );

        return new RegisterResponse(true, "User Registered Successfully", userData, token);
    }

    public LoginResponse login(LoginRequest request) {

        User user = null;

        // Case 1: Email provided → find by email
        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            user = userRepository.findByEmail(request.getEmail()).orElse(null);
        }

        // Case 2: Username provided → find by username
        if (user == null && request.getUsername() != null && !request.getUsername().isBlank()) {
            user = userRepository.findByUsername(request.getUsername()).orElse(null);
        }

        // Case 3: Neither matched
        if (user == null) {
            return new LoginResponse(false, "User Not Found", null, null);
        }

        // Password check
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new LoginResponse(false, "Invalid Password", null, null);
        }

        String token = jwtUtil.generateToken(user);

        UserData userData = new UserData(
                user.getId(),
                user.getFullName(),
                user.getUsername(),
                user.getEmail()
        );

        return new LoginResponse(true, "Login Successful", userData, token);
    }
}