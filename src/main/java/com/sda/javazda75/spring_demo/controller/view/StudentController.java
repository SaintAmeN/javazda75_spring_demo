package com.sda.javazda75.spring_demo.controller.view;

import com.sda.javazda75.spring_demo.model.Student;
import com.sda.javazda75.spring_demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping()
    public String getAllStudents(Model model){
        List<Student> studentList = studentService.getAll();

        model.addAttribute("lista_studencikow", studentList);
        return "student-list";
    }

    @GetMapping("/details")
    public String getAllStudents(Model model, Long studentId){
        Optional<Student> studentDetails = studentService.getStudentWithId(studentId);
        if(studentDetails.isPresent()) {
            model.addAttribute("szczegoly_studenta", studentDetails.get());

            return "student-details";
        }

        return "redirect:/student";
    }

    // aby móc usunąć studenta
    @GetMapping("/remove")
    public String removeStudent(@RequestParam(name = "id") Long identyfikatorek){
        studentService.removeStudent(identyfikatorek);

        // po tym jak usuniemy studenta wracamy na stronę z listą (lub wrócimy do miejsca które nas redirectowało)
        return "redirect:/student";
    }

    // aby móc usunąć studenta
    @GetMapping("/edit")
    public String editStudent(Model model, @RequestParam(name = "id_student_to_edit") Long id){
        Optional<Student> studentToEdit = studentService.getStudentWithId(id);
        if(studentToEdit.isPresent()){
            model.addAttribute("nowy_student", studentToEdit.get());

            log.info("Student do edycji: " + studentToEdit);
            return "student-add";
        }
        // jeśli nie udało się znaleźć studenta, to wracamy na listę studentów
        return "redirect:/student";
    }

    // aby móc wyświetlić formularz
    @GetMapping("/add")
    public String addStudentForm(Model model){
        Student student = new Student();

        // dla użytkownika wysyłam obiekt studenta który ma być wypełniony w formularzu
        model.addAttribute("nowy_student", student);
        return "student-add";
    }

    // aby móc dodać rekord do bazy
    @PostMapping("/add")
    public String addStudent(Student student){
        log.info("Student do zapisu: " + student);
        studentService.addStudent(student);
        return "redirect:/student";
    }
}
