package com.example.university_management.acl.auth.controller;

import com.example.university_management.acl.auth.entity.jwt.JwtRequest;
import com.example.university_management.acl.auth.entity.jwt.JwtResponse;
import com.example.university_management.acl.auth.springUser.UserServiceImpl;
import com.example.university_management.config.JwtUtils;
import com.example.university_management.exception.InvalidCredentialsException;
import com.example.university_management.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticateController {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private JwtUtils jwtUtils;


    //generate token
    @PostMapping("/generateToken")
    public ResponseEntity<?> generateToken(@Valid @RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

        } catch (NotFoundException e) {
            throw new Exception("User not found");
        }
        //authenticate
        UserDetails userDetails = this.userServiceImpl.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        boolean validate = this.jwtUtils.validateToken(token, userDetails);
        System.out.println("Token is validate " + validate);
        JwtResponse jwtResponse = new JwtResponse(token);
        return ResponseEntity.ok(jwtResponse);
    }

//    @PostMapping("/generateToken")
//    public ResponseEntity<?> generateToken(@Valid @RequestBody JwtRequest jwtRequest) throws Exception {
//        try {
//            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
//        } catch (NotFoundException e) {
//            throw new Exception("User not found");
//        }
//
//        // get user's role from the database
//        User user = UserRepo.findByUsername(jwtRequest.getUsername());
//        Role role = RoleRepo.findByUserId(user.getId());
//
//        // build JWT token with user's role as a claim
//        String token = Jwts.builder()
//                .setSubject(jwtRequest.getUsername())
//                .claim("role", role.getName())
//                .signWith(SignatureAlgorithm.HS256, "secret")
//                .compact();
//
//        JwtResponse jwtResponse = new JwtResponse(token);
//        return ResponseEntity.ok(jwtResponse);
//    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {
            throw new Exception("User Disabled" + e.getMessage());
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Invalid Credentials");
        }
    }


}
