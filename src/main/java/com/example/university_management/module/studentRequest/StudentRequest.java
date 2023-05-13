package com.example.university_management.module.studentRequest;

import com.example.university_management.module.student.Student;
import com.example.university_management.module.teacher.Teacher;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
public class StudentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    private Student student;

     @ManyToOne(fetch = FetchType.EAGER)
     @JoinColumn(name = "student_id", nullable = false)
     private Student student;

    @ManyToOne
    private Teacher teacher;

    // if 1 request accepted, 2 rejected, 3 submit
    private Integer reqState;



    public Integer getReqState() {
        return reqState;
    }

    public void setReqState(Integer reqState) {
        this.reqState = reqState;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

}
