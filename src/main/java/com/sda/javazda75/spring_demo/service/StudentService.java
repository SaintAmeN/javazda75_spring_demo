package com.sda.javazda75.spring_demo.service;

import com.sda.javazda75.spring_demo.model.Student;
import com.sda.javazda75.spring_demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public void addStudent(Student student) {
        if (!isValid(student)) {
            return;
        }

        // jeśli poprawny, dodaj do bazy
        studentRepository.save(student);
    }

    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    private boolean isValid(Student student) {
        return Objects.nonNull(student) &&
                Objects.nonNull(student.getFirstName()) &&
                Objects.nonNull(student.getLastName());
    }

    public void removeStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Optional<Student> getStudentWithId(Long id) {
        return studentRepository.findById(id);
    }
}
