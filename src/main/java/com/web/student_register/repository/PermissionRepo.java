package com.web.student_register.repository;

import com.web.student_register.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepo extends JpaRepository<Permission, Long> {
    Permission getBypermissionName(String permissionName);
}
