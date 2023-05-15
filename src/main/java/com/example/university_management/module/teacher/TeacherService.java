package com.example.university_management.module.teacher;

import com.example.university_management.module.student.Student;
import com.example.university_management.module.student.StudentRepo;
import com.example.university_management.module.studentRequest.StudentRequest;
import com.example.university_management.module.studentRequest.StudentRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private StudentRequestRepo studentRequestRepo;
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private StudentRepo studentRepo;

    public Teacher getTeacher() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Teacher teacher = this.teacherRepo.findByName(currentUserName);
        return teacher;
    }

    public List<StudentRequest> getAllRequest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Teacher teacher = this.teacherRepo.findByName(currentUserName);

        if (teacher == null) {
            return null;
        } else {
            List<StudentRequest> studentRequestList = this.studentRequestRepo.getAllTeacherReq(teacher.getId());
            return studentRequestList;
        }
    }

    @Transactional
    public Boolean acceptRequest(Long studentRequestId, Boolean acceptStatus) {
        StudentRequest studentRequest = this.studentRequestRepo.findById(studentRequestId).orElse(null);
        if (studentRequest == null) {
            return false;
        } else {
            if (studentRequest.getReqState() == 3 && acceptStatus) {
                studentRequest.setReqState(1);//1 means accepted
                Student student = this.studentRepo.findById(studentRequest.getStudent().getId()).orElse(null);
                student.setTeacher(studentRequest.getTeacher());
                studentRepo.save(student);////update in student request table
            } else if (studentRequest.getReqState() == 3 && !acceptStatus) {
                studentRequest.setReqState(2);//2 means rejected.
            }
            this.studentRequestRepo.save(studentRequest);//update in student request table
            return true;
        }
    }
    public List<Student> getAllStudent(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Teacher teacher = this.teacherRepo.findByName(currentUserName);

        if (teacher == null) {
            return null;
        } else {
            List<Student> studentList = this.studentRepo.findByTeacher(teacher);
            return studentList;
        }

    }


}
