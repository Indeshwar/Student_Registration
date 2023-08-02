package com.web.student_register.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Week {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long weekId;
    private Integer weekNum;
    private Integer sunday;
    private Integer monday;
    private Integer tuesday;
    private Integer wednesday;
    private Integer thursday;
    private Integer friday;
    private Integer saturday;
    private Integer totalPresentDay = 0;
    private Integer totalAbsentDay = 0;
    private Integer totalHoliday = 0;
    private Integer consecutiveDay = 0;

    @JsonBackReference
    @ManyToMany(mappedBy = "weeks", cascade = CascadeType.ALL)
    private Collection<Student> students = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    private Month month;


    public void setDay(Integer value, String dayName){
        if(dayName.equalsIgnoreCase("sunday")){
            this.sunday = value;
        }
        if(dayName.equalsIgnoreCase("monday")){
            this.monday = value;
        }

        if(dayName.equalsIgnoreCase("tuesday")){
            this.tuesday = value;
        }

        if(dayName.equalsIgnoreCase("wednesday")){
            this.wednesday = value;
        }

        if(dayName.equalsIgnoreCase("thursday")){
            this.thursday = value;
        }

        if(dayName.equalsIgnoreCase("friday")){
            this.friday = value;
        }

        if(dayName.equalsIgnoreCase("saturday")){
            this.saturday = value;
        }

    }



}
