package com.example.university_management.module.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getStudent/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable(value = "id") Long id){
        Student student=this.studentService.getStudent(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
    @GetMapping("/addRequest/{id}")
    public ResponseEntity<?> addRequest(@PathVariable(value = "id") Long id){
        Boolean add=this.studentService.addRequest(id);
        return new ResponseEntity<>(add,HttpStatus.OK);
    }

}
