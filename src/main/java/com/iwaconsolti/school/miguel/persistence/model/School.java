package com.iwaconsolti.school.miguel.persistence.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    /*Map<Integer, Students> students = new HashMap<>();
    Map<Integer, Courses> courses = new HashMap<>();
    Map<Integer, Grade> grades = new HashMap<>();*/
}