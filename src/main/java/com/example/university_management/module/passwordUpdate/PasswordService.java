package com.example.university_management.module.passwordUpdate;

import com.example.university_management.acl.auth.entity.Role;
import com.example.university_management.acl.auth.entity.User;
import com.example.university_management.acl.auth.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PasswordService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Boolean passwordChange(String password) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = userRepo.findByUserName(currentUserName);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepo.save(user);
        return true;
    }

    public Set<Role> getUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = userRepo.findByUserName(currentUserName);
        return user.getRoles();
    }

}
