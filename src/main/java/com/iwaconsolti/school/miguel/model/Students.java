package com.iwaconsolti.school.miguel.model;

import com.iwaconsolti.school.miguel.model.dto.StudentsDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Students {
    private int id;
    private String firstName;
    private String lastName;
    private int age;

    public Students(){}

    public Students(StudentsDTO studentsDTO){
        this.id = studentsDTO.getId();
        this.firstName = studentsDTO.getFirstName();
        this.lastName = studentsDTO.getLastName();
        this.age = studentsDTO.getAge();
    }
}