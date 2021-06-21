package com.sda.javazda75.spring_demo.repository;

import com.sda.javazda75.spring_demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// CRUD

// DAO - Data Access Object - obiekt dostępu do danych
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Zapytanie SQL jest automatycznie wygenerowane i nie musimy go pisać.
    List<Student> findAllByFirstNameAndLastName(String first, String last);
}
