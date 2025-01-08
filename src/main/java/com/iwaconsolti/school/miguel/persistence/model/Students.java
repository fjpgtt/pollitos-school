package com.iwaconsolti.school.miguel.persistence.model;

import com.iwaconsolti.school.miguel.persistence.model.dto.StudentsDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Entity
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    private int age;

    public Students(){}

    public Students(StudentsDTO dto){
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.age = dto.getAge();
    }
}