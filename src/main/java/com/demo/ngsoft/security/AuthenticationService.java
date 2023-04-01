package com.demo.ngsoft.security;

import com.demo.ngsoft.entities.Role;
import com.demo.ngsoft.entities.User;
import com.demo.ngsoft.errorHandler.ValidationErrorException;
import com.demo.ngsoft.repositories.UserRepo;
import com.demo.ngsoft.requestObjects.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private AuthenticationManager authMng;


    public AuthResponse registerUser(CreateUserRequest registerRequest){
        Optional<User> checkDuplication = userRepo.findByEmail(registerRequest.email());
        if(checkDuplication.isPresent())
            throw new ValidationErrorException("The user: "+registerRequest.email()+" already exists");
        User user = new User(registerRequest);
        user.setRole(Role.chooseRole(user.isAdmin()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return this.generateTokenByUser(user);
    }

    public AuthResponse authenticateUser(AuthenticationRequest request){
        this.authenticateUser(request.getEmail(),request.getPassword());
        return this.generateTokenByUser(userRepo.findByEmail(request.getEmail()).get());
    }

    public void authenticateUser(String email, String pass){
        authMng.authenticate(new UsernamePasswordAuthenticationToken(
                email,
                pass
        ));
    }
    private AuthResponse generateTokenByUser(User user){
        var jwtToken = jwtService.generateToken(user);
       return new AuthResponse(jwtToken);
    }

}
