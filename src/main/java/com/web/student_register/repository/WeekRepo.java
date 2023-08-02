package com.web.student_register.repository;

import com.web.student_register.entity.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekRepo extends JpaRepository<Week, Long> {
}
