package com.sda.javazda75.spring_demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

// POJO - Plain Old Java Object
// - klasa modelu (data)
// - zasłonięte pola (private)
// - gettery/settery
// - konstruktor bezargumentowy

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "poleStudent", fetch = FetchType.EAGER)
    private List<Grade> gradeList;
}
