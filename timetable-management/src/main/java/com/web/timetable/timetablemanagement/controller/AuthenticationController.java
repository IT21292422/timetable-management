package com.web.timetable.timetablemanagement.controller;

import com.web.timetable.timetablemanagement.model.*;
import com.web.timetable.timetablemanagement.repository.RoleRepository;
import com.web.timetable.timetablemanagement.repository.StudentRepository;
import com.web.timetable.timetablemanagement.repository.UserRepository;
import com.web.timetable.timetablemanagement.security.JWTGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepo;
    private RoleRepository roleRepo;
    private StudentRepository stdRepo;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator, StudentRepository stdRepo) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.stdRepo = stdRepo;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequestLogin loginData){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginData.getUsername(),loginData.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication); //Due to this context we don't need to log in each time
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequestRegister registerData){
        if(userRepo.existsByUsername(registerData.getUsername())){
            return new ResponseEntity<>("Username is taken", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerData.getUsername());
        user.setPassword(passwordEncoder.encode(registerData.getPassword())); //This will encode the password

        Role roles = roleRepo.findByName(registerData.getRole()).get(); //Changed from student to registerData.getRole()
        user.setRoles(Collections.singletonList(roles));

        userRepo.save(user);

        if(registerData.getRole().equals("Student")){
            Student student = new Student();
            student.setUser(user);
            stdRepo.save(student);
        }
        return new ResponseEntity<>("User registered successfully",HttpStatus.OK);
    }

}
