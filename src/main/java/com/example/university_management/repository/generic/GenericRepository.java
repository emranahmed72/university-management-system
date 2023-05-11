package com.example.university_management.repository.generic;

import com.example.university_management.entity.system.baseEnitiy.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericRepository <T extends BaseEntity> extends JpaRepository<T, Long> {
}
