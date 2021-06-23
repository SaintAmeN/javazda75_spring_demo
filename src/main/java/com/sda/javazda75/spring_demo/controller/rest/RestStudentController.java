package com.sda.javazda75.spring_demo.controller.rest;

import com.sda.javazda75.spring_demo.controller.view.StudentController;
import com.sda.javazda75.spring_demo.model.Student;
import com.sda.javazda75.spring_demo.model.dto.StudentDto;
import com.sda.javazda75.spring_demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class RestStudentController {
    private final StudentService studentService;

    @GetMapping
    public List<StudentDto> getStudentList(){
        return studentService.getAllStudentInfo();
    }
}
