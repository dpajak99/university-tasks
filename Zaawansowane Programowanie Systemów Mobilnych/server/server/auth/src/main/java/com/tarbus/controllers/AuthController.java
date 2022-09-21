package com.tarbus.controllers;

import com.tarbus.exceptions.RequestException;
import com.tarbus.payload.request.LoginRequest;
import com.tarbus.payload.request.SignupRequest;
import com.tarbus.payload.response.JwtResponse;
import com.tarbus.payload.response.SignupResponse;
import com.tarbus.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    try {
      JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
      return ResponseEntity.ok(jwtResponse);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    try {
      SignupResponse signupResponse = authService.registerUser(signUpRequest);
      return new ResponseEntity<>(signupResponse, HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }
}
