package com.example.university_management.module.student;

import com.example.university_management.acl.auth.entity.User;
import com.example.university_management.module.department.Department;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String departmentName;

    @ManyToOne(fetch = FetchType.EAGER)
    private Department department;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

}
