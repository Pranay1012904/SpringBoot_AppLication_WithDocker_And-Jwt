package com.blogapp.Practice.controller;

import com.blogapp.Practice.dto.LoginDto;
import com.blogapp.Practice.dto.RegisterDto;
import com.blogapp.Practice.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.boot.micrometer.observation.autoconfigure.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login/api")
@AllArgsConstructor
public class AuthenticationController {

    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<String> log_in(@RequestBody LoginDto loginDto) {
        String resp = authService.logging(loginDto);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    //Register REST API
    @PostMapping(value = {"/register","/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
