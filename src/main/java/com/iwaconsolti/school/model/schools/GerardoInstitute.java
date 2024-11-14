package com.iwaconsolti.school.model.schools;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.model.Student;

import java.util.ArrayList;

public class GerardoInstitute extends School {
    public GerardoInstitute() {
        super("GerardoInstitute", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        this.name = "GerardoInstitute";
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.grades = new ArrayList<>();
    }
}