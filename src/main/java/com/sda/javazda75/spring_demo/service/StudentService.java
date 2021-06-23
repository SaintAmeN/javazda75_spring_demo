package com.sda.javazda75.spring_demo.service;

import com.sda.javazda75.spring_demo.model.Student;
import com.sda.javazda75.spring_demo.model.dto.StudentDto;
import com.sda.javazda75.spring_demo.model.mapper.StudentMapper;
import com.sda.javazda75.spring_demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public void addStudent(Student student) {
        if (!isValid(student)) {
            return;
        }

        // jeśli poprawny, dodaj do bazy
        studentRepository.save(student);
    }

    public boolean addStudent(StudentDto studentDto) {
        Student student = studentMapper.getStudentFromDto(studentDto);
        if (!isValid(student)) {
            return false;
        }

        // jeśli poprawny, dodaj do bazy
        studentRepository.save(student);
        return true;
    }

    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    public List<StudentDto> getAllStudentInfo(){
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::getDtoFromStudent)
                .collect(Collectors.toList());
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

    public boolean removeStudentWithId(Long id) {
        Optional<Student> studentOptional = getStudentWithId(id);
        if(studentOptional.isPresent()){
            removeStudentWithId(id);
            return true;
        }
        return false;
    }

    public Optional<StudentDto> update(Long id, StudentDto studentDto) {
        Optional<Student> studentOptional = getStudentWithId(id);
        if(studentOptional.isPresent()){
            Student student = studentOptional.get();

            studentMapper.update(studentDto, student);
            return Optional.of(studentMapper.getDtoFromStudent(studentRepository.save(student)));
        }
        return Optional.empty();
    }
}
