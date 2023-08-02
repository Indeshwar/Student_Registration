package com.web.student_register.repository;

import com.web.student_register.entity.Year;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearRepo extends JpaRepository<Year, Long> {
}
