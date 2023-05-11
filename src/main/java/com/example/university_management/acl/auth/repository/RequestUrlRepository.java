package com.example.university_management.acl.auth.repository;

import com.example.university_management.acl.auth.entity.RequestUrl;
import com.example.university_management.repository.generic.GenericRepository;
import org.springframework.data.jpa.repository.Query;

public interface RequestUrlRepository extends GenericRepository<RequestUrl> {
    @Query("SELECT e FROM RequestUrl e WHERE e.url = ?1")
    RequestUrl getByUrlPath(String url);
}
