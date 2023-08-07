package com.web.student_register.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Year {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long yearId;
    private Integer year;

    @JsonBackReference
    @ManyToMany(mappedBy = "years", cascade = CascadeType.ALL)
    private Collection<Student> students = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Month> months = new ArrayList<>();

}
