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

    @GetMapping("/getTeacher/{id}")
    public ResponseEntity<?> getTeacher(@PathVariable(name = "id") Long id) {
        Teacher teacher = this.teacherService.getTeacher(id);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @GetMapping("/getRequestList")
    public ResponseEntity<?> getRequestList() {
        List<StudentRequest> studentRequestList = this.teacherService.getAllRequest();
        return new ResponseEntity<>(studentRequestList, HttpStatus.OK);
    }

    @PutMapping("/acceptRequest")
    public ResponseEntity<?> acceptRequest(@RequestBody HashMap<String, Object> data) {

        Long id = (Long) data.get("id");
        Boolean acceptStatus = (Boolean) data.get("status");
        Boolean acceptRequest = this.teacherService.acceptRequest(id, acceptStatus);
        return new ResponseEntity<>(acceptRequest, HttpStatus.OK);

    }
    @GetMapping("/getAllStudent")
    public ResponseEntity<?> getAllStudent(){
        List<Student>studentList=this.teacherService.getAllStudent();
        return new ResponseEntity<>(studentList,HttpStatus.OK);
    }


}
