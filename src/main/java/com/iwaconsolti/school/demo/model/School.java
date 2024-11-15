package com.iwaconsolti.school.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class School{
    private String name;
    private final List<Student> studentsList;
    private final List<Course> courseList;
    private final List<Grade> gradeList;

    public School() {
        this.studentsList = new ArrayList<>();
        this.courseList = new ArrayList<>();
        this.gradeList = new ArrayList<>();
    }

    public School(String name){
        this.name = name;
        this.studentsList = new ArrayList<>();
        this.courseList = new ArrayList<>();
        this.gradeList = new ArrayList<>();
    }

}
