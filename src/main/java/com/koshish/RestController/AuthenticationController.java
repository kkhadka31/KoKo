package com.koshish.RestController;

import com.koshish.Model.LoginRequest;
import com.koshish.Model.AuthenticationResponse;
import com.koshish.Model.RegisterRequest;
import com.koshish.Service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Inject
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerPerson (
            @RequestBody RegisterRequest registerRequest
    ) {
        try {
            // Process the registration request
            // Assuming you have a method to handle registration and return an AuthenticationResponse
            AuthenticationResponse authenticationResponse = authenticationService.register(registerRequest);

            // Return the response with a 201 status code for successful registration
            return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResponse);
        } catch (Exception e) {
            // Return a 400 status code for bad request
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Bad Request"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginPerson (
            @RequestBody LoginRequest loginRequest
    ) {
//        return ResponseEntity.ok(authenticationService.authenticate(loginRequest));
        try {
            // Process the registration request
            // Assuming you have a method to handle registration and return an AuthenticationResponse
            AuthenticationResponse authenticationResponse = authenticationService.authenticate(loginRequest);

            // Return the response with a 201 status code for successful registration
            return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResponse);
        } catch (Exception e) {
            // Return a 400 status code for bad request
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Bad Request"));
        }
    }
}
