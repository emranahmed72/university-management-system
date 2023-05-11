package com.example.university_management.acl.auth.repository;

import com.example.university_management.acl.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Role getRoleByRoleName(String role_user);
}
