package com.iwaconsolti.school.model.schools;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.model.Student;

import java.util.ArrayList;

public class ZetCollege extends School {
    public ZetCollege() {
        super("ZetCollege", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        this.name = "ZetCollege";
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.grades = new ArrayList<>();
    }
}
