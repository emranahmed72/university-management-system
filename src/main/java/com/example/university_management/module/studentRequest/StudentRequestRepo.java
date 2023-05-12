package com.example.university_management.module.studentRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRequestRepo extends JpaRepository<StudentRequest, Long> {

    @Query(value = "select sr from StudentRequest sr where sr.teacher.id = ?1  and sr.reqState = 3")
    List<StudentRequest> getAllTeacherReq(Long teacherId);

}
