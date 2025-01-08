package com.iwaconsolti.school.miguel.persistence.model.dto;

import com.iwaconsolti.school.miguel.persistence.model.Students;
import lombok.Data;

@Data
public class StudentsDTO {

    private int id;
    private String firstName;
    private String lastName;
    private int age;

    public StudentsDTO(){}

    public StudentsDTO(Students students){
        this.id = students.getId();
        this.firstName = students.getFirstName();
        this.lastName = students.getLastName();
        this.age = students.getAge();
    }
}

