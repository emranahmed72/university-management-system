package com.example.university_management.acl.auth.entity.repository;

import com.example.university_management.acl.auth.entity.settings.AuthType;
import com.example.university_management.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthTypeRepository extends GenericRepository<AuthType> {
    AuthType findByAuthType(String url_based);
}

