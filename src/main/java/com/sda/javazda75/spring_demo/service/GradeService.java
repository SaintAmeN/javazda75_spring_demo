package com.sda.javazda75.spring_demo.service;

import com.sda.javazda75.spring_demo.model.Grade;
import com.sda.javazda75.spring_demo.model.Student;
import com.sda.javazda75.spring_demo.repository.GradeRepository;
import com.sda.javazda75.spring_demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;

    public List<Grade> getAllStudentGrades(Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            return studentOptional.get().getGradeList();
        }
        // jeśli student nie zostanie znaleziony (to idealnie byłoby rzucić exception) zwracamy pustą listę
        return new ArrayList<>();
    }

    public void addGradeToStudent(Grade grade, Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            // powiązaliśmy studenta z Grade
            grade.setStudent(student);
            gradeRepository.save(grade);
        }
        log.error("Nie udało się dodać oceny.");
    }

    public Optional<Grade> getGradeWithId(Long gradeId) {
        return gradeRepository.findById(gradeId);
    }

    public void removeGrade(Long gradeId) {
        gradeRepository.deleteById(gradeId);
    }
}
