/*
package com.demo.ngsoft.controllers;

import com.demo.ngsoft.requestObjects.CreateUserRequest;
import com.demo.ngsoft.security.AuthResponse;
import com.demo.ngsoft.security.AuthenticationRequest;
import com.demo.ngsoft.security.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@Validated
@RequiredArgsConstructor
@RestController("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody CreateUserRequest registerRequest){
        return ResponseEntity.ok(authenticationService.registerUser(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthenticationRequest authRequest){
        return ResponseEntity.ok(authenticationService.authenticateUser(authRequest));
    }
}
*/
