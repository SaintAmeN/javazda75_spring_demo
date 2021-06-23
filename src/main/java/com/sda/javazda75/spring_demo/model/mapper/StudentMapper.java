package com.sda.javazda75.spring_demo.model.mapper;

import com.sda.javazda75.spring_demo.model.Student;
import com.sda.javazda75.spring_demo.model.dto.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mappings(value = {
            @Mapping(target = "name", source = "firstName"),
            @Mapping(target = "surname", source = "lastName"),
            @Mapping(target = "birthDate", source = "dateOfBirth")
    })
    StudentDto getDtoFromStudent(Student student);

    @Mappings(value = {
            @Mapping(source = "name", target = "firstName"),
            @Mapping(source = "surname", target = "lastName"),
            @Mapping(source = "birthDate", target = "dateOfBirth")
    })
    Student getStudentFromDto(StudentDto studentDto);
}
