package com.example.university_management.module.teacher;

import com.example.university_management.module.student.Student;
import com.example.university_management.module.studentRequest.StudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/teacher")
@CrossOrigin("*")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/getTeacher")
    public ResponseEntity<?> getTeacher() {
        Teacher teacher = this.teacherService.getTeacher();
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @GetMapping("/getRequestList")
    public ResponseEntity<?> getRequestList() {
        List<StudentRequest> studentRequestList = this.teacherService.getAllRequest();
        return new ResponseEntity<>(studentRequestList, HttpStatus.OK);
    }

    @PutMapping("/acceptRequest")
    public ResponseEntity<?> acceptRequest(@RequestBody HashMap<String, Object> data) {

        Integer id = (Integer) data.get("id");
        Boolean acceptStatus = (Boolean) data.get("status");
        Boolean acceptRequest = this.teacherService.acceptRequest(Long.valueOf(id), acceptStatus);
        return new ResponseEntity<>(acceptRequest, HttpStatus.OK);

    }
    @GetMapping("/getAllStudent")
    public ResponseEntity<?> getAllStudent(){
        List<Student>studentList=this.teacherService.getAllStudent();
        return new ResponseEntity<>(studentList,HttpStatus.OK);
    }


}
