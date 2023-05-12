package com.example.university_management.module.student;

import com.example.university_management.module.studentRequest.StudentRequest;
import com.example.university_management.module.studentRequest.StudentRequestRepo;
import com.example.university_management.module.teacher.Teacher;
import com.example.university_management.module.teacher.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRequestRepo studentRequestRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private TeacherRepo teacherRepo;

    public Student getStudent(Long studentId){
        return this.studentRepo.findById(studentId).orElse(null);
    }

    public Boolean addRequest(Long teacherId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Student student = this.studentRepo.findByName(currentUserName);

        Teacher teacher = this.teacherRepo.findById(teacherId).orElse(null);

        if(teacher == null) {
            return false;
        }
        else {
            StudentRequest studentRequest = new StudentRequest();
            studentRequest.setStudent(student);
            studentRequest.setTeacher(teacher);
            studentRequest.setReqState(3);
            studentRequestRepo.save(studentRequest);
            return true;

        }
    }

}
