package com.web.student_register.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentDto {
    private Long studentId;
    private String studentName;
    private String dayName;
    private Integer weekNum;
    private String monthName;
    private Integer year;
    private boolean attendance;
    private boolean holiday;


}
