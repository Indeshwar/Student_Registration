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
public class Month {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long monthId;
    private String monthName;

//    The fix is to get Jackson to be able to handle bi-directional references. And this is done by using two Annotations: @JsonManagedReference and @JsonBackReference.
//    @JsonManagedReference is used to annotate the inverse side while @JsonBackReference maps the owning side of the relationship.
    @JsonBackReference
    @ManyToMany(mappedBy= "months", cascade = CascadeType.ALL)
    private Collection<Student> students = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Week> weeks = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    private Year year;
}
