package com.example.university_management.module.student;

import com.example.university_management.module.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
  // public Student findByName(String name);
     Student  findByname(String name);

   List<Student> findByTeacher(Teacher teacher);
}
