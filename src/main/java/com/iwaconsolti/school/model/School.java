package com.iwaconsolti.school.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class School {

    protected String name;
    protected List<Student> students;
    protected List<Course> courses;
    protected List<Grade> grades;

}
