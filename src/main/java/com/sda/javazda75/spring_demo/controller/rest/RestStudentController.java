package com.sda.javazda75.spring_demo.controller.rest;

import com.sda.javazda75.spring_demo.controller.view.StudentController;
import com.sda.javazda75.spring_demo.model.Student;
import com.sda.javazda75.spring_demo.model.dto.ResponseMessage;
import com.sda.javazda75.spring_demo.model.dto.StudentDto;
import com.sda.javazda75.spring_demo.model.mapper.StudentMapper;
import com.sda.javazda75.spring_demo.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation(value = "Returns full list of student information.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List successfully returned."),
            @ApiResponse(code = 403, message = "Access to this call is forbidden."),
            @ApiResponse(code = 404, message = "Call with such method does not exist."),
            @ApiResponse(code = 415, message = "List can't be retrieved and is not supported."),
            @ApiResponse(code = 500, message = "An unexpected error has occurred. Error should be investigated.")})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<ResponseMessage<List<StudentDto>>> getStudentList() {
        // Response Entity - klasa java - Ramka HTTP
        //          -- status (code)
        //          -- body
        return ResponseEntity.ok(new ResponseMessage<>(studentService.getAllStudentInfo(), "Response OK!"));
    }

    // localhost:8080/api/student/32
    //                            ^^
    //                            identyfikator
    @GetMapping("/{studentId}")
    public ResponseEntity<ResponseMessage<StudentDto>> getStudentWithId(@PathVariable Long studentId) {
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

    // dodawanie
    @PostMapping()
    public ResponseEntity<ResponseMessage<StudentDto>> addStudent(@RequestBody StudentDto dtoOfStudentToAdd) {
        boolean result = studentService.addStudent(dtoOfStudentToAdd);
        if(result){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // usuwanie
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> removeStudent(@PathVariable Long id) {
        boolean result = studentService.removeStudentWithId(id);
        if(result){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // GET - pobieramy
    // POST - wstawiamy
    // PUT - zastępujemy
    // PATCH - aktualizujemy (części)
    // DELETE - usuwanie
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseMessage<StudentDto>> updateStudent(@PathVariable Long id,
                                                                     @RequestBody StudentDto studentDto) {
        Optional<StudentDto> resultOpt = studentService.update(id, studentDto);
        if(resultOpt.isPresent()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage<>(resultOpt.get(), "Student updated!"));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage<>(null, "Student does not exist!"));
        }
    }

}
