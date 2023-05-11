package com.example.university_management.repository.system;

import com.example.university_management.entity.system.SystemMenu;
import com.example.university_management.repository.generic.GenericRepository;

import java.util.Optional;

public interface SystemMenuRepository extends GenericRepository<SystemMenu> {

    boolean existsByCode(String system);

    SystemMenu getByCode(String system);

    SystemMenu findByCode(String auth_user);


    SystemMenu findSystemMenuByRequestUrl(String url);

    SystemMenu findSystemMenuByUrl(String url);

    Optional<SystemMenu> findByUrl(String reqUrl);

}
