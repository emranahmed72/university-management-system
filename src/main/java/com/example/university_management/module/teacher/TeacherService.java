package com.example.university_management.module.teacher;

import com.example.university_management.module.studentRequest.StudentRequest;
import com.example.university_management.module.studentRequest.StudentRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private StudentRequestRepo studentRequestRepo;
    @Autowired
    private TeacherRepo teacherRepo;

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

    public Boolean acceptRequest(Long id, Boolean acceptStatus) {
        StudentRequest studentRequest = this.studentRequestRepo.findById(id).orElse(null);
        if (studentRequest == null) {
            return false;
        } else {
            if (studentRequest.getReqState() == 3 && acceptStatus) {
                studentRequest.setReqState(1);//1 means accepted
            } else if (studentRequest.getReqState() == 3 && !acceptStatus) {
                studentRequest.setReqState(2);//2 means rejected.
            }

            return true;
        }


    }


}
