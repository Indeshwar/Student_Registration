package com.web.student_register.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Permission {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO )
    private Long permissionId;
    private String permissionName;

    @JsonBackReference
    @ManyToMany(mappedBy="permissions")
    private Set<Role> roles;


}
