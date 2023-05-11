package com.example.university_management.module.admin;

import com.example.university_management.acl.auth.entity.Role;
import com.example.university_management.acl.auth.entity.User;
import com.example.university_management.acl.auth.repository.RoleRepo;
import com.example.university_management.acl.auth.repository.UserRepo;
import com.example.university_management.acl.auth.service.UserService;
import com.example.university_management.module.student.Student;
import com.example.university_management.module.student.StudentRepo;
import com.example.university_management.module.teacher.Teacher;
import com.example.university_management.module.teacher.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private UserService userService;

    public Admin get(Long id) {
        Admin admin = this.adminRepo.findById(id).orElse(null);
        return admin;
    }

    public Boolean deactivateOrActiveStudent(Long id) {
        Student student = this.studentRepo.findById(id).orElse(null);
        if (student == null) {
            return null;
        } else {
            User user = student.getUser();
            if (!user.getAccountLocked()) {
                user.setAccountLocked(true);
                this.userRepo.save(user);
            } else {
                user.setAccountLocked(false);
                this.userRepo.save(user);
            }
            return true;
        }
    }
    @Autowired
    private TeacherRepo teacherRepo;

    public Boolean deactivateOrActiveTeacher(Long id){
        Teacher teacher=this.teacherRepo.findById(id).orElse(null);
        if(teacher==null){
            return null;
        }
        else {

            User user=teacher.getUser();
            if(user.getAccountLocked()==false){
                user.setAccountLocked(true);
                this.userRepo.save(user);
            }
            else{
                user.setAccountLocked(false);
                this.userRepo.save(user);
            }
            return true;
        }

    }

    @Transactional
    public Student addStudent(Student student) throws Exception {
        Student student1 = this.studentRepo.save(student);//save the specific user in student table
        User user = new User();
        user.setUserName(student1.getName());
        user.setEmail(student1.getEmail());
        user.setPassword("1234");

        Role roleStudent = this.roleRepo.getRoleByRoleName("STUDENT");

        user.setEnabled(true);

        Set<Role> rolesStudentSet = new HashSet<>();
        rolesStudentSet.add(roleStudent);

        user.setRoles(rolesStudentSet);
        user.setAccountLocked(false);
        user.setAccountExpired(false);
        user.setPasswordExpired(false);

        this.userService.createUser(user);//save the specific user in user table

        return student1;
    }

    public Teacher addTeacher(Teacher teacher) throws Exception{
        Teacher teacher1=this.teacherRepo.save(teacher);//save the specific user in teacher table
        User user=new User();
        user.setPassword("1234");
        user.setEmail(teacher1.getEmail());
        user.setUserName(teacher1.getName());

        Role roleTeacher=this.roleRepo.getRoleByRoleName("TEACHER");

        Set <Role> rolesTeacherSet=new HashSet<>();
        rolesTeacherSet.add(roleTeacher);
        user.setRoles(rolesTeacherSet);
        user.setAccountLocked(false);
        user.setAccountExpired(false);
        user.setAccountLocked(false);

        this.userService.createUser(user);//save the specific user in user table

        return teacher1;

    }



}