package com.demo.ngsoft.controllers;

import com.demo.ngsoft.requestObjects.CreateUserRequest;
import com.demo.ngsoft.security.AuthResponse;
import com.demo.ngsoft.security.AuthenticationRequest;
import com.demo.ngsoft.security.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/auth/")
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("admin/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody CreateUserRequest registerRequest){
        return ResponseEntity.ok(authenticationService.registerUser(registerRequest));
    }

    @PostMapping("user/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthenticationRequest authRequest){
        return ResponseEntity.ok(authenticationService.authenticateUser(authRequest));
    }
}
