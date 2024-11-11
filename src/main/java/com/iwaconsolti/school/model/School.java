package com.iwaconsolti.school.model;

import java.util.List;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public abstract class School {

    protected String name;
    protected List<Student> students;
    protected List<Course> courses;
    protected List<Grade> grades;

}
