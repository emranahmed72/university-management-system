package com.example.university_management.module.passwordUpdate;

import com.example.university_management.acl.auth.entity.User;
import com.example.university_management.acl.auth.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/passChange")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PutMapping("/changePass")
    public ResponseEntity<?> changePass(@RequestBody Map<String, String> pass) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = userRepo.findByUserName(currentUserName);
        user.setPassword(bCryptPasswordEncoder.encode(pass.get("password")));
        userRepo.save(user);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }
}
