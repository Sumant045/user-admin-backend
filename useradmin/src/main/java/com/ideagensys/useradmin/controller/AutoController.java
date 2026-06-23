package com.ideagensys.useradmin.controller;

import com.ideagensys.useradmin.dto.LoginRequest;
import com.ideagensys.useradmin.dto.LoginResponse;
import com.ideagensys.useradmin.dto.RegisterRequest;
import com.ideagensys.useradmin.dto.RegisterResponse;
import com.ideagensys.useradmin.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutoController {

    @Autowired
    private AutoService autoService;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        return autoService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return autoService.login(request);
    }
}