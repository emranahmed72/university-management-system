package com.example.university_management.repository.system;

import com.example.university_management.entity.system.SystemMenu;
import com.example.university_management.entity.system.SystemMenuAuthorization;
import com.example.university_management.repository.generic.GenericRepository;

public interface SystemMenuAuthorizationRepository extends GenericRepository<SystemMenuAuthorization> {
    SystemMenuAuthorization findBySystemMenu(SystemMenu menuInst_system);

    SystemMenuAuthorization findByUsernameAndSystemMenu(String username, SystemMenu systemMenu);

    SystemMenuAuthorization findByUsername(String username);
}
