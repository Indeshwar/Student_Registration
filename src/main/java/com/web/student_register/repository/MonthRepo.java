package com.web.student_register.repository;

import com.web.student_register.entity.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthRepo extends JpaRepository<Month, Long> {
}
