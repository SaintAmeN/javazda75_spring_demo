package com.sda.javazda75.spring_demo.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@ApiModel(value = "Basic Student information.",
        description = "This model contains information about student.")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    @ApiModelProperty(value = "First name.", example = "Jan")
    private String name;

    @ApiModelProperty(value = "Last name.", example = "Kowalski")
    private String surname;

    @ApiModelProperty(value = "Date of birth in format yyyy-MM-dd.", example = "2021-06-23")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
}
