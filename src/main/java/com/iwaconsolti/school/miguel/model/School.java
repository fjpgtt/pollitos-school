package com.iwaconsolti.school.miguel.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@ToString
public class School {
    private String name;
    Map<Integer, Students> students = new HashMap<>();
    Map<Integer, Courses> courses = new HashMap<>();
    Map<Integer, Grade> grades = new HashMap<>();

    public School(String name){
        this.name = name;
    }
}
