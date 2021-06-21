package com.sda.javazda75.spring_demo.repository;

import com.sda.javazda75.spring_demo.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// DAO
@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
}
