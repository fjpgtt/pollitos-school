package com.iwaconsolti.school.miguel.persistence.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private final String name;

    public School(String name){
        this.name = name;
    }

    /*Map<Integer, Students> students = new HashMap<>();
    Map<Integer, Courses> courses = new HashMap<>();
    Map<Integer, Grade> grades = new HashMap<>();*/
}