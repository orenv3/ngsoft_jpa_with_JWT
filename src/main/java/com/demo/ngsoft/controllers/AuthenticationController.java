package com.demo.ngsoft.controllers;

import com.demo.ngsoft.errorHandler.UserValidationErrorException;
import com.demo.ngsoft.requestObjects.CreateUserRequest;
import com.demo.ngsoft.security.AuthResponse;
import com.demo.ngsoft.security.AuthenticationRequest;
import com.demo.ngsoft.security.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Validated
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "AuthenticationController", description = "The Authentication API. " +
        "Responsible on user authentications and adding new users to the system(create users).")
@RequestMapping("/api/auth/")
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("admin/registerUser")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody CreateUserRequest registerRequest){
        try {
            return ResponseEntity.ok(authenticationService.registerUser(registerRequest));
        } catch (UserValidationErrorException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("user/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthenticationRequest authRequest){
        return ResponseEntity.ok(authenticationService.authenticateUser(authRequest));
    }
}
