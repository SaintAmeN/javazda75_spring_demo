package com.sda.javazda75.spring_demo.controller;

import com.sda.javazda75.spring_demo.model.Grade;
import com.sda.javazda75.spring_demo.model.GradeSubject;
import com.sda.javazda75.spring_demo.model.Student;
import com.sda.javazda75.spring_demo.service.GradeService;
import com.sda.javazda75.spring_demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/grade")
@RequiredArgsConstructor
public class GradeController {
    private final GradeService gradeService;

    // aby móc wyświetlić formularz
    @GetMapping("/add")
    public String addGradeForm(Model model, Long student_to_whom_we_want_to_add_grade){
        // dla użytkownika wysyłam obiekt studenta który ma być wypełniony w formularzu
        model.addAttribute("newGrade", new Grade());
        model.addAttribute("lista_przedmiotow", GradeSubject.values());
        model.addAttribute("hiddenStudentId", student_to_whom_we_want_to_add_grade);
        return "grade-add";
    }

    @GetMapping("/edit")
    public String editGradeForm(Model model, Long gradeId){
        Optional<Grade> gradeWithId = gradeService.getGradeWithId(gradeId);
        if(gradeWithId.isPresent()) {
            Grade grade = gradeWithId.get();
            model.addAttribute("newGrade", grade);
            model.addAttribute("lista_przedmiotow", GradeSubject.values());
            model.addAttribute("hiddenStudentId", grade.getStudent().getId());

            return "grade-add";
        }
        return "reditect:/student";
    }

    // aby móc usunąć studenta
    @GetMapping("/remove")
    public String removeStudent(Long gradeId, Long studentId){
        gradeService.removeGrade(gradeId);

        // po tym jak usuniemy studenta wracamy na stronę z listą (lub wrócimy do miejsca które nas redirectowało)
        return "redirect:/student/details?studentId="+ studentId;
    }


    @PostMapping("/add")
    public String addGrade(Grade newGrade, Long student_id){
        log.info("Grade to add: " + newGrade);
        gradeService.addGradeToStudent(newGrade, student_id);
        return "redirect:/student/details?studentId="+student_id;
    }
}
