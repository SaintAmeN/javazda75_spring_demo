package com.sda.javazda75.spring_demo.controller.rest;

import com.sda.javazda75.spring_demo.controller.view.StudentController;
import com.sda.javazda75.spring_demo.model.Student;
import com.sda.javazda75.spring_demo.model.dto.ResponseMessage;
import com.sda.javazda75.spring_demo.model.dto.StudentDto;
import com.sda.javazda75.spring_demo.model.mapper.StudentMapper;
import com.sda.javazda75.spring_demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class RestStudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    // status code
    // message
    // body
    @GetMapping
    public ResponseEntity<ResponseMessage<List<StudentDto>>> getStudentList() {
        // Response Entity - klasa java - Ramka HTTP
        //          -- status (code)
        //          -- body
        return ResponseEntity.ok(new ResponseMessage<>(studentService.getAllStudentInfo(), "Response OK!"));
    }

    @GetMapping
    public ResponseEntity<ResponseMessage<StudentDto>> getStudentWithId(Long studentId) {
        // Response Entity - klasa java - Ramka HTTP
        //          -- status (code)
        //          -- body
        Optional<Student> studentOptional = studentService.getStudentWithId(studentId);
        if (studentOptional.isPresent()) {   // jeśli znaleźliśmy studenta
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseMessage<>(studentMapper.getDtoFromStudent(studentOptional.get()), "Response OK!"));
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage<>(null, "Student with given ID does not exist!"));
        }
    }


}
