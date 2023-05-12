package com.example.university_management.module.teacher;

import com.example.university_management.module.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher,Long> {
    public Teacher findByName(String name);
}


