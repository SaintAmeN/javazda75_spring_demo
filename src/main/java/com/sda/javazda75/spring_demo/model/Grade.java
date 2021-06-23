package com.sda.javazda75.spring_demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data   // Getter Setter Requiredargsconstructor tostring equalshashcode
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double gradeValue;

    @Enumerated(value = EnumType.STRING)
    private GradeSubject gradeSubject;

    @CreationTimestamp
    private LocalDateTime localDateTimeAdded;

    @ManyToOne()
    @ToString.Exclude
    @JsonBackReference
    private Student poleStudent;
}
