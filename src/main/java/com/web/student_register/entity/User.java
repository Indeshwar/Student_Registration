package com.web.student_register.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO )
    private Long userId;
    private String userName;
    private String password;

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="user_student",
    joinColumns = @JoinColumn(name="user_id", referencedColumnName="userId" ),
            inverseJoinColumns = @JoinColumn(name="student_id", referencedColumnName= "studentId")
    )
    private Collection<Student> students = new ArrayList<>();

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="user_teacher",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName="userId" ),
            inverseJoinColumns = @JoinColumn(name="teacher_id", referencedColumnName= "teacherId")
    )
    private Collection<Teacher> teachers = new ArrayList<>();

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable( name = "user_role",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName= "userId" ),
            inverseJoinColumns = @JoinColumn(name= "role_id", referencedColumnName="roleId")
    )
    private Collection<Role> roles;

}
