package com.iwaconsolti.school.miguel.model;

import lombok.*;
import java.util.HashMap;
import java.util.Map;

@Data
public class School {
    private final String name;

    public School(String name){
        this.name = name;
    }
    Map<Integer, Students> students = new HashMap<>();
    Map<Integer, Courses> courses = new HashMap<>();
    Map<Integer, Grade> grades = new HashMap<>();
}