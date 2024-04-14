package com.koshish.Service;

import com.koshish.Model.LoginRequest;
import com.koshish.Model.AuthenticationResponse;
import com.koshish.Model.Person;
import com.koshish.Model.RegisterRequest;
import com.koshish.Repository.PersonDLImpl;
import com.koshish.Repository.UserCredentialsDLImpl;
import com.koshish.Security.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JWTUtil jwtUtil = new JWTUtil();
    @Inject
    private final AuthenticationManager authenticationManager;
    @Inject
    private final PasswordEncoder passwordEncoder;
    @Inject
    private PersonDLImpl personDLImpl;

    public AuthenticationResponse register(RegisterRequest request) {

        Person person = new Person();//Create person from the request
        //save the person now

        String firstName = request.getFirstName();
        String secondName = request.getSecondName();
        String fullName = request.getFullName();
        String password = passwordEncoder.encode(request.getPassword());
        String email = request.getEmail();
        String gender = request.getGender();
        String phoneNumber = request.getPhoneNumber();
        String userName = request.getUserName();
        String role = String.valueOf(request.getRole());

        UserCredentialsDLImpl userCredentialsDL = new UserCredentialsDLImpl();
        userCredentialsDL.createUser(firstName, secondName, fullName, password, email, gender, phoneNumber, userName, role);

        var jwtToken = jwtUtil.generateToken(person);
        return AuthenticationResponse.builder().token(jwtToken).build();

    }

    public AuthenticationResponse authenticate(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUserName(),
                loginRequest.getPassword()));
        Person person = personDLImpl.getPersonByUserName(loginRequest.getUserName());
        var jwtToken = jwtUtil.generateToken(person);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

// For later integration
    //        PasswordService passwordService = new PasswordService();
//        if (!userCredentialsDL.userExists(userName)) {
//            passwordService.storePasswordWithSalt(userName, password);
//            userCredentialsDL.createUser(firstName, secondName, fullName, email, gender, phoneNumber, userName, role);
//            PersonDLImpl personDL = new PersonDLImpl();
//            person = personDL.getPerson(userName);
//        }
}
