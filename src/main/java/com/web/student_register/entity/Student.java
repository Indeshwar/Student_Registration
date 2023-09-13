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
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long studentId;
    private String studentName;

    @JsonBackReference
    @ManyToMany(mappedBy = "students")
    private Collection<User> users = new ArrayList<>();
    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_week",
    joinColumns = @JoinColumn(name ="student_id", referencedColumnName = "studentId"),
            inverseJoinColumns = @JoinColumn(name = "week_id", referencedColumnName = "weekId")
    )
    private Collection<Week> weeks = new ArrayList<Week>();

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_month",
    joinColumns=@JoinColumn(name = "student_id", referencedColumnName = "studentId"),
            inverseJoinColumns = @JoinColumn(name = "month_id", referencedColumnName = "monthId")
    )
    private Collection<Month> months = new ArrayList<Month>();

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_year",
    joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "year_id")
    )
    private Collection<Year> years = new ArrayList<Year>();

}
