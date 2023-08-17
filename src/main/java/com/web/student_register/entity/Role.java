package com.web.student_register.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO )
    private Long roleId;
    private String roleName;

}
