package com.example.university_management.module.admin;

import com.example.university_management.acl.auth.entity.User;
import com.example.university_management.module.department.Department;
import com.example.university_management.module.student.Student;
import com.example.university_management.module.teacher.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/get")
    public ResponseEntity<?> getadmin() {
        Admin admin = this.adminService.get();
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @GetMapping("/deactivateStudent/{id}")
    public ResponseEntity<?> deactivateStudent(@PathVariable(name = "id") Long id) {
        User deactivated = this.adminService.deactivateOrActiveStudent(id);
        return new ResponseEntity<>(deactivated, HttpStatus.OK);
    }

    @GetMapping("/deactivateTeacher/{id}")
    public ResponseEntity<?> deactivateTeacher(@PathVariable(name = "id") Long id) {
        User deactivated = this.adminService.deactivateOrActiveTeacher(id);
        return new ResponseEntity<>(deactivated, HttpStatus.OK);
    }

    @PostMapping("/addTeacher")
    public ResponseEntity<?> addTeacher(@RequestBody Teacher teacher) throws Exception {
        Teacher teacher1 = this.adminService.addTeacher(teacher);
        return new ResponseEntity<>(teacher1, HttpStatus.OK);

    }

    @PostMapping("/addStudent")
    public ResponseEntity<?> addStudent(@RequestBody Student student) throws Exception {
        Student student1 = this.adminService.addStudent(student);
        return new ResponseEntity<>(student1, HttpStatus.OK);
    }

    @PostMapping("/addDepartment")
    public ResponseEntity<?> addDepartment(@RequestBody Department department) {
        Department department1 = this.adminService.addDpt(department);
        return new ResponseEntity<>(department1, HttpStatus.OK);
    }

    @GetMapping("/getAllTeach")
    public ResponseEntity<?> getAllTeach() {
        List<Teacher> teacherList = this.adminService.getAllTeacher();
        return new ResponseEntity<>(teacherList, HttpStatus.OK);
    }

    @GetMapping("/getAllStudent")
    public ResponseEntity<?> getALlStud() {
        List<Student> studentList = this.adminService.getAllStudent();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }


}
