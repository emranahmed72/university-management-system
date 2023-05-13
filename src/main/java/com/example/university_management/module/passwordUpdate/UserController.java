package com.example.university_management.module.passwordUpdate;

import com.example.university_management.acl.auth.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/passChange")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private PasswordService passwordService;

    @PutMapping("/changePass")
    public ResponseEntity<?> changePass(@RequestBody Map<String, String> pass) {

        passwordService.passwordChange(pass.get("password"));

        return new ResponseEntity<>("Done", HttpStatus.OK);
    }
}
