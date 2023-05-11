package com.example.university_management.module.admin;

import com.example.university_management.acl.auth.entity.User;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
