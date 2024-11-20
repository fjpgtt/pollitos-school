package com.iwaconsolti.school.miguel.persistence.model.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class StudentsDTO {

    private int id;
    private String firstName;
    private String lastName;
    private int age;
}

