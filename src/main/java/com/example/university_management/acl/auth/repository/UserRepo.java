package com.example.university_management.acl.auth.repository;

import com.example.university_management.acl.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByUserName(String username);

    User getUserByUserName(String user);
}
